package com.disciolli.coopervoto.request;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class PautaRequest {

	@ApiModelProperty(required = true)
	@NotEmpty
	private String nome;

	private String descricao;

	public PautaRequest() {
	}

	public PautaRequest(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "PautaRequest [nome=" + nome + ", descricao=" + descricao + "]";
	}

}
