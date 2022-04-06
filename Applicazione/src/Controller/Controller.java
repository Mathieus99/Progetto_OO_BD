package Controller;

import Model.Insegnante;
import Model.Studente;
import Model.Test;
import Database.ConnessioneDatabase;
import Model.Domanda;

public class Controller {
	
	// TODO creare Arraylist con tutti gli elementi presi dal DB
	
	private Studente s;
	private Insegnante i;
	private Test t;
	private String ruolo;
	private Domanda d;
	private ConnessioneDatabase connessione;
	private boolean registerSuccesful;
	public boolean fromRegister=false;
	public boolean back=false;
	
	
	/*----------------------------------------------------------REGISTRAZIONE UTENTI-----------------------------------------------------------*/
	public void registraDocente (String nome, String cognome,String password, String email){
		i.setNome(nome);
		i.setCognome(cognome);
		i.setPassword(password);
		i.setEmail(email);
	}	
	public void registraStudente (String nome, String cognome, String password, String email, String matricola) {
		s.setNome(nome);
		s.setCognome(cognome);
		s.setPassword(password);
		s.setEmail(email);
		s.setMatricola(matricola);
	}
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*--------------------------------------------------PRELEVA E SETTA IL RUOLO DEGLI UTENTI--------------------------------------------------*/
	public void setRuolo(String ruolo) {
		this.ruolo=ruolo;
	}	
	public String getRuolo() {
		return ruolo;
	}
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*-------------------------------------------------SETTA I DATI DEGLI UTENTI NELLE FINESTRE------------------------------------------------*/
	public String matricolaStudente() {
		return s.getMatricola();
	}	
	public String emailS() {
		return s.getEmail();
	}	
	public String emailD() {
		return i.getEmail();
	}	
	public String nomeS() {
		return s.getNome();
	}	
	public String cognomeS() {
		return s.getCognome();
	}	
	public String nomeD() {
		return i.getNome();
	}	
	public String cognomeD() {
		return i.getCognome();
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------*/
	
	public void setRegisterSuccesful(boolean registerSuccesful) {
		this.registerSuccesful=registerSuccesful;
		fromRegister=true;
	}
	public boolean getregisterSuccesful() {
		return registerSuccesful;
	}
	
	/*-------------------------------------------------------------GESTIONE DEI TEST--------------------------------------------------------------*/
	public void setNumDomande(int nDomande) {
		t.setNumeroDomande(nDomande);
	}
	public int getNumDomande() {
		return t.getNumeroDomande();
	}
	public void incrementaID() {
		
	}
	
	
	/*--------------------------------------------------------------------------------------------------------------------------------------------*/	
}
