package com.CSH.beans;

public class NasResultado {
	private long id;
	private String complexidade;
	private long idNas;

	public NasResultado() {		
	}
	
	public NasResultado(long id, String complexidade, long idNas) {
		this.id = id;
		this.complexidade = complexidade;
		this.idNas = idNas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(String complexidade) {
		this.complexidade = complexidade;
	}

	public long getIdNas() {
		return idNas;
	}

	public void setIdNas(long idNas) {
		this.idNas = idNas;
	}
	
	
	
}
