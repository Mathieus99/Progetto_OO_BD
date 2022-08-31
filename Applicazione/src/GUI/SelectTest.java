package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import DAO.*;
import Model.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

@SuppressWarnings({"serial","unused"})
public class SelectTest extends JFrame {
	
	private JPanel contentPane;
	private JFrame frame;
	private Controller controller;
	private ArrayList<Insegnante> docenti = new ArrayList<Insegnante>();
	private ArrayList<Test> testDocente = new ArrayList<Test>();
	private JComboBox<String> comboBoxMateria = new JComboBox<String>();
	private JComboBox<String> comboBoxTitoloTest = new JComboBox<String>();
	private JButton btnDoTest = new JButton("Svolgi Test");
	private JTextField PunteggioMaxVal;
	private JTextField NumeroDomandeVal;
	private JTextField PunteggioDMaxVal;
	private JTextField PunteggioDMinVal;

	public SelectTest(Controller c, JFrame guiUtente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectTest.class.getResource("/Immagini/Legnarino_icon2.png")));
		setTitle("Legnarino");
		setResizable(false);
		setBounds(100, 100, 820, 386);
		Dimension dim = getToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame = this;
		controller = c;
		
		/*------------------------------------------TITOLO---------------------------------------*/
		
		JPanel panelTitolo = new JPanel();
		panelTitolo.setBackground(Color.WHITE);
		panelTitolo.setBounds(10, 10, 228, 41);
		contentPane.add(panelTitolo);
		panelTitolo.setLayout(null);
		
		JLabel lblSelezione = new JLabel("Selezione");
		lblSelezione.setForeground(new Color(255, 153, 51));
		lblSelezione.setBackground(Color.WHITE);
		lblSelezione.setBounds(10, 5, 151, 31);
		lblSelezione.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelTitolo.add(lblSelezione);
		
		JLabel lblTest = new JLabel("Test");
		lblTest.setForeground(new Color(51, 102, 255));
		lblTest.setBackground(Color.WHITE);
		lblTest.setBounds(160, 5, 66, 31);
		lblTest.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelTitolo.add(lblTest);
		
		/*---------------------------------------------------------------------------------------*/
		
		/*-------------------------------------SELEZIONE PROF------------------------------------*/
		
		JLabel lblProf = new JLabel("Professore");
		lblProf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProf.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProf.setBounds(10, 99, 113, 28);
		contentPane.add(lblProf);
		
		JComboBox<String> comboBoxProf = new JComboBox<String>();
		comboBoxProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestDAO tdao = new TestDAO();
				for (Insegnante i: docenti)
					if(comboBoxProf.getSelectedItem().toString().contains(i.getNome()) && comboBoxProf.getSelectedItem().toString().contains(i.getCognome()))
						testDocente = tdao.getTestDocente(i.getIdDocente());
				comboBoxMateria.removeAllItems();
				for(Test t: testDocente)
					comboBoxMateria.addItem(t.getCategoria());
			}
		});
		comboBoxProf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		InsegnanteDAO idao = new InsegnanteDAO();
		docenti = idao.getListaDocenti();
		for (Insegnante i: docenti) {
			comboBoxProf.addItem(i.toString());
		}
		comboBoxProf.setBounds(133, 99, 178, 28);
		contentPane.add(comboBoxProf);
		
		/*---------------------------------------------------------------------------------------*/
		
		JPanel panelTest = new JPanel();
		panelTest.setBounds(321, 99, 475, 196);
		contentPane.add(panelTest);
		panelTest.setLayout(null);
		
		JLabel lblInfoTest = new JLabel("Informazioni Test");
		lblInfoTest.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInfoTest.setBounds(10, 11, 160, 22);
		panelTest.add(lblInfoTest);
		
		JPanel panelPunteggioMax = new JPanel();
		panelPunteggioMax.setBorder(new LineBorder(new Color(51, 102, 255), 2));
		panelPunteggioMax.setBounds(10, 44, 220, 40);
		panelTest.add(panelPunteggioMax);
		
		JLabel lblPunteggioMax = new JLabel("Punteggio Massimo");
		panelPunteggioMax.add(lblPunteggioMax);
		lblPunteggioMax.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		PunteggioMaxVal = new JTextField();
		PunteggioMaxVal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PunteggioMaxVal.setEditable(false);
		panelPunteggioMax.add(PunteggioMaxVal);
		PunteggioMaxVal.setColumns(3);
		
		JPanel panelNumDomande = new JPanel();
		panelNumDomande.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		panelNumDomande.setBounds(245, 44, 220, 40);
		panelTest.add(panelNumDomande);
		
		JLabel lblNumeroDomande = new JLabel("Numero Domande");
		panelNumDomande.add(lblNumeroDomande);
		lblNumeroDomande.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		NumeroDomandeVal = new JTextField();
		NumeroDomandeVal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		NumeroDomandeVal.setEditable(false);
		panelNumDomande.add(NumeroDomandeVal);
		NumeroDomandeVal.setColumns(3);
		
		JPanel panelPunteggioDMax = new JPanel();
		panelPunteggioDMax.setBorder(new LineBorder(new Color(51, 102, 255), 2));
		panelPunteggioDMax.setBounds(10, 90, 300, 40);
		panelTest.add(panelPunteggioDMax);
		
		JLabel lblPunteggioMassimoDomanda = new JLabel("Punteggio Massimo Domanda");
		panelPunteggioDMax.add(lblPunteggioMassimoDomanda);
		lblPunteggioMassimoDomanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		PunteggioDMaxVal = new JTextField();
		PunteggioDMaxVal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PunteggioDMaxVal.setEditable(false);
		panelPunteggioDMax.add(PunteggioDMaxVal);
		PunteggioDMaxVal.setColumns(3);
		
		JPanel panelPunteggioDMin = new JPanel();
		panelPunteggioDMin.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		panelPunteggioDMin.setBounds(10, 140, 300, 40);
		panelTest.add(panelPunteggioDMin);
		
		JLabel lblPunteggioMinimoDomanda = new JLabel("Punteggio Minimo Domanda");
		panelPunteggioDMin.add(lblPunteggioMinimoDomanda);
		lblPunteggioMinimoDomanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		PunteggioDMinVal = new JTextField();
		PunteggioDMinVal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PunteggioDMinVal.setEditable(false);
		panelPunteggioDMin.add(PunteggioDMinVal);
		PunteggioDMinVal.setColumns(3);
		
		comboBoxMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxTitoloTest.removeAllItems();
				for (Test t: testDocente)
					if(comboBoxMateria.getSelectedItem()!= null)
						if(comboBoxMateria.getSelectedItem().toString().contains(t.getCategoria()))
							comboBoxTitoloTest.addItem(t.getTitolo());
			}
		});
		comboBoxMateria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxMateria.setBounds(133, 133, 178, 28);
		contentPane.add(comboBoxMateria);
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMateria.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMateria.setBounds(10, 133, 113, 28);
		contentPane.add(lblMateria);
		
		comboBoxTitoloTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Test t: testDocente)
					if(comboBoxTitoloTest.getSelectedItem()!=null)
						if(comboBoxTitoloTest.getSelectedItem().toString().contains(t.getTitolo())) {
							PunteggioMaxVal.setText(Double.toString(t.getMaxPunteggio()));
							NumeroDomandeVal.setText(Double.toString(t.getNumeroDomande()));
							PunteggioDMaxVal.setText(Double.toString(t.getPunteggioDomandeMax()));
							PunteggioDMinVal.setText(Double.toString(t.getPunteggioDomandeMin()));
						}
							
			}
		});
		
		comboBoxTitoloTest.setBounds(133, 167, 178, 28);
		contentPane.add(comboBoxTitoloTest);
		
		JLabel lblTitoloTest = new JLabel("Titolo");
		lblTitoloTest.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitoloTest.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitoloTest.setBounds(10, 167, 113, 28);
		contentPane.add(lblTitoloTest);
		
		btnDoTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frame.setVisible(false);
				for (Test t: testDocente) {
					System.out.println(t.getTitolo()+" "+t.getCategoria()+" "+t.getProprietario()+" "+t.getDomande().size()+" Selezionato - "+comboBoxTitoloTest.getSelectedItem().toString()+" "+comboBoxMateria.getSelectedItem().toString());
					if(comboBoxTitoloTest.getSelectedItem().toString().contains(t.getTitolo()) && comboBoxMateria.getSelectedItem().toString().contains(t.getCategoria())) {
						c.setTestInCorso(t);
						System.out.println("Test in corso: "+c.getTestInCorso().getTitolo() +" - "+c.getTestInCorso().getCategoria());
						c.setIstanzaDiTest(t, c.getStudente());				
						DoTest dT = new DoTest(c,guiUtente);
					}
				}
			}
		});
		btnDoTest.setBackground(new Color(51, 102, 255));
		btnDoTest.setForeground(new Color(255, 153, 0));
		btnDoTest.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnDoTest.setBounds(534, 11, 262, 48);
		contentPane.add(btnDoTest);
		
	}
}
