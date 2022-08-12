package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class provaFrameTest extends JFrame {

	private JPanel contentPane;
	
	public provaFrameTest() {
		setTitle("Legnarino");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(provaFrameTest.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 441);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelDomanda = new JPanel();
		panelDomanda.setBounds(10, 10, 167, 41);
		contentPane.add(panelDomanda);
		
		JLabel lblDomanda = new JLabel("Domanda");
		lblDomanda.setForeground(new Color(255, 153, 0));
		lblDomanda.setFont(new Font("Tahoma", Font.BOLD, 25));
		panelDomanda.add(lblDomanda);
		
		JLabel lblNumDomanda = new JLabel("50");
		lblNumDomanda.setForeground(new Color(51, 102, 255));
		lblNumDomanda.setFont(new Font("Tahoma", Font.BOLD, 25));
		panelDomanda.add(lblNumDomanda);
		
		JEditorPane txtDomanda = new JEditorPane();
		txtDomanda.setEditable(false);
		txtDomanda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDomanda.setBounds(10, 61, 778, 71);
		contentPane.add(txtDomanda);
		
		JButton btnNewButton = new JButton("Avanti");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(new Color(51, 102, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setIcon(new ImageIcon(provaFrameTest.class.getResource("/Immagini/Freccia_Avanit.png")));
		btnNewButton.setBounds(617, 311, 171, 83);
		contentPane.add(btnNewButton);
		
		JRadioButton A = new JRadioButton("A");
		A.setBackground(Color.WHITE);
		A.setBounds(10, 139, 778, 23);
		contentPane.add(A);
		
		JRadioButton B = new JRadioButton("B");
		B.setBackground(Color.WHITE);
		B.setBounds(10, 165, 778, 23);
		contentPane.add(B);
		
		JRadioButton C = new JRadioButton("C");
		C.setBackground(Color.WHITE);
		C.setBounds(10, 191, 778, 23);
		contentPane.add(C);
		
		JRadioButton D = new JRadioButton("D");
		D.setBackground(Color.WHITE);
		D.setBounds(10, 217, 778, 23);
		contentPane.add(D);
		
		JRadioButton E = new JRadioButton("E");
		E.setBackground(Color.WHITE);
		E.setBounds(10, 243, 778, 23);
		contentPane.add(E);
	}
}
