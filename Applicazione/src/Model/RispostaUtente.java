package Model;

import DAO.RispostaUtenteDAO;

public class RispostaUtente {
	
	private int idRispostaUtente;
	private IstanzaDiTest idIstanzaDiTest;
	private Domanda idDomanda;
	private String testoRisposta;
	private int punteggio;
	private RispostaUtenteDAO rUdao;
	
	public RispostaUtente() {
		rUdao = new RispostaUtenteDAO();
		this.idRispostaUtente = rUdao.getMaxId();
	}
	
	public int getIdRispostaUtente() {
		return idRispostaUtente;
	}
	
	public void setIdRispostaUtente(int idRispostaUtente) {
		this.idRispostaUtente = idRispostaUtente;
	}
	
	public IstanzaDiTest getIdIstanzaDiTest() {
		return idIstanzaDiTest;
	}
	
	public void setIdIstanzaDiTest(IstanzaDiTest idIstanzaDiTest) {
		this.idIstanzaDiTest = idIstanzaDiTest;
	}
	
	public Domanda getIdDomanda() {
		return idDomanda;
	}
	
	public void setIdDomanda(Domanda idDomanda) {
		this.idDomanda = idDomanda;
	}
		
	public String getTestoRisposta() {
		return testoRisposta;
	}
	
	public void setTestoRisposta(String testoRisposta) {
		this.testoRisposta = testoRisposta;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
	
}
