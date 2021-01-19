package com.disciolli.coopervoto.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Document(value = "voto")
public class Voto {

	@Id
	private String id;

	@Indexed
	@NotNull
	private String pautaId;

	@NotNull
	private String cpf;

	@NotNull
	private VotoOpcao opcao;

	private LocalDateTime dataHora;

	public Voto() {
	}

	public Voto(String pautaId, String cpf, VotoOpcao opcao, LocalDateTime dataHora) {
		this.pautaId = pautaId;
		this.cpf = cpf;
		this.opcao = opcao;
		this.dataHora = dataHora;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VotoOpcao getOpcao() {
		return opcao;
	}

	public void setOpcao(VotoOpcao opcao) {
		this.opcao = opcao;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getPautaId() {
		return pautaId;
	}

	public void setPautaId(String pautaId) {
		this.pautaId = pautaId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
