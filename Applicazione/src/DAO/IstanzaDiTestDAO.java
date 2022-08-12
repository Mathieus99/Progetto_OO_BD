package DAO;

import java.sql.*;
import java.util.ArrayList;

import Controller.Controller;
import Database.ConnessioneDatabase;
import Model.IstanzaDiTest;
 
public class IstanzaDiTestDAO {
	
	Connection conn;
	Controller c;
	
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
	
	public void insertIstanza(IstanzaDiTest IdT) {
		try {
			PreparedStatement save = conn.prepareStatement("INSERT INTO istanzaditest(idistanzaditest,stato,risultato,idtest,studente,datasostenuto,numerorcorrette,numerorerrate) VALUES("+IdT.getIdIstanza()+",'"+IdT.getStato()+"',"+IdT.getPunteggio()+","+IdT.getIdTest()+","+IdT.getIdStudente().getIdStudente()+",'"+IdT.getOrarioFine()+"',"+IdT.getNumCorrette()+","+IdT.getNumErrate()+")");
			save.execute();
		} catch (SQLException e) {
			System.out.println("Errore nel salvataggio del test dello studente");
			e.printStackTrace();
		}
	}
	
	public ArrayList<IstanzaDiTest> getIstanzeDaCorreggere(){
		ArrayList<IstanzaDiTest> istanzeProf = new ArrayList<IstanzaDiTest>();
		IstanzaDiTest IdT;
		try {
			PreparedStatement loadIstances = conn.prepareStatement("SELECT * FROM istanzaditest I JOIN test T ON I.idtest = T.idtest WHERE insegnante = "+c.getDocente().getIdDocente());
			ResultSet rs = loadIstances.executeQuery();
			while(rs.next()) {
				IdT = new IstanzaDiTest();
				IdT.setIdIstanza(rs.getLong("idistanzaditest"));
				IdT.setNumCorrette(rs.getInt("numerorcorrette"));
				IdT.setNumErrate(rs.getInt("numerorerrate"));
				
			}
		}catch(SQLException e) {
			System.out.println("Errore caricamento test da correggere!");
			e.printStackTrace();
		}
	}
}
