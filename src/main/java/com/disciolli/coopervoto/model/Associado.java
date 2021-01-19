package com.disciolli.coopervoto.model;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Document(value = "associado")
public class Associado {

	@Id
	@CPF
	private String cpf;

	@NotEmpty
	private String nome;

	public Associado() {
	}

	public Associado(String cpf, String nome) {
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
