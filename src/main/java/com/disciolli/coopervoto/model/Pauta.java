package com.disciolli.coopervoto.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.disciolli.coopervoto.response.PautaResponse;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Document(value = "pauta")
public class Pauta {

	@Id
	private String codigo;
	
	@NotEmpty
	private String nome;

	private String descricao;

	public Pauta() {
	}

	public Pauta(String codigo, String nome, String descricao) {
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	
	public PautaResponse toDTO() {
		return new PautaResponse(getCodigo(), getNome(), getDescricao());
	}

	@Override
	public String toString() {
		return "Pauta [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + "]";
	}

}
