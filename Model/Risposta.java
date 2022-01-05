package Model;

public class Risposta {
    private String testoRisposta;
    private String stato;
    private IstanzaDiTest idIstanzaDiTest;
    private Domanda domandaRisposta;
    
    public final static String ERRATO = "ERRATO";
    public final static String CORRETTO = "CORRETTO";
    public final static String NON_VALUTATO = "NON VALUTATO";

	public Risposta(String testoRisposta, IstanzaDiTest idIstanzaDiTest, Domanda domandaRisposta) {
		super();
		this.testoRisposta = testoRisposta;
		this.stato = Risposta.NON_VALUTATO;
		this.idIstanzaDiTest = idIstanzaDiTest;
		this.domandaRisposta = domandaRisposta;
	}

	public String getTestoRisposta() {
		return testoRisposta;
	}

	public void setTestoRisposta(String testoRisposta) {
		this.testoRisposta = testoRisposta;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public IstanzaDiTest getIdIstanzaDiTest() {
		return idIstanzaDiTest;
	}

	public void setIdIstanzaDiTest(IstanzaDiTest idIstanzaDiTest) {
		this.idIstanzaDiTest = idIstanzaDiTest;
	}

	public Domanda getDomandaRisposta() {
		return domandaRisposta;
	}

	public void setDomandaRisposta(Domanda domandaRisposta) {
		this.domandaRisposta = domandaRisposta;
	}
    
    
}
