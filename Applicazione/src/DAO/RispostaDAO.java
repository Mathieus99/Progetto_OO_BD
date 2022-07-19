package DAO;

import java.sql.*;
import java.util.ArrayList;

import Database.ConnessioneDatabase;
import Model.Domanda;
import Model.IstanzaDiTest;
import Model.Risposta;
import Model.RispostaUtente;

public class RispostaDAO {
	
	Connection conn;
	//Crea la connessione
	
	public RispostaDAO(){
		try {
			conn = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("Connessione fallita.");
			e.printStackTrace();
		}
	}
	
	//--------------------
	
	//Cerca l'ultimo id di risposta presente nel db e lo restituisce
	
	public int getMaxId() {
		int id = 0;
		try {
			PreparedStatement getid = conn.prepareStatement("SELECT MAX(idrisposta) FROM risposta");
			ResultSet rs = getid.executeQuery();
			while (rs.next())
				id = rs.getInt("max");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	//--------------------------------------------------------------- 
	
	//Cerca le possibili risposte alla relativa domanda-------------
	public ArrayList<Risposta> getRisposte(Domanda d){
		ArrayList<Risposta> risposteD = new ArrayList<Risposta>();
		Risposta r = new Risposta();
		try {
			PreparedStatement getR = conn.prepareStatement("SELECT * FROM risposta WHERE iddomanda = "+d.getIdDomanda());
			ResultSet rs = getR.executeQuery();
			while (rs.next()) {
				r.setIdDomanda(rs.getInt("iddomanda"));
				r.setIdRisposta(rs.getInt("idrisposta"));
				r.setTestoRisposta(rs.getString("testoriposta"));
				r.setCorretta(rs.getBoolean("corretta"));
				risposteD.add(r);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return risposteD;
	}
	//--------------------------------------------------------------
	
	public void checkPunteggioRisposta(IstanzaDiTest IdT) {
		int numeroRisposte = 0;
		try {
			for (RispostaUtente rU: IdT.getRisposteUtente()) {
				PreparedStatement checkP = conn.prepareStatement("SELECT * FROM test JOIN (domanda JOIN risposta ON iddomanda) ON idtest WHERE testorisposta = \""+rU.getTestoRisposta()+"\"");
				ResultSet rs = checkP.executeQuery();
				while (rs.next()) {
					if(rs.getBoolean("corretta")) {
						rU.setPunteggio(rs.getInt("punteggiodmax"));
						IdT.aggiungiCorretta(rs.getInt("punteggiodmax"));
						numeroRisposte++;
					}
					else {
						rU.setPunteggio(rs.getInt("punteggiodmin"));
						IdT.aggiungiErrata(rs.getInt("punteggiodmin"));
						numeroRisposte++;
					}
				}
				if(numeroRisposte==IdT.getTest().getNumeroDomande()) {
					IdT.setStato(IstanzaDiTest.Valutato);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	//Salva le nuove risposte nel db
	
	public void saveRisposte(ArrayList<Risposta> risposte) {
		try {
			for (Risposta r: risposte) {
				PreparedStatement saveR = conn.prepareStatement("INSERT INTO risposta values("+r.getIdRisposta()+","+r.getIdDomanda()+","+r.getTestoRisposta()+","+r.isCorretta()+")");
				saveR.execute();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//---------------------------------
}
