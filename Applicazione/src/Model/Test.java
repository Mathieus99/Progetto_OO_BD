package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    private long idTest;
    private String titolo;
    private int maxPunteggio;
    private int numeroDomande;
    private int punteggioDomandeMin;
    private int punteggioDomandeMax;
    private String categoria;
    private ArrayList<Domanda> domande = new ArrayList<Domanda>();
    private Insegnante proprietario;
    
    /*--------------------------------------------------------------IMPOSTA IL TEST-------------------------------------------------------------*/
	public Test(long idTest,String titolo, int maxPunteggio, int numeroDomande, int punteggioDomandeMin, int punteggioDomandeMax,
		   String categoria, Insegnante proprietario) {
		super();
		this.idTest = idTest;
		this.titolo = titolo;
		this.maxPunteggio = maxPunteggio;
		this.numeroDomande = numeroDomande;
		this.punteggioDomandeMin = punteggioDomandeMin;
		this.punteggioDomandeMax = punteggioDomandeMax;
		this.categoria = categoria;
		this.proprietario = proprietario;
	}
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*----------------------------------------------IMPOSTA E RESTITUISCE I SINGOLI DATI DEL TEST----------------------------------------------*/
	public void setDomande(Domanda d)
	{
		domande.add(d);
	}

	public long getIdTest() {
		return idTest;
	}

	public void setIdTest(long idTest) {
		this.idTest = idTest;
	}

	public int getMaxPunteggio() {
		return maxPunteggio;
	}

	public void setMaxPunteggio(int maxPunteggio) {
		this.maxPunteggio = maxPunteggio;
	}

	public int getNumeroDomande() {
		return numeroDomande;
	}

	public void setNumeroDomande(int numeroDomande) {
		this.numeroDomande = numeroDomande;
	}

	public int getPunteggioDomandeMin() {
		return punteggioDomandeMin;
	}

	public void setPunteggioDomandeMin(int punteggioDomandeMin) {
		this.punteggioDomandeMin = punteggioDomandeMin;
	}

	public int getPunteggioDomandeMax() {
		return punteggioDomandeMax;
	}

	public void setPunteggioDomandeMax(int punteggioDomandeMax) {
		this.punteggioDomandeMax = punteggioDomandeMax;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public ArrayList<Domanda> getDomande() {
		return domande;
	}

	public void setDomande(ArrayList<Domanda> domande) {
		this.domande = domande;
	}

	public Insegnante getProprietario() {
		return proprietario;
	}

	public void setProprietario(Insegnante proprietario) {
		this.proprietario = proprietario;
	}

	public ArrayList<IstanzaDiTest> getTestSostenuti() {
		return testSostenuti;
	}

	public void setTestSostenuti(ArrayList<IstanzaDiTest> testSostenuti) {
		this.testSostenuti = testSostenuti;
	}
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
}
