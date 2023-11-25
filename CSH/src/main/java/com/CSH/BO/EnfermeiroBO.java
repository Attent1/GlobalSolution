package com.CSH.BO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.CSH.DAO.EnfermeiroDAO;
import com.CSH.beans.Enfermeiro;
import com.CSH.conexao.DaoFactory;
import com.CSH.exception.EnfermeiroBOException;

public class EnfermeiroBO {
	
	private EnfermeiroDAO ed;
	
	public EnfermeiroBO() {
		try {
			ed = DaoFactory.getEnfermeiroDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Só será realizado insert se possuir valor de coren e o ID sem 0 (Está sendo gerado automaticamente pelo Banco NEXTVAL)
	public void cadastrar(Enfermeiro enfermeiro) throws EnfermeiroBOException {
		try {			
			if (enfermeiro.getId() == 0 && enfermeiro.getCoren() != 0) {
				ed.insert(enfermeiro);			
			}
			else {
				throw new EnfermeiroBOException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteEnfermeiro(int coren)throws Exception {
		ed.deleteEnfermeiro(coren);
	}	
	
	public List<Enfermeiro> select() throws SQLException {
		return ed.selectEnfermeiro();
	}

	public Optional<Enfermeiro> EnfermeiroPorCoren(int coren) throws Exception {
		Optional<Enfermeiro> enfermeiro = ed.selectEnfermeiroPorCoren(coren);
		return enfermeiro;
	}

	public void atualizar(Enfermeiro enfermeiro) throws SQLException {
		ed.atualizar(enfermeiro);
	}
	
	public String validaLoginEnfemeiro(String senhaEnfermeiro) throws SQLException{
		return ed.validaLoginEnfermeiro(senhaEnfermeiro);
	}
	
}
