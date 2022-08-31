package GUI;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import DAO.IstanzaDiTestDAO;
import DAO.TestDAO;
import Model.IstanzaDiTest;
import Model.Test;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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
	private Controller controller;
	private JFrame frame;
	private JPanel panelProf = new JPanel();
	private JLabel lblNome = new JLabel("");
	private JLabel lblCognome = new JLabel("");
	private IstanzaDiTest iT;
	private ArrayList<IstanzaDiTest> testDaCorreggere = new ArrayList<IstanzaDiTest>();
	private JTable tableIstanze;

	public SelectCorreggiTest(Controller c, JFrame guiDocente) {
		setTitle("Legnarino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectCorreggiTest.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 434);
		Dimension dim = getToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		controller=c;
		frame=this;
		IstanzaDiTestDAO idao = new IstanzaDiTestDAO(controller);
		
		// Caricamento Test---------------------------------------
		testDaCorreggere = idao.getIstanzeDaCorreggere();
		lblNome.setText(controller.getDocente().getNome());
		lblCognome.setText(controller.getDocente().getCognome());
		panelProf.setBounds(10, 11, lblNome.getWidth()+lblCognome.getWidth(), 39);
		
		
		JLabel lblSelezionaTest = new JLabel("Seleziona il test da correggere :");
		lblSelezionaTest.setForeground(new Color(51, 102, 255));
		lblSelezionaTest.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSelezionaTest.setBounds(10, 61, 243, 30);
		contentPane.add(lblSelezionaTest);		
		
		panelProf.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		panelProf.setBackground(Color.WHITE);
		panelProf.setBounds(10, 11, 300, 39);
		contentPane.add(panelProf);
		panelProf.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelProf.add(lblNome);		

		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelProf.add(lblCognome);		
		
		JPanel panelTest = new JPanel();
		panelTest.setBounds(10, 102, 502, 282);
		contentPane.add(panelTest);
		panelTest.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBack = new JButton("Indietro");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				guiDocente.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setForeground(new Color(255, 153, 0));
		btnBack.setBackground(new Color(51, 102, 255));
		btnBack.setBounds(340, 11, 172, 39);
		contentPane.add(btnBack);
		
		tableIstanze = new JTable();
		tableIstanze.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"IdIstanza" , "Nome", "Cognome"
				}
		));
		DefaultTableModel model = (DefaultTableModel) tableIstanze.getModel();
		model.addRow(new Object[] {"IdIstanza" , "Nome" , "Cognome"});
		for (IstanzaDiTest I: testDaCorreggere) {
			model.addRow(new Object[] {I.getIdIstanza(),I.getIdStudente().getNome(),I.getIdStudente().getCognome()});
		}
		panelTest.add(tableIstanze);
		
		JButton btnCorreggi = new JButton("Correggi");
		btnCorreggi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iT = new IstanzaDiTest();
				for (IstanzaDiTest I: testDaCorreggere) {
					if(I.getIdIstanza() == (Long) tableIstanze.getValueAt(tableIstanze.getSelectedRow(),0))
						iT = I;
				}
				CorreggiTest cT = new CorreggiTest(c,guiDocente,iT);
				frame.setVisible(false);
				cT.setVisible(true);
			}
		});
		btnCorreggi.setForeground(new Color(255, 153, 0));
		btnCorreggi.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCorreggi.setBackground(new Color(51, 102, 255));
		btnCorreggi.setBounds(340, 57, 172, 39);
		contentPane.add(btnCorreggi);
	}
}
