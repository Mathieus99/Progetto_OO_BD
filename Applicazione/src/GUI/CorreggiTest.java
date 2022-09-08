package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import DAO.DomandaDAO;
import DAO.IstanzaDiTestDAO;
import DAO.RispostaUtenteDAO;
import Model.Domanda;
import Model.IstanzaDiTest;
import Model.RispostaUtente;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.FlowLayout;

@SuppressWarnings({"serial","unused"})
public class CorreggiTest extends JFrame {
	
	private int larghezzaRisposte=0;
	private JPanel contentPane;
	private JPanel panelDomande = new JPanel();
	private Controller c;
	private JFrame frame;
	private JTable tableDomande = new JTable();
	private JButton btnInvia;
	private ArrayList<Domanda> domandeTest = new ArrayList<Domanda>();
	
	public CorreggiTest(Controller c, JFrame guiDocente,IstanzaDiTest iT) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				btnInvia.setBounds(contentPane.getWidth()-198, 17, 172, 39);
				panelDomande.setBounds(10, 67, contentPane.getWidth()-36, 414);
			}
		});
		setTitle("Legnarino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CorreggiTest.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 531);
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
		DomandaDAO ddao = new DomandaDAO();
		domandeTest = ddao.getDomandeTest(iT.getIdTest());
		
		for(int i=0;i<iT.getRisposteUtente().size();i++) {
			if(larghezzaRisposte<iT.getRisposteUtente().get(i).getTestoRisposta().length())
				larghezzaRisposte=iT.getRisposteUtente().get(i).getTestoRisposta().length();
		}
		
		tableDomande.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		tableDomande.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Domanda" , "Risposta", "Punteggio Min","Punteggio Max","Punteggio Domanda"
				}
		));
		DefaultTableModel model = (DefaultTableModel) tableDomande.getModel();
		model.addRow(new Object[] {"Domanda" , "Risposta" , "Min","Max","P. Domanda"});
		for (int i=0;i<iT.getRisposteUtente().size();i++) {
				model.addRow(new Object[] {i+1,iT.getRisposteUtente().get(i).getTestoRisposta(),iT.getTest().getPunteggioDomandeMin(),iT.getTest().getPunteggioDomandeMax(),iT.getRisposteUtente().get(i).getPunteggio()});
		}
		tableDomande.setRowHeight(20);
		tableDomande.getColumnModel().getColumn(1).setPreferredWidth(7*larghezzaRisposte);
		tableDomande.getColumnModel().getColumn(2).setPreferredWidth(30);
		tableDomande.getColumnModel().getColumn(3).setPreferredWidth(30);
		tableDomande.getColumnModel().getColumn(4).setPreferredWidth(100);
		panelDomande.add(tableDomande);
		
		panelDomande.setBounds(10, 67, 964, 414);
		contentPane.add(panelDomande);
		
		JPanel panelStudente = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelStudente.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelStudente.setBackground(Color.WHITE);
		panelStudente.setBounds(10, 11, 350, 45);
		contentPane.add(panelStudente);
		
		JLabel lblNewLabel = new JLabel("Studente:");
		lblNewLabel.setForeground(new Color(51, 102, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelStudente.add(lblNewLabel);
		
		JLabel lblNomeS = new JLabel("");
		lblNomeS.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelStudente.add(lblNomeS);
		lblNomeS.setText(iT.getIdStudente().getNome());
		
		JLabel lblCognomeS = new JLabel("");
		lblCognomeS.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelStudente.add(lblCognomeS);
		lblCognomeS.setText(iT.getIdStudente().getCognome());
		
		btnInvia = new JButton("Invia");
		btnInvia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IstanzaDiTestDAO iTdao = new IstanzaDiTestDAO(c);
				RispostaUtenteDAO rUdao = new RispostaUtenteDAO();
				for(int i=1;i<tableDomande.getRowCount();i++) {
					int p=0;
					if(tableDomande.getValueAt(i, 4).getClass().toString().contentEquals("class java.lang.String")) {
						p = Integer.parseInt((String) tableDomande.getValueAt(i, 4));
					} else {
						p = (int) tableDomande.getValueAt(i, 4);
					}
					iT.getRisposteUtente().get(i-1).setPunteggio(p);
				}
				iT.setStato(IstanzaDiTest.Valutato);
				rUdao.aggiornaPunteggi(iT.getRisposteUtente());
				iTdao.aggiornaTestCorretto(iT);
				frame.setVisible(false);
				guiDocente.setVisible(true);
			}
		});
		btnInvia.setForeground(new Color(255, 153, 0));
		btnInvia.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnInvia.setBackground(new Color(51, 102, 255));
		btnInvia.setBounds(802, 17, 172, 39);
		contentPane.add(btnInvia);
	}
}
