package com.CSH.DTO;

public class NasDTO {
	private long id;
	private String data;
	private double valor;
	private long idPaciente;
	private String idEquipe;
	private String complexidade;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(String idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(String complexidade) {
		this.complexidade = complexidade;
	}	

}
