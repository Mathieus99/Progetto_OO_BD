package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

public class RegistrazioneDocente extends JFrame {
	private JFrame frame;
	private JFrame accessoapp;
	private Controller controller;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldEmail;
	private JTextField textFieldEmailConferma;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConferma;
	private JTextField textFieldMatricola;
	boolean annulla=false;
	
	public RegistrazioneDocente (Controller c, JFrame accesso){
		getContentPane().setBackground(Color.WHITE);
		setTitle("Legnarino Web Learning");
		setIconImage(Toolkit.getDefaultToolkit().getImage("H:\\DESKTOP\\Legnarino_icon2.png"));
		setBounds(750, 250, 714, 445);
		getContentPane().setLayout(null);
		frame=this;
		controller=c;
		accessoapp=accesso;
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblNome.setBounds(46, 107, 63, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblCognome.setBounds(367, 104, 77, 20);
		getContentPane().add(lblCognome);
		
		JLabel lblPassword = new JLabel("Password*");
		lblPassword.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblPassword.setBounds(35, 144, 89, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblPasswordConferma = new JLabel("Conferma Password");
		lblPasswordConferma.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblPasswordConferma.setBounds(283, 144, 173, 14);
		getContentPane().add(lblPasswordConferma);
		
		JLabel lblEmail = new JLabel("Email**");
		lblEmail.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblEmail.setBounds(61, 180, 63, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblEmailConferma = new JLabel("Conferma Email");
		lblEmailConferma.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblEmailConferma.setBounds(319, 180, 125, 14);
		getContentPane().add(lblEmailConferma);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		textFieldNome.setBounds(128, 105, 145, 20);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		textFieldCognome.setColumns(10);
		textFieldCognome.setBounds(456, 105, 145, 20);
		getContentPane().add(textFieldCognome);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(128, 178, 145, 20);
		getContentPane().add(textFieldEmail);
		
		textFieldEmailConferma = new JTextField();
		textFieldEmailConferma.setColumns(10);
		textFieldEmailConferma.setBounds(454, 179, 145, 20);
		getContentPane().add(textFieldEmailConferma);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setColumns(10);
		passwordField.setBounds(128, 142, 145, 20);
		getContentPane().add(passwordField);
		
		passwordFieldConferma = new JPasswordField();
		passwordFieldConferma.setColumns(10);
		passwordFieldConferma.setBounds(456, 143, 145, 20);
		getContentPane().add(passwordFieldConferma);
		
		JLabel lblNomeError = new JLabel("");
		lblNomeError.setForeground(Color.RED);
		lblNomeError.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblNomeError.setBounds(128, 124, 145, 14);
		getContentPane().add(lblNomeError);
		
		JLabel lblCognomeError = new JLabel("");
		lblCognomeError.setForeground(Color.RED);
		lblCognomeError.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblCognomeError.setBounds(456, 124, 145, 14);
		getContentPane().add(lblCognomeError);
		
		JLabel lblPswError = new JLabel("");
		lblPswError.setForeground(Color.RED);
		lblPswError.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblPswError.setBounds(128, 163, 145, 14);
		getContentPane().add(lblPswError);
		
		JLabel lblPswConfError = new JLabel("");
		lblPswConfError.setForeground(Color.RED);
		lblPswConfError.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblPswConfError.setBounds(456, 163, 179, 14);
		getContentPane().add(lblPswConfError);
		
		JLabel lblEmailError = new JLabel("");
		lblEmailError.setForeground(Color.RED);
		lblEmailError.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblEmailError.setBounds(128, 198, 145, 14);
		getContentPane().add(lblEmailError);
		
		JLabel lblEmailConfError = new JLabel("");
		lblEmailConfError.setForeground(Color.RED);
		lblEmailConfError.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblEmailConfError.setBounds(454, 198, 166, 14);
		getContentPane().add(lblEmailConfError);
				
		JButton btnRegistrazione = new JButton("Registrati");
		btnRegistrazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNomeError.setText("");
				lblCognomeError.setText("");
				lblEmailError.setText("");
				lblEmailConfError.setText("");
				lblPswError.setText("");
				lblPswConfError.setText("");
				boolean ok=true;
				if(textFieldNome.getText().contentEquals("")) {
					lblNomeError.setText("Inserisci il nome!");
					ok=false;
				}
				if(textFieldCognome.getText().contentEquals("")) {
					lblCognomeError.setText("Inserisci il cognome!");
					ok=false;
				}
				
				if(textFieldEmail.getText().contentEquals("")) {
					lblEmailError.setText("Inserisci l'email!");
					ok=false;
				}
				if(!textFieldEmail.getText().contains("@unina.it") && !lblEmailError.getText().contentEquals("Inserisci l'email!")) {
					lblEmailError.setText("Email non valida!");
					ok=false;
				}
				if(!textFieldEmailConferma.getText().contentEquals(textFieldEmail.getText()) && !textFieldEmail.getText().contentEquals("") && !lblEmailError.getText().contentEquals("Email non valida!")) {
					lblEmailConfError.setText("Le email sono diverse!");
					ok=false;
				}
				
				if(passwordField.getText().length()<8 || passwordField.getText().length()>32) {
					lblPswError.setText("Password non valida!");
					ok=false;
				}
				if(passwordFieldConferma.getText().contentEquals("") && !lblPswConfError.getText().contentEquals("Le password sono diverse!")) {
					lblPswConfError.setText("Conferma password!");
					ok=false;
				}
				if(!passwordFieldConferma.getText().contentEquals(passwordField.getText()) && !lblPswError.getText().contentEquals("Password non valida!")) {
					lblPswConfError.setText("Le password sono diverse!");
					ok=false;
				}
				if(ok) { 
					controller.registraDocente(textFieldNome.getText(), textFieldCognome.getText(), passwordField.getText(), textFieldEmail.getText());
					controller.setRegisterSuccesful(ok);
					controller.fromRegister=true;
					frame.setVisible(false);
					accesso.setVisible(true);
					
				}
			}
		});
		btnRegistrazione.setSelectedIcon(null);
		btnRegistrazione.setBackground(Color.WHITE);
		btnRegistrazione.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		btnRegistrazione.setBounds(46, 254, 308, 35);
		getContentPane().add(btnRegistrazione);
		
		JPanel panel = new JPanel();
		panel.setBounds(46, 11, 555, 82);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("");
		lblTitle.setBounds(128, 0, 300, 75);
		panel.add(lblTitle);
		lblTitle.setIcon(new ImageIcon("H:\\DESKTOP\\Registrazione_Title2.png"));
		
		JLabel lblNewLabel = new JLabel("*La password deve contenere da 8 a 32 caratteri");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(46, 310, 398, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("**Inserire solo email \"@studenti.unina.it\" o \"@unina.it\"");
		lblNewLabel_1.setBounds(46, 325, 410, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBackground(Color.WHITE);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.back=true;
				frame.setVisible(false);
				accessoapp.setVisible(true);
			}
		});
		btnAnnulla.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		btnAnnulla.setBounds(390, 254, 125, 35);
		getContentPane().add(btnAnnulla);
	}
}