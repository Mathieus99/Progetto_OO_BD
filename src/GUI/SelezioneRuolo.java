package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import java.awt.Toolkit;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelezioneRuolo extends JFrame {
	
	private Controller controller;
	JFrame frame;
	JFrame accessoapp;
	String ruolo="";
	
	public SelezioneRuolo(Controller c, JFrame accesso) {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Legnarino Web Learning");
		setIconImage(Toolkit.getDefaultToolkit().getImage("H:\\DESKTOP\\Legnarino_icon2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 250, 350, 100);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		frame=this;
		controller=c;
		accessoapp=accesso;
		
		JButton btnStudente = new JButton("Studente");
		btnStudente.setForeground(Color.WHITE);
		btnStudente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ruolo="Studente";
				JFrame frameRegistrazione=new Registrazione (controller, accessoapp, ruolo);
				frame.setVisible(false);
				frameRegistrazione.setVisible(true);
				
			}
		});
		contentPane.setLayout(null);
		btnStudente.setBackground(new Color(51, 102, 255));
		btnStudente.setBounds(46, 11, 120, 35);
		btnStudente.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		getContentPane().add(btnStudente);
		
		JButton btnDocente = new JButton("Docente");
		btnDocente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ruolo="Docente";
				JFrame frameRegistrazione=new RegistrazioneDocente (controller, accessoapp, ruolo);
				frame.setVisible(false);
				frameRegistrazione.setVisible(true);
			}
		});
		btnDocente.setBackground(new Color(255, 153, 0));
		btnDocente.setBounds(174, 11, 120, 35);
		btnDocente.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		getContentPane().add(btnDocente);
	}

}
