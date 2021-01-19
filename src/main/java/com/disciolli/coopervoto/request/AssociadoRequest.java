package com.disciolli.coopervoto.request;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

public class AssociadoRequest {

	@CPF
	private String cpf;

	@NotEmpty
	private String nome;

	public AssociadoRequest() {
	}

	public AssociadoRequest(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
