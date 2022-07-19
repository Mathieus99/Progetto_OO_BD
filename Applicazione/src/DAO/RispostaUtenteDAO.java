package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.ConnessioneDatabase;
import Model.RispostaUtente;

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
	
	public void saveRisposteUtente(ArrayList<RispostaUtente> risposte) {
		try {
			for(RispostaUtente rU: risposte) {
				PreparedStatement save = conn.prepareStatement("INSERT INTO rispostautente VALUES("+rU.getIdRispostaUtente()+",\""+rU.getTestoRisposta()+"\","+rU.getPunteggio()+","+rU.getIdIstanzaDiTest()+","+rU.getIdDomanda());
				save.execute();
			}
		} catch (SQLException e) {
			System.out.println("Errore nel salvataggio delle risposte");
			e.printStackTrace();
		}
	}
}
