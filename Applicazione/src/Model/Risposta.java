package Model;

public class Risposta {
	
	private long idRisposta;
	private long idDomanda;
    private String testoRisposta;
    private boolean corretta;
    
	public String getTestoRisposta() {
		return testoRisposta;
	}

	public void setTestoRisposta(String testoRisposta) {
		this.testoRisposta = testoRisposta;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}

	public long getIdDomanda() {
		return idDomanda;
	}

	public void setIdDomanda(long l) {
		this.idDomanda = l;
	}
	
	public long getIdRisposta() {
		return idRisposta;
	}

	public void setIdRisposta(long l) {
		this.idRisposta = l;
	}    
}
