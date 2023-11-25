package com.CSH.beans;

public class Paciente {
	private long id;
	private String cpf;
	private String nome;	
	private String dtNascimento;
	private int idade;
	private String telefone;

	public Paciente() {		
	}
	
	public Paciente(long id, String cpf, String nome, String dtNascimento, int idade, String telefone) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.idade = idade;
		this.telefone = telefone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
