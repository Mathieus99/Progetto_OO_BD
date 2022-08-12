 package Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class IstanzaDiTest {
	private long idIstanza;
    private String stato;
    private int punteggio;
    private Test t;
    private Studente idStudente; 
    private ArrayList<RispostaUtente> risposteUtente = new ArrayList<RispostaUtente>();
    private Timestamp orarioFine;
    private int numCorrette;
    private int numErrate;
    
    public static final String InFaseDiValutazione= "In fase di valutazione";
    public static final String Valutato = "Valutato";
    
    public Scanner input = new Scanner(System.in);
    
    public IstanzaDiTest() {
    	
    }
     
	public IstanzaDiTest(Test t, Studente s) {
		this.t = t;
		this.idStudente = s;
		this.stato = IstanzaDiTest.InFaseDiValutazione;
	}
	
	public void setIdIstanza(long id) {
		idIstanza = id;
	}
	
	public long getIdIstanza() {
		return idIstanza;
	}
	
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public int getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	public long getIdTest() {
		return t.getIdTest();
	}
	public Studente getIdStudente() {
		return idStudente;
	}
	public void setIdStudente(Studente idStudente) {
		this.idStudente = idStudente;
	}
	public void setOrarioFine(Long CurrentTime) {
		this.orarioFine = new Timestamp(CurrentTime);		
	}
	
	public void setOrarioFine(Timestamp orario) {
		orarioFine = orario;
	}
	
	public void addRispostaUtente(RispostaUtente r) {
		risposteUtente.add(r);
	}
	
	public int getNumCorrette() {
		return numCorrette;
	}
	
	public void aggiungiCorretta(int corretta) {
		numCorrette++;
		punteggio = punteggio + corretta;
	}
	
	public void rimuoviCorretta(int scorretta) {
		numCorrette--;
		punteggio = punteggio - scorretta;
	}
	
	public int getNumErrate() {
		return numErrate;
	}
	
	public void aggiungiErrata(int errata) {
		numErrate++;
		punteggio = punteggio + errata;
	}
	
	public void rimuoviErrata(int serrata) {
		numErrate--;
		punteggio = punteggio - serrata;
	}
	
	public ArrayList<RispostaUtente> getRisposteUtente(){
		return risposteUtente;
	}
	
	public Test getTest() {
		return t;
	}
	
	public Timestamp getOrarioFine() {
		return orarioFine;
	}
	
	public void setNumCorrette(int corrette) {
		numCorrette = corrette;
	}
	
	public void setNumErrate(int errate) {
		numErrate = errate;
	}
	
	public void setTest(Test t) {
		this.t = t;
	}
}
