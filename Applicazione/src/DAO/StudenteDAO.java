package DAO;

import java.sql.*;
import Database.ConnessioneDatabase;
import Model.Studente;

public class StudenteDAO {
	Connection conn;
	Studente s;
	
	public StudenteDAO(){
		try {
			conn = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("Connessione fallita.");
			e.printStackTrace();
		}
	}
	
	public Studente login(String email, String pass){
		s= new Studente();
		try {
			PreparedStatement login = conn.prepareStatement("SELECT * FROM utente WHERE email = '" + email + "' AND password = '" + pass + "' AND ruolo = 'Studente'");
			ResultSet rs = login.executeQuery();
			if (rs.wasNull())
				return null;	
			while(rs.next()) {
				s.setNome(rs.getString("nome"));
				s.setCognome(rs.getString("cognome"));
				s.setEmail("email");
				s.setPassword("password");
			}
			rs.close();
			conn.close();
			return s;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}			
	}
	
	public void insertStudent(Studente s) {
		try {
			PreparedStatement register = conn.prepareStatement("INSERT INTO utente (nome,cognome,email,password,ruolo) values ('"+ s.getNome() + "','" + s.getCognome() + "','" +s.getEmail() + "','" + s.getPassword() + "','Studente')");
			register.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
