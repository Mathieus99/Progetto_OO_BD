package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import Controller.Controller;
import DAO.RispostaDAO;
import Model.RispostaUtente;

public class DoTest {
	
	JFrame frame;
	private Controller c;
	private int stato = 0;
	private JTextPane txtRispostaAperta;
	private String[] risposte;
	private RispostaUtente rU;
	
	public DoTest(Controller c) {
		this.c = c;
		c.caricaDomandeTest();
		initialize();
		frame.setVisible(true);
	}
	
	public void initialize() {
		frame = new JFrame();
		
		int lunghezzaTest = c.getDomandeTest().length;
		for (int i=0;i<c.getDomandeTest()[stato].getRisposte().size();i++)
			risposte[i] = c.getDomandeTest()[stato].getRisposte().get(i).getTestoRisposta();
		
		//Creazione del frame -------------------------------------------------------------------------------------------------------
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setAlwaysOnTop(true);
		frame.setTitle("Legnarino");
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(DoTest.class.getResource("/Immagini/Legnarino_icon2.png")));
		frame.setBounds(100, 100, 812, 421);
		frame.getContentPane().setLayout(null);
		//---------------------------------------------------------------------------------------------------------------------------
		
		JButton btnAvanti = new JButton("Avanti");
		btnAvanti.setBackground(Color.WHITE);
		btnAvanti.setForeground(new Color(51, 102, 255));
		btnAvanti.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAvanti.setIcon(new ImageIcon(DoTest.class.getResource("/Immagini/Freccia_Avanit.png")));
		btnAvanti.setBounds(617, 290, 171, 83);
		frame.getContentPane().add(btnAvanti);
		
		JPanel panelDomanda = new JPanel();
		panelDomanda.setBounds(10, 10, 167, 41);
		frame.getContentPane().add(panelDomanda);
		
		JLabel lblDomanda = new JLabel("Domanda");
		lblDomanda.setForeground(new Color(255, 153, 0));
		lblDomanda.setFont(new Font("Tahoma", Font.BOLD, 25));
		panelDomanda.add(lblDomanda);
		
		JLabel lblNumDomanda = new JLabel();
		lblNumDomanda.setForeground(new Color(51, 102, 255));
		lblNumDomanda.setFont(new Font("Tahoma", Font.BOLD, 25));
		panelDomanda.add(lblNumDomanda);
		
		JEditorPane txtDomanda = new JEditorPane();
		txtDomanda.setText(c.getDomandeTest()[stato].getTestoDomanda());
		txtDomanda.setEditable(false);
		txtDomanda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDomanda.setBounds(10, 60, 778, 71);
		frame.getContentPane().add(txtDomanda);
		
		JPanel panelRispostaMultipla = new JPanel();
		panelRispostaMultipla.setBounds(10, 142, 778, 137);
		panelRispostaMultipla.setLayout(null);
		frame.getContentPane().add(panelRispostaMultipla);
		
		JRadioButton A = new JRadioButton("A");
		A.setFont(new Font("Tahoma", Font.PLAIN, 13));
		A.setBounds(6, 7, 766, 23);
		panelRispostaMultipla.add(A);
		
		JRadioButton B = new JRadioButton("B");
		B.setFont(new Font("Tahoma", Font.PLAIN, 13));
		B.setBounds(6, 33, 766, 23);
		panelRispostaMultipla.add(B);
		
		JRadioButton C = new JRadioButton("C");
		C.setFont(new Font("Tahoma", Font.PLAIN, 13));
		C.setBounds(6, 59, 766, 23);
		panelRispostaMultipla.add(C);
		
		JRadioButton D = new JRadioButton("D");
		D.setFont(new Font("Tahoma", Font.PLAIN, 13));
		D.setBounds(6, 85, 766, 23);
		panelRispostaMultipla.add(D);
		
		JRadioButton E = new JRadioButton("E");
		E.setFont(new Font("Tahoma", Font.PLAIN, 13));
		E.setBounds(6, 111, 766, 23);
		panelRispostaMultipla.add(E);
		
		txtRispostaAperta = new JTextPane();
		txtRispostaAperta.setBounds(10, 154, 778, 71);
		txtRispostaAperta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRispostaAperta.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(txtRispostaAperta);
		
		if(c.getDomandeTest()[0].getRisposte().size() == 0) {
			panelRispostaMultipla.setVisible(false);
			txtRispostaAperta.setVisible(true);
		} else {
			if(risposte[0].equals(""))
				A.setVisible(false);
			else {
				A.setVisible(true);
				A.setText(risposte[0]);
			}
			if(risposte[1].equals(""))
				B.setVisible(false);
			else {
				B.setVisible(true);
				B.setText(risposte[1]);
			}
			if(risposte[2].equals(""))
				C.setVisible(false);
			else {
				C.setVisible(true);
				C.setText(risposte[2]);
			}
			if(risposte[3].equals(""))
				D.setVisible(false);
			else {
				D.setVisible(true);
				D.setText(risposte[3]);
			}
			if(risposte[4].equals(""))
				E.setVisible(false);
			else {
				E.setVisible(true);
				E.setText(risposte[4]);
			}
			if (A.isVisible()) {
				panelRispostaMultipla.setVisible(true);
				txtRispostaAperta.setVisible(false);
			}
		}
		
		btnAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(c.getDomandeTest()[stato].getRisposte().size()==0) {
					rU = new RispostaUtente();
					rU.setIdIstanzaDiTest(c.getIstanzaDiTest());
					rU.setIdDomanda(c.getDomandeTest()[stato]);
					rU.setTestoRisposta(txtRispostaAperta.getText());
					c.getIstanzaDiTest().addRispostaUtente(rU);
					stato++;
					
					if(checkStatoTest(stato, lunghezzaTest)) {
						stato--;
						frame.setVisible(false);
						c.getIstanzaDiTest().setOrarioFine(System.currentTimeMillis());
						//Fase di "Test Completato"
						return;
					}
					
					txtDomanda.setText(c.getDomandeTest()[stato].getTestoDomanda());
					if(c.getDomandeTest()[stato].getRisposte().size()==0) {
						panelRispostaMultipla.setVisible(false);
						A.setVisible(false);
						B.setVisible(false);
						C.setVisible(false);
						D.setVisible(false);
						E.setVisible(false);
						txtRispostaAperta.setVisible(true);
					}
					else {
						panelRispostaMultipla.setVisible(true);
						for(int i=0;i<c.getDomandeTest()[stato].getRisposte().size();i++)
							risposte[i] = c.getDomandeTest()[stato].getRisposte().get(i).getTestoRisposta();
						if(risposte[0].equals("")) {
							A.setVisible(false);
							A.setText(risposte[0]);
						}
						else {
							A.setVisible(true);
							A.setText(risposte[0]);
						}
						if(risposte[1].equals("")) {
							B.setVisible(false);
							B.setText(risposte[1]);
						}
						else {
							B.setVisible(true);
							B.setText(risposte[1]);
						}
						if(risposte[2].equals("")) {
							C.setVisible(false);
							C.setText(risposte[2]);
						}
						else {
							C.setVisible(true);
							C.setText(risposte[2]);
						}
						if(risposte[3].equals("")) {
							D.setVisible(false);
							D.setText(risposte[3]);
						}
						else {
							D.setVisible(true);
							D.setText(risposte[3]);
						}
						if(risposte[4].equals("")) {
							E.setVisible(false);
							E.setText(risposte[4]);
						}
						else {
							E.setVisible(true);
							E.setText(risposte[4]);
						}
						txtRispostaAperta.setVisible(false);
					}
				}
				else {
					String risposta = "";
					if(A.isSelected())
						risposta = A.getText();
					else if(B.isSelected())
						risposta = B.getText();
					else if(C.isSelected())
						risposta = C.getText();
					else if(D.isSelected())
						risposta = D.getText();
					else if(E.isSelected())
						risposta = E.getText();
					rU = new RispostaUtente();
					rU.setIdIstanzaDiTest(c.getIstanzaDiTest());
					rU.setIdDomanda(c.getDomandeTest()[stato]);
					rU.setTestoRisposta(risposta);
					RispostaDAO rdao = new RispostaDAO();
					c.getIstanzaDiTest().addRispostaUtente(rU);
					stato++;
					if(checkStatoTest(stato,lunghezzaTest)) {
						stato--;
						frame.setVisible(false);
						//Passaggio a "Test Completato"
						return;
					}
						
					txtDomanda.setText(c.getDomandeTest()[stato].getTestoDomanda());
						
					if(c.getDomandeTest()[stato].getRisposte().size()==0) {
						panelRispostaMultipla.setVisible(false);
						A.setVisible(false);
						B.setVisible(false);
						C.setVisible(false);
						D.setVisible(false);
						E.setVisible(false);
						txtRispostaAperta.setVisible(true);
					}
					else {
						panelRispostaMultipla.setVisible(true);
						A.setVisible(false);
						B.setVisible(false);
						C.setVisible(false);
						D.setVisible(false);
						E.setVisible(false);
						for(int i=0;i<c.getDomandeTest()[stato].getRisposte().size();i++)
							risposte[i] = c.getDomandeTest()[stato].getRisposte().get(i).getTestoRisposta();
						if(risposte[0].equals("")) {
							A.setVisible(false);
							A.setText(risposte[0]);
						}
						else {
							A.setVisible(true);
							A.setText(risposte[0]);
						}
						if(risposte[1].equals("")) {
							B.setVisible(false);
							B.setText(risposte[1]);
						}
						else {
							B.setVisible(true);
							B.setText(risposte[1]);
						}
						if(risposte[2].equals("")) {
							C.setVisible(false);
							C.setText(risposte[2]);
						}
						else {
							C.setVisible(true);
							C.setText(risposte[2]);
						}
						if(risposte[3].equals("")) {
							D.setVisible(false);
							D.setText(risposte[3]);
						}
						else {
							D.setVisible(true);
							D.setText(risposte[3]);
						}
						if(risposte[4].equals("")) {
							E.setVisible(false);
							E.setText(risposte[4]);
						}
						else {
							E.setVisible(true);
							E.setText(risposte[4]);
						}
						txtRispostaAperta.setVisible(false);
					}
				}
			}
		});
		
		A.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				B.setSelected(false);
				C.setSelected(false);
				D.setSelected(false);
				E.setSelected(false);
			}
		});
		
		B.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				A.setSelected(false);
				C.setSelected(false);
				D.setSelected(false);
				E.setSelected(false);
			}
		});
		
		C.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		B.setSelected(false);
				A.setSelected(false);
				D.setSelected(false);
				E.setSelected(false);
		 	}
		 });
		
		D.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		B.setSelected(false);
				C.setSelected(false);
				A.setSelected(false);
				E.setSelected(false);
		 	}
		 });
		
		E.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		B.setSelected(false);
				C.setSelected(false);
				D.setSelected(false);
				A.setSelected(false);
		 	}
		 });		
	}
	
	private boolean checkStatoTest(int stato, int lunghezzaTest) {
		if (stato == lunghezzaTest)
			return true;
		else
			return false;
	}
}
