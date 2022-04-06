package Model;

import java.util.ArrayList;

public class Studente{
	private String Nome;
	private String Cognome;
	private String Password;
	private String Email;
	private String Matricola;
	private ArrayList<IstanzaDiTest> testSostenuti;
	
	/*------------------------------------------------------------REGISTRA L'UTENTE------------------------------------------------------------*/
	public void registraStudente(String nome, String cognome, String password, String email,String matricola){
		this.Nome = nome;
		this.Cognome = cognome;
		this.Password = password;	
		this.Email = email;
		this.Matricola= matricola;
	}
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*-------------------------------------------------SETTA E RESTITUISCE I DATI DEGLI STUDENTI-----------------------------------------------*/
	
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
	public void setMatricola(String matricola) {
		this.Matricola=matricola;
	}
	public String getMatricola() {
		return this.Matricola;
	}
	public ArrayList<IstanzaDiTest> getTestSostenuti() {
		return testSostenuti;
	}
	public void setTestSostenuti(ArrayList<IstanzaDiTest> testSostenuti) {
		this.testSostenuti = testSostenuti;
	}
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/

}
