package com.CSH.BO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.CSH.DAO.TecnicoDAO;
import com.CSH.beans.Tecnico;
import com.CSH.conexao.DaoFactory;
import com.CSH.exception.TecnicoBOException;

public class TecnicoBO {

	private TecnicoDAO td;
	
	public TecnicoBO() {
		try {
			td = DaoFactory.getTecnicoDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar(Tecnico tecnico) throws TecnicoBOException {
		if (tecnico.getId() == 0 && tecnico.getCoren() != 0 ) {
			td.insert(tecnico);
		}
		else {
			throw new TecnicoBOException();
		}
	}
	
	public void delete(int coren)throws Exception {
		td.deleteTecnico(coren);
	}		
	public List<Tecnico> select() throws SQLException {
		return td.selectTecnico();
	}

	public Optional<Tecnico> TecnicoPorCoren(int coren) throws Exception {
		Optional<Tecnico> tecnico = td.selectTecnicoPorCoren(coren);
		return tecnico;
	}
	
	public void atualizar(Tecnico tecnico) throws SQLException {
		td.atualizar(tecnico);
	}

	
	public List<String> selectNomeTecnicoPorIdEquipe(long id){
		return td.nomeTecnicoPorIdEquipe(id);
	}
	
}
