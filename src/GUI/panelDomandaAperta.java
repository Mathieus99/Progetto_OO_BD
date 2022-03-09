package GUI;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import java.awt.Font;

public class panelDomandaAperta extends JPanel {
	JPanel panel;
	public panelDomandaAperta(int i) {
		panel=this;
		setLayout(null);
		setBounds(10, 73+i, 864, 67);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(147, 0, 717, 56);
		add(editorPane);
		
		JLabel lblTDomanda = new JLabel("");
		lblTDomanda.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblTDomanda.setBounds(0, 0, 137, 20);
		add(lblTDomanda);
		panel.setLayout(null);
	}

}
