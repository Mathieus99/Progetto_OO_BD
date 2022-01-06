package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class IstanzaDiTest {
    private String stato;
    private int punteggio;
    private Test idTest;
    private Studente idStudente;
    private ArrayList<Risposta> risposteUtente;
    
    public static final String IN_FASE_DI_VALUTAZIONE = "IN FASE DI VALUTAZIONE";
    public static final String VALUTATO = "VALUTATO";
    
    public Scanner input = new Scanner(System.in);
     
	public IstanzaDiTest(Test idTest, Studente idStudente) {
		super();
		this.stato = IstanzaDiTest.IN_FASE_DI_VALUTAZIONE;
		this.idTest = idTest;
		this.idStudente = idStudente;
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
	public Test getIdTest() {
		return idTest;
	}
	public void setIdTest(Test idTest) {
		this.idTest = idTest;
	}
	public Studente getIdStudente() {
		return idStudente;
	}
	public void setIdStudente(Studente idStudente) {
		this.idStudente = idStudente;
	}
    
    public void setRisposte()
    {
    	for(int i = 0; i < this.idTest.getNumeroDomande(); i++)
    	{
    		System.out.println(this.idTest.getDomande().get(i).getTestoDomanda());
    		System.out.println("Inserisci la risposta:");
    		String risp = input.nextLine();
    		risposteUtente.add(new Risposta(risp, this, this.idTest.getDomande().get(i)));
    	}
    	
    	this.idStudente.getTestSostenuti().add(this);
    	this.idTest.getTestSostenuti().add(this);
    }
    
}
