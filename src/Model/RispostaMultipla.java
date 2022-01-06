package Model;

import java.util.ArrayList;

public class RispostaMultipla extends Domanda {
    private ArrayList<String> risposte;

	public RispostaMultipla(String testoDomanda, Test idTest) {
		super(testoDomanda, idTest);
	}
	
	public ArrayList<String> getRisposte() {
		return risposte;
	}

	public void setRisposte(ArrayList<String> risposte) {
		this.risposte = risposte;
	}
    
}
