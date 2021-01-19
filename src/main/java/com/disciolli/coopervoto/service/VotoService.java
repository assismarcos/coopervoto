package com.disciolli.coopervoto.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.disciolli.coopervoto.dto.ResultadoVotacaoDto;
import com.disciolli.coopervoto.exception.CustomValidationException;
import com.disciolli.coopervoto.exception.NotFoundException;
import com.disciolli.coopervoto.exception.UnprocessableException;
import com.disciolli.coopervoto.model.Pauta;
import com.disciolli.coopervoto.model.TotalVotoOpcao;
import com.disciolli.coopervoto.model.Voto;
import com.disciolli.coopervoto.model.VotoOpcao;
import com.disciolli.coopervoto.repository.VotoRepository;
import com.disciolli.coopervoto.util.Messages;
import com.disciolli.coopervoto.util.ValidaCPF;

@Service
public class VotoService {

	Logger log = LoggerFactory.getLogger(VotoService.class);

	private VotoRepository votoRepository;
	private AssociadoService associadoService;
	private PautaService pautaService;
	private KafkaService<ResultadoVotacaoDto> kafkaService;

	@Value("${coopervoto.resultadovotacao.kafka.topic}")
	private String topicoKafka;

	public VotoService(VotoRepository votoRepository, AssociadoService associadoService, PautaService pautaService,
			KafkaService<ResultadoVotacaoDto> kafkaService) {
		this.votoRepository = votoRepository;
		this.associadoService = associadoService;
		this.pautaService = pautaService;
		this.kafkaService = kafkaService;
	}

	public Voto votar(String pautaId, String cpfAssociado, VotoOpcao opcao) {
		// Validar CPF
		if (cpfAssociado == null || cpfAssociado.isBlank() && !ValidaCPF.isCPF(cpfAssociado)) {
			throw new CustomValidationException(Messages.CPF_INVALIDO);
		}

		cpfAssociado = cpfAssociado.replaceAll("[^0-9]", "");

		// Obter o associado do BD
		if (associadoService.findAvalibleById(cpfAssociado) == null) {
			throw new NotFoundException(Messages.ASSOCIADO_INEXISTENTE);
		}

		// Checar se a pauta esta disponivel para votacao
		if (pautaService.buscarPorIdDisponivelVotacao(pautaId) == null) {
			throw new UnprocessableException(Messages.PAUTA_INDISPONIVEL_VOTACAO);
		}

		// Checar se o associado ja votou (permitido apenas uma vez por pauta)
		if (votoRepository.findByPautaIdAndCpf(pautaId, cpfAssociado) != null) {
			throw new UnprocessableException(Messages.ASSOCIADO_JA_VOTOU);
		}

		// Checar se o associado pode votar (API externa)
		if (!verifyCPF(cpfAssociado)) {
			throw new UnprocessableException(Messages.CPF_UNABLE_TO_VOTE);
		}

		return votoRepository.save(new Voto(pautaId, cpfAssociado, opcao, LocalDateTime.now(ZoneOffset.UTC)));
	}

	private boolean verifyCPF(String cpf) {
		RestTemplate restTemplate = new RestTemplate();
		String resposta = restTemplate.getForObject("https://user-info.herokuapp.com/users/" + cpf, String.class);
		if (resposta.contains("ABLE_TO_VOTE")) {
			return true;
		}
		return false;
	}

	public ResultadoVotacaoDto calcularResultadoVotacao(String pautaId) {
		Pauta pauta = pautaService.buscarPorId(pautaId);

		if (pauta == null) {
			throw new NotFoundException(Messages.PAUTA_INEXISTENTE);
		}

		if (pauta.getSessao() == null || pauta.getSessao().getDataHoraFim() == null) {
			throw new NotFoundException(Messages.SESSAO_INEXISTENTE);
		}

		if (pauta.getSessao().getDataHoraFim().isAfter(LocalDateTime.now(ZoneOffset.UTC))) {
			throw new UnprocessableException(Messages.PAUTA_EM_VOTACAO);
		}

		// Resultado ja processado
		if (pauta.isResultadoProcessado()) {
			return new ResultadoVotacaoDto(pautaId, pauta.getTotalVotos());
		}

		return processarResultado(pauta);
	}

	public ResultadoVotacaoDto processarResultado(Pauta pauta) {
		log.info("Processando votos da pauta {}", pauta.getId());

		ResultadoVotacaoDto result = null;

		AggregationResults<TotalVotoOpcao> resultado = votoRepository.calcularResultadoVotacao(pauta.getId());
		result = new ResultadoVotacaoDto(pauta.getId(), resultado.getMappedResults());

		if (result.getTotalVotos() == null || result.getTotalVotos().isEmpty()) {
			throw new RuntimeException(Messages.ERRO_RESULTADO_FINAL);
		}

		pauta.setTotalVotos(result.getTotalVotos());
		pauta.setResultadoProcessado(true);

		pautaService.gravar(pauta);

		kafkaService.send(topicoKafka, result).addCallback(
				sucesso -> log.info("Mensagem enviada para o topico '{}' com sucesso {}", topicoKafka, sucesso),
				erro -> log.info("Erro ao enviar mensagem para o topico {} {}", topicoKafka, erro));

		return result;
	}

}
