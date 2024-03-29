package Controller;

import Model.*;
import java.util.ArrayList;
import DAO.*;


public class Controller {
	
	private Studente s = new Studente();
	private Insegnante i = new Insegnante();
	private Test newTest = new Test();
	private Test testInCorso = new Test();
	private IstanzaDiTest IdT;
	private String ruolo;
	private Domanda[] domandeTest;
	private ArrayList<Insegnante> docenti = new ArrayList<Insegnante>();
	private ArrayList<Test> testInseriti = new ArrayList<Test>();
	private boolean registerSuccesful;
	public boolean fromRegister=false;
	public boolean back=false;

	/*----------------------------------------------------------REGISTRAZIONE E FUNZIONI LOGOUT UTENTI------------------------------------------*/
		
	public void registrazione (String nome, String cognome,String password, String email){
		StudenteDAO sdao = new StudenteDAO();
		InsegnanteDAO idao = new InsegnanteDAO();
		if(email.contains("@Studenti.Universita.it")) {
			s.setIdStudente(sdao.getMaxId()+1);
			s.setNome(nome);
			s.setCognome(cognome);
			s.setEmail(email);
			s.setPassword(password);
			sdao.register(s);
		}
		if(email.contains("@Docenti.Universita.it")) {
			i.setIdDocente(idao.getMaxId()+1);
			i.setNome(nome);
			i.setCognome(cognome);
			i.setEmail(email);
			i.setPassword(password);
			idao.register(i);
		}
	}	
	
	public void logout(Studente s) {
		s = null;
	}
	
	public void logout(Insegnante i) {
		i = null;
	}
	
	public Studente getStudente() {
		return s;
	}
	
	public Insegnante getDocente() {
		return i;
	}
	
	public ArrayList<Insegnante> getDocenti(){
		return docenti;
	}
	
	public ArrayList<Test> getTestInseriti(){
		return testInseriti;
	}
	
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*--------------------------------------------------PRELEVA E SETTA IL RUOLO DEGLI UTENTI--------------------------------------------------*/
	public void setRuolo(String ruolo) {
		this.ruolo=ruolo;
	}	
	public String getRuolo() {
		return ruolo;
	}
	/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*-------------------------------------------------SETTA I DATI DEGLI UTENTI NELLE FINESTRE------------------------------------------------*/
	public String emailS() {
		return s.getEmail();
	}	
	public String emailD() {
		return i.getEmail();
	}	
	public String nomeS() {
		return s.getNome();
	}	
	public String cognomeS() {
		return s.getCognome();
	}	
	public String nomeD() {
		return i.getNome();
	}	
	public String cognomeD() {
		return i.getCognome();
	}
	
	public ArrayList<Test> getTestDocente(){
		return i.getTestCreati();
	}
	
	public void addTest(Test t) {
		i.addTest(t);
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*------------------------------------------------CONTROLLA LO STATO DELLA REGISTRAZIONE-----------------------------------------------------*/
	public void setRegisterSuccesful(boolean registerSuccesful) {
		this.registerSuccesful=registerSuccesful;
		fromRegister=true;
	}
	public boolean getregisterSuccesful() {
		return registerSuccesful;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*-----------------------------------------------------------COLLOQUIO CON IL DB-------------------------------------------------------------*/
	
	public void setUtente(String email,String pass) {
		StudenteDAO student = new StudenteDAO();
		InsegnanteDAO insegnante = new InsegnanteDAO();
		if (email.contains("@Studenti.Universita.it")) {
			s = student.login(email, pass);
			ruolo = "Studente";
		}
		else {
			i = insegnante.login(email, pass);
			ruolo = "Insegnante";
		}
	}
	
	public boolean checkUtente() {
		if(s.getNome() == null && i.getNome() == null) {
			return false;
		}
		else 
			return true;
	}
	
	public void caricaDocenti() {
		InsegnanteDAO idao = new InsegnanteDAO();
		docenti = idao.getListaDocenti();
	}
	
	public void caricaTest() {
		TestDAO tdao = new TestDAO();
		testInseriti = tdao.getTest();
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------*/
		
	/*-------------------------------------------------------------GESTIONE DEI TEST--------------------------------------------------------------*/
	
	public void creaTest() {
		TestDAO tdao = new TestDAO();
		newTest.setIdTest(tdao.getMaxId()+1);
	}
	
	public double getNumDomande() {
		return newTest.getNumeroDomande();
	}
	
	public void submitCreaTest() {
		newTest.setProprietario(i.getIdDocente());
	}
	
	public Test getTest() {
		return newTest;
	}
	
	public void resetTest() {
		newTest = null;
	}

	public IstanzaDiTest getIstanzaDiTest() {
		return IdT;
	}
	
	public void setIstanzaDiTest(Test t,Studente s) {
		IdT = new IstanzaDiTest(t,s);
		IstanzaDiTestDAO IdTDAO = new IstanzaDiTestDAO(this);
		IdT.setIdIstanza(IdTDAO.getMaxId());
	}
	
	public void setIstanzaDiTest(IstanzaDiTest IdT) {
		this.IdT = IdT; 
	}
	
	public void resetIstanzaDiTest() {
		this.IdT.setPunteggio(0);
		this.IdT.setStato(IstanzaDiTest.InFaseDiValutazione);
	}

	public Test getTestInCorso() {
		return testInCorso;
	}

	public void setTestInCorso(Test testInCorso) {
		this.testInCorso = testInCorso;
		DomandaDAO ddao = new DomandaDAO();
		testInCorso.setDomande(ddao.getDomandeTest(testInCorso.getIdTest()));
		System.out.println("Domande caricate: "+testInCorso.getDomande().size());
	}

	public Domanda[] getDomandeTest() {
		return domandeTest;
	}

	public void setDomandeTest(Domanda[] domandeTest){
		this.domandeTest = domandeTest;
	}
	
	public void caricaDomandeTest() {
		domandeTest = new Domanda[testInCorso.getDomande().size()];
		int i = 0;
		RispostaDAO rdao = new RispostaDAO();
		for (Domanda d: testInCorso.getDomande()) {
			domandeTest[i] = d;
			System.out.println("Controllo domanda - id: "+domandeTest[i].getIdDomanda()+" Testo: "+domandeTest[i].getTestoDomanda());
			if (domandeTest[i].getTipo().contentEquals("Multipla"))
				domandeTest[i].setRisposte(rdao.getRisposte(d));
			i++;
		}
		
	}
	
	/*--------------------------------------------------------------------------------------------------------------------------------------------*/
}
