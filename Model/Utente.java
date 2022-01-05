package Model;

public class Utente {
    private String nome;
    private String cognome;
    private String login;
    private String password;
    
	public Utente(String nome, String cognome, String login, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.login = login;
		this.password = password;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
    
    
}
