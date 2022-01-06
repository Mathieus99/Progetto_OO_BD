package Controller;

import Model.Insegnante;
import Model.Studente;
import Model.Test;

public class Controller {
	
	// TODO creare Arraylist con tutti gli elementi presi dal DB
	
	private Studente s;
	private Insegnante d;
	private Test t;
	
	public void registraDocente (String nome, String cognome,String password, String email){
		if ()
		d.setNome(nome);
		d.setCognome(cognome);
		d.setPassword(password);
		d.setEmail(email);
	}
	
	public void registraStudente (String nome, String cognome, String password, String email, String matricola) {
		s.setNome(nome);
		s.setCognome(cognome);
		s.setPassword(password);
		s.setEmail(email);
		s.setMatricola(matricola);
	}
	
	
}
