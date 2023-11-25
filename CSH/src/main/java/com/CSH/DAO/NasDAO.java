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
import com.CSH.beans.Nas;

public class NasDAO {

	private Connection connection;

	public NasDAO(Connection connection){
		try {
			ConnectionManager cm = ConnectionManager.getInstance();
	    	connection = cm.getConnection();
	    	this.connection = connection;
		} catch (Exception e) {
			e.printStackTrace();
		}		
    }
	
	public void insert(Nas nas) {   
	       try {
	           String sql = "INSERT INTO NAS VALUES (SEQ_NAS.NEXTVAL, TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, ?)";   
	           PreparedStatement stmt = connection.prepareStatement(sql);   
	           stmt.setString(1, nas.getData());
	           stmt.setDouble(2, nas.getValor());
	           stmt.setLong(3, nas.getIdPaciente());
	           stmt.setLong(4, nas.getIdEquipe());
	           stmt.setString(5, nas.getComplexidade());
	           stmt.execute();   
	           stmt.close();   
	       } catch (SQLException e) {   
	           e.printStackTrace();   
	       }    
	   } 
	
	public List<Nas> selectNas() throws SQLException {
		try {
			List<Nas> listaNas= new ArrayList<>();
			long id = 0;
			String data = "";
			double valor = 0;
			long  idPaciente = 0;
			long  idEquipe= 0;
			String complexidade = "";
			String sql = "SELECT * FROM NAS";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getLong("ID_NAS");
				data = rs.getString("DATA_NAS");
				valor = rs.getDouble("VALOR");
				idPaciente = rs.getLong("ID_PACIENTE");
				idEquipe = rs.getLong("ID_EQUIPE");
				complexidade = rs.getString("COMPLEXIDADE_PACIENTE");
				Nas nas = new Nas(id, data, valor, idPaciente, idEquipe, complexidade);
				listaNas.add(nas);
			}
			return listaNas;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<Nas> selectNasPorId(long vId) throws SQLException {
		try {
			Nas nas = new Nas();
			long id = 0;
			String data = "";
			double valor = 0;
			long  idPaciente = 0;
			long  idEquipe= 0;
			String complexidade = "";
			String sql = "SELECT * FROM NAS WHERE ID_NAS = " + vId;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getLong("ID_NAS");
				data = rs.getString("DATA_NAS");
				valor = rs.getDouble("VALOR");
				idPaciente = rs.getLong("ID_PACIENTE");
				idEquipe = rs.getLong("ID_EQUIPE");
				complexidade = rs.getString("COMPLEXIDADE_PACIENTE");
				nas = new Nas(id, data, valor, idPaciente, idEquipe, complexidade);
	            
			}
			return Optional.of(nas);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteNas(long id) throws SQLException {
		try {
			String sql = "DELETE FROM NAS WHERE ID_NAS = " + id;
			Statement st = connection.createStatement();
			st.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Nas nas) throws SQLException {
		String sql = "UPDATE NAS SET DATA_NAS = ?, VALOR = ? WHERE ID_NAS = ?";		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, nas.getData());
			ps.setDouble(2, nas.getValor());
			ps.setLong(3, nas.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		} 
	}
	
}
