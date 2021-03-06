package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import DAO.DomandaDAO;
import DAO.RispostaDAO;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import GUI.*;
import Model.Domanda;
import Model.Risposta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

@SuppressWarnings({"serial" , "unused"})
public class AggiungiDomanda extends JFrame {

	private JPanel contentPane;
	private JPanel panelDMultipla;
	private JPanel panelDAperta;
	private JRadioButton selectRispostaAperta;
	private JRadioButton selectRispostaMultipla;
	private JRadioButton selectVero;
	private JRadioButton selectFalso;
	private JRadioButton selectGiusta1;
	private JRadioButton selectGiusta2;
	private JRadioButton selectGiusta3;
	private JRadioButton selectGiusta4;
	private JRadioButton selectGiusta5;
	private JPanel panelVoF;
	private JPanel risposta1Panel;
	private JPanel risposta2Panel;
	private JPanel risposta3Panel;
	private JPanel risposta4Panel;
	private JPanel risposta5Panel;
	private JFrame frame;
	private Controller controller;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AggiungiDomanda(Controller c, JFrame guiCreaTest) {
		setResizable(false);
		setBounds(100, 100, 692, 514);
		Dimension dim = getToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		this.setLocationRelativeTo(null);
		setTitle("Legnarino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AggiungiDomanda.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame = this;
		controller = c;
		
		panelDMultipla = new JPanel();
		panelDMultipla.setBackground(Color.WHITE);
		panelDMultipla.setBounds(0, 59, 666, 373);
		contentPane.add(panelDMultipla);
		panelDMultipla.setLayout(null);
		panelDMultipla.setVisible(false);
		
		JLabel lblTestoDomandaM = new JLabel("Testo Domanda");
		lblTestoDomandaM.setBounds(10, 5, 128, 20);
		lblTestoDomandaM.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelDMultipla.add(lblTestoDomandaM);
		
		JEditorPane testoDomandaM = new JEditorPane();
		testoDomandaM.setFont(new Font("Tahoma", Font.PLAIN, 13));
		testoDomandaM.setBounds(148, 5, 508, 61);
		panelDMultipla.add(testoDomandaM);
		testoDomandaM.setBorder(BorderFactory.createLineBorder(Color.black));

		/*----------------------------------------------------SELEZIONE NUMERO RISPOSTE------------------------------------------------------------*/
		
		JLabel lblNumRisposte = new JLabel("Numero Risposte");
		lblNumRisposte.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumRisposte.setBounds(10, 69, 139, 23);
		panelDMultipla.add(lblNumRisposte);
		
		JComboBox numRisposte = new JComboBox();
		numRisposte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				risposta1Panel.setVisible(false);
				risposta2Panel.setVisible(false);
				risposta3Panel.setVisible(false);
				risposta4Panel.setVisible(false);
				risposta5Panel.setVisible(false);
				panelVoF.setVisible(false);
				selectGiusta1.setSelected(false);
				selectGiusta2.setSelected(false);
				selectGiusta3.setSelected(false);
				selectGiusta4.setSelected(false);
				selectGiusta5.setSelected(false);
				selectVero.setSelected(false);
				selectFalso.setSelected(false);
				if(numRisposte.getItemAt(numRisposte.getSelectedIndex())=="Vero o Falso")
					panelVoF.setVisible(true);
				if(numRisposte.getItemAt(numRisposte.getSelectedIndex())=="3 Risposte") {
					risposta1Panel.setVisible(true);
					risposta2Panel.setVisible(true);
					risposta3Panel.setVisible(true);
				}
				if(numRisposte.getItemAt(numRisposte.getSelectedIndex())=="4 Risposte") {
					risposta1Panel.setVisible(true);
					risposta2Panel.setVisible(true);
					risposta3Panel.setVisible(true);
					risposta4Panel.setVisible(true);
				}
				if(numRisposte.getItemAt(numRisposte.getSelectedIndex())=="5 Risposte") {
					risposta1Panel.setVisible(true);
					risposta2Panel.setVisible(true);
					risposta3Panel.setVisible(true);
					risposta4Panel.setVisible(true);
					risposta5Panel.setVisible(true);
				}
			}
		});
		numRisposte.setFont(new Font("Tahoma", Font.BOLD, 13));
		numRisposte.setModel(new DefaultComboBoxModel(new String[] {"Vero o Falso", "3 Risposte", "4 Risposte", "5 Risposte"}));
		numRisposte.setBackground(Color.WHITE);
		numRisposte.setBounds(158, 69, 139, 25);
		panelDMultipla.add(numRisposte);
		
		JPanel panelRisposte = new JPanel();
		panelRisposte.setBackground(Color.WHITE);
		panelRisposte.setBounds(10, 103, 646, 259);
		panelDMultipla.add(panelRisposte);
		panelRisposte.setLayout(null);
		
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*-----------------------------------------------------FORM RISPOSTA 1---------------------------------------------------------------------*/
		
		risposta1Panel = new JPanel();
		risposta1Panel.setBackground(Color.WHITE);
		risposta1Panel.setBounds(10, 11, 626, 44);
		panelRisposte.add(risposta1Panel);
		risposta1Panel.setLayout(null);
		risposta1Panel.setVisible(false);
		
		JLabel lblRisposta1 = new JLabel("Risposta 1");
		lblRisposta1.setBounds(10, 0, 68, 22);
		risposta1Panel.add(lblRisposta1);
		lblRisposta1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JEditorPane risposta1Text = new JEditorPane();
		risposta1Text.setFont(new Font("Tahoma", Font.PLAIN, 13));
		risposta1Text.setBounds(80, 2, 536, 39);
		risposta1Panel.add(risposta1Text);
		risposta1Text.setBorder(BorderFactory.createLineBorder(Color.black));
		
		selectGiusta1 = new JRadioButton("Giusta");
		selectGiusta1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectGiusta2.isSelected() || selectGiusta3.isSelected() || selectGiusta4.isSelected() || selectGiusta5.isSelected()) {
					selectGiusta2.setSelected(false);
					selectGiusta3.setSelected(false);
					selectGiusta4.setSelected(false);
					selectGiusta5.setSelected(false);
				}
			}
		});
		selectGiusta1.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectGiusta1.setBackground(Color.WHITE);
		selectGiusta1.setBounds(6, 24, 72, 17);
		risposta1Panel.add(selectGiusta1);
		
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*-----------------------------------------------------FORM RISPOSTA 2---------------------------------------------------------------------*/
		
		risposta2Panel = new JPanel();
		risposta2Panel.setLayout(null);
		risposta2Panel.setBackground(Color.WHITE);
		risposta2Panel.setBounds(10, 59, 626, 44);
		panelRisposte.add(risposta2Panel);
		risposta2Panel.setVisible(false);
		
		JLabel lblRisposta2 = new JLabel("Risposta 2");
		lblRisposta2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRisposta2.setBounds(10, 0, 68, 22);
		risposta2Panel.add(lblRisposta2);
		
		JEditorPane risposta2Text = new JEditorPane();
		risposta2Text.setFont(new Font("Tahoma", Font.PLAIN, 13));
		risposta2Text.setBorder(BorderFactory.createLineBorder(Color.black));
		risposta2Text.setBounds(80, 2, 536, 39);
		risposta2Panel.add(risposta2Text);
		
		selectGiusta2 = new JRadioButton("Giusta");
		selectGiusta2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectGiusta1.isSelected() || selectGiusta3.isSelected() || selectGiusta4.isSelected() || selectGiusta5.isSelected()) {
					selectGiusta1.setSelected(false);
					selectGiusta3.setSelected(false);
					selectGiusta4.setSelected(false);
					selectGiusta5.setSelected(false);
				}
			}
		});
		selectGiusta2.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectGiusta2.setBackground(Color.WHITE);
		selectGiusta2.setBounds(6, 20, 72, 17);
		risposta2Panel.add(selectGiusta2);
		
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*-----------------------------------------------------FORM RISPOSTA 3---------------------------------------------------------------------*/
		
		risposta3Panel = new JPanel();
		risposta3Panel.setLayout(null);
		risposta3Panel.setBackground(Color.WHITE);
		risposta3Panel.setBounds(10, 112, 626, 44);
		panelRisposte.add(risposta3Panel);
		risposta3Panel.setVisible(false);
		
		JLabel lblRisposta3 = new JLabel("Risposta 3");
		lblRisposta3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRisposta3.setBounds(10, 0, 68, 22);
		risposta3Panel.add(lblRisposta3);
		
		JEditorPane risposta3Text= new JEditorPane();
		risposta3Text.setFont(new Font("Tahoma", Font.PLAIN, 13));
		risposta3Text.setBorder(BorderFactory.createLineBorder(Color.black));
		risposta3Text.setBounds(80, 2, 536,39);
		risposta3Panel.add(risposta3Text);
		
		selectGiusta3 = new JRadioButton("Giusta");
		selectGiusta3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectGiusta2.isSelected() || selectGiusta1.isSelected() || selectGiusta4.isSelected() || selectGiusta5.isSelected()) {
					selectGiusta2.setSelected(false);
					selectGiusta1.setSelected(false);
					selectGiusta4.setSelected(false);
					selectGiusta5.setSelected(false);
				}
			}
		});
		selectGiusta3.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectGiusta3.setBackground(Color.WHITE);
		selectGiusta3.setBounds(6, 20, 72, 17);
		risposta3Panel.add(selectGiusta3);
		
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*-----------------------------------------------------FORM RISPOSTA 4---------------------------------------------------------------------*/
		
		risposta4Panel = new JPanel();
		risposta4Panel.setLayout(null);
		risposta4Panel.setBackground(Color.WHITE);
		risposta4Panel.setBounds(10, 162, 626, 44);
		panelRisposte.add(risposta4Panel);
		risposta4Panel.setVisible(false);
		
		JLabel lblRisposta4 = new JLabel("Risposta 4");
		lblRisposta4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRisposta4.setBounds(10, 0, 68, 22);
		risposta4Panel.add(lblRisposta4);
		
		JEditorPane risposta4Text = new JEditorPane();
		risposta4Text.setFont(new Font("Tahoma", Font.PLAIN, 13));
		risposta4Text.setBorder(BorderFactory.createLineBorder(Color.black));
		risposta4Text.setBounds(80, 2, 536, 39);
		risposta4Panel.add(risposta4Text);
		
		selectGiusta4 = new JRadioButton("Giusta");
		selectGiusta4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectGiusta2.isSelected() || selectGiusta3.isSelected() || selectGiusta1.isSelected() || selectGiusta5.isSelected()) {
					selectGiusta2.setSelected(false);
					selectGiusta3.setSelected(false);
					selectGiusta1.setSelected(false);
					selectGiusta5.setSelected(false);
				}
			}
		});
		selectGiusta4.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectGiusta4.setBackground(Color.WHITE);
		selectGiusta4.setBounds(6, 20, 72, 17);
		risposta4Panel.add(selectGiusta4);
		
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*-----------------------------------------------------FORM RISPOSTA 5---------------------------------------------------------------------*/
		
		risposta5Panel = new JPanel();
		risposta5Panel.setLayout(null);
		risposta5Panel.setBackground(Color.WHITE);
		risposta5Panel.setBounds(10, 215, 626, 44);
		panelRisposte.add(risposta5Panel);
		risposta5Panel.setVisible(false);
		
		JLabel lblRisposta5 = new JLabel("Risposta 5");
		lblRisposta5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRisposta5.setBounds(10, 0, 68, 22);
		risposta5Panel.add(lblRisposta5);
		
		JEditorPane risposta5Text = new JEditorPane();
		risposta5Text.setFont(new Font("Tahoma", Font.PLAIN, 13));
		risposta5Text.setBorder(BorderFactory.createLineBorder(Color.black));
		risposta5Text.setBounds(80, 2, 536, 39);
		risposta5Panel.add(risposta5Text);
		
		selectGiusta5 = new JRadioButton("Giusta");
		selectGiusta5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectGiusta2.isSelected() || selectGiusta3.isSelected() || selectGiusta4.isSelected() || selectGiusta1.isSelected()) {
					selectGiusta2.setSelected(false);
					selectGiusta3.setSelected(false);
					selectGiusta4.setSelected(false);
					selectGiusta1.setSelected(false);
				}
			}
		});
		selectGiusta5.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectGiusta5.setBackground(Color.WHITE);
		selectGiusta5.setBounds(6, 20, 72, 17);
		risposta5Panel.add(selectGiusta5);
		
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*-------------------------------------------------------SELEZIONE VERO O FALSO------------------------------------------------------------*/
		
		panelVoF = new JPanel();
		panelVoF.setBackground(Color.WHITE);
		panelVoF.setBounds(308, 69, 109, 25);
		panelDMultipla.add(panelVoF);
		panelVoF.setLayout(null);
		panelVoF.setVisible(false);
		
		selectVero = new JRadioButton("Vero");
		selectVero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectFalso.isSelected())
					selectFalso.setSelected(false);
			}
		});
		selectVero.setBounds(0, 0, 51, 23);
		selectVero.setBackground(Color.WHITE);
		selectVero.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelVoF.add(selectVero);
		
		selectFalso = new JRadioButton("Falso");
		selectFalso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectVero.isSelected())
					selectVero.setSelected(false);
			}
		});
		selectFalso.setBounds(53, 0, 55, 23);
		selectFalso.setBackground(Color.WHITE);
		selectFalso.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelVoF.add(selectFalso);
		
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*-------------------------------------------------------------PANEL DOMANDA APERTA--------------------------------------------------------*/
		
		panelDAperta = new JPanel();
		panelDAperta.setBackground(Color.WHITE);
		panelDAperta.setBounds(0, 59, 666, 72);
		contentPane.add(panelDAperta);
		panelDAperta.setLayout(null);
		panelDAperta.setVisible(false);
		
		JLabel lblTestoDomandaA = new JLabel("Testo Domanda");
		lblTestoDomandaA.setBounds(10, 5, 128, 20);
		lblTestoDomandaA.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelDAperta.add(lblTestoDomandaA);
		
		JEditorPane testoDomandaA = new JEditorPane();
		testoDomandaA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		testoDomandaA.setBounds(148, 5, 508, 56);
		panelDAperta.add(testoDomandaA);
		testoDomandaA.setBorder(BorderFactory.createLineBorder(Color.black));
		
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*---------------------------------------------------------PANEL SELEZIONE TIPO DOMANDA----------------------------------------------------*/
		
		JPanel panelSelectDomanda = new JPanel();
		panelSelectDomanda.setBackground(Color.WHITE);
		panelSelectDomanda.setBounds(361, 12, 296, 37);
		contentPane.add(panelSelectDomanda);
		
		selectRispostaMultipla = new JRadioButton("Risposta Multipla");
		selectRispostaMultipla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectRispostaAperta.isSelected())
					selectRispostaAperta.setSelected(false);
				panelDAperta.setVisible(false);
				panelDMultipla.setVisible(true);
			}
		});
		selectRispostaMultipla.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelSelectDomanda.add(selectRispostaMultipla);
		selectRispostaMultipla.setBackground(Color.WHITE);
				
		selectRispostaAperta = new JRadioButton("Risposta Aperta");
		selectRispostaAperta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectRispostaMultipla.isSelected())
					selectRispostaMultipla.setSelected(false);
				panelDAperta.setVisible(true);
				panelDMultipla.setVisible(false);				
			}
		});
		selectRispostaAperta.setBackground(Color.WHITE);
		selectRispostaAperta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelSelectDomanda.add(selectRispostaAperta);
		
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*------------------------------------------------------TITOLO FINESTRA--------------------------------------------------------------------*/
		
		JPanel panelTitolo = new JPanel();
		panelTitolo.setBackground(Color.WHITE);
		panelTitolo.setBounds(10, 11, 294, 47);
		contentPane.add(panelTitolo);
		
		JLabel lblNewLabel = new JLabel("Aggiungi");
		lblNewLabel.setForeground(new Color(51, 102, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelTitolo.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Domanda");
		lblNewLabel_1.setForeground(new Color(255, 153, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelTitolo.add(lblNewLabel_1);
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*--------------------------------------------BUTTON AGGIUNGI DOMANDA E ANNULLA------------------------------------------------------------*/
		JButton btnAddDomanda = new JButton("Aggiungi Domanda");
		btnAddDomanda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAddDomanda.setForeground(new Color(51, 102, 255));
				btnAddDomanda.setBackground(new Color(255, 153, 0));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnAddDomanda.setForeground(new Color(255, 153, 0));
				btnAddDomanda.setBackground(new Color(51, 102, 255));
				Domanda d = new Domanda();
				DomandaDAO ddao = new DomandaDAO();
				if (selectRispostaAperta.isSelected()) {
					d.setTestoDomanda(testoDomandaA.getText());
					d.setTipo("Aperta");
					d.setIdDomanda(ddao.getMaxId()+1);
					d.setIdTest(controller.getTest().getIdTest());
				}
				if(selectRispostaMultipla.isSelected()) {
					d.setTestoDomanda(testoDomandaM.getText());
					d.setTipo("Multipla");
					d.setIdDomanda(ddao.getMaxId()+1);
					d.setIdTest(controller.getTest().getIdTest());
					ArrayList<Risposta> risposte = new ArrayList<Risposta>();
					RispostaDAO rdao = new RispostaDAO();
					if (numRisposte.getItemAt(numRisposte.getSelectedIndex())=="Vero o Falso") 
						for (int i=0;i<2;i++) {
							Risposta r = new Risposta();
							if(i==0) {
								r.setTestoRisposta("Vero");
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectVero.isSelected());
								risposte.add(r);
							}
							else {
								r.setTestoRisposta("Falso");
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectFalso.isSelected());
								risposte.add(r);
							}
						}
					if(numRisposte.getItemAt(numRisposte.getSelectedIndex())=="3 Risposte")
						for (int i=0;i<3;i++) {
							Risposta r = new Risposta();
							if (i==0) {
								r.setTestoRisposta(risposta1Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta1.isSelected());
								risposte.add(r);
							}
							if (i==1) {
								r.setTestoRisposta(risposta2Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta2.isSelected());
								risposte.add(r);
							}
							if (i==2) {
								r.setTestoRisposta(risposta3Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta3.isSelected());
								risposte.add(r);
							}
						}
					if(numRisposte.getItemAt(numRisposte.getSelectedIndex())=="4 Risposte")
						for (int i=0;i<4;i++) {
							Risposta r = new Risposta();
							if (i==0) {
								r.setTestoRisposta(risposta1Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta1.isSelected());
								risposte.add(r);
							}
							if (i==1) {
								r.setTestoRisposta(risposta2Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta2.isSelected());
								risposte.add(r);
							}
							if (i==2) {
								r.setTestoRisposta(risposta3Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta3.isSelected());
								risposte.add(r);
							}
							if (i==3) {
								r.setTestoRisposta(risposta4Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta4.isSelected());
								risposte.add(r);
							}
						}
					if(numRisposte.getItemAt(numRisposte.getSelectedIndex())=="5 Risposte")
						for (int i=0;i<5;i++) {
							Risposta r = new Risposta();
							if (i==0) {
								r.setTestoRisposta(risposta1Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta1.isSelected());
								risposte.add(r);
							}
							if (i==1) {
								r.setTestoRisposta(risposta2Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta2.isSelected());
								risposte.add(r);
							}
							if (i==2) {
								r.setTestoRisposta(risposta3Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta3.isSelected());
								risposte.add(r);
							}
							if (i==3) {
								r.setTestoRisposta(risposta4Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta4.isSelected());
								risposte.add(r);
							}
							if (i==4) {
								r.setTestoRisposta(risposta5Text.getText());
								r.setIdRisposta(rdao.getMaxId()+1+i);
								r.setIdDomanda(ddao.getMaxId()+1);
								r.setCorretta(selectGiusta5.isSelected());
								risposte.add(r);
							}
						}
					d.setRisposte(risposte);
					rdao.saveRisposte(risposte);
				}
				ddao.saveDomanda(d);
				controller.getTest().addDomande(d);
				frame.setVisible(false);
				guiCreaTest.setVisible(true);
			}
		});
		btnAddDomanda.setBackground(new Color(51, 102, 255));
		btnAddDomanda.setForeground(new Color(255, 153, 0));
		btnAddDomanda.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddDomanda.setBounds(53, 432, 251, 32);
		contentPane.add(btnAddDomanda);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAnnulla.setForeground(new Color(51, 102, 255));
				btnAnnulla.setBackground(new Color(255, 153, 0));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnAnnulla.setForeground(new Color(255, 153, 0));
				btnAnnulla.setBackground(new Color(51, 102, 255));
				frame.setVisible(false);
				guiCreaTest.setVisible(true);
			}
		});
		btnAnnulla.setBackground(new Color(51, 102, 255));
		btnAnnulla.setForeground(new Color(255, 153, 0));
		btnAnnulla.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAnnulla.setBounds(361, 432, 251, 32);
		contentPane.add(btnAnnulla);
		
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	}
}
