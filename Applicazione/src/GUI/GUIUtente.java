package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JMenuBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import Controller.Controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUIUtente extends JFrame {
	
	private Controller controller;
	JFrame frame;
	private JFrame accessoapp;
	private String ruolo;
	
	public GUIUtente(Controller c, JFrame accesso) {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Legnarino Web Learning");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 250, 876, 576);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		frame=this;
		controller=c;
		accessoapp=accesso;
		
		/*---------------------------------------------------------------------PANNELLO VISUALIZZAZIONE DATI UTENTE-------------------------------------------*/
		JPanel panelUtente = new JPanel();
		panelUtente.setBounds(10, 11, 236, 72);
		getContentPane().add(panelUtente);
		panelUtente.setLayout(null);
		
		JLabel lblMatricola = new JLabel("");
		lblMatricola.setBounds(10, 45, 185, 16);
		lblMatricola.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		panelUtente.add(lblMatricola);
		lblMatricola.setText(controller.matricolaStudente());
		
		JLabel lblEmail = new JLabel(controller.emailS());
		lblEmail.setBounds(10, 29, 203, 16);
		lblEmail.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
		panelUtente.add(lblEmail);
		lblEmail.setText(controller.emailS());
				
		JPanel panelNomeUtente = new JPanel();
		panelNomeUtente.setBackground(new Color(0, 102, 255));
		panelNomeUtente.setBorder(null);
		panelNomeUtente.setBounds(0, 0, 236, 28);
		panelUtente.add(panelNomeUtente);
		
		JLabel lblNome = new JLabel("");
		lblNome.setForeground(new Color(255, 153, 0));
		lblNome.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panelNomeUtente.add(lblNome);
		lblNome.setText(controller.nomeS());
		
		JLabel lblCognome = new JLabel("");
		lblCognome.setForeground(new Color(255, 153, 0));
		lblCognome.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panelNomeUtente.add(lblCognome);
		lblCognome.setText(controller.cognomeS());
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
		
		JButton btnTestSostenuti = new JButton("I miei test");
		btnTestSostenuti.setBounds(0, 62, 206, 31);
		BloccoMenu.add(btnTestSostenuti);
		btnTestSostenuti.setForeground(new Color(51, 102, 255));
		btnTestSostenuti.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		btnTestSostenuti.setBackground(new Color(255, 153, 0));
		
		JButton btnIlMioProfilo = new JButton("Il mio profilo");
		btnIlMioProfilo.setBounds(0, 0, 206, 31);
		BloccoMenu.add(btnIlMioProfilo);
		btnIlMioProfilo.setForeground(new Color(51, 102, 255));
		btnIlMioProfilo.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		btnIlMioProfilo.setBackground(new Color(255, 153, 0));
		
		JButton btnSostieniTest = new JButton("Sostieni test");
		btnSostieniTest.setBounds(0, 31, 206, 31);
		BloccoMenu.add(btnSostieniTest);
		btnSostieniTest.setForeground(new Color(51, 102, 255));
		btnSostieniTest.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		btnSostieniTest.setBackground(new Color(255, 153, 0));
		
		JButton btnCreaTest = new JButton("Logout");
		btnCreaTest.setBounds(15, 366, 206, 31);
		panelMenu.add(btnCreaTest);
		btnCreaTest.setForeground(new Color(51, 102, 255));
		btnCreaTest.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		btnCreaTest.setBackground(new Color(255, 153, 0));
		/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	}
}

