package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Model.Test;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VisualizzaTest extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private Controller controller;
	private JTable table_1;
	

	public VisualizzaTest(Controller c, JFrame guiDocente) {
		setResizable(false);
		setBounds(100, 100, 852, 591);
		Dimension dim = getToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		this.setLocationRelativeTo(null);
		setTitle("Legnarino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VisualizzaTest.class.getResource("/Immagini/Legnarino_icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame = this;
		controller = c;
		
		JLabel lblTitolo = new JLabel("Test Creati");
		lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitolo.setBounds(355, 9, 141, 29);
		contentPane.add(lblTitolo);
		
		JButton btnPietro = new JButton("Indietro");
		btnPietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnPietro.setBackground(new Color(255, 153, 0));
				btnPietro.setForeground(new Color(51, 102, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnPietro.setBackground(new Color(51, 102, 255));
				btnPietro.setForeground(new Color(255, 153, 0));
				frame.setVisible(false);
				guiDocente.setVisible(true);
			}
		});
		btnPietro.setBackground(new Color(51, 102, 255));
		btnPietro.setForeground(new Color(255, 153, 0));
		btnPietro.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		btnPietro.setBounds(10, 10, 141, 33);
		contentPane.add(btnPietro);
		
		table_1 = new JTable();
		table_1.setBackground(Color.WHITE);
		table_1.setBounds(10, 72, 816, 469);
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"NomeTest", "NumeroDomande", "PunteggioMassimo", "Materia", "PunteggioDMax", "PunteggioDMin"
				}
			));
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			ArrayList<Test> testDocente = new ArrayList<Test>();
			testDocente = controller.getTestDocente();
			if (testDocente!=null)
				for (int i=0;i<testDocente.size();i++) 
					model.addRow(new Object[]{testDocente.get(i).getTitolo(),testDocente.get(i).getNumeroDomande(),testDocente.get(i).getMaxPunteggio(),testDocente.get(i).getCategoria(),testDocente.get(i).getPunteggioDomandeMax(),testDocente.get(i).getPunteggioDomandeMin()});
		contentPane.add(table_1);
		
		
	}
}
