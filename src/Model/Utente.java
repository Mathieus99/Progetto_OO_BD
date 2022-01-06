package Model;

public class Utente {
    private String nome;
    private String cognome;
    private String password;
    
	public Utente(String nome, String cognome, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
    
    
}
