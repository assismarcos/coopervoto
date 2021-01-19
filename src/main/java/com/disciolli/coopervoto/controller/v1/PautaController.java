package com.disciolli.coopervoto.controller.v1;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disciolli.coopervoto.dto.PautaDto;
import com.disciolli.coopervoto.request.PautaRequest;
import com.disciolli.coopervoto.request.PautaSessaoRequest;
import com.disciolli.coopervoto.response.IdResponse;
import com.disciolli.coopervoto.service.PautaService;
import com.disciolli.coopervoto.util.Messages;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/v1")
public class PautaController {

	Logger log = LoggerFactory.getLogger(PautaController.class);

	private PautaService pautaService;

	public PautaController(PautaService pautaService) {
		this.pautaService = pautaService;
	}

	@PostMapping(value = "/pauta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Criar nova pauta", produces = "application/json")
	@ApiResponses({ //
			@ApiResponse(code = 400, message = Messages.PAUTA_NOME_VAZIO), //
			@ApiResponse(code = 500, message = Messages.ERRO_INTERNO) //
	})
	public ResponseEntity<IdResponse> cadastrar(@Valid @RequestBody PautaRequest pauta) {
		log.info("Cadastrando Pauta {}", pauta);
		return ResponseEntity.ok(new IdResponse(pautaService.gravar(pauta.getNome(), pauta.getDescricao()).getId()));
	}

	@GetMapping(value = "/pautas", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Listar pautas disponíveis para votação", produces = "application/json")
	public List<PautaDto> pautasEmVotacao() {
		log.info("Consultando Pautas em votacao");
		List<PautaDto> pautas = pautaService.listarTodasDisponiveis();
		return pautas;
	}

	@GetMapping(value = "/pauta/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Consultar pauta por ID", produces = "application/json")
	public PautaDto pauta(@PathVariable String id) {
		log.info("Consultando Pauta: Id {} ", id);
		return pautaService.buscarPorIdDto(id);
	}

	@PostMapping(value = "/iniciarsessao", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Iniciar sessão de votação em uma pauta")
	public void iniciarSessao(@Valid @RequestBody PautaSessaoRequest request) {
		log.info("Iniciando sessao: {}", request);
		pautaService.iniciarSessao(request.getPauta(), request.getDuracao());
	}

}
