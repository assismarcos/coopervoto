package com.disciolli.coopervoto.request;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class PautaSessaoRequest {

	@ApiModelProperty(value = "Id da Pauta")
	@NotEmpty
	private String pauta;

	@ApiModelProperty(value = "Tempo de duração em minutos")
	private Integer duracao;

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

}
