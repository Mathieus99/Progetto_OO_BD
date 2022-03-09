package GUI;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class panelDomandaMultipla extends JPanel {
	JPanel panel;
	private int i;
	public panelDomandaMultipla(int i) {
		panel=this;
		setLayout(null);
		this.i=i;
		setBounds(864,323);
		JEditorPane editorPaneTDomanda = new JEditorPane();
		editorPaneTDomanda.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		editorPaneTDomanda.setBounds(154, 0, 710, 56);
		add(editorPaneTDomanda);
		
		JLabel lblNDomanda = new JLabel("");
		lblNDomanda.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblNDomanda.setBounds(0, 0, 144, 20);
		add(lblNDomanda);
		
		JLabel lblNewLabel = new JLabel("Quante possibili risposte: ");
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblNewLabel.setBounds(0, 69, 208, 20);
		add(lblNewLabel);
		
		JComboBox comboBoxNRisposte = new JComboBox();
		comboBoxNRisposte.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		comboBoxNRisposte.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5"}));
		comboBoxNRisposte.setBounds(206, 70, 41, 22);
		add(comboBoxNRisposte);
		comboBoxNRisposte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i=0;i<Integer.parseInt(comboBoxNRisposte.getSelectedItem().toString());i++) {
					JEditorPane editorPaneRiposta = new JEditorPane();
					editorPaneRiposta.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
					editorPaneRiposta.setBounds(37, 98+(42*i), 827, 35);
					panel.add(editorPaneRiposta);
				}
					
			}
		});		
	}
}
