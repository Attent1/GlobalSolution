package com.CSH.BO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.CSH.DAO.PacienteDAO;
import com.CSH.DTO.PacienteComplexidadeDTO;
import com.CSH.beans.Paciente;
import com.CSH.conexao.DaoFactory;

public class PacienteBO {

	private PacienteDAO pd;
	
	public PacienteBO() {
		try {
			pd = DaoFactory.getPacienteDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar(Paciente paciente) {
		pd.insert(paciente);
	}
	
	public List<Paciente> select() throws SQLException {
		return pd.selectPaciente();
	}
	
	public List<PacienteComplexidadeDTO> selectNomeComplexidadePaciente(String data, long idEquipe) throws SQLException {
		return pd.nomeComplexidadePaciente(data, idEquipe);
	}
	
	
	public Optional<Paciente> PacientePorId(long id) throws Exception {
		Optional<Paciente> paciente = pd.selectPacientePorId(id);
		return paciente;
	}

	
	public void delete(long id)throws Exception {
		pd.deletePaciente(id);
	}	
	
	public void atualizar(Paciente paciente) throws SQLException {
		pd.atualizar(paciente);
	}
	
	public long IdPacientePorCpf(String cpf) throws SQLException {
		return pd.selectIdPacientePorCpf(cpf);		
	}
	
}
