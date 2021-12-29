package Model;

public class Studente extends Utente {
	private String studenteEmail;

	public String getStudenteEmail() {
		return studenteEmail;
	}

	public void setStudenteEmail(String studenteEmail) {
		this.studenteEmail = studenteEmail;
	}
	
	public void registrazione (String n, String c, String l,String p,String e) {
		setNome(n);
		setCognome(c);
		setLogin(l);
		setPassword(p);
		setStudenteEmail(e);
	}
	
	public void visualizzaRisultati () {
		
	}
}
