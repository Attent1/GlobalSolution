package com.CSH.DTO;

public class PacienteComplexidadeDTO {

	private String nome;
	private String complexidade;
	
	public PacienteComplexidadeDTO(String nome, String complexidade) {
		this.nome = nome;
		this.complexidade = complexidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getComplexidade() {
		return complexidade;
	}
	public void setComplexidade(String complexidade) {
		this.complexidade = complexidade;
	}
		
	
}
