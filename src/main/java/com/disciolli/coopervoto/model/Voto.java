package com.disciolli.coopervoto.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.DBRef;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
public class Voto {

	@NotNull
	@DBRef
	private Associado associado;

	@NotNull
	private VotoOpcao opcao;

	private Date dataHora;

	public Voto() {
	}

	public Voto(Associado associado, VotoOpcao opcao, Date dataHora) {
		this.associado = associado;
		this.opcao = opcao;
		this.dataHora = dataHora;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public VotoOpcao getOpcao() {
		return opcao;
	}

	public void setOpcao(VotoOpcao opcao) {
		this.opcao = opcao;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

}
