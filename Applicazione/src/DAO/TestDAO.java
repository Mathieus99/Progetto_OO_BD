package DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Database.ConnessioneDatabase;
import Model.Test;

public class TestDAO {

	Connection conn;
	
	public TestDAO(){
		try {
			conn = ConnessioneDatabase.getInstance().getConnection();
		} catch (SQLException e) {
			System.out.println("Connessione fallita.");
			e.printStackTrace();
		}
	}
	
	public int getMaxId() {
		int maxIdTest = 0;
		try {
			PreparedStatement maxId = conn.prepareStatement("SELECT MAX(idtest) from test");
			ResultSet rs = maxId.executeQuery();
			while (rs.next())
				maxIdTest = rs.getInt("max");
			rs.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return maxIdTest;
	}
	
	public ArrayList<Test> getTest(){
		ArrayList<Test> testInseriti = new ArrayList<Test>();
		try {
			PreparedStatement loadTest = conn.prepareStatement("SELECT * FROM test");
			ResultSet rs = loadTest.executeQuery();
			while (rs.next()) {
				Test t = new Test();
				t.setIdTest(rs.getLong("idtest"));
				t.setTitolo(rs.getString("nometest"));
				t.setMaxPunteggio(rs.getDouble("maxpunteggio"));
				t.setNumeroDomande(rs.getInt("numdomande"));
				t.setPunteggioDomandeMax(rs.getDouble("punteggiodmax"));
				t.setPunteggioDomandeMin(rs.getDouble("punteggiodmin"));
				t.setTipo(rs.getString("tipoquiz"));
				t.setCategoria(rs.getString("categoria"));
				t.setProprietario(rs.getInt("insegnante"));
				testInseriti.add(t);
			}
		}catch (SQLException e) {
				e.printStackTrace();
			}
		return testInseriti;	
	}
	
	public ArrayList<Test> getTestDocente(int IdDocente){
		ArrayList<Test> testDocenteD = new ArrayList<Test>();
		try {
			PreparedStatement searchTest = conn.prepareStatement("SELECT * FROM test WHERE insegnante = " + IdDocente);
			ResultSet rs = searchTest.executeQuery();
			while(rs.next()) {
				Test t = new Test();
				t.setIdTest(rs.getInt("idtest"));
				t.setTitolo(rs.getString("nometest"));
				t.setMaxPunteggio(rs.getDouble("maxpunteggio"));
				t.setNumeroDomande(rs.getInt("numdomande"));
				t.setPunteggioDomandeMax(rs.getDouble("punteggiodmax"));
				t.setPunteggioDomandeMin(rs.getDouble("punteggiodmin"));
				t.setTipo(rs.getString("tipoquiz"));
				t.setCategoria(rs.getString("categoria"));
				t.setProprietario(IdDocente);
				testDocenteD.add(t);
			}
			rs.close();
			conn.close();				
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return testDocenteD;
	}
	
	public void saveTest(Test t) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			PreparedStatement insertTest = conn.prepareStatement("INSERT INTO test(idtest,nometest,data_ora,tipoquiz,maxpunteggio,numdomande,punteggiodmax,punteggiodmin,categoria,insegnante) "
																	+ "VALUES("+t.getIdTest()+",'"+t.getTitolo()+"','"+dtf.format(LocalDateTime.now())+"','"+t.getTipo()+"',"+t.getMaxPunteggio()+","+t.getNumeroDomande()+","+t.getPunteggioDomandeMax()+","+t.getPunteggioDomandeMin()+",'"+t.getCategoria()+"',"+t.getProprietario()+")");
			insertTest.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
