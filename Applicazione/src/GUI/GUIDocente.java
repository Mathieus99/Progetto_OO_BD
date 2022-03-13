package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controller.Controller;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIDocente extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private Controller controller;
	JFrame accessoapp;
	
	public GUIDocente(Controller c,JFrame accesso) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("H:\\DESKTOP\\Legnarino_icon2.png"));
		setTitle("Legnarino Web Learning");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 250, 876, 576);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame=this;
		controller=c;
		accessoapp=accesso;
		
		/*---------------------------------------------------------------------PANNELLO VISUALIZZAZIONE DATI UTENTE-------------------------------------------*/			
		JPanel panelUtente = new JPanel();
		panelUtente.setBounds(10, 11, 236, 72);
		getContentPane().add(panelUtente);
		panelUtente.setLayout(null);
		
		JLabel lblEmail = new JLabel("");
		lblEmail.setBounds(10, 29, 203, 16);
		lblEmail.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
		panelUtente.add(lblEmail);
		//lblEmail.setText(controller.emailS());
		
		JPanel panelNomeUtente = new JPanel();
		panelNomeUtente.setBounds(0, 0, 236, 28);
		panelUtente.add(panelNomeUtente);
		panelNomeUtente.setBackground(new Color(0, 102, 255));
		panelNomeUtente.setBorder(null);
		
		JLabel lblNome = new JLabel("");
		lblNome.setForeground(new Color(255, 153, 0));
		lblNome.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panelNomeUtente.add(lblNome);
		//lblNome.setText(controller.nomeS());
		
		JLabel lblCognome = new JLabel("");
		lblCognome.setForeground(new Color(255, 153, 0));
		lblCognome.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panelNomeUtente.add(lblCognome);
		//lblCognome.setText(controller.cognomeS());
		/*-----------------------------------------------------------------------------------------------------------------------------------------------------*/
		
		/*------------------------------------------------------------------------------------MENU-------------------------------------------------------------*/
		JPanel panelMenu = new JPanel();
		panelMenu.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new LineBorder(new Color(51, 0, 255), 2)));
		panelMenu.setBounds(10, 94, 236, 408);
		getContentPane().add(panelMenu);
		panelMenu.setLayout(null);
		
		JPanel BloccoMenu = new JPanel();
		BloccoMenu.setBounds(15, 15, 206, 106);
		panelMenu.add(BloccoMenu);
		BloccoMenu.setLayout(null);
		
		JButton btnTestCreati = new JButton("I miei test");
		btnTestCreati.setBounds(0, 62, 206, 31);
		BloccoMenu.add(btnTestCreati);
		btnTestCreati.setForeground(new Color(51, 102, 255));
		btnTestCreati.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		btnTestCreati.setBackground(new Color(255, 153, 0));
		
		JButton btnIlMioProfilo = new JButton("Il mio profilo");
		btnIlMioProfilo.setBounds(0, 0, 206, 31);
		BloccoMenu.add(btnIlMioProfilo);
		btnIlMioProfilo.setForeground(new Color(51, 102, 255));
		btnIlMioProfilo.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		btnIlMioProfilo.setBackground(new Color(255, 153, 0));
		
		JButton btnCreazioneTest = new JButton("Crea Test");
		btnCreazioneTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frameCreazioneTest=new CreazioneTest(controller, frame, accessoapp);
				frameCreazioneTest.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnCreazioneTest.setBounds(0, 31, 206, 31);
		BloccoMenu.add(btnCreazioneTest);
		btnCreazioneTest.setForeground(new Color(51, 102, 255));
		btnCreazioneTest.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		btnCreazioneTest.setBackground(new Color(255, 153, 0));
		
		JButton btnCreaTest = new JButton("Logout");
		btnCreaTest.setBounds(15, 366, 206, 31);
		panelMenu.add(btnCreaTest);
		btnCreaTest.setForeground(new Color(51, 102, 255));
		btnCreaTest.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		btnCreaTest.setBackground(new Color(255, 153, 0));
		/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	}

}
