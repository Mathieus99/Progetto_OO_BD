package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import java.awt.Color;
import java.awt.Toolkit;
import java.lang.reflect.Array;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class CreazioneTest extends JFrame {
	JFrame frame;
	JFrame guiUtente;
	JFrame accessoapp;
	private Controller controller;
	private JTextField textFieldNumDomande;
	private JTextField textField;
	private int i;

	public CreazioneTest(Controller c, JFrame guiUtente, JFrame accesso) {
		setTitle("Legnarino Web Learning");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreazioneTest.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 250, 922, 649);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		frame=this;
		controller=c;
		contentPane.setLayout(null);
		
		/*----------------------------------------------PANNELLO VISUALIZZAZIONE DATI DOCENTE-------------------------------------------------------*/
		JPanel panelUtente = new JPanel();
		panelUtente.setBounds(10, 11, 236, 72);
		getContentPane().add(panelUtente);
		panelUtente.setLayout(null);
		
		JLabel lblEmail = new JLabel("");
		lblEmail.setBounds(10, 29, 203, 16);
		lblEmail.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
		panelUtente.add(lblEmail);
		//lblEmail.setText(controller.emailD());
		
		JPanel panelNomeUtente = new JPanel();
		panelNomeUtente.setBackground(new Color(0, 102, 255));
		panelNomeUtente.setBorder(null);
		panelNomeUtente.setBounds(0, 0, 236, 28);
		panelUtente.add(panelNomeUtente);
		
		JLabel lblNome = new JLabel("");
		lblNome.setForeground(new Color(255, 153, 0));
		lblNome.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panelNomeUtente.add(lblNome);
		//lblNome.setText(controller.nomeD());
		
		JLabel lblCognome = new JLabel("");
		lblCognome.setForeground(new Color(255, 153, 0));
		lblCognome.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panelNomeUtente.add(lblCognome);
		//lblCognome.setText(controller.cognomeD());
		/*------------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*---------------------------------------------------------TITOLO FINESTRA------------------------------------------------------------------*/
		JPanel panelTitolo = new JPanel();
		panelTitolo.setBounds(372, 25, 236, 39);
		panelTitolo.setBackground(Color.WHITE);
		contentPane.add(panelTitolo);
		
		JLabel lblCreazione = new JLabel("Creazione");
		panelTitolo.add(lblCreazione);
		lblCreazione.setForeground(new Color(51, 102, 255));
		lblCreazione.setFont(new Font("Lucida Bright", Font.BOLD, 30));
		
		JLabel lblTest = new JLabel("Test");
		panelTitolo.add(lblTest);
		lblTest.setForeground(new Color(255, 153, 0));
		lblTest.setFont(new Font("Lucida Bright", Font.BOLD, 30));
		/*------------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*--------------------------------------------------------------BODY------------------------------------------------------------------------*/
		JScrollPane scrollPaneBody = new JScrollPane();
		scrollPaneBody.setBounds(10, 94, 886, 505);
		scrollPaneBody.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPaneBody);
		
		JPanel panelBody = new JPanel();
		scrollPaneBody.setViewportView(panelBody);
		panelBody.setLayout(null);
		
		JLabel lblNumDomande = new JLabel("Numero di domande:");
		lblNumDomande.setBounds(10, 11, 170, 14);
		lblNumDomande.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		panelBody.add(lblNumDomande);
		
		JLabel lblNumDomandeError = new JLabel("");
		lblNumDomandeError.setForeground(Color.RED);
		lblNumDomandeError.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		lblNumDomandeError.setBounds(226, 12, 170, 14);
		panelBody.add(lblNumDomandeError);
		
		textFieldNumDomande = new JTextField();
		textFieldNumDomande.addInputMethodListener(new InputMethodListener() {
			public void inputMethodTextChanged(InputMethodEvent event) {
				lblNumDomandeError.setText("");
				if (!textFieldNumDomande.getText().matches("-?\\d+")) //Errore se input non è un numero
					lblNumDomandeError.setText("Deve essere un numero!");
				else {
					//Inserisci i form per le domande
													
				}
			}

			@Override
			public void caretPositionChanged(InputMethodEvent event) {
				lblNumDomandeError.setText("Successo Qualcosa");				
			}
		});
		
		textFieldNumDomande.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNumDomande.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		textFieldNumDomande.setBounds(180, 8, 36, 20);
		panelBody.add(textFieldNumDomande);
		textFieldNumDomande.setColumns(10);
				
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	}
}
