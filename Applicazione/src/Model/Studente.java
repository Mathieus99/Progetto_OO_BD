package Model;

import java.util.ArrayList;

public class Studente extends Utente {

	private String email;
	private String matricola;
	private ArrayList<IstanzaDiTest> testSostenuti;
	
	/*------------------------------------------------------------REGISTRA L'UTENTE------------------------------------------------------------*/
	public Studente(String nome, String cognome, String password, String email,String matricola) {
		super(nome, cognome, password);
		this.email = email;
		this.matricola=matricola;
	}
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*-------------------------------------------------SETTA E RESTITUISCE I DATI DEGLI STUDENTI-----------------------------------------------*/
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public void setMatricola(String matricola) {
		this.matricola=matricola;
	}
	public String getMatricola() {
		return this.matricola;
	}
	public ArrayList<IstanzaDiTest> getTestSostenuti() {
		return testSostenuti;
	}
	public void setTestSostenuti(ArrayList<IstanzaDiTest> testSostenuti) {
		this.testSostenuti = testSostenuti;
	}
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/

}
