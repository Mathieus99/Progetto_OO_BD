package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.Controller;
import DAO.IstanzaDiTestDAO;
import DAO.RispostaDAO;
import DAO.RispostaUtenteDAO;

public class TestDone {
	
	private JFrame frame;
	Controller c;
	JFrame guiUtente;
	
	public TestDone(Controller c, JFrame guiUtente) {
		this.c = c;
		this.guiUtente = guiUtente;
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		
		// Creazione del frame---------------------------------------------------------
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setTitle("Legnarino");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(TestDone.class.getResource("/Immagini/Legnarino_icon2.png")));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 400, 214);
		Dimension dim = frame.getToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);	
		//-----------------------------------------------------------------------------
		
		JLabel lblTestDone = new JLabel("Test Completato");
		lblTestDone.setForeground(new Color(51, 102, 255));
		lblTestDone.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTestDone.setBounds(81, 11, 205, 64);
		frame.getContentPane().add(lblTestDone);
		
		JButton btnBack = new JButton("Torna al Menu");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IstanzaDiTestDAO IdTDAO = new IstanzaDiTestDAO();
				IdTDAO.insertIstanza(c.getIstanzaDiTest());
				RispostaDAO rdao = new RispostaDAO();
				rdao.checkPunteggioRisposta(c.getIstanzaDiTest());
				RispostaUtenteDAO rUDAO = new RispostaUtenteDAO();
				rUDAO.saveRisposteUtente(c.getIstanzaDiTest().getRisposteUtente());
				frame.setVisible(false);
				guiUtente.setVisible(true);
			}
		});
		btnBack.setBackground(new Color(255, 153, 0));
		btnBack.setForeground(new Color(51, 102, 255));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(100, 103, 162, 43);
		frame.getContentPane().add(btnBack);
	}
}
