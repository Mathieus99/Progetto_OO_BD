package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({ "serial", "unused" })
public class CreazioneTest extends JFrame {
	
	private JFrame frame;
	private Controller c;
	private JPanel contentPane;
	private JTextField txtTitoloDiProva;
	private JTextField txtTestoDiProva;
	private JTextField punteggioDomandaMax;
	private JTextField textField;
	private JTable tableDomande;

	public CreazioneTest(Controller c,JFrame guiDocente) {
		setTitle("Legnarino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreazioneTest.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 563);
		Dimension dim = getToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame=this;
		this.c=c;
		
		JPanel panelUser = new JPanel();
		panelUser.setBackground(Color.WHITE);
		panelUser.setBounds(10, 11, 160, 155);
		contentPane.add(panelUser);
		
		JLabel lblIcon = new JLabel("");
		panelUser.add(lblIcon);
		
		JLabel lblNome = new JLabel((String) null);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblNome);
		
		JLabel lblCognome = new JLabel((String) null);
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblCognome);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				guiDocente.setVisible(true);
			}
		});
		btnBack.setBackground(new Color(51, 102, 255));
		btnBack.setForeground(new Color(255, 153, 0));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnBack.setBounds(30, 177, 120, 30);
		contentPane.add(btnBack);
		
		JButton btnCreaTest = new JButton("Crea Test");
		btnCreaTest.setForeground(new Color(255, 153, 0));
		btnCreaTest.setBackground(new Color(51, 102, 255));
		btnCreaTest.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnCreaTest.setBounds(30, 218, 120, 30);
		contentPane.add(btnCreaTest);
		
		JPanel panelDomandeAggiunte = new JPanel();
		panelDomandeAggiunte.setBounds(178, 172, 696, 341);
		contentPane.add(panelDomandeAggiunte);
		
		tableDomande = new JTable();
		tableDomande.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelDomandeAggiunte.add(tableDomande);
		
		JPanel panelInfoTest = new JPanel();
		panelInfoTest.setBackground(Color.WHITE);
		panelInfoTest.setBounds(180, 11, 694, 109);
		contentPane.add(panelInfoTest);
		panelInfoTest.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 11, 188, 29);
		panelInfoTest.add(panel_1);
		
		JLabel lblInformazioni = new JLabel("Informazioni");
		lblInformazioni.setForeground(new Color(51, 102, 255));
		lblInformazioni.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.add(lblInformazioni);
		
		JLabel lblTestTitolo = new JLabel("Test");
		lblTestTitolo.setForeground(new Color(255, 153, 0));
		lblTestTitolo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.add(lblTestTitolo);
		
		JLabel lblNewLabel = new JLabel("Titolo");
		lblNewLabel.setForeground(new Color(255, 153, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 48, 43, 19);
		panelInfoTest.add(lblNewLabel);
		
		txtTitoloDiProva = new JTextField();
		txtTitoloDiProva.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTitoloDiProva.setBounds(75, 45, 160, 25);
		panelInfoTest.add(txtTitoloDiProva);
		txtTitoloDiProva.setColumns(10);
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setForeground(new Color(51, 102, 255));
		lblMateria.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMateria.setBounds(10, 81, 58, 19);
		panelInfoTest.add(lblMateria);
		
		txtTestoDiProva = new JTextField();
		txtTestoDiProva.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTestoDiProva.setColumns(10);
		txtTestoDiProva.setBounds(75, 79, 160, 25);
		panelInfoTest.add(txtTestoDiProva);
		
		JLabel lblPunteggioMaxD = new JLabel("Punteggio Max per domanda");
		lblPunteggioMaxD.setForeground(new Color(51, 102, 255));
		lblPunteggioMaxD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPunteggioMaxD.setBounds(288, 48, 210, 19);
		panelInfoTest.add(lblPunteggioMaxD);
		
		JLabel lblPunteggioMinD = new JLabel("Punteggio Min per domanda");
		lblPunteggioMinD.setForeground(new Color(255, 153, 0));
		lblPunteggioMinD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPunteggioMinD.setBounds(288, 81, 210, 19);
		panelInfoTest.add(lblPunteggioMinD);
		
		punteggioDomandaMax = new JTextField();
		punteggioDomandaMax.setFont(new Font("Tahoma", Font.PLAIN, 17));
		punteggioDomandaMax.setColumns(10);
		punteggioDomandaMax.setBounds(508, 46, 37, 25);
		panelInfoTest.add(punteggioDomandaMax);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setColumns(10);
		textField.setBounds(508, 79, 37, 25);
		panelInfoTest.add(textField);
		
		JButton btnAddDomanda = new JButton("Aggiungi Domanda");
		btnAddDomanda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame addDomanda = new AddDomanda(c,frame);
				frame.setVisible(false);
				addDomanda.setVisible(true);
			}
		});
		btnAddDomanda.setForeground(new Color(255, 153, 0));
		btnAddDomanda.setBackground(new Color(51, 102, 255));
		btnAddDomanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddDomanda.setBounds(426, 136, 200, 30);
		contentPane.add(btnAddDomanda);
	}
}
