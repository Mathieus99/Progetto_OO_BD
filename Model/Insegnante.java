package Model;

import java.util.ArrayList;

public class Insegnante extends Utente {

	private String email;
	private ArrayList<Test> testCreati;
	
	public Insegnante(String nome, String cognome, String login, String password, String email) {
		super(nome, cognome, login, password);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Test> getTestCreati() {
		return testCreati;
	}

	public void setTestCreati(ArrayList<Test> testCreati) {
		this.testCreati = testCreati;
	}

	
}
