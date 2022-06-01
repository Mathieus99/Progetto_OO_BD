package DAO;

import java.sql.*;
import Database.ConnessioneDatabase;
import Model.*;

public class DomandaDAO {
	
	Connection conn;
	Domanda d;
	
	public DomandaDAO(){
		try {
			conn = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("Connessione fallita.");
			e.printStackTrace();
		}
	}
	
	
	
}
