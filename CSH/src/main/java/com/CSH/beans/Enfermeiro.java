package com.CSH.beans;

public class Enfermeiro extends Funcionario {	
	private String senha;

	public Enfermeiro() {		
	}
	
	public Enfermeiro(long id, String nome, int coren, String funcao, String senha) {
		super(id, nome, coren, funcao);
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
				
}
