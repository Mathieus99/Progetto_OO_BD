package DAO;

import java.sql.*;
import java.util.ArrayList;

import Database.ConnessioneDatabase;
import Model.Insegnante;
import Model.Test;

public class InsegnanteDAO {
	Connection conn;
	Insegnante i; 
	
	public InsegnanteDAO(){
		try {
			conn = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("Connessione fallita.");
			e.printStackTrace();
		}
	}
	
	public Insegnante login(String email, String pass){
		i = new Insegnante();
		try {
			PreparedStatement login = conn.prepareStatement("SELECT * FROM utente WHERE email = '"+email+"' AND password = '"+pass+"' AND ruolo='Insegnante'");
			ResultSet rs = login.executeQuery();
			if (rs.wasNull())
				return null;
			while(rs.next()) {
				i.setNome(rs.getString("nome"));
				i.setCognome(rs.getString("cognome"));
				i.setEmail("email");
				i.setPassword("password");
			}
			rs.close();
			Test t = new Test();
			ArrayList<Test> testLoaded = new ArrayList<Test>();
			PreparedStatement loadTest = conn.prepareStatement("SELECT * FROM Test JOIN utente ON idutente = insegnante WHERE email = '" + email + "'");
			rs = loadTest.executeQuery();
			if (rs.wasNull())
				return null;
			while (rs.next()) {
				t.setIdTest(rs.getLong("idtest"));
				t.setMaxPunteggio(rs.getDouble("maxpunteggio"));
				t.setPunteggioDomandeMax(rs.getDouble("punteggiodmax"));
				t.setPunteggioDomandeMin(rs.getDouble("punteggiodmin"));
				t.setNumeroDomande(rs.getInt("numdomande"));
				t.setCategoria(rs.getString("categoria"));
				t.setProprietario(this.i);
				t.setTitolo(rs.getString("nometest"));
				testLoaded.add(t);
				
			}
			rs.close();
			i.setTestCreati(testLoaded);
			conn.close();
			return i;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}			
	}
	
	public void insertInsegnante(Insegnante i) {
		try {
			PreparedStatement register = conn.prepareStatement("INSERT INTO utente (nome,cognome,email,password,ruolo) values ('"+ i.getNome() + "','" + i.getCognome() + "','" +i.getEmail() + "','" + i.getPassword() + "','Insegnante')");
			register.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
