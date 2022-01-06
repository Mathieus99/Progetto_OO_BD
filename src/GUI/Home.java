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
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		lblUsername.setBounds(148, 132, 70, 14);
		getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(228, 130, 145, 20);
		getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		lblPassword.setBounds(148, 171, 70, 14);
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(228, 169, 145, 20);
		getContentPane().add(passwordField);
		
		JLabel lblUsernameError = new JLabel("");
		lblUsernameError.setForeground(Color.RED);
		lblUsernameError.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblUsernameError.setBounds(228, 150, 145, 14);
		getContentPane().add(lblUsernameError);
		
		JLabel lblPswError = new JLabel("");
		lblPswError.setForeground(Color.RED);
		lblPswError.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		lblPswError.setBounds(228, 188, 145, 14);
		getContentPane().add(lblPswError);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblUsernameError.setText("");
				lblPswError.setText("");
				boolean ok=true;
				if (textFieldUsername.getText().contentEquals("")) {
					lblUsernameError.setText("Inserisci username!");
					ok=false;
				}
				if (passwordField.getText().contentEquals("") && !textFieldUsername.getText().contentEquals("")) {
					lblPswError.setText("Password errata!");
					ok=false;
				}
				if(ok) {
					
				}
			}
		});
		btnAccedi.setFont(new Font("Lucida Bright", Font.BOLD, 11));
		btnAccedi.setBounds(148, 213, 87, 23);
		getContentPane().add(btnAccedi);
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frameSelezione=new SelezioneRuolo (controller, frame);
				frame.setVisible(false);
				frameSelezione.setVisible(true);
			}
		});
		btnRegistrati.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 11));
		btnRegistrati.setBounds(265, 213, 108, 23);
		getContentPane().add(btnRegistrati);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("H:\\DESKTOP\\ProvaLogo4.png"));
		lblNewLabel.setBounds(180, 11, 150, 108);
		getContentPane().add(lblNewLabel);		
	}
}
