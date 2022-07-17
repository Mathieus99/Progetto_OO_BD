package Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unused")
public class IstanzaDiTest {
	private long idIstanza;
    private String stato;
    private int punteggio;
    private Test t;
    private Studente idStudente;
    private ArrayList<RispostaUtente> risposteUtente;
    private Timestamp orarioFine;
    
    public static final String InFaseDiValutazione= "IN FASE DI VALUTAZIONE";
    public static final String Valutato = "VALUTATO";
    
    public Scanner input = new Scanner(System.in);
     
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
	
	public void addRispostaUtente(RispostaUtente r) {
		risposteUtente.add(r);
	}
}
