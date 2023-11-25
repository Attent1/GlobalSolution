package com.CSH.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.CSH.Singleton.ConnectionManager;
import com.CSH.beans.Equipe;

public class EquipeDAO {

	private Connection connection;

	public EquipeDAO(Connection connection){
		try {
			ConnectionManager cm = ConnectionManager.getInstance();
	    	connection = cm.getConnection();
	    	this.connection = connection;
		} catch (Exception e) {
			e.printStackTrace();
		}		
    }
	
	public void insert(Equipe equipe) throws SQLException {   
       try {
           String sql = "INSERT INTO EQUIPE VALUES (SEQ_EQUIPE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";   
           PreparedStatement stmt = connection.prepareStatement(sql);   
           stmt.setString(1, equipe.getNome());
           stmt.setInt(2, equipe.getCorenEnf());
           stmt.setInt(3, equipe.getCorenTecnicos().get(0));
           stmt.setInt(4, equipe.getCorenTecnicos().get(1));
           stmt.setInt(5, equipe.getCorenTecnicos().get(2));
           stmt.setInt(6, equipe.getCorenTecnicos().get(3));
           stmt.setInt(7, equipe.getCorenTecnicos().get(4));
           stmt.setString(8, equipe.getSenhaEquipe());
           stmt.execute();   
           stmt.close();   
       } catch (SQLException e) {
    	   e.printStackTrace();
       }    
   } 
	
	public List<Equipe> selectEquipe() throws SQLException {
		try {
			List<Equipe> listaEquipe = new ArrayList<>();
			
			long id = 0;
			String nome = "";
			int corenEnf = 0;
			int corenTec1 = 0;
			int corenTec2 = 0;
			int corenTec3 = 0;
			int corenTec4 = 0;
			int corenTec5 = 0;			
			String senhaEquipe = "";
			String sql = "SELECT * FROM EQUIPE";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				ArrayList<Integer> corenTecnicos = new ArrayList<Integer>();
				id = rs.getLong("ID_EQUIPE");
	            nome = rs.getString("NOME_EQUIPE");
	            corenEnf = rs.getInt("COREN_ENF");
	            corenTec1 = rs.getInt("COREN_TEC_ENF_1");
	            corenTec2 = rs.getInt("COREN_TEC_ENF_2");
	            corenTec3 = rs.getInt("COREN_TEC_ENF_3");
	            corenTec4 = rs.getInt("COREN_TEC_ENF_4");
	            corenTec5 = rs.getInt("COREN_TEC_ENF_5");
	            senhaEquipe = rs.getString("SENHA_EQUIPE");
	            
	            corenTecnicos.add(corenTec1);
	            corenTecnicos.add(corenTec2);
	            corenTecnicos.add(corenTec3);
	            corenTecnicos.add(corenTec4);
	            corenTecnicos.add(corenTec5);
	            Equipe equipe = new Equipe(id, nome, corenEnf, corenTecnicos, senhaEquipe);	            
	            listaEquipe.add(equipe);	            
			}
			return listaEquipe;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<Equipe> selectEquipePorId(long vId) throws SQLException {
		try {
			Equipe equipe = new Equipe();
			long id = 0;
			String nome = "";
			int corenEnf = 0;
			int corenTec1 = 0;
			int corenTec2 = 0;
			int corenTec3 = 0;
			int corenTec4 = 0;
			int corenTec5 = 0;			
			String senhaEquipe = "";
			String sql = "SELECT * FROM EQUIPE WHERE ID_EQUIPE = " + vId;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				ArrayList<Integer> corenTecnicos = new ArrayList<Integer>();
				id = rs.getLong("ID_EQUIPE");
	            nome = rs.getString("NOME_EQUIPE");
	            corenEnf = rs.getInt("COREN_ENF");
	            corenTec1 = rs.getInt("COREN_TEC_ENF_1");
	            corenTec2 = rs.getInt("COREN_TEC_ENF_2");
	            corenTec3 = rs.getInt("COREN_TEC_ENF_3");
	            corenTec4 = rs.getInt("COREN_TEC_ENF_4");
	            corenTec5 = rs.getInt("COREN_TEC_ENF_5");
	            senhaEquipe = rs.getString("SENHA_EQUIPE");
	            
	            corenTecnicos.add(corenTec1);
	            corenTecnicos.add(corenTec2);
	            corenTecnicos.add(corenTec3);
	            corenTecnicos.add(corenTec4);
	            corenTecnicos.add(corenTec5);
	            equipe = new Equipe(id, nome, corenEnf, corenTecnicos, senhaEquipe);	  	            
			}
			return Optional.of(equipe);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteEquipe(long id) throws SQLException {
		try {
			String sql = "DELETE FROM EQUIPE WHERE ID_EQUIPE = " + id;
			Statement st = connection.createStatement();
			st.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Equipe equipe) throws SQLException {
		String sql = "UPDATE EQUIPE SET NOME_EQUIPE = ? WHERE ID_EQUIPE = ?";		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, equipe.getNome());
			ps.setLong(2, equipe.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		} 
	}
	
	public long selectIdEquipePorNome(String nomeEquipe) throws SQLException {
		try {
			long id = 0;
			String sql1 = "SELECT ID_EQUIPE FROM EQUIPE WHERE UPPER(NOME_EQUIPE) LIKE(UPPER('%"+ nomeEquipe + "%'))";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql1);

			while (rs.next()) {
				id = rs.getLong("ID_EQUIPE");
			}
			return id;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<String> validaLogin(String nomeEquipe){
		try {
			List<String> nomeEquipeSenha = new ArrayList<>();
			String nome = "";
			String senha = "";
		
			String sql =  "SELECT NOME_EQUIPE, SENHA_EQUIPE FROM EQUIPE WHERE NOME_EQUIPE = '"+nomeEquipe+"'";
											
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery(sql);			
			
			while (rs.next()) {
				nome = rs.getString("NOME_EQUIPE");
				senha = rs.getString("SENHA_EQUIPE");
				
				nomeEquipeSenha.add(nome);
				nomeEquipeSenha.add(senha);
			}
					
			return nomeEquipeSenha;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
