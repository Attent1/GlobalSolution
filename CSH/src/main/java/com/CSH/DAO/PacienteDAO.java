package com.CSH.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.CSH.DTO.PacienteComplexidadeDTO;
import com.CSH.Singleton.ConnectionManager;
import com.CSH.beans.Paciente;

public class PacienteDAO {

	private Connection connection;
	
	public PacienteDAO(Connection connection){
		try {
			ConnectionManager cm = ConnectionManager.getInstance();
	    	connection = cm.getConnection();
	    	if (this.connection == null) {
	    		this.connection = connection;			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
    } 
	
	public void insert(Paciente paciente){   
        try {        
        	
            String sql = "INSERT INTO PACIENTE VALUES (SEQ_PACIENTE.NEXTVAL, ?, ?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?)";   
            PreparedStatement stmt = connection.prepareStatement(sql);   
            stmt.setString(1, paciente.getCpf());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getDtNascimento());
            stmt.setInt(4, paciente.getIdade());
            stmt.setString(5, paciente.getTelefone());
            stmt.execute();   
            stmt.close();   
        } catch (SQLException e) {   
            e.printStackTrace();   
        }    
    } 
	
	public List<Paciente> selectPaciente() throws SQLException {
		try {
			List<Paciente> listaPaciente = new ArrayList<>();
			long id = 0;
			String cpf = "";
			String nome = "";
			String dtNascimento;
			int  idade = 0;
			String telefone = "";
			String sql = "SELECT * FROM PACIENTE";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getLong("ID_PACIENTE");
				cpf = rs.getString("CPF");
				nome = rs.getString("NOME_PACIENTE");
				dtNascimento = rs.getString("DATA_NASC");
				idade = rs.getInt("IDADE");
				telefone = rs.getString("TELEFONE");
				Paciente paciente = new Paciente(id, cpf, nome, dtNascimento, idade, telefone);
				listaPaciente.add(paciente);
			}
			return listaPaciente;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<Paciente> selectPacientePorId(long vId) throws SQLException {
		try {
			Paciente paciente = new Paciente();
			long id = 0;
			String cpf = "";
			String nome = "";
			String dtNascimento;
			int  idade = 0;
			String telefone = "";
			String sql = "SELECT * FROM PACIENTE WHERE ID_PACIENTE = " + vId;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getLong("ID_PACIENTE");
				cpf = rs.getString("CPF");
				nome = rs.getString("NOME_PACIENTE");
				dtNascimento = rs.getString("DATA_NASC");
				idade = rs.getInt("IDADE");
				telefone = rs.getString("TELEFONE");
				paciente = new Paciente(id, cpf, nome, dtNascimento, idade, telefone);
	            
			}
			return Optional.of(paciente);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public long selectIdPacientePorCpf(String cpf) throws SQLException {
		try {
			long id = 0;
			String sql = "SELECT ID_PACIENTE FROM PACIENTE WHERE CPF = " + cpf;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				id = rs.getLong("ID_PACIENTE");
			}
			return id;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void deletePaciente(long id) throws SQLException {
		try {
			String sql = "DELETE FROM PACIENTE WHERE ID_PACIENTE = " + id;
			Statement st = connection.createStatement();
			st.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Paciente paciente) throws SQLException {
		String sql = "UPDATE PACIENTE SET TELEFONE = ? WHERE ID_PACIENTE = ?";		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, paciente.getTelefone());
			ps.setLong(2, paciente.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		} 
	}
	
	public List<PacienteComplexidadeDTO> nomeComplexidadePaciente(String data, long idEquipe){
		try {
			List<PacienteComplexidadeDTO> listaPacientes = new ArrayList<>();
			String nome = "";
			String complexidade = "";			
			String sql ="SELECT PACIENTE.NOME_PACIENTE, NAS.COMPLEXIDADE_PACIENTE " +
                    "FROM PACIENTE " +
                    "JOIN NAS ON PACIENTE.ID_PACIENTE = NAS.ID_PACIENTE " +
                    "JOIN EQUIPE ON NAS.ID_EQUIPE = EQUIPE.ID_EQUIPE "     +
                    "WHERE EQUIPE.ID_EQUIPE = " +idEquipe + " AND NAS.DATA_NAS = TO_DATE('"+ data +"', 'DD/MM/YYYY')";		
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				nome = rs.getString("NOME_PACIENTE");
				complexidade = rs.getString("COMPLEXIDADE_PACIENTE");
				PacienteComplexidadeDTO pacienteComplexidade = new PacienteComplexidadeDTO(nome, complexidade);
				listaPacientes.add(pacienteComplexidade);
			}
			return listaPacientes;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
