package Model;

import java.util.ArrayList;

public class Studente{
	private long idStudente;
	private String Nome;
	private String Cognome;
	private String Password;
	private String Email;
	private ArrayList<IstanzaDiTest> testSostenuti = new ArrayList<IstanzaDiTest>();
	
	/*-------------------------------------------------SETTA E RESTITUISCE I DATI DEGLI STUDENTI-----------------------------------------------*/
	
	public Studente() {
		Nome = null;
		Cognome = null;
		Password = null;
		Email = null;
	}
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		this.Nome = nome;
	}
	
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		this.Cognome = cognome;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		this.Email = email;
	}	
	
	public ArrayList<IstanzaDiTest> getTestSostenuti() {
		return testSostenuti;
	}
	public void setTestSostenuti(ArrayList<IstanzaDiTest> testSostenuti) {
		this.testSostenuti = testSostenuti;
	}
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/

	public long getIdStudente() {
		return idStudente;
	}

	public void setIdStudente(long l) {
		this.idStudente = l;
	}

}
