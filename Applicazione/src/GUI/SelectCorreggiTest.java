package GUI;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import DAO.IstanzaDiTestDAO;
import DAO.TestDAO;
import Model.IstanzaDiTest;
import Model.Test;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({"serial","unused"})
public class SelectCorreggiTest extends JFrame {

	private JPanel contentPane;
	private JPanel panelStudente;
	private JPanel lblNomeS;
	private JPanel lblCognomeS;
	private Controller c;
	private JFrame frame;
	private JLabel lblNome;
	private JLabel lblCognome;
	private ArrayList<IstanzaDiTest> testDaCorreggere = new ArrayList<IstanzaDiTest>();
	private IstanzaDiTestDAO idao;

	public SelectCorreggiTest(Controller c, JFrame guiDocente) {
		setTitle("Legnarino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectCorreggiTest.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 434);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.c=c;
		frame=this;
		
		// Caricamento Test---------------------------------------
		testDaCorreggere = idao.getIstanzeDaCorreggere();
		
		
		JLabel lblSelezionaTest = new JLabel("Seleziona il test da correggere :");
		lblSelezionaTest.setForeground(new Color(51, 102, 255));
		lblSelezionaTest.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSelezionaTest.setBounds(10, 61, 243, 30);
		contentPane.add(lblSelezionaTest);
		
		JPanel panelProf = new JPanel();
		panelProf.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		panelProf.setBackground(Color.WHITE);
		panelProf.setBounds(10, 11, lblNome.getWidth()+lblCognome.getWidth(), 39);
		contentPane.add(panelProf);
		panelProf.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNome = new JLabel("");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelProf.add(lblNome);
		lblNome.setText(c.getDocente().getNome());
		
		lblCognome = new JLabel("");
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelProf.add(lblCognome);
		lblCognome.setText(c.getDocente().getCognome());
		
		JPanel panelTest = new JPanel();
		panelTest.setBounds(10, 102, 502, 282);
		contentPane.add(panelTest);
		panelTest.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//--------Panel Studenti da correggere---------------------------
		lblNomeS.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelStudente.add(lblNomeS);
		
		lblCognomeS.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelStudente.add(lblCognomeS);
		
		JButton btnCorreggi = new JButton("Correggi");
		btnCorreggi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				CorreggiTest frameCorreggi = new CorreggiTest(c,guiDocente);
				frame.setVisible(false);
				frameCorreggi.setVisible(true);				
			}
		});
		btnCorreggi.setBackground(new Color(51, 102, 255));
		btnCorreggi.setForeground(new Color(255, 153, 0));
		btnCorreggi.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelStudente.add(btnCorreggi);
		
		//-----------------------------------------------------------------
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setForeground(new Color(255, 153, 0));
		btnBack.setBackground(new Color(51, 102, 255));
		btnBack.setBounds(340, 11, 172, 39);
		contentPane.add(btnBack);
		
	}
}
