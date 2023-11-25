package com.CSH.beans;

public abstract class Funcionario {
	private long id;
	private String nome;
	private int coren;
	private String funcao;
	
	public Funcionario (long id, String nome, int coren, String funcao) {
		this.id     = id;
		this.nome   = nome;
		this.coren  = coren;		
		this.funcao = funcao;
	}
	
	public Funcionario() {		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCoren() {
		return coren;
	}

	public void setCoren(int coren) {
		this.coren = coren;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	
	
}
