package Model;

import java.util.ArrayList;

public class Domanda {
    private String testoDomanda;
    private int idDomanda;
	private long idTest;
	private String tipo;
	private ArrayList<Risposta> risposte;
    	
	public String getTestoDomanda() {
		return testoDomanda;
	}

	public void setTestoDomanda(String testoDomanda) {
		this.testoDomanda = testoDomanda;
	}

	public long getIdTest() {
		return idTest;
	}

	public void setIdTest(long idTest) {
		this.idTest = idTest;
	}

	public int getIdDomanda() {
		return idDomanda;
	}

	public void setIdDomanda(int idDomanda) {
		this.idDomanda = idDomanda;
	}
	
	public void addRisposta(Risposta r) {
		risposte.add(r);
	}
	
	public void setRisposte(ArrayList<Risposta> risposte) {
		this.risposte = risposte;
	}
	
	public ArrayList<Risposta> getRisposte(){
		return risposte;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
