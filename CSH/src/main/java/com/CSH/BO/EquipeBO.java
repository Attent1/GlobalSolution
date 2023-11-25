package com.CSH.BO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.CSH.DAO.EquipeDAO;
import com.CSH.beans.Equipe;
import com.CSH.conexao.DaoFactory;
import com.CSH.exception.EquipeBOException;

public class EquipeBO {

	private EquipeDAO ed;
	
	public EquipeBO() {
		try {
			ed = DaoFactory.getEquipeDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar(Equipe equipe) throws EquipeBOException {
		if(equipe.getCorenTecnicos().size() == 5 ) {
			try {
				ed.insert(equipe);
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}else {
			throw new EquipeBOException();
		}
		
	}
	
	public void delete(long id)throws Exception {
		ed.deleteEquipe(id);
	}	
	
	public List<Equipe> select() throws SQLException {
		return ed.selectEquipe();
	}
	
	public Optional<Equipe> EquipePorId(long id) throws Exception {
		Optional<Equipe> enfermeiro = ed.selectEquipePorId(id);
		return enfermeiro;
	}
	
	public void atualizar(Equipe equipe) throws SQLException {
		ed.atualizar(equipe);
	}

	public long selectEquipePorNome(String nomeEquipe) throws SQLException{
		return ed.selectIdEquipePorNome(nomeEquipe);
	}
	
	public List<String> validaLogin(String nomeEquipe){
		return ed.validaLogin(nomeEquipe);
	}
	
}
