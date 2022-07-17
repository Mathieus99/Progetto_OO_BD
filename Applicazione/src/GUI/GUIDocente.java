package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet.ColorAttribute;

import Controller.Controller;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({"serial" , "unused"})
public class GUIDocente extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private Controller controller;
	JFrame accessoapp;
	
	public GUIDocente(Controller c,JFrame accesso) {
		setResizable(false);
		setBounds(750, 250, 876, 576);
		Dimension dim = getToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		this.setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIDocente.class.getResource("/Immagini/Legnarino_icon2.png")));
		setTitle("Legnarino Web Learning");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame=this;
		controller=c;
		accessoapp=accesso;
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout(controller.getDocente());
				frame.setVisible(false);
				accesso.setVisible(true);
			}
		});
		btnLogout.setBackground(new Color(0, 102, 255));
		btnLogout.setForeground(new Color(255, 153, 0));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogout.setBounds(78, 489, 698, 29);
		contentPane.add(btnLogout);
		
		JLabel lblCreaTestBtn = new JLabel("");
		lblCreaTestBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameCreaTest = new CreazioneTest(controller, frame, accessoapp);
				controller.creaTest();
				frame.setVisible(false);
				frameCreaTest.setVisible(true);
			}
		});
		lblCreaTestBtn.setBackground(Color.WHITE);
		lblCreaTestBtn.setIcon(new ImageIcon(GUIDocente.class.getResource("/Immagini/ButtonAppCreaTest.png")));
		lblCreaTestBtn.setBounds(200, 149, 160, 192);
		contentPane.add(lblCreaTestBtn);
		
		JLabel lblCreaTest = new JLabel("Crea Test");
		lblCreaTest.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCreaTest.setBounds(246, 339, 86, 20);
		contentPane.add(lblCreaTest);
		
		JLabel lblCorreggiTest = new JLabel("Correggi Test");
		lblCorreggiTest.setBounds(383, 166, 86, 37);
		contentPane.add(lblCorreggiTest);
		
		JPanel panelUser = new JPanel();
		panelUser.setBackground(Color.WHITE);
		panelUser.setBounds(10, 11, 160, 155);
		contentPane.add(panelUser);
		
		JLabel lblIcon = new JLabel("");
		panelUser.add(lblIcon);
		if(controller.nomeD().contentEquals("Porfirio") && controller.cognomeD().contentEquals("Tramontana"))
			lblIcon.setIcon(new ImageIcon(GUIDocente.class.getResource("/Immagini/User_Icon_Porfirio.png")));
		else
			lblIcon.setIcon(new ImageIcon(GUIDocente.class.getResource("/Immagini/User_Iconv2_2.png")));
		
		JLabel lblNome = new JLabel((String) null);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblNome);
		lblNome.setText(controller.nomeD());
		
		JLabel lblCognome = new JLabel((String) null);
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblCognome);
		lblCognome.setText(controller.cognomeD());
		
		JLabel lblVisualizzaTestBtn = new JLabel("");
		lblVisualizzaTestBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameVisualizzaTest = new VisualizzaTest(controller, frame);
				frame.setVisible(false);
				frameVisualizzaTest.setVisible(true);
			}
		});
		lblVisualizzaTestBtn.setIcon(new ImageIcon(GUIDocente.class.getResource("/Immagini/BtnVisualizza Test.png")));
		lblVisualizzaTestBtn.setBounds(510, 149, 160, 192);
		contentPane.add(lblVisualizzaTestBtn);
		
		JLabel lblNewLabel = new JLabel("Visualizza Test");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(520, 335, 134, 29);
		contentPane.add(lblNewLabel);
		/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
	}
}
