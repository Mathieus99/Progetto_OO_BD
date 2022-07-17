package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import DAO.TestDAO;
import Model.Domanda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

@SuppressWarnings({ "serial", "unused" })
public class CreazioneTest extends JFrame {
	JFrame frame;
	JFrame guiUtente;
	JFrame accessoapp;
	private Controller controller;
	private JTextField textField;
	private JTextField textFieldNomeTest;
	private JTextField textFieldMateria;
	private JTextField pDomandeMax;
	private JTextField pDomandeMin;
	private JTable tableDomande;
	private JButton btnAddDomanda;

	public CreazioneTest(Controller c, JFrame guiUtente, JFrame accesso) {
		setResizable(false);
		setBounds(750, 250, 922, 649);
		Dimension dim = getToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		this.setLocationRelativeTo(null);
		setTitle("Legnarino ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreazioneTest.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		frame=this;
		controller=c;
		accessoapp=accesso;
		this.guiUtente = guiUtente;
		contentPane.setLayout(null);
		
		JPanel panelUser = new JPanel();
		panelUser.setBackground(Color.WHITE);
		panelUser.setBounds(10, 11, 167, 155);
		contentPane.add(panelUser);
		
		JLabel lblIcon = new JLabel("");
		if (controller.getDocente().getNome().contentEquals("Porfirio"))
			lblIcon.setIcon(new ImageIcon(CreazioneTest.class.getResource("/Immagini/User_Icon_Porfirio.png")));
		else
			lblIcon.setIcon(new ImageIcon(CreazioneTest.class.getResource("/Immagini/User_Iconv2_2.png")));
		panelUser.add(lblIcon);
		
		JLabel lblNome = new JLabel((String) null);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblNome);
		lblNome.setText(controller.nomeD());
		
		JLabel lblCognome = new JLabel((String) null);
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblCognome);
		lblCognome.setText(controller.cognomeD());
		
		JPanel panelTitolo = new JPanel();
		panelTitolo.setBackground(Color.WHITE);
		panelTitolo.setBounds(345, 11, 228, 47);
		contentPane.add(panelTitolo);
		
		JLabel lblTitolo = new JLabel("Creazione");
		lblTitolo.setForeground(new Color(255, 153, 0));
		lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelTitolo.add(lblTitolo);
		
		JLabel lblTitolo2 = new JLabel("Test");
		lblTitolo2.setForeground(new Color(51, 102, 255));
		lblTitolo2.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelTitolo.add(lblTitolo2);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnAnnulla.setForeground(new Color(51, 102, 255));
				btnAnnulla.setBackground(new Color(255, 153, 0));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnAnnulla.setForeground(new Color(255, 153, 0));
				btnAnnulla.setBackground(new Color(51, 102, 255));
				frame.setVisible(false);
				guiUtente.setVisible(true);
			}
		});
		btnAnnulla.setForeground(new Color(255, 153, 0));
		btnAnnulla.setBackground(new Color(51, 102, 255));
		btnAnnulla.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
		btnAnnulla.setBounds(10, 177, 149, 31);
		contentPane.add(btnAnnulla);
		
		JPanel panelDatiTest = new JPanel();
		panelDatiTest.setBackground(new Color(51, 102, 255));
		panelDatiTest.setBounds(187, 89, 461, 97);
		contentPane.add(panelDatiTest);
		panelDatiTest.setLayout(null);
		
		textFieldNomeTest = new JTextField();
		textFieldNomeTest.setBounds(10, 30, 175, 20);
		panelDatiTest.add(textFieldNomeTest);
		textFieldNomeTest.setColumns(10);
		
		JLabel lblNomeTest = new JLabel("Nome Test");
		lblNomeTest.setForeground(new Color(255, 153, 0));
		lblNomeTest.setBounds(10, 11, 97, 14);
		panelDatiTest.add(lblNomeTest);
		lblNomeTest.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFieldMateria = new JTextField();
		textFieldMateria.setBounds(267, 30, 175, 20);
		panelDatiTest.add(textFieldMateria);
		textFieldMateria.setColumns(10);
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setForeground(new Color(255, 153, 0));
		lblMateria.setBounds(267, 11, 57, 14);
		panelDatiTest.add(lblMateria);
		lblMateria.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPunteggioDomandeMax = new JLabel("Punteggio Domande max");
		lblPunteggioDomandeMax.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPunteggioDomandeMax.setForeground(new Color(255, 153, 0));
		lblPunteggioDomandeMax.setBounds(10, 52, 188, 19);
		panelDatiTest.add(lblPunteggioDomandeMax);
		
		JLabel lblPunteggioDomandeMin = new JLabel("Punteggio Domande min");
		lblPunteggioDomandeMin.setForeground(new Color(255, 153, 0));
		lblPunteggioDomandeMin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPunteggioDomandeMin.setBounds(10, 72, 188, 19);
		panelDatiTest.add(lblPunteggioDomandeMin);
		
		pDomandeMax = new JTextField();
		pDomandeMax.setBounds(212, 54, 30, 20);
		panelDatiTest.add(pDomandeMax);
		pDomandeMax.setColumns(10);
		
		pDomandeMin = new JTextField();
		pDomandeMin.setColumns(10);
		pDomandeMin.setBounds(212, 74, 30, 20);
		panelDatiTest.add(pDomandeMin);
		
		JPanel panelDomandeTest = new JPanel();
		panelDomandeTest.setBounds(187, 197, 709, 402);
		contentPane.add(panelDomandeTest);
		
		tableDomande = new JTable();
		tableDomande.setBounds(10, 10, 689, 382);
		tableDomande.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableDomande.setBackground(Color.WHITE);
		tableDomande.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"NDomanda", "TestoDomanda"
				}
			));
			DefaultTableModel model = (DefaultTableModel) tableDomande.getModel();
			ArrayList<Domanda> domandeTest = new ArrayList<Domanda>();
			if(controller.getTest() != null)
				domandeTest = controller.getTest().getDomande();
			if (domandeTest != null)
				for (int i=0;i<domandeTest.size();i++) {
					model.addRow(new Object[] {i+1,domandeTest.get(i).getTestoDomanda()});					
				}
		panelDomandeTest.setLayout(null);
		panelDomandeTest.add(tableDomande);
		
		btnAddDomanda = new JButton("Aggiungi Domanda");
		btnAddDomanda.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnAddDomanda.setForeground(new Color(51, 102, 255));
				btnAddDomanda.setBackground(new Color(255, 153, 0));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnAddDomanda.setForeground(new Color(255, 153, 0));
				btnAddDomanda.setBackground(new Color(51, 102, 255));
				JFrame guiAggiungiDomanda = new AggiungiDomanda(c, frame);
				frame.setVisible(false);
				guiAggiungiDomanda.setVisible(true);				
			}
		});
		btnAddDomanda.setForeground(new Color(255, 153, 0));
		btnAddDomanda.setBackground(new Color(51, 102, 255));
		btnAddDomanda.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddDomanda.setBounds(658, 89, 228, 59);
		contentPane.add(btnAddDomanda);
		
		JButton btnCreaTest = new JButton("Crea Test");
		btnCreaTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreaTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCreaTest.setForeground(new Color(51, 102, 255));
				btnCreaTest.setBackground(new Color(255, 153, 0));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnCreaTest.setForeground(new Color(255, 153, 0));
				btnCreaTest.setBackground(new Color(51, 102, 255));
				controller.getTest().setTitolo(textFieldNomeTest.getText());
				controller.getTest().setCategoria(textFieldMateria.getText());
				controller.getTest().setPunteggioDomandeMax(Integer.parseInt(pDomandeMax.getText()));
				controller.getTest().setPunteggioDomandeMin(Integer.parseInt(pDomandeMin.getText()));
				controller.getTest().setMaxPunteggio();
				controller.getTest().setNumeroDomande();
				int aperte = 0, multiple = 0;
				for (Domanda d: controller.getTest().getDomande()) {
					if (d.getTipo().contentEquals("Aperta"))
						aperte++;
					else
						multiple++;
				}
				if (aperte == controller.getTest().getDomande().size())
					controller.getTest().setTipo("Aperta");
				else if (multiple == controller.getTest().getDomande().size())
					controller.getTest().setTipo("Multipla");
				else
					controller.getTest().setTipo("Mista");
				controller.submitCreaTest();
				TestDAO tdao = new TestDAO();
				tdao.saveTest(controller.getTest());
				frame.setVisible(false);
				guiUtente.setVisible(true);
			}
		});
		btnCreaTest.setForeground(new Color(255, 153, 0));
		btnCreaTest.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
		btnCreaTest.setBackground(new Color(51, 102, 255));
		btnCreaTest.setBounds(10, 219, 149, 31);
		contentPane.add(btnCreaTest);
		
		/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	}
}
