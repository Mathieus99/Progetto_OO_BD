package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

@SuppressWarnings("serial")
public class CorreggiTest extends JFrame {

	private JPanel contentPane;
	private Controller c;
	private JFrame frame;
	
	public CorreggiTest(Controller c, JFrame guiDocente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		frame=this;
		this.c=c;
		
	}

}
