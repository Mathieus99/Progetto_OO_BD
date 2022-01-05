package Model;

import java.util.ArrayList;

public class Studente extends Utente {

	private String email;
	private ArrayList<IstanzaDiTest> testSostenuti;
	
	public Studente(String nome, String cognome, String login, String password, String email) {
		super(nome, cognome, login, password);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<IstanzaDiTest> getTestSostenuti() {
		return testSostenuti;
	}

	public void setTestSostenuti(ArrayList<IstanzaDiTest> testSostenuti) {
		this.testSostenuti = testSostenuti;
	}
	

}
