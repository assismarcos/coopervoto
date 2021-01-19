package com.disciolli.coopervoto.model;

import java.time.LocalDateTime;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
public class Sessao {

	private LocalDateTime dataHoraInicio;

	private int minutosDuracao;

	private LocalDateTime dataHoraFim;

	public Sessao() {
	}

	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public int getMinutosDuracao() {
		return minutosDuracao;
	}

	public void setMinutosDuracao(int minutosDuracao) {
		this.minutosDuracao = minutosDuracao;
	}

	public LocalDateTime getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(LocalDateTime dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

}
