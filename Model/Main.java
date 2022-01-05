package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
        Insegnante ins1 = new Insegnante("Porfirio", "Tramontana", "TestaDiCazzo", "muori", "tramontana@docenti.unina.it");
        Studente stu1 = new Studente("Antonio", "Legnante", "Fallimento", "muoriTramontana", "anto.legnante@unina.it");
        Test test1 = new Test(0, 10, 5, 0, 2, "Storia", ins1);
        
        ArrayList<Domanda> domanda = new ArrayList(); 
        for(int i = 0; i < test1.getNumeroDomande(); i++)
        {
        	System.out.println("1)domanda a risposta aperta 2)domanda a risposta chiusa");
			int scelta = input.nextInt();
			
			System.out.println("Inserisci il testo della domanda");
			if(scelta == 1)
			{
				String testoDomanda = input.nextLine();
				domanda.add(new RispostaChiusa(testoDomanda, test1));
				System.out.println(domanda.get(i).getTestoDomanda());
			}
			else
			{
				String testoDomanda = input.nextLine();
				domanda.add(new RispostaChiusa(testoDomanda, test1));
				System.out.println("Inserisci le possibili risposte");
				while(true)
				{
					String testoRisposta = input.nextLine();
				    ((RispostaMultipla) domanda.get(i)).getRisposte().add(testoRisposta);
				    System.out.println("1)Inserisci altre risposte 2)Salva");
				    scelta = input.nextInt();
				    if(scelta == 2)
				    	break;
				}
			}
        }
        
        IstanzaDiTest istanza1 = new IstanzaDiTest(test1, stu1);
        //istanza1.setRisposte();
        
	}

}
