package com.disciolli.coopervoto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.disciolli.coopervoto.model.VotoOpcao;

import io.swagger.annotations.ApiModelProperty;

public class VotoRequest {
	
	@NotEmpty
	@ApiModelProperty(value = "Código da pauta disponível para votação")
	private String pauta;

	@CPF
	@NotEmpty
	@ApiModelProperty(value = "CPF do associado")
	private String cpf;

	@ApiModelProperty(value = "Opção de voto (SIM / NAO)")
	@NotNull
	private VotoOpcao opcao;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public VotoOpcao getOpcao() {
		return opcao;
	}

	public void setOpcao(VotoOpcao opcao) {
		this.opcao = opcao;
	}

}
