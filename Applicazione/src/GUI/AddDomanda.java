package GUI;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import Controller.Controller;
import java.awt.Color;
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

@SuppressWarnings("serial")
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

	public AddDomanda(Controller c,JFrame CreazioneTest) {
		setTitle("Legnarino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddDomanda.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 500);
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
		panelSelectTipoDomanda.setBounds(351, 11, 286, 35);
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
		
		panelRisposta1 = new JPanel();
		panelRisposta1.setBackground(Color.WHITE);
		panelRisposta1.setBounds(0, 37, 627, 47);
		panelRisposteMultiple.add(panelRisposta1);
		panelRisposta1.setLayout(null);
		
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
		
		JButton AddDomanda = new JButton("Aggiungi");
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
		
		
	}
}
