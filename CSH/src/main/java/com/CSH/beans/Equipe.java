package com.CSH.beans;

import java.util.ArrayList;
import java.util.List;


public class Equipe {
	private long id;
	private String nome;
	private int corenEnf;
	private ArrayList<Integer> corenTecnicos = new ArrayList<Integer>();
	private String senhaEquipe;	
	
	public Equipe() {
		
	}
	
	public Equipe(long id, String nome, int corenEnf, ArrayList<Integer> corenTecnicos, String senhaEquipe) {
		this.id = id;
		this.nome = nome;
		this.corenEnf = corenEnf;
		this.corenTecnicos = corenTecnicos;
		this.senhaEquipe = senhaEquipe;
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

	public int getCorenEnf() {
		return corenEnf;
	}

	public void setCorenEnf(int corenEnf) {
		this.corenEnf = corenEnf;
	}

	public List<Integer> getCorenTecnicos() {
		return corenTecnicos;
	}

	public void setCorenTecnicos(ArrayList<Integer> corenTecnicos) {
		this.corenTecnicos = corenTecnicos;
	}

	public String getSenhaEquipe() {
		return senhaEquipe;
	}

	public void setSenhaEquipe(String senhaEquipe) {
		this.senhaEquipe = senhaEquipe;
	}

	
		
}
