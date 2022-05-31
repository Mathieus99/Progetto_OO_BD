package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import GUI.*;
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

public class AggiungiDomanda extends JFrame {

	private JPanel contentPane;
	private JPanel panelDMultipla;
	private JPanel panelDAperta;
	private JRadioButton selectRispostaAperta;
	private JRadioButton selectRispostaMultipla;
	private JPanel risposta1Panel;
	private JPanel risposta2Panel;
	private JPanel risposta3Panel;
	private JPanel risposta4Panel;
	private JPanel risposta5Panel;
	private JFrame frame;
	private Controller controller;

	public AggiungiDomanda(Controller c, JFrame guiCreaTest) {
		setTitle("Legnarino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AggiungiDomanda.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 514);
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
		
		JLabel lblTestoDomanda = new JLabel("Testo Domanda");
		lblTestoDomanda.setBounds(10, 5, 128, 20);
		lblTestoDomanda.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelDMultipla.add(lblTestoDomanda);
		
		JEditorPane testoDomanda = new JEditorPane();
		testoDomanda.setFont(new Font("Tahoma", Font.PLAIN, 13));
		testoDomanda.setBounds(148, 5, 508, 61);
		panelDMultipla.add(testoDomanda);
		testoDomanda.setBorder(BorderFactory.createLineBorder(Color.black));
		
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
		
		JEditorPane risposta1Text_1_1 = new JEditorPane();
		risposta1Text_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		risposta1Text_1_1.setBorder(BorderFactory.createLineBorder(Color.black));
		risposta1Text_1_1.setBounds(80, 2, 536,39);
		risposta3Panel.add(risposta1Text_1_1);
		
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
		
		panelDAperta = new JPanel();
		panelDAperta.setBackground(Color.WHITE);
		panelDAperta.setBounds(0, 59, 666, 72);
		contentPane.add(panelDAperta);
		panelDAperta.setLayout(null);
		panelDAperta.setVisible(false);
		
		JLabel lblNewLabel_2 = new JLabel("Testo Domanda");
		lblNewLabel_2.setBounds(10, 5, 128, 20);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelDAperta.add(lblNewLabel_2);
		
		JEditorPane editorPaneDAperta = new JEditorPane();
		editorPaneDAperta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		editorPaneDAperta.setBounds(148, 5, 508, 56);
		panelDAperta.add(editorPaneDAperta);
		editorPaneDAperta.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel panelSelectDomanda = new JPanel();
		panelSelectDomanda.setBackground(Color.WHITE);
		panelSelectDomanda.setBounds(393, 11, 273, 37);
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
		
		JButton btnNewButton = new JButton("Aggiungi Domanda");
		btnNewButton.setBackground(new Color(51, 102, 255));
		btnNewButton.setForeground(new Color(255, 153, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(53, 432, 251, 32);
		contentPane.add(btnNewButton);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
	}
}
