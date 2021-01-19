package com.disciolli.coopervoto.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.disciolli.coopervoto.dto.PautaDto;
import com.disciolli.coopervoto.exception.CustomValidationException;
import com.disciolli.coopervoto.exception.NotFoundException;
import com.disciolli.coopervoto.exception.UnprocessableException;
import com.disciolli.coopervoto.model.Pauta;
import com.disciolli.coopervoto.model.Sessao;
import com.disciolli.coopervoto.repository.PautaRepository;
import com.disciolli.coopervoto.util.Messages;

@Service
public class PautaService {

	Logger log = LoggerFactory.getLogger(PautaService.class);

	private PautaRepository pautaRepository;

	public PautaService(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}

	public Pauta gravar(String nome, String descricao) {
		return save(new Pauta(nome, descricao));
	}

	public Pauta gravar(Pauta pauta) {
		return save(pauta);
	}

	private Pauta save(Pauta pauta) {
		log.info("Persistindo pauta {}", pauta.getNome());

		if (pauta.getNome() == null || pauta.getNome().isBlank()) {
			throw new CustomValidationException(Messages.PAUTA_NOME_VAZIO);
		}

		return pautaRepository.save(pauta);
	}

	/**
	 * Obter a Pauta disponível por ID.
	 * 
	 * @param id
	 * @return PautaDto
	 */
	public PautaDto buscarPorIdDto(String id) {
		try {
			return pautaRepository.findById(id).get().toDto();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	/**
	 * Obter a Pauta disponível por ID.
	 * 
	 * @param id
	 * @return Pauta
	 */
	public Pauta buscarPorId(String id) {
		try {
			return pautaRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	/**
	 * Retorna as pautas que estao disponiveis para votacao.
	 * @return pautas
	 */
	public List<PautaDto> listarTodasDisponiveis() {
		return pautaRepository.findAllSessaoEmVotacao(LocalDateTime.now(ZoneOffset.UTC)).stream().map(p -> p.toDto())
				.collect(Collectors.toList());
	}

	/**
	 * Retorna as pautas que aguardam processamento do resultado.
	 * @return pautas
	 */
	public List<Pauta> listarTodasPendenteResultado() {
		return pautaRepository.findAllPendenteResultado(LocalDateTime.now(ZoneOffset.UTC));
	}

	public Pauta buscarPorIdDisponivelVotacao(String id) {
		try {
			Pauta pauta = pautaRepository.findById(id).get();

			if (pauta != null && pauta.getSessao() != null && pauta.getSessao().getDataHoraFim() != null
					&& pauta.getSessao().getDataHoraFim().isAfter(LocalDateTime.now(ZoneOffset.UTC))) {
				return pauta;
			}
		} catch (Exception e) {
			log.error("Erro", e);
		}

		return null;
	}

	/**
	 * Iniciar uma Sessao de votacao em uma Pauta.
	 * 
	 * @param pautaId
	 * @param duracaoMinutos
	 */
	public void iniciarSessao(String pautaId, Integer duracaoMinutos) {
		if (pautaId == null || pautaId.isBlank()) {
			throw new NotFoundException(Messages.PAUTA_INEXISTENTE);
		}

		Pauta pauta = pautaRepository.findById(pautaId).get();
		if (pauta == null) {
			throw new NotFoundException(Messages.PAUTA_INEXISTENTE);
		}

		if (pauta.getSessao() != null) {
			if (pauta.getSessao().getDataHoraFim() != null
					&& pauta.getSessao().getDataHoraFim().isBefore(LocalDateTime.now(ZoneOffset.UTC)))
				throw new UnprocessableException(Messages.SESSAO_FINALIZADA);
			else
				throw new UnprocessableException(Messages.PAUTA_EM_VOTACAO);
		}

		Sessao sessao = new Sessao();
		sessao.setDataHoraInicio(LocalDateTime.now(ZoneOffset.UTC));
		if (duracaoMinutos == null || duracaoMinutos <= 0) {
			duracaoMinutos = 1; // Tempo de duracao default 1 minuto.
		}
		sessao.setMinutosDuracao(duracaoMinutos);
		sessao.setDataHoraFim(sessao.getDataHoraInicio().plusMinutes(duracaoMinutos));

		pauta.setSessao(sessao);
		pautaRepository.save(pauta);
	}

}
