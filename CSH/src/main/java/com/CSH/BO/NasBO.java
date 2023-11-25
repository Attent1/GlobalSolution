package com.CSH.BO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.CSH.DAO.NasDAO;
import com.CSH.beans.Nas;
import com.CSH.conexao.DaoFactory;
import com.CSH.exception.EquipeBOException;

public class NasBO {
	private NasDAO nd;
	
	public NasBO() {
		try {
			nd = DaoFactory.getNasDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar(Nas nas) throws EquipeBOException {
		if (nas.getIdEquipe() != 0 && nas.getIdPaciente() != 0) {
			nd.insert(nas);				
		}
		else {
			throw new EquipeBOException();
		}
				
	}
	
	public List<Nas> select() throws SQLException {
		return nd.selectNas();
	}
	
	public Optional<Nas> NasPorId(long id) throws Exception {
		Optional<Nas> nas = nd.selectNasPorId(id);
		return nas;
	}
	
	public void delete(long id)throws Exception {
		nd.deleteNas(id);
	}	
	
	public void atualizar(Nas nas) throws SQLException {
		nd.atualizar(nas);
	}
	
	
}
