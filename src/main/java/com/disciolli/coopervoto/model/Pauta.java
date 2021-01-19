package com.disciolli.coopervoto.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.disciolli.coopervoto.dto.PautaDto;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Document(value = "pauta")
public class Pauta {

	@Id
	private String id;

	@NotEmpty
	private String nome;

	private String descricao;

	private Sessao sessao;

	private boolean resultadoProcessado;

	private List<TotalVotoOpcao> totalVotos;

	public Pauta() {
	}

	public Pauta(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
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

	public boolean isResultadoProcessado() {
		return resultadoProcessado;
	}

	public void setResultadoProcessado(boolean resultadoProcessado) {
		this.resultadoProcessado = resultadoProcessado;
	}

	public PautaDto toDto() {
		return new PautaDto(id, nome, descricao, sessao);
	}

	public List<TotalVotoOpcao> getTotalVotos() {
		return totalVotos;
	}

	public void setTotalVotos(List<TotalVotoOpcao> totalVotos) {
		this.totalVotos = totalVotos;
	}

}
