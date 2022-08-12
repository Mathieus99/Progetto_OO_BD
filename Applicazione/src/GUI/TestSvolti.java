package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.IstanzaDiTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

@SuppressWarnings({"serial" , "unused"})
public class TestSvolti extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private Controller c;
	private JTable testSvolti;
	private JTable table;

	public TestSvolti(Controller c, JFrame guiUtente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TestSvolti.class.getResource("/Immagini/Legnarino_icon2.png")));
		setTitle("Legnarino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 537);
		Dimension dim = getToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame = this;
		this.c = c;
		
		JPanel panelUser = new JPanel();
		panelUser.setBackground(Color.WHITE);
		panelUser.setBounds(10, 11, 149, 155);
		contentPane.add(panelUser);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(TestSvolti.class.getResource("/Immagini/User_Iconv2_2.png")));
		panelUser.add(lblIcon);
		
		JLabel lblNome = new JLabel("");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblNome);
		lblNome.setText(c.nomeS());
		
		JLabel lblCognome = new JLabel("");
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblCognome);
		lblCognome.setText(c.cognomeS());
		
		JLabel lblNessunTestSvolto = new JLabel("Nessun Test Svolto!");
		lblNessunTestSvolto.setForeground(new Color(51, 102, 255));
		lblNessunTestSvolto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNessunTestSvolto.setBounds(280, 148, 149, 18);
		contentPane.add(lblNessunTestSvolto);
		lblNessunTestSvolto.setVisible(false);
		
		JPanel panelTestSvolti = new JPanel();
		panelTestSvolti.setBounds(10, 177, 706, 310);
		contentPane.add(panelTestSvolti);
		panelTestSvolti.setLayout(null);
		
		table = new JTable();
		table.setBackground(SystemColor.controlHighlight);
		table.setForeground(Color.BLACK);
		table.setBounds(10, 11, 686, 288);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Materia","NomeTest","NumCorrette","NumErrate","Stato"
				}
		));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{"Materia","NomeTest","NumCorrette","NumErrate","Stato"});
		if(c.getStudente().getTestSostenuti()!=null) {
			for (IstanzaDiTest IdT: c.getStudente().getTestSostenuti())
				model.addRow(new Object[] {IdT.getTest().getCategoria(),IdT.getTest().getTitolo(),IdT.getNumCorrette(),IdT.getNumErrate(),IdT.getStato()});
		}
		else
			lblNessunTestSvolto.setVisible(true);
		panelTestSvolti.add(table);
		
		JPanel panelTitolo = new JPanel();
		panelTitolo.setBackground(Color.WHITE);
		panelTitolo.setBounds(306, 11, 172, 47);
		contentPane.add(panelTitolo);
		
		JLabel lblTest = new JLabel("Test");
		lblTest.setForeground(new Color(255, 153, 0));
		lblTest.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelTitolo.add(lblTest);
		
		JLabel lblSvolti = new JLabel("Svolti");
		lblSvolti.setForeground(new Color(51, 102, 255));
		lblSvolti.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelTitolo.add(lblSvolti);		
		
		JButton btnNewButton = new JButton("Indietro");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				guiUtente.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(51, 102, 255));
		btnNewButton.setForeground(new Color(255, 153, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(572, 119, 144, 47);
		contentPane.add(btnNewButton);
				
	}
}
