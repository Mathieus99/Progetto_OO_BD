package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Button;

public class Home extends JFrame {

	private Controller controller;
	JFrame frame;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	
	public Home(Controller c) {
		getContentPane().setBackground(Color.WHITE);		
		controller=c;
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 250, 530, 381);
		
		setTitle("Legnarino Web Learning");
		setIconImage(Toolkit.getDefaultToolkit().getImage("H:\\DESKTOP\\Legnarino_icon2.png"));
		getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("H:\\DESKTOP\\ProvaLogo4.png"));
		lblLogo.setBounds(168, 8, 150, 108);
		getContentPane().add(lblLogo);		
		
		JLabel lblRegisterSuccesful = new JLabel("");
		lblRegisterSuccesful.setForeground(new Color(51, 102, 255));
		lblRegisterSuccesful.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterSuccesful.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		lblRegisterSuccesful.setBounds(89, 311, 328, 20);
		getContentPane().add(lblRegisterSuccesful);
		
		JPanel panelForm = new JPanel();
		panelForm.setBackground(Color.WHITE);
		panelForm.setBounds(77, 156, 335, 144);
		getContentPane().add(panelForm);
		panelForm.setLayout(null);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(139, 28, 145, 20);
		panelForm.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(62, 30, 70, 14);
		panelForm.add(lblUsername);
		lblUsername.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(62, 76, 70, 14);
		panelForm.add(lblPassword);
		lblPassword.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		
		JLabel lblPswError = new JLabel("");
		lblPswError.setBounds(139, 59, 145, 14);
		panelForm.add(lblPswError);
		lblPswError.setForeground(Color.RED);
		lblPswError.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		
		JLabel lblUsernameError = new JLabel("");
		lblUsernameError.setBounds(139, 11, 145, 14);
		panelForm.add(lblUsernameError);
		lblUsernameError.setForeground(Color.RED);
		lblUsernameError.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setBounds(51, 112, 87, 23);
		panelForm.add(btnAccedi);
		btnAccedi.setBackground(Color.WHITE);
		btnAccedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean no=true;
				JFrame frameGUIUtente;
				//TODO Inserire controllo sugli utenti registrati per l'accesso
				if (textFieldUsername.getText().contentEquals("") && !controller.back) {
					lblUsernameError.setText("Inserisci username!");
					no=false;
				}			
				if (passwordField.getText().contentEquals("") && !textFieldUsername.getText().contentEquals("")) {
					lblPswError.setText("Password errata!");
					no=false;
				}
				if(no==true) {
					//if(controller.getRuolo().contentEquals("Studente")) {frameGUIUtente=new GUIUtente (controller, frame);}
					//else {frameGUIUtente=new GUIDocente (controller, frame);}
					frameGUIUtente=new GUIDocente(controller, frame);
					frame.setVisible(false);
					frameGUIUtente.setVisible(true);
				}
			}
		});
		btnAccedi.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setBounds(176, 112, 108, 23);
		panelForm.add(btnRegistrati);
		btnRegistrati.setBackground(Color.WHITE);
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frameSelezione=new SelezioneRuolo (controller, frame);
				frame.setVisible(false);
				frameSelezione.setVisible(true);
			}
		});
		btnRegistrati.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 11));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(139, 74, 145, 20);
		panelForm.add(passwordField);
		
		JLabel lblLogin= new JLabel("Login");
		lblLogin.setBounds(199, 109, 91, 36);
		getContentPane().add(lblLogin);
		lblLogin.setForeground(new Color(51, 102, 255));
		lblLogin.setFont(new Font("Lucida Bright", Font.BOLD, 30));
		if (controller.fromRegister && controller.getregisterSuccesful()) {
			controller.fromRegister=false;
			lblRegisterSuccesful.setText("Registrazione avvenuta con successo!");
		}
	}
}
