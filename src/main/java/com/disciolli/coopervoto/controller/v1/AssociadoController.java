package com.disciolli.coopervoto.controller.v1;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disciolli.coopervoto.request.AssociadoRequest;
import com.disciolli.coopervoto.response.IdResponse;
import com.disciolli.coopervoto.service.AssociadoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1")
public class AssociadoController {

	private AssociadoService associadoService;

	public AssociadoController(AssociadoService associadoService) {
		this.associadoService = associadoService;
	}

	@PostMapping(value = "/associado", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Cadastrar associado", produces = "application/json")
	public ResponseEntity<IdResponse> cadastrar(@Valid @RequestBody AssociadoRequest associado) {
		return ResponseEntity.ok(new IdResponse(associadoService.save(associado).getCpf()));
	}

}
