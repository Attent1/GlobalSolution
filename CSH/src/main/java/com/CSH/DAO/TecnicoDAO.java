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
import com.CSH.beans.Tecnico;

public class TecnicoDAO {

	private Connection connection;

	public TecnicoDAO(Connection connection){
		try {
			ConnectionManager cm = ConnectionManager.getInstance();
	    	connection = cm.getConnection();
	    	this.connection = connection;
		} catch (Exception e) {
			e.printStackTrace();
		}		
    }
	
	public void insert(Tecnico tecnico) {   
        try {
            String sql = "INSERT INTO TEC_ENFERMAGEM VALUES (SEQ_TEC_ENFERMAGEM.NEXTVAL, ?, ?, ?)";   
            PreparedStatement stmt = connection.prepareStatement(sql);   
            stmt.setString(1, tecnico.getNome());
            stmt.setInt(2, tecnico.getCoren());
            stmt.setString(3, "Técnico de Enfermagem");
            stmt.execute();   
            stmt.close();   
        } catch (SQLException e) {   
            e.printStackTrace();   
        }    
    } 
	
	public void atualizar(Tecnico tecnico) throws SQLException {
		String sql = "UPDATE TEC_ENFERMAGEM SET FUNCAO_TEC = ? WHERE COREN_TEC = ?";		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, tecnico.getFuncao());
			ps.setInt(2, tecnico.getCoren());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		} 
	}
	
	public List<Tecnico> selectTecnico() throws SQLException {
		try {
			List<Tecnico> listaTecnico = new ArrayList<>();
			long id = 0;
			String nome = "";
			int coren = 0;
			String funcao = "";
			String sql = "SELECT * FROM TEC_ENFERMAGEM";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getLong("ID_TEC");
	            nome = rs.getString("NOME_TEC");
	            coren = rs.getInt("COREN_TEC");
	            funcao = rs.getString("FUNCAO_TEC");
	            Tecnico tecnico = new Tecnico(id, nome, coren, funcao);
	            listaTecnico.add(tecnico);
			}
			return listaTecnico;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<Tecnico> selectTecnicoPorCoren(int coren) throws SQLException {
		try {
			Tecnico tecnico = new Tecnico();
			long id = 0;
			String nome = "";
			int corenEnf = 0;
			String funcao = "";
			String sql = "SELECT * FROM TEC_ENFERMAGEM WHERE COREN_TEC = " + coren;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getLong("ID_TEC");
	            nome = rs.getString("NOME_TEC");
	            corenEnf = rs.getInt("COREN_TEC");
	            funcao = rs.getString("FUNCAO_TEC");
	            tecnico = new Tecnico(id, nome, corenEnf, funcao);	            
			}
			return Optional.of(tecnico);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteTecnico(int coren) throws SQLException {
		try {
			String sql = "DELETE FROM TEC_ENFERMAGEM WHERE COREN_TEC = " + coren;
			Statement st = connection.createStatement();
			st.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> nomeTecnicoPorIdEquipe(long idEquipe){
		try {
			List<String> listaTecnicos = new ArrayList<>();
			String nome = "";
		
			String sql =  "SELECT NOME_TEC FROM TEC_ENFERMAGEM JOIN EQUIPE ON TEC_ENFERMAGEM.COREN_TEC = EQUIPE.COREN_TEC_ENF_1 WHERE EQUIPE.ID_EQUIPE = "+ idEquipe+"";
			String sql2 = "SELECT NOME_TEC FROM TEC_ENFERMAGEM JOIN EQUIPE ON TEC_ENFERMAGEM.COREN_TEC = EQUIPE.COREN_TEC_ENF_2 WHERE EQUIPE.ID_EQUIPE = "+ idEquipe+"";
			String sql3 = "SELECT NOME_TEC FROM TEC_ENFERMAGEM JOIN EQUIPE ON TEC_ENFERMAGEM.COREN_TEC = EQUIPE.COREN_TEC_ENF_3 WHERE EQUIPE.ID_EQUIPE = "+ idEquipe+"";
			String sql4 = "SELECT NOME_TEC FROM TEC_ENFERMAGEM JOIN EQUIPE ON TEC_ENFERMAGEM.COREN_TEC = EQUIPE.COREN_TEC_ENF_4 WHERE EQUIPE.ID_EQUIPE = "+ idEquipe+"";
			String sql5 = "SELECT NOME_TEC FROM TEC_ENFERMAGEM JOIN EQUIPE ON TEC_ENFERMAGEM.COREN_TEC = EQUIPE.COREN_TEC_ENF_5 WHERE EQUIPE.ID_EQUIPE = "+ idEquipe+"";					
			
			Statement st = connection.createStatement();			
			ResultSet rs = st.executeQuery(sql);			
			
			while (rs.next()) {
				nome = rs.getString("NOME_TEC");
				
				listaTecnicos.add(nome);
			}
			ResultSet rs2 = st.executeQuery(sql2);
			while (rs2.next()) {
				nome = rs2.getString("NOME_TEC");
				
				listaTecnicos.add(nome);
			}
			ResultSet rs3 = st.executeQuery(sql3);
			while (rs3.next()) {
				nome = rs3.getString("NOME_TEC");
				
				listaTecnicos.add(nome);
			}
			ResultSet rs4 = st.executeQuery(sql4);
			while (rs4.next()) {
				nome = rs4.getString("NOME_TEC");
				
				listaTecnicos.add(nome);
			}
			ResultSet rs5 = st.executeQuery(sql5);
			while (rs5.next()) {
				nome = rs5.getString("NOME_TEC");
				
				listaTecnicos.add(nome);
			}
			
			
			return listaTecnicos;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
}
