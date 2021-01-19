package com.disciolli.coopervoto.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.disciolli.coopervoto.model.Pauta;
import com.disciolli.coopervoto.service.PautaService;
import com.disciolli.coopervoto.service.VotoService;

/**
 * 
 * Classe para processar o resultado da votação automaticamente, sem que seja
 * requisitado via API.
 * 
 * @author Disciolli
 *
 */

@Component
public class ContabilizaVotosTask {

	Logger log = LoggerFactory.getLogger(ContabilizaVotosTask.class);

	private PautaService pautaService;
	private VotoService votoService;

	public ContabilizaVotosTask(PautaService pautaService, VotoService votoService) {
		this.pautaService = pautaService;
		this.votoService = votoService;
	}

	@Scheduled(fixedRate = 5000)
	private void contabilizarVotos() {

		List<Pauta> pautas = pautaService.listarTodasPendenteResultado();

		pautas.forEach(pauta -> {
			votoService.processarResultado(pauta);
		});

	}

}
