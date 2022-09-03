package DAO;

import java.sql.*;
import java.util.ArrayList;

import Database.ConnessioneDatabase;
import Model.IstanzaDiTest;
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
	
	public int getMaxId() {
		int maxId = 0;
		try {
			PreparedStatement getId = conn.prepareStatement("SELECT MAX(idutente) FROM UTENTE");
			ResultSet rs = getId.executeQuery();
			while (rs.next())
				maxId = rs.getInt("max");
		} catch (SQLException e) {
			System.out.println("StudenteDAO - Errore recupero max ID");
			e.printStackTrace();
		}
		return maxId;
	}
	
	public ArrayList<IstanzaDiTest> loadTestSostenuti (Studente s){
		IstanzaDiTest IdT;
		TestDAO tdao = new TestDAO();
		ArrayList<IstanzaDiTest> testStudente = new ArrayList<IstanzaDiTest>();
		try {
		PreparedStatement loadTestS = conn.prepareStatement("SELECT * FROM istanzaditest WHERE studente="+s.getIdStudente());
		ResultSet rs = loadTestS.executeQuery();		
		if (rs.wasNull())
			s.setTestSostenuti(null);
		else
			while(rs.next()) {
				IdT = new IstanzaDiTest();
				IdT.setIdIstanza(rs.getInt("idistanzaditest"));
				IdT.setStato(rs.getString("stato"));
				IdT.setPunteggio(rs.getInt("risultato"));
				IdT.setOrarioFine(rs.getTimestamp("datasostenuto"));
				IdT.setNumCorrette(rs.getInt("numerorcorrette"));
				IdT.setNumErrate(rs.getInt("numerorerrate"));					
				IdT.setTest(tdao.getTestbyID(rs.getInt("idtest")));
				testStudente.add(IdT);
			}
		s.setTestSostenuti(testStudente);
		} catch (SQLException e) {
			System.out.println("Errore nel caricamento dei test dello studente");
			e.printStackTrace();
		}
		return testStudente;
	}
	
	public void register(Studente s) {
		try {
			PreparedStatement registra = conn.prepareStatement("INSERT INTO utente(idutente,nome,cognome,email,password) VALUES("+s.getIdStudente()+",'"+s.getNome()+"','"+s.getCognome()+"','"+s.getEmail()+"','"+s.getPassword()+"')");
			registra.execute();
		} catch (SQLException e) {
			System.out.println("StudenteDAO - Errore Registrazione Studente");
			e.printStackTrace();
		}
	}
	
	public Studente login(String email, String pass){
		s= new Studente();
		ResultSet rs;
		try {
			PreparedStatement login = conn.prepareStatement("SELECT * FROM utente WHERE email = '" + email + "' AND password = '" + pass + "' AND ruolo = 'Studente'");
			rs = login.executeQuery();
			if (rs.wasNull())
				return null;	
			while(rs.next()) {
				s.setIdStudente(rs.getLong("idutente"));
				s.setNome(rs.getString("nome"));
				s.setCognome(rs.getString("cognome"));
				s.setEmail("email");
				s.setPassword("password");
			}
			rs.close();
			s.setTestSostenuti(loadTestSostenuti(s));
			return s;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}			
	}
	
	public long searchIdStudente(Studente s) {
		long idStudente = 0;
		try {
			PreparedStatement searchId = conn.prepareStatement("SELECT idutente FROM utente WHERE email='"+s.getEmail()+"' AND ruolo='Studente'");
			ResultSet rs = searchId.executeQuery();
			while(rs.next())
				idStudente = rs.getLong("idutente");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return idStudente;
	}
	
	public Studente setStudenteIstanza(int id) {
		Studente s = new Studente();
		try {
			PreparedStatement search = conn.prepareStatement("SELECT * FROM utente where idutente ="+ id);
			ResultSet rs = search.executeQuery();
			while (rs.next()) {
				s.setIdStudente(rs.getLong("idutente"));
				s.setNome(rs.getString("nome"));
				s.setCognome(rs.getString("cognome"));
				s.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			System.out.println("Set Studente Istanza - Errore nel cercare lo studente");
			e.printStackTrace();
		}
		return s;
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
