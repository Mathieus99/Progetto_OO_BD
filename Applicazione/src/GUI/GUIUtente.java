package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings({"serial" , "unused"})
public class GUIUtente extends JFrame {
	
	private Controller controller;
	JFrame frame;
	private JFrame accessoapp;
	private String ruolo;
	
	public GUIUtente(Controller c, JFrame accesso) {
		setResizable(false);
		setBounds(750, 250, 861, 518);
		Dimension dim = getToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		this.setLocationRelativeTo(null);
		setTitle("Legnarino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIUtente.class.getResource("/Immagini/Legnarino_icon2.png")));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Legnarino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		frame=this;
		controller=c;
		accessoapp=accesso;
		
		/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout(controller.getStudente());
				frame.setVisible(false);
				accesso.setVisible(true);
			}
		});
		btnLogout.setBackground(new Color(0, 102, 255));
		btnLogout.setForeground(new Color(255, 153, 0));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogout.setBounds(74, 442, 698, 29);
		contentPane.add(btnLogout);
		
		JPanel panelUser = new JPanel();
		panelUser.setBackground(Color.WHITE);
		panelUser.setBounds(10, 11, 149, 155);
		contentPane.add(panelUser);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(GUIUtente.class.getResource("/Immagini/User_Iconv2_2.png")));
		panelUser.add(lblIcon);
		
		JLabel lblNome = new JLabel("");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblNome);
		lblNome.setText(controller.nomeS());
		
		JLabel lblCognome = new JLabel("");
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblCognome);
		lblCognome.setText(controller.cognomeS());
		
		JLabel lblBtnDoTest = new JLabel("");
		lblBtnDoTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBtnDoTest.setBackground(Color.GRAY);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame selectTestGUI = new SelectTest(controller, frame);
				frame.setVisible(false);
				selectTestGUI.setVisible(true);
			}
		});
		lblBtnDoTest.setIcon(new ImageIcon(GUIUtente.class.getResource("/Immagini/ButtonDoTest.png")));
		lblBtnDoTest.setBounds(267, 171, 150, 180);
		contentPane.add(lblBtnDoTest);
		
		JLabel lblDoTest = new JLabel("Svolgi Test");
		lblDoTest.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDoTest.setBounds(287, 331, 105, 20);
		contentPane.add(lblDoTest);
	}
}

