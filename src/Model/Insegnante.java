package Model;

import java.util.ArrayList;

public class Insegnante{

	private String Nome;
	private String Cognome;
	private String Password;
	private String Email;
	private ArrayList<Test> testCreati = new ArrayList<Test>();
	
	/*-------------------------------------------------------------------REGISTRA IL DOCENTE---------------------------------------------------*/
	
	public void Insegnante() {
		Nome = null;
		Cognome = null;
		Password = null;
		Email = null;
	}
	
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*---------------------------------------------------------SETTA E RESTITUISCE I DATI DEL DOCENTE------------------------------------------*/
	
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
	
	public ArrayList<Test> getTestCreati() {
		return testCreati;
	}
	
	public void setTestCreati(ArrayList<Test> t) {
		testCreati= t;
	}
	
	public void addTest(Test t) {
		testCreati.add(t);
	}
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	
}
