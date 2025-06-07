package GUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipal extends JFrame {
	
	
	public PantallaPrincipal() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Centro De Salud");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		lblNewLabel.setBounds(121, 11, 195, 74);
		getContentPane().add(lblNewLabel);
		
	
	}
	
	
}
