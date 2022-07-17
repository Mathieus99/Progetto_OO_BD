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
import java.awt.Dimension;
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

@SuppressWarnings({ "serial", "unused" })
public class Home extends JFrame {

	private Controller controller;
	JFrame frame;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	
	public Home(Controller c) {
		setResizable(false);
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
		setBounds(750, 250, 530, 401);
		Dimension dim = getToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		this.setLocationRelativeTo(null);
		
		setTitle("Legnarino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/Immagini/Legnarino_icon2.png")));
		getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Home.class.getResource("/Immagini/LogoLogin2.png")));
		lblLogo.setBounds(48, -16, 437, 200);
		getContentPane().add(lblLogo);
		JLabel lblRegisterSuccesful = new JLabel("");
		lblRegisterSuccesful.setForeground(new Color(51, 102, 255));
		lblRegisterSuccesful.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterSuccesful.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		lblRegisterSuccesful.setBounds(70, 331, 335, 20);
		getContentPane().add(lblRegisterSuccesful);
		
		JPanel panelForm = new JPanel();
		panelForm.setBackground(Color.WHITE);
		panelForm.setBounds(70, 187, 335, 144);
		getContentPane().add(panelForm);
		panelForm.setLayout(null);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(84, 28, 241, 20);
		panelForm.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 30, 70, 14);
		panelForm.add(lblUsername);
		lblUsername.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 76, 70, 14);
		panelForm.add(lblPassword);
		lblPassword.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(84, 74, 241, 20);
		panelForm.add(passwordField);
		if (controller.fromRegister && controller.getregisterSuccesful()) {
			controller.fromRegister=false;
			lblRegisterSuccesful.setText("Registrazione avvenuta con successo!");
		}
		
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
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				lblRegisterSuccesful.setText("");
				boolean no=true;
				JFrame frameGUIUtente;
				controller.caricaDocenti();
				controller.caricaTest();
				//TODO Inserire controllo sugli utenti registrati per l'accesso
				if (textFieldUsername.getText().contentEquals("") && !controller.back) {
					lblUsernameError.setText("Inserisci username!");
					no=false;
				}
				
				if (passwordField.getText().contentEquals("") && !textFieldUsername.getText().contentEquals("")) {
					lblPswError.setText("Inserisci Password!");
					no=false;
				}
				
				controller.setUtente(textFieldUsername.getText(), passwordField.getText());
								
				if(!controller.checkUtente()) {
					lblRegisterSuccesful.setText("Utente non trovato!");
					no=false;
				}
				
				if(no==true) {
					if(controller.getRuolo().contentEquals("Studente")) 
						frameGUIUtente=new GUIUtente (controller, frame);
					else 
						frameGUIUtente=new GUIDocente (controller, frame);		

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
				JFrame frameRegistrazione=new Registrazione (controller, frame);
				frame.setVisible(false);
				frameRegistrazione.setVisible(true);
			}
		});
		btnRegistrati.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 11));
	}
}
