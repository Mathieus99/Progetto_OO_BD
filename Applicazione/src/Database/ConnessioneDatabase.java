package Database;

import java.sql.*;

public class ConnessioneDatabase {
		// ATTRIBUTI
		private static ConnessioneDatabase instance;
		private Connection connection = null;
		private String nome = "postgres";
		private String password = "Legnarino30L";
		private String url = "jdbc:postgresql://localhost:5432/progettooobd";
		private String driver = "org.postgresql.Driver";
		String success = "Connessione al Database effettuata con successo.";

		// COSTRUTTORE
		public ConnessioneDatabase() throws SQLException {
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, nome, password);
			} catch (ClassNotFoundException ex) {
				System.out.println("Connessione al Database fallita: " + ex.getMessage());
				ex.printStackTrace();
			}

		}

		public Connection getConnection() {
				return connection;
		}
		
		public static ConnessioneDatabase getInstance() throws SQLException {
			if (instance == null) {
				instance = new ConnessioneDatabase();
			} else if (instance.getConnection().isClosed()) {
				instance = new ConnessioneDatabase();
			}
			return instance;
		}
}
