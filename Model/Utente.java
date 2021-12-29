package Model;

public class Utente {
	protected String nome;
	protected String cognome;
	protected String login;
	protected String password;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public void registrazione (String n, String c, String l,String p) {
		setNome(n);
		setCognome(c);
		setLogin(l);
		setPassword(p);
	}	
}