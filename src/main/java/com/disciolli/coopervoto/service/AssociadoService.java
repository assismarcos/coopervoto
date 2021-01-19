package com.disciolli.coopervoto.service;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.disciolli.coopervoto.model.Associado;
import com.disciolli.coopervoto.repository.AssociadoRepository;
import com.disciolli.coopervoto.request.AssociadoRequest;

@Service
public class AssociadoService {

	Logger log = LoggerFactory.getLogger(AssociadoService.class);
	
	private AssociadoRepository associadoRepository;

	public AssociadoService(AssociadoRepository associadoRepository) {
		this.associadoRepository = associadoRepository;
	}

	/**
	 * Gravar / atualizar o Associado.
	 * 
	 */
	public Associado save(AssociadoRequest associadoDto) {
		log.info("Persistindo associado");
		return associadoRepository.save(new Associado(associadoDto.getCpf(), associadoDto.getNome()));
	}

	/**
	 * Obter o Associado disponível por ID.
	 * 
	 * @param id
	 * @return Associado
	 */
	// Poderia aplicar outros filtros, Ex.(Status, disponibilidade, etc).
	public Associado findAvalibleById(String id) {
		log.info("Consultando associado por ID {}", id);
		
		try {
			return associadoRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

}
