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
import com.CSH.beans.Enfermeiro;

public class EnfermeiroDAO {
	private Connection connection;
	
	public EnfermeiroDAO(Connection connection){
		try {
			ConnectionManager cm = ConnectionManager.getInstance();
	    	connection = cm.getConnection();
	    	this.connection = connection;
		} catch (Exception e) {
			e.printStackTrace();
		}		
    }  
	
	public void insert(Enfermeiro enfermeiro) throws SQLException{   
        try {        
            String sql = "INSERT INTO ENFERMEIRO VALUES (SEQ_ENFERMEIRO.NEXTVAL, ?, ?, ?, ?)";   
            PreparedStatement stmt = connection.prepareStatement(sql);   
            stmt.setString(1, enfermeiro.getNome());
            stmt.setInt(2, enfermeiro.getCoren());
            stmt.setString(3, enfermeiro.getSenha());
            stmt.setString(4, "Enfermeiro Chefe");
            stmt.execute();   
            stmt.close();   
        } catch (SQLException e) {   
            e.printStackTrace();   
        }    
    } 
	
	public void atualizar(Enfermeiro enfermeiro) throws SQLException {
		String sql = "UPDATE ENFERMEIRO SET SENHA_ENF = ? WHERE COREN_ENF = ?";		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, enfermeiro.getSenha());
			ps.setInt(2, enfermeiro.getCoren());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		} 
	}
	
	public void deleteEnfermeiro(int coren) throws SQLException {
		try {
			String sql = "DELETE FROM ENFERMEIRO WHERE COREN_ENF = " + coren;
			Statement st = connection.createStatement();
			st.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Enfermeiro> selectEnfermeiro() throws SQLException {
		try {
			List<Enfermeiro> listaEnfermeiros = new ArrayList<>();
			long id = 0;
			String nome = "";
			int coren = 0;
			String funcao = "";
			String senha = "";
			String sql = "SELECT * FROM ENFERMEIRO";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getLong("ID_ENF");
	            nome = rs.getString("NOME_ENF");
	            coren = rs.getInt("COREN_ENF");
	            funcao = rs.getString("FUNCAO_ENF");
	            senha = rs.getString("SENHA_ENF");
	            Enfermeiro enfermeiro = new Enfermeiro(id, nome, coren, funcao, senha);
	            listaEnfermeiros.add(enfermeiro);
			}
			return listaEnfermeiros;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	    	
	public Optional<Enfermeiro> selectEnfermeiroPorCoren(int coren) throws SQLException {
		try {
			Enfermeiro enfermeiro = new Enfermeiro();
			long id = 0;
			String nome = "";
			int corenEnf = 0;
			String funcao = "";
			String senha = "";
			String sql = "SELECT * FROM ENFERMEIRO WHERE COREN_ENF = " + coren;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getLong("ID_ENF");
	            nome = rs.getString("NOME_ENF");
	            corenEnf = rs.getInt("COREN_ENF");
	            funcao = rs.getString("FUNCAO_ENF");
	            senha = rs.getString("SENHA_ENF");
	            enfermeiro = new Enfermeiro(id, nome, corenEnf, funcao, senha);
	            
			}
			return Optional.of(enfermeiro);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String validaLoginEnfermeiro(String vSenhaEnfemeiro) throws SQLException {
		try {
			String senhaEnfemeiro = "";
			
			String sql = "SELECT SENHA_ENF FROM ENFERMEIRO WHERE SENHA_ENF = '"+ vSenhaEnfemeiro+ "'";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				senhaEnfemeiro = rs.getString("SENHA_ENF");
			}
			return senhaEnfemeiro;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
