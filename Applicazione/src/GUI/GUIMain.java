package GUI;

import java.awt.EventQueue;
import Controller.Controller;

@SuppressWarnings("unused")
public class GUIMain {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller c=new Controller();
					Home window = new Home(c);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
