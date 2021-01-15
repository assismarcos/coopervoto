package com.disciolli.coopervoto.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Document(value = "sessao")
public class Sessao {

	@Id
	private String codigo;

	@NotEmpty
	private String pautaCodigo;
	
	@DBRef
	private Pauta pauta;

	private List<Voto> votos;

	private Date dataHoraInicio;

	private int minutosDuracao;

	private Date dataHoraFim;

	public Sessao() {
	}

	public Sessao(String codigo, String pautaCodigo, Pauta pauta, List<Voto> votos, Date dataHoraInicio, Date dataHoraFim,
			int minutosDuracao) {
		this.codigo = codigo;
		this.pautaCodigo = pautaCodigo;
		this.pauta = pauta;
		this.votos = votos;
		this.dataHoraInicio = dataHoraInicio;
		this.minutosDuracao = minutosDuracao;
		this.dataHoraFim = dataHoraFim;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public int getMinutosDuracao() {
		return minutosDuracao;
	}

	public void setMinutosDuracao(int minutosDuracao) {
		this.minutosDuracao = minutosDuracao;
	}

	public String getPautaCodigo() {
		return pautaCodigo;
	}

	public void setPautaCodigo(String pautaCodigo) {
		this.pautaCodigo = pautaCodigo;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

}
