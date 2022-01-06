package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

public class CreazioneTest extends JFrame {
	JFrame guiUtente;
	JFrame accessoapp;
	private Controller controller;
	public CreazioneTest(Controller c, JFrame guiUtente, JFrame accesso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 250, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		controller=c;
		this.guiUtente=guiUtente;
		accessoapp=accesso;
		
		
		
	}

}
