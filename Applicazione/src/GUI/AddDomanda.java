package GUI;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import Controller.Controller;
import DAO.DomandaDAO;
import DAO.RispostaDAO;
import Model.Domanda;
import Model.Risposta;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@SuppressWarnings({"serial","unused"})
public class AddDomanda extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private Controller c;
	private JRadioButton selectRispostaAperta;
	private JRadioButton selectRispostaMultipla;
	private JRadioButton selectVeroGiusta;
	private JRadioButton selectFalsoGiusta;
	private JPanel panelRisposteMultiple;
	private JPanel selectVoF;
	private JPanel panelRisposta1;
	private JPanel panelRisposta2;
	private JPanel panelRisposta3;
	private JPanel panelRisposta4;
	private JPanel panelRisposta5;
	private JRadioButton Giusta1;
	private JRadioButton Giusta2;
	private JRadioButton Giusta3;
	private JRadioButton Giusta4;
	private JRadioButton Giusta5;
	private JLabel lblRisposteError;
	private JLabel lblTestoError;
	private JLabel VeroOFalsoError;
	private boolean ok;
	private JLabel lblGiustaError;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddDomanda(Controller c, CreazioneTest CreazioneTest) {
		setTitle("Legnarino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddDomanda.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 500);
		Dimension dim = getToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame = this;
		this.c = c;
		
		JPanel panelTitolo = new JPanel();
		panelTitolo.setBackground(Color.WHITE);
		panelTitolo.setBounds(10, 11, 214, 35);
		contentPane.add(panelTitolo);
		
		JLabel lblAggiungi = new JLabel("Aggiungi");
		lblAggiungi.setForeground(new Color(51, 102, 255));
		lblAggiungi.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelTitolo.add(lblAggiungi);
		
		JLabel lblDomanda = new JLabel("Domanda");
		lblDomanda.setForeground(new Color(255, 153, 0));
		lblDomanda.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelTitolo.add(lblDomanda);
		
		JPanel panelSelectTipoDomanda = new JPanel();
		panelSelectTipoDomanda.setBackground(Color.WHITE);
		panelSelectTipoDomanda.setBounds(340, 11, 297, 35);
		contentPane.add(panelSelectTipoDomanda);
		
		selectRispostaAperta = new JRadioButton("Risposta Aperta");
		selectRispostaAperta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectRispostaMultipla.setSelected(false);
				selectRispostaMultipla.setFont(new Font("Tahoma", Font.PLAIN, 15));
				selectRispostaAperta.setFont(new Font("Tahoma", Font.BOLD, 15));
				panelRisposteMultiple.setVisible(false);
			}
		});
		selectRispostaAperta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		selectRispostaAperta.setBackground(Color.WHITE);
		panelSelectTipoDomanda.add(selectRispostaAperta);
		
		selectRispostaMultipla = new JRadioButton("Risposta Multipla");
		selectRispostaMultipla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectRispostaAperta.setSelected(false);
				selectRispostaAperta.setFont(new Font("Tahoma", Font.PLAIN, 15));
				selectRispostaMultipla.setFont(new Font("Tahoma", Font.BOLD, 15));
				panelRisposteMultiple.setVisible(true);
			}
		});
		selectRispostaMultipla.setFont(new Font("Tahoma", Font.PLAIN, 15));
		selectRispostaMultipla.setBackground(Color.WHITE);
		panelSelectTipoDomanda.add(selectRispostaMultipla);
		
		JEditorPane TestoDomanda = new JEditorPane();
		TestoDomanda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TestoDomanda.setBounds(71, 80, 566, 68);
		TestoDomanda.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(TestoDomanda);
		
		JLabel lblTesto = new JLabel("Testo");
		lblTesto.setForeground(Color.BLACK);
		lblTesto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTesto.setBounds(10, 80, 61, 19);
		contentPane.add(lblTesto);
		
		panelRisposteMultiple = new JPanel();
		panelRisposteMultiple.setBackground(Color.WHITE);
		panelRisposteMultiple.setBounds(10, 159, 627, 291);
		contentPane.add(panelRisposteMultiple);
		panelRisposteMultiple.setLayout(null);
		panelRisposteMultiple.setVisible(false);
		
		JComboBox<String> selectNumeroRisposte = new JComboBox<String>();
		selectNumeroRisposte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VeroOFalsoError.setVisible(false);
				lblRisposteError.setVisible(false);
				lblGiustaError.setVisible(false);
				if(selectNumeroRisposte.getSelectedItem()=="Vero o Falso") {
					panelRisposta1.setVisible(false);
					panelRisposta2.setVisible(false);
					panelRisposta3.setVisible(false);
					panelRisposta4.setVisible(false);
					panelRisposta5.setVisible(false);
					selectVoF.setVisible(true);
				}
				if(selectNumeroRisposte.getSelectedItem()=="3 Risposte") {
					selectVoF.setVisible(false);
					panelRisposta1.setVisible(true);
					panelRisposta2.setVisible(true);
					panelRisposta3.setVisible(true);
					panelRisposta4.setVisible(false);
					panelRisposta5.setVisible(false);
				}
				if(selectNumeroRisposte.getSelectedItem()=="4 Risposte") {
					selectVoF.setVisible(false);
					panelRisposta1.setVisible(true);
					panelRisposta2.setVisible(true);
					panelRisposta3.setVisible(true);
					panelRisposta4.setVisible(true);
					panelRisposta5.setVisible(false);
				}
				if(selectNumeroRisposte.getSelectedItem()=="5 Risposte") {
					selectVoF.setVisible(false);
					panelRisposta1.setVisible(true);
					panelRisposta2.setVisible(true);
					panelRisposta3.setVisible(true);
					panelRisposta4.setVisible(true);
					panelRisposta5.setVisible(true);
				}
				
			}
		});
		selectNumeroRisposte.setBackground(Color.WHITE);
		selectNumeroRisposte.setBounds(0, 0, 120, 26);
		panelRisposteMultiple.add(selectNumeroRisposte);
		selectNumeroRisposte.setFont(new Font("Tahoma", Font.PLAIN, 16));
		selectNumeroRisposte.setModel(new DefaultComboBoxModel(new String[] {"Vero o Falso", "3 Risposte", "4 Risposte", "5 Risposte"}));
		
		selectVoF = new JPanel();
		selectVoF.setBackground(Color.WHITE);
		selectVoF.setBounds(130, 0, 120, 33);
		panelRisposteMultiple.add(selectVoF);
		selectVoF.setVisible(false);
		
		selectVeroGiusta = new JRadioButton("Vero");
		selectVeroGiusta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		selectVeroGiusta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectFalsoGiusta.setSelected(false);
				selectFalsoGiusta.setFont(new Font("Tahoma", Font.PLAIN, 11));
				selectVeroGiusta.setFont(new Font("Tahoma", Font.BOLD, 11));
			}
		});
		selectVeroGiusta.setBackground(Color.WHITE);
		selectVoF.add(selectVeroGiusta);
		
		selectFalsoGiusta = new JRadioButton("Falso");
		selectFalsoGiusta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectVeroGiusta.setSelected(false);
				selectVeroGiusta.setFont(new Font("Tahoma", Font.PLAIN, 11));
				selectFalsoGiusta.setFont(new Font("Tahoma", Font.BOLD, 11));
			}
		});
		selectFalsoGiusta.setBackground(Color.WHITE);
		selectVoF.add(selectFalsoGiusta);
		
		//---------------------------------------PANEL RISPOSTE MULTIPLE-----------------------------------------------
		
		panelRisposta1 = new JPanel();
		panelRisposta1.setBackground(Color.WHITE);
		panelRisposta1.setBounds(0, 37, 627, 47);
		panelRisposteMultiple.add(panelRisposta1);
		panelRisposta1.setLayout(null);
		panelRisposta1.setVisible(false);
		
		JLabel lblRisposta1 = new JLabel("Risposta 1");
		lblRisposta1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRisposta1.setBounds(0, 0, 68, 16);
		panelRisposta1.add(lblRisposta1);
		
		JEditorPane TestoRisposta1 = new JEditorPane();
		TestoRisposta1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		TestoRisposta1.setBounds(73, 0, 554, 47);
		panelRisposta1.add(TestoRisposta1);
		
		Giusta1 = new JRadioButton("Giusta");
		Giusta1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Giusta2.setSelected(false);
				Giusta3.setSelected(false);
				Giusta4.setSelected(false);
				Giusta5.setSelected(false);
			}
		});
		Giusta1.setBackground(Color.WHITE);
		Giusta1.setBounds(0, 24, 68, 23);
		panelRisposta1.add(Giusta1);
		
		panelRisposta2 = new JPanel();
		panelRisposta2.setLayout(null);
		panelRisposta2.setBackground(Color.WHITE);
		panelRisposta2.setBounds(0, 88, 627, 47);
		panelRisposteMultiple.add(panelRisposta2);
		panelRisposta2.setVisible(false);
		
		JLabel lblRisposta2 = new JLabel("Risposta 2");
		lblRisposta2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRisposta2.setBounds(0, 0, 68, 16);
		panelRisposta2.add(lblRisposta2);
		
		JEditorPane TestoRisposta2 = new JEditorPane();
		TestoRisposta2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		TestoRisposta2.setBounds(73, 0, 554, 47);
		panelRisposta2.add(TestoRisposta2);
		
		Giusta2 = new JRadioButton("Giusta");
		Giusta2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Giusta1.setSelected(false);
				Giusta3.setSelected(false);
				Giusta4.setSelected(false);
				Giusta5.setSelected(false);
			}
		});
		Giusta2.setBackground(Color.WHITE);
		Giusta2.setBounds(0, 24, 68, 23);
		panelRisposta2.add(Giusta2);
		
		panelRisposta3 = new JPanel();
		panelRisposta3.setLayout(null);
		panelRisposta3.setBackground(Color.WHITE);
		panelRisposta3.setBounds(0, 139, 627, 47);
		panelRisposteMultiple.add(panelRisposta3);
		panelRisposta3.setVisible(false);
		
		JLabel lblRisposta3 = new JLabel("Risposta 3");
		lblRisposta3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRisposta3.setBounds(0, 0, 68, 16);
		panelRisposta3.add(lblRisposta3);
		
		JEditorPane TestoRisposta3 = new JEditorPane();
		TestoRisposta3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		TestoRisposta3.setBounds(73, 0, 554, 47);
		panelRisposta3.add(TestoRisposta3);
		
		Giusta3 = new JRadioButton("Giusta");
		Giusta3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Giusta2.setSelected(false);
				Giusta1.setSelected(false);
				Giusta4.setSelected(false);
				Giusta5.setSelected(false);
			}
		});
		Giusta3.setBackground(Color.WHITE);
		Giusta3.setBounds(0, 24, 68, 23);
		panelRisposta3.add(Giusta3);
		
		panelRisposta4 = new JPanel();
		panelRisposta4.setLayout(null);
		panelRisposta4.setBackground(Color.WHITE);
		panelRisposta4.setBounds(0, 188, 627, 47);
		panelRisposteMultiple.add(panelRisposta4);
		panelRisposta4.setVisible(false);
		
		JLabel lblRisposta4 = new JLabel("Risposta 4");
		lblRisposta4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRisposta4.setBounds(0, 0, 68, 16);
		panelRisposta4.add(lblRisposta4);
		
		JEditorPane TestoRisposta4 = new JEditorPane();
		TestoRisposta4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		TestoRisposta4.setBounds(73, 0, 554, 47);
		panelRisposta4.add(TestoRisposta4);
		
		Giusta4 = new JRadioButton("Giusta");
		Giusta4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Giusta2.setSelected(false);
				Giusta3.setSelected(false);
				Giusta1.setSelected(false);
				Giusta5.setSelected(false);
			}
		});
		Giusta4.setBackground(Color.WHITE);
		Giusta4.setBounds(0, 24, 68, 23);
		panelRisposta4.add(Giusta4);
		
		panelRisposta5 = new JPanel();
		panelRisposta5.setLayout(null);
		panelRisposta5.setBackground(Color.WHITE);
		panelRisposta5.setBounds(0, 238, 627, 47);
		panelRisposteMultiple.add(panelRisposta5);
		panelRisposta5.setVisible(false);
		
		JLabel lblRisposta5 = new JLabel("Risposta 5");
		lblRisposta5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRisposta5.setBounds(0, 0, 68, 16);
		panelRisposta5.add(lblRisposta5);
		
		JEditorPane TestoRisposta5 = new JEditorPane();
		TestoRisposta5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		TestoRisposta5.setBounds(73, 0, 554, 47);
		panelRisposta5.add(TestoRisposta5);
		
		Giusta5 = new JRadioButton("Giusta");
		Giusta5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Giusta2.setSelected(false);
				Giusta3.setSelected(false);
				Giusta4.setSelected(false);
				Giusta1.setSelected(false);
			}
		});
		Giusta5.setBackground(Color.WHITE);
		Giusta5.setBounds(0, 24, 68, 23);
		panelRisposta5.add(Giusta5);
		
		lblRisposteError = new JLabel("Mancano delle risposte!");
		lblRisposteError.setForeground(Color.RED);
		lblRisposteError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRisposteError.setBounds(260, 0, 182, 26);
		panelRisposteMultiple.add(lblRisposteError);
		lblRisposteError.setVisible(false);
		
		VeroOFalsoError = new JLabel("E' vero o falso?");
		VeroOFalsoError.setForeground(Color.RED);
		VeroOFalsoError.setFont(new Font("Tahoma", Font.BOLD, 15));
		VeroOFalsoError.setBounds(260, 4, 120, 18);
		panelRisposteMultiple.add(VeroOFalsoError);
		VeroOFalsoError.setVisible(false);
		
		lblGiustaError = new JLabel("Nessuna è giusta!");
		lblGiustaError.setForeground(Color.RED);
		lblGiustaError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiustaError.setBounds(260, 3, 137, 20);
		panelRisposteMultiple.add(lblGiustaError);
		lblGiustaError.setVisible(false);
		
		//------------------------------------------------------------------------------------------------------------
		
		JButton AddDomanda = new JButton("Aggiungi");
		AddDomanda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ok=true;
				lblTestoError.setVisible(false);
				lblRisposteError.setVisible(false);
				VeroOFalsoError.setVisible(false);
				lblGiustaError.setVisible(false);
				if(TestoDomanda.getText().contentEquals("")) {
					lblTestoError.setVisible(true);
					ok=false;
				}
				if(selectRispostaMultipla.isSelected() && (selectNumeroRisposte.getSelectedItem()=="Vero o Falso" && (!selectVeroGiusta.isSelected() && !selectFalsoGiusta.isSelected()))) {
					VeroOFalsoError.setVisible(true);
					ok=false;
				}
				if(selectRispostaMultipla.isSelected()) {
						if(((selectNumeroRisposte.getSelectedItem()=="3 Risposte" && (TestoRisposta1.getText().contentEquals("") || TestoRisposta2.getText().contentEquals("") || TestoRisposta3.getText().contentEquals("")))
					|| (selectNumeroRisposte.getSelectedItem()=="4 Risposte" && (TestoRisposta1.getText().contentEquals("") || TestoRisposta2.getText().contentEquals("") || TestoRisposta3.getText().contentEquals("") || TestoRisposta4.getText().contentEquals("")))
					|| (selectNumeroRisposte.getSelectedItem()=="5 Risposte" && (TestoRisposta1.getText().contentEquals("") || TestoRisposta2.getText().contentEquals("") || TestoRisposta3.getText().contentEquals("") || TestoRisposta4.getText().contentEquals("") || TestoRisposta5.getText().contentEquals("")))
					)) {
						lblRisposteError.setVisible(true);
						ok=false;
					}
					if((selectNumeroRisposte.getSelectedItem()=="3 Risposte" && (!Giusta1.isSelected() && !Giusta2.isSelected() && !Giusta3.isSelected()))
					|| (selectNumeroRisposte.getSelectedItem()=="4 Risposte" && (!Giusta1.isSelected() && !Giusta2.isSelected() && !Giusta3.isSelected() && !Giusta4.isSelected()))
					|| (selectNumeroRisposte.getSelectedItem()=="5 Risposte" && (!Giusta1.isSelected() && !Giusta2.isSelected() && !Giusta3.isSelected() && !Giusta4.isSelected() && !Giusta5.isSelected()))
					) {
						lblGiustaError.setVisible(true);
						ok=false;
					}
				}
				if(ok) {
					Domanda d = new Domanda();
					DomandaDAO ddao = new DomandaDAO();
					d.setIdDomanda(ddao.getMaxId()+1+c.getTest().getDomande().size());
					d.setIdTest(c.getTest().getIdTest());
					d.setTestoDomanda(TestoDomanda.getText());
					if(selectRispostaAperta.isSelected()) {
						d.setTipo("Aperta");
					}
					if(selectRispostaMultipla.isSelected()) {
						d.setTipo("Multipla");
						ArrayList<Risposta> risposteNewDomanda = new ArrayList<Risposta>();
						Risposta r;
						RispostaDAO rdao = new RispostaDAO();
						if(selectNumeroRisposte.getSelectedItem()=="Vero o Falso"){
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+1);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta("Vero");
							r.setCorretta(selectVeroGiusta.isSelected());
							risposteNewDomanda.add(r);
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+2);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta("Falso");
							r.setCorretta(selectFalsoGiusta.isSelected());
							risposteNewDomanda.add(r);
						}
						if(selectNumeroRisposte.getSelectedItem()=="3 Risposte") {
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+1);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta1.getText());
							r.setCorretta(Giusta1.isSelected());
							risposteNewDomanda.add(r);
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+2);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta2.getText());
							r.setCorretta(Giusta2.isSelected());
							risposteNewDomanda.add(r);
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+3);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta3.getText());
							r.setCorretta(Giusta3.isSelected());
							risposteNewDomanda.add(r);
						}
						if(selectNumeroRisposte.getSelectedItem()=="4 Risposte") {
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+1);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta1.getText());
							r.setCorretta(Giusta1.isSelected());
							risposteNewDomanda.add(r);
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+2);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta2.getText());
							r.setCorretta(Giusta2.isSelected());
							risposteNewDomanda.add(r);
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+3);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta3.getText());
							r.setCorretta(Giusta3.isSelected());
							risposteNewDomanda.add(r);
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+4);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta4.getText());
							r.setCorretta(Giusta4.isSelected());
							risposteNewDomanda.add(r);
						}
						if(selectNumeroRisposte.getSelectedItem()=="5 Risposte") {
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+1);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta1.getText());
							r.setCorretta(Giusta1.isSelected());
							risposteNewDomanda.add(r);
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+2);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta2.getText());
							r.setCorretta(Giusta2.isSelected());
							risposteNewDomanda.add(r);
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+3);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta3.getText());
							r.setCorretta(Giusta3.isSelected());
							risposteNewDomanda.add(r);
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+4);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta4.getText());
							r.setCorretta(Giusta4.isSelected());
							risposteNewDomanda.add(r);
							r = new Risposta();
							r.setIdRisposta(rdao.getMaxId()+5);
							r.setIdDomanda(d.getIdDomanda());
							r.setTestoRisposta(TestoRisposta5.getText());
							r.setCorretta(Giusta5.isSelected());
							risposteNewDomanda.add(r);
						}
						d.setRisposte(risposteNewDomanda);
					}
					c.getTest().addDomande(d);
					CreazioneTest.getTableModel().addRow(new Object[] {d.getIdDomanda(),d.getTestoDomanda()});
					frame.setVisible(false);
					CreazioneTest.setVisible(true);
				}
			}
		});
		AddDomanda.setForeground(new Color(255, 153, 0));
		AddDomanda.setBackground(new Color(51, 102, 255));
		AddDomanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		AddDomanda.setBounds(10, 46, 100, 23);
		contentPane.add(AddDomanda);
		
		JButton Back = new JButton("Indietro");
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				CreazioneTest.setVisible(true);
			}
		});
		Back.setForeground(new Color(255, 153, 0));
		Back.setFont(new Font("Tahoma", Font.BOLD, 15));
		Back.setBackground(new Color(51, 102, 255));
		Back.setBounds(127, 46, 97, 23);
		contentPane.add(Back);
		
		lblTestoError = new JLabel("Manca il testo!");
		lblTestoError.setForeground(Color.RED);
		lblTestoError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTestoError.setBounds(274, 57, 116, 19);
		contentPane.add(lblTestoError);
		lblTestoError.setVisible(false);
		
	}
}
