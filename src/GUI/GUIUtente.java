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

public class GUIUtente extends JFrame {
	
	private Controller controller;
	JFrame frame;
	private JFrame accessoapp;
	private String ruolo;
	
	public GUIUtente(Controller c, JFrame accesso) {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Legnarino Web Learning");
		setIconImage(Toolkit.getDefaultToolkit().getImage("H:\\DESKTOP\\Legnarino_icon2.png"));
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
		ruolo=controller.getRuolo(); //il controller recupera il ruolo dell'utente per attivare i privilegi
		
		ruolo="Studente";
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 236, 72);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMatricola = new JLabel("");
		lblMatricola.setBounds(10, 45, 185, 16);
		lblMatricola.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		panel.add(lblMatricola);
		
		JLabel lblEmail = new JLabel("");
		lblEmail.setBounds(10, 29, 203, 16);
		lblEmail.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
		panel.add(lblEmail);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 102, 255));
		panel_1.setBorder(null);
		panel_1.setBounds(0, 0, 236, 28);
		panel.add(panel_1);
		
		JLabel lblNome = new JLabel("");
		lblNome.setForeground(new Color(255, 153, 0));
		lblNome.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panel_1.add(lblNome);
		
		JLabel lblCognome = new JLabel("");
		lblCognome.setForeground(new Color(255, 153, 0));
		lblCognome.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panel_1.add(lblCognome);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new LineBorder(new Color(51, 0, 255), 2)));
		panel_2.setBounds(256, 11, 589, 72);
		getContentPane().add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblMnCreaTest = new JLabel("Crea Test");
		lblMnCreaTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameCreaTest=new CreazioneTest (controller, frame, accesso);
				frame.setVisible(false);
				frameCreaTest.setVisible(true);
			}
		});
		lblMnCreaTest.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panel_2.add(lblMnCreaTest);
		if(!ruolo.contentEquals("docente")) lblMnCreaTest.setVisible(false);
		
		JLabel lblMnTest = new JLabel("Test");
		lblMnTest.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panel_2.add(lblMnTest);
		if(!ruolo.contentEquals("docente")) lblMnCreaTest.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Effettua Test");
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panel_2.add(lblNewLabel);
		
		JLabel lblMnTestFatti = new JLabel("Test Completati");
		lblMnTestFatti.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		panel_2.add(lblMnTestFatti);		
	}
}
