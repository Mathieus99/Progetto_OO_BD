package DAO;

import java.sql.*;
import Database.ConnessioneDatabase;
import Model.*;

public class RispostaDAO {
	
	Connection conn;
	
	public RispostaDAO(){
		try {
			conn = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("Connessione fallita.");
			e.printStackTrace();
		}
	}
	
	
}
