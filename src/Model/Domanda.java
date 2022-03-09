package Model;

public class Domanda {
    private String testoDomanda;
	private Test idTest;
    
	public Domanda(String testoDomanda, Test idTest) {
		super();
		this.testoDomanda = testoDomanda;
		this.idTest = idTest;
	}

	public String getTestoDomanda() {
		return testoDomanda;
	}

	public void setTestoDomanda(String testoDomanda) {
		this.testoDomanda = testoDomanda;
	}

	public Test getIdTest() {
		return idTest;
	}

	public void setIdTest(Test idTest) {
		this.idTest = idTest;
	}

}
