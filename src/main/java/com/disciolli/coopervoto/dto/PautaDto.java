package com.disciolli.coopervoto.dto;

import com.disciolli.coopervoto.model.Sessao;

public class PautaDto {

	private String id;

	private String nome;

	private String descricao;

	private Sessao sessao;

	public PautaDto(String id, String nome, String descricao, Sessao sessao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.sessao = sessao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

}
