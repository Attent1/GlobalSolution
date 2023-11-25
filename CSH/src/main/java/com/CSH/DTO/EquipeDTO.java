package com.CSH.DTO;

import java.util.ArrayList;

public class EquipeDTO {

	   	private String nome;
	    private String corenEnf;
	    private ArrayList<TecnicoDTO> corenTecnicos;
	    private String senhaEquipe;
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getCorenEnf() {
			return corenEnf;
		}
		public void setCorenEnf(String corenEnf) {
			this.corenEnf = corenEnf;
		}
		public ArrayList<TecnicoDTO> getCorenTecnicos() {
			return corenTecnicos;
		}
		public void setCorenTecnicos(ArrayList<TecnicoDTO> corenTecnicos) {
			this.corenTecnicos = corenTecnicos;
		}
		public String getSenhaEquipe() {
			return senhaEquipe;
		}
		public void setSenhaEquipe(String senhaEquipe) {
			this.senhaEquipe = senhaEquipe;
		}
	    
	    
	    
}
