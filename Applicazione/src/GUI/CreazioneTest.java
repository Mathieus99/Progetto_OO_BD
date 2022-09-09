package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import DAO.DomandaDAO;
import DAO.RispostaDAO;
import DAO.TestDAO;
import Model.Domanda;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({ "serial", "unused" })
public class CreazioneTest extends JFrame {
	
	private CreazioneTest frame;
	private Controller c;
	private JPanel contentPane;
	private JTextField txtTitoloTest;
	private JTextField txtMateriaTest;
	private JTextField punteggioDomandaMax;
	private JTextField punteggioDMin;
	private JTable tableDomande;
	private DefaultTableModel model;
	private JLabel TitoloTestError;
	private JLabel MateriaTestError;
	private JLabel PunteggioMaxError;
	private JLabel PunteggioMinError;
	private JLabel lblInfoTestError;
	private JLabel lblDomandeError;
	private boolean ok;
	
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
		if(c.nomeD().contentEquals("Porfirio") && c.cognomeD().contentEquals("Tramontana"))
			lblIcon.setIcon(new ImageIcon(GUIDocente.class.getResource("/Immagini/User_Icon_Porfirio.png")));
		else
			lblIcon.setIcon(new ImageIcon(GUIDocente.class.getResource("/Immagini/User_Iconv2_2.png")));
		
		JLabel lblNome = new JLabel((String) null);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblNome);
		lblNome.setText(c.nomeD());
		
		JLabel lblCognome = new JLabel((String) null);
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelUser.add(lblCognome);
		lblCognome.setText(c.cognomeD());
		
		JButton btnBack = new JButton("Indietro");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.resetTest();
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
		btnCreaTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ok=true;
				TitoloTestError.setVisible(false);
				MateriaTestError.setVisible(false);
				PunteggioMaxError.setVisible(false);
				PunteggioMinError.setVisible(false);
				lblInfoTestError.setVisible(false);
				lblDomandeError.setVisible(false);
				if(txtTitoloTest.getText().contentEquals("")) {
					TitoloTestError.setVisible(true);
					lblInfoTestError.setVisible(true);
					ok=false;
				}
				if(txtMateriaTest.getText().contentEquals("")) {
					MateriaTestError.setVisible(true);
					lblInfoTestError.setVisible(true);
					ok=false;
				}
				if(punteggioDomandaMax.getText().contentEquals("")) {
					PunteggioMaxError.setVisible(true);
					lblInfoTestError.setVisible(true);
					ok=false;
				}
				if(punteggioDMin.getText().contentEquals("")) {
					PunteggioMinError.setVisible(true);
					lblInfoTestError.setVisible(true);
					ok=false;
				}
				if(c.getTest().getDomande().size()==0) {
					lblDomandeError.setVisible(true);
					ok=false;
				}
				if(ok) {
					c.getTest().setTitolo(txtTitoloTest.getText());
					c.getTest().setPunteggioDomandeMin(Double.parseDouble(punteggioDMin.getText()));
					c.getTest().setPunteggioDomandeMax(Double.parseDouble(punteggioDomandaMax.getText()));
					c.getTest().setCategoria(txtMateriaTest.getText());
					c.getTest().setProprietario(c.getDocente().getIdDocente());
					if(c.getTest().getDomande().get(0).getTipo().contentEquals("Aperta")) {
						c.getTest().setTipo("Aperta");
					} else {
						c.getTest().setTipo("Multipla");
					}
					for(int i=0;i<c.getTest().getDomande().size();i++)
						if((c.getTest().getTipo().contentEquals("Aperta") && c.getTest().getDomande().get(i).getTipo().contentEquals("Multipla")) || (c.getTest().getTipo().contentEquals("Multipla") && c.getTest().getDomande().get(i).getTipo().contentEquals("Aperta"))) {
							c.getTest().setTipo("Mista");
							break;
						}
					c.getTest().setNumeroDomande(c.getTest().getDomande().size());
					c.getTest().setMaxPunteggio(c.getTest().getPunteggioDomandeMax()*c.getTest().getNumeroDomande());
					TestDAO tdao = new TestDAO();
					DomandaDAO ddao = new DomandaDAO();
					RispostaDAO rdao = new RispostaDAO();
					tdao.saveTest(c.getTest());
					for (Domanda d: c.getTest().getDomande()) {
						ddao.saveDomanda(d);
						rdao.saveRisposte(d.getRisposte());
					}				
					frame.setVisible(false);
					guiDocente.setVisible(true);
				}
			}
		});
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
		tableDomande.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID Domanda", "Testo Domanda"
				}
		));
		model = (DefaultTableModel) tableDomande.getModel();
		model.addRow(new Object[] {"ID Domanda","TestoDomanda"});
		tableDomande.getColumnModel().getColumn(1).setPreferredWidth(400);
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
		
		txtTitoloTest = new JTextField();
		txtTitoloTest.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTitoloTest.setBounds(75, 45, 160, 25);
		panelInfoTest.add(txtTitoloTest);
		txtTitoloTest.setColumns(10);
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setForeground(new Color(51, 102, 255));
		lblMateria.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMateria.setBounds(10, 81, 58, 19);
		panelInfoTest.add(lblMateria);
		
		txtMateriaTest = new JTextField();
		txtMateriaTest.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMateriaTest.setColumns(10);
		txtMateriaTest.setBounds(75, 79, 160, 25);
		panelInfoTest.add(txtMateriaTest);
		
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
		
		punteggioDMin = new JTextField();
		punteggioDMin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		punteggioDMin.setColumns(10);
		punteggioDMin.setBounds(508, 79, 37, 25);
		panelInfoTest.add(punteggioDMin);
		
		TitoloTestError = new JLabel("*");
		TitoloTestError.setForeground(Color.RED);
		TitoloTestError.setFont(new Font("Tahoma", Font.BOLD, 17));
		TitoloTestError.setBounds(239, 49, 16, 14);
		panelInfoTest.add(TitoloTestError);
		TitoloTestError.setVisible(false);
		
		MateriaTestError = new JLabel("*");
		MateriaTestError.setForeground(Color.RED);
		MateriaTestError.setFont(new Font("Tahoma", Font.BOLD, 17));
		MateriaTestError.setBounds(239, 85, 16, 14);
		panelInfoTest.add(MateriaTestError);
		MateriaTestError.setVisible(false);
		
		PunteggioMaxError = new JLabel("*");
		PunteggioMaxError.setForeground(Color.RED);
		PunteggioMaxError.setFont(new Font("Tahoma", Font.BOLD, 17));
		PunteggioMaxError.setBounds(549, 52, 16, 14);
		panelInfoTest.add(PunteggioMaxError);
		PunteggioMaxError.setVisible(false);
		
		PunteggioMinError = new JLabel("*");
		PunteggioMinError.setForeground(Color.RED);
		PunteggioMinError.setFont(new Font("Tahoma", Font.BOLD, 17));
		PunteggioMinError.setBounds(549, 85, 16, 14);
		panelInfoTest.add(PunteggioMinError);
		PunteggioMinError.setVisible(false);
		
		lblInfoTestError = new JLabel("Mancano delle informazioni!");
		lblInfoTestError.setBounds(301, 11, 210, 25);
		panelInfoTest.add(lblInfoTestError);
		lblInfoTestError.setForeground(Color.RED);
		lblInfoTestError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInfoTestError.setVisible(false);
		
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
		
		lblDomandeError = new JLabel("Non ci sono domande!");
		lblDomandeError.setForeground(Color.RED);
		lblDomandeError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDomandeError.setBounds(232, 139, 169, 25);
		contentPane.add(lblDomandeError);
		lblDomandeError.setVisible(false);
	}
	
	public DefaultTableModel getTableModel() {
		return model;
	}
}
