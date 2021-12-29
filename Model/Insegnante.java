package Model;

public class Insegnante extends Utente {
	private String docenteEmail;
	
	public String getDocenteEmail() {
		return docenteEmail;
	}

	public void setDocenteEmail(String docenteEmail) {
		this.docenteEmail = docenteEmail;
	}
	
	public void registrazione (String n, String c, String l, String p, String e) {
		setNome(n);
		setCognome(c);
		setLogin(l);
		setPassword(p);
		setDocenteEmail(e);
	}
	
	public void valutazione () {
		
	}
	
}