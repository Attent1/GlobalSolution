package com.CSH.conexao;

import java.io.IOException;
import java.sql.SQLException;

import com.CSH.DAO.EnfermeiroDAO;
import com.CSH.DAO.EquipeDAO;
import com.CSH.DAO.NasDAO;
import com.CSH.DAO.PacienteDAO;
import com.CSH.DAO.TecnicoDAO;
import com.CSH.Singleton.ConnectionManager;

public class DaoFactory {

    public static EnfermeiroDAO getEnfermeiroDAO() throws SQLException, IOException {
        return new EnfermeiroDAO(ConnectionManager.getInstance().getConnection());
    }

    public static TecnicoDAO getTecnicoDAO() throws SQLException, IOException {
        return new TecnicoDAO(ConnectionManager.getInstance().getConnection());
    }

    public static EquipeDAO getEquipeDAO() throws SQLException, IOException {
        return new EquipeDAO(ConnectionManager.getInstance().getConnection());
    }
    
    public static PacienteDAO getPacienteDAO() throws SQLException, IOException {
        return new PacienteDAO(ConnectionManager.getInstance().getConnection());
    }
    
    public static NasDAO getNasDAO() throws SQLException, IOException {
        return new NasDAO(ConnectionManager.getInstance().getConnection());
    }
    
    
}