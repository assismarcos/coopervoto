package com.disciolli.coopervoto.dto;

import java.util.List;

import com.disciolli.coopervoto.model.TotalVotoOpcao;

public class ResultadoVotacaoDto {

	private String pauta;
	private List<TotalVotoOpcao> totalVotos;

	public ResultadoVotacaoDto() {
	}

	public ResultadoVotacaoDto(String pauta, List<TotalVotoOpcao> totalVotos) {
		this.pauta = pauta;
		this.totalVotos = totalVotos;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public List<TotalVotoOpcao> getTotalVotos() {
		return totalVotos;
	}

	public void setTotalVotos(List<TotalVotoOpcao> totalVotos) {
		this.totalVotos = totalVotos;
	}

}
