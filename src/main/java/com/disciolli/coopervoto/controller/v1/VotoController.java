package com.disciolli.coopervoto.controller.v1;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disciolli.coopervoto.dto.ResultadoVotacaoDto;
import com.disciolli.coopervoto.request.VotoRequest;
import com.disciolli.coopervoto.response.IdResponse;
import com.disciolli.coopervoto.service.VotoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1")
public class VotoController {

	private VotoService votoService;

	public VotoController(VotoService votoService) {
		this.votoService = votoService;
	}

	@PostMapping(value = "/votar", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Registrar voto", produces = "application/json")
	public ResponseEntity<IdResponse> votar(@Valid @RequestBody VotoRequest votoRequest) {
		return ResponseEntity.ok(new IdResponse(
				votoService.votar(votoRequest.getPauta(), votoRequest.getCpf(), votoRequest.getOpcao()).getId()));
	}
	
	@GetMapping(value = "/resultado/{pautaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Obter resultado da votação na pauta", produces = "application/json")
	public ResponseEntity<ResultadoVotacaoDto> resultadoVotacao(@PathVariable String pautaId) {
		return ResponseEntity.ok(votoService.calcularResultadoVotacao(pautaId));
	}

}
