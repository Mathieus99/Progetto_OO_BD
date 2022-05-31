package Controller;

import Model.*;

import java.util.ArrayList;

import DAO.*;
import Database.*;

public class Controller {
	// TODO creare Arraylist con tutti gli elementi presi dal DB
	
	private Studente s = new Studente();
	private Insegnante i = new Insegnante();
	private Test t;
	private String ruolo;
	private Domanda d;
	private ConnessioneDatabase connessione;
	private boolean registerSuccesful;
	public boolean fromRegister=false;
	public boolean back=false;
		
	/*----------------------------------------------------------REGISTRAZIONE E FUNZIONI LOGOUT UTENTI------------------------------------------*/
		
	public void registrazione (String nome, String cognome,String password, String email){
		i.setNome(nome);
		i.setCognome(cognome);
		i.setPassword(password);
		i.setEmail(email);
	}	
	
	public void logout(Studente s) {
		s = null;
	}
	
	public void logout(Insegnante i) {
		i = null;
	}
	
	public Studente getStudente() {
		return s;
	}
	
	public Insegnante getDocente() {
		return i;
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
	
	public ArrayList<Test> getTestDocente(){
		return i.getTestCreati();
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*------------------------------------------------CONTROLLA LO STATO DELLA REGISTRAZIONE-----------------------------------------------------*/
	public void setRegisterSuccesful(boolean registerSuccesful) {
		this.registerSuccesful=registerSuccesful;
		fromRegister=true;
	}
	public boolean getregisterSuccesful() {
		return registerSuccesful;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*-----------------------------------------------SETTA L'UTENTE CON I DATI DEL DB------------------------------------------------------------*/
	
	public void setUtente(String email,String pass) {
		StudenteDAO student = new StudenteDAO();
		InsegnanteDAO insegnante = new InsegnanteDAO();
		if (email.contains("@Studenti.Universita.it")) {
			s = student.login(email, pass);
			ruolo = "Studente";
		}
		else {
			i = insegnante.login(email, pass);
			ruolo = "Insegnante";
		}
	}
	
	public boolean checkUtente() {
		if(s.getNome() == null && i.getNome() == null) {
			return false;
		}
		else 
			return true;
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------*/
		
	/*-------------------------------------------------------------GESTIONE DEI TEST--------------------------------------------------------------*/
	public void setNumDomande(int nDomande) {
		t.setNumeroDomande(nDomande);
	}
	public double getNumDomande() {
		return t.getNumeroDomande();
	}
	public void incrementaID() {
		
	}
	
	/*--------------------------------------------------------------------------------------------------------------------------------------------*/
	public void setConnessione(ConnessioneDatabase connessione) {
		this.connessione = connessione;
	}
}
