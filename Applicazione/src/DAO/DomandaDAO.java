package DAO;

import java.sql.*;
import java.util.ArrayList;

import Database.ConnessioneDatabase;
import Model.Domanda;

public class DomandaDAO {
	
	Connection conn;
	
	public DomandaDAO(){
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
			PreparedStatement getid = conn.prepareStatement("Select max(iddomanda) FROM domanda");
			ResultSet rs = getid.executeQuery();
			while (rs.next())
				id = rs.getInt("max");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public ArrayList<Domanda> getDomandeTest(long idTest){
		ArrayList<Domanda> domandeTest = new ArrayList<Domanda>();
		Domanda d = new Domanda();
		RispostaDAO rdao = new RispostaDAO();
		try {
			PreparedStatement searchD = conn.prepareStatement("SELECT * FROM domanda WHERE idtest = " + idTest);
			ResultSet rs = searchD.executeQuery();
			while (rs.next()) {
				d.setIdDomanda(rs.getInt("iddomanda"));
				d.setIdTest(rs.getInt("idtest"));
				d.setTestoDomanda(rs.getString("testodomanda"));
				d.setTipo(rs.getString("tipodomanda"));
				if (d.getTestoDomanda().equals("Multipla"))
					d.setRisposte(rdao.getRisposte(d));
				domandeTest.add(d);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return domandeTest;
	}
	
	public void saveDomanda(Domanda d) {
		try {
			PreparedStatement saveD = conn.prepareStatement("INSERT INTO domanda values ("+d.getIdDomanda()+","+d.getTipo()+","+d.getTestoDomanda()+","+d.getIdTest()+")");
			saveD.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
