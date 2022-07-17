package Model;

import java.util.ArrayList;

public class Test {
	private long idTest;
    private String titolo;
    private String tipo;
    private double maxPunteggio;
    private double numeroDomande;
    private double punteggioDomandeMin;
    private double punteggioDomandeMax;
    private String categoria;
    private ArrayList<Domanda> domande = new ArrayList<Domanda>();
    private int proprietario;
        
	/*----------------------------------------------IMPOSTA E RESTITUISCE I SINGOLI DATI DEL TEST----------------------------------------------*/
	public void addDomande(Domanda d){
		domande.add(d);
	}

	public long getIdTest() {
		return idTest;
	}

	public void setIdTest(long idTest) {
		this.idTest = idTest;
	}

	public double getMaxPunteggio() {
		return maxPunteggio;
	}
	
	public void setMaxPunteggio(double maxp) {
		maxPunteggio = maxp;
	}
	
	public void setMaxPunteggio() {
		maxPunteggio = punteggioDomandeMax * domande.size();
	}

	public double getNumeroDomande() {
		return numeroDomande;
	}
	
	public void setNumeroDomande(int nDomande) {
		numeroDomande = nDomande;
	}

	public void setNumeroDomande() {
		numeroDomande = domande.size();
	}

	public double getPunteggioDomandeMin() {
		return punteggioDomandeMin;
	}

	public void setPunteggioDomandeMin(double punteggioDomandeMin) {
		this.punteggioDomandeMin = punteggioDomandeMin;
	}

	public double getPunteggioDomandeMax() {
		return punteggioDomandeMax;
	}

	public void setPunteggioDomandeMax(double punteggioDomandeMax) {
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

	public int getProprietario() {
		return proprietario;
	}

	public void setProprietario(int proprietario) {
		this.proprietario = proprietario;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	@Override
	public String toString() {
		return "Nome Test=" + titolo + ", Punteggio Massimo=" + maxPunteggio + ", Numero Domande="
				+ numeroDomande + ", punteggio Domande Min=" + punteggioDomandeMin + ", punteggio Domande Max="
				+ punteggioDomandeMax + ", Materia=" + categoria + "]";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
}
