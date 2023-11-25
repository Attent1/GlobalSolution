package com.CSH.beans;

public class Nas {
	private long id;
	private String data;
	private double valor;
	private long idPaciente;
	private long idEquipe;
	private String complexidade;

	public Nas() {
	}

	public Nas(long id, String data, double valor, long idPaciente, long idEquipe, String complexidade) {
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.idPaciente = idPaciente;
		this.idEquipe = idEquipe;
		this.complexidade = complexidade;
	}

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

	public long getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(long idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(String complexidade) {
		this.complexidade = complexidade;
	}	

}
