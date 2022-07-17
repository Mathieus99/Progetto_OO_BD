package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.ConnessioneDatabase;

public class RispostaUtenteDAO {
	
	Connection conn;
	//Crea la connessione
	
	public RispostaUtenteDAO(){
		try {
			conn = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("Connessione fallita.");
			e.printStackTrace();
		}
	}
	
	public int getMaxId() {
		int id = 0;
		try {
			PreparedStatement getId = conn.prepareStatement("SELECT MAX(idrispostautente) FROM rispostautente");
			ResultSet rs = getId.executeQuery();
			while (rs.next()) {
				id = rs.getInt("max");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
}
