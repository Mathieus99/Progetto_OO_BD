package DAO;

import java.sql.*;
import Database.ConnessioneDatabase;
 
public class IstanzaDiTestDAO {
	
	Connection conn;
	
	public IstanzaDiTestDAO() {
		try {
			conn = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("Connessione fallita.");
			e.printStackTrace();
		}
	}
	
	public long getMaxId () {
		long id = 0;
		try {
			PreparedStatement maxId = conn.prepareStatement("SELECT MAX(idistanzaditest) FROM istanzaditest");
			ResultSet rs = maxId.executeQuery();
			while (rs.next()) {
				id = rs.getInt("max");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return id+1;
	}
}
