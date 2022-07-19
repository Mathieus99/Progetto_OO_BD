package DAO;

import java.sql.*;
import Database.ConnessioneDatabase;
import Model.IstanzaDiTest;
 
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
	
	public void insertIstanza(IstanzaDiTest IdT) {
		try {
			PreparedStatement save = conn.prepareStatement("INSERT INTO istanzaditest(idistanzaditest,stato,risultato,idtest,studente,datasostenuto,numerocorrette,numeroerrate) VALUES("+IdT.getIdIstanza()+",\""+IdT.getStato()+","+IdT.getPunteggio()+","+IdT.getIdTest()+","+IdT.getIdStudente()+",\""+IdT.getOrarioFine()+"\","+IdT.getNumCorrette()+","+IdT.getNumErrate()+")");
			save.execute();
		} catch (SQLException e) {
			System.out.println("Errore nel salvataggio del test dello studente");
			e.printStackTrace();
		}
	}
}
