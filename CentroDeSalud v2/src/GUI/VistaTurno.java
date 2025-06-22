package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VistaTurno extends JFrame {
		
	private JComponent contentPane;	
	
	public static void main(String[] args) {
	        EventQueue.invokeLater(() -> {
	            try {
	                VistaTurno frame = new VistaTurno();
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }

		

	public VistaTurno() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 800, 700);

	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	        
	        // titulo de la ventana Turno
	        JLabel lblTitulo = new JLabel("Centro De Salud - Turnos");
	        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
	        lblTitulo.setBounds(10, 10, 800 , 15);
	        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	        contentPane.add(lblTitulo);
	        
        
	     // Imagen de Turno 
	        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/turno.png"));
	        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

	        JLabel lblImagen = new JLabel(iconoEscalado);
	        lblImagen.setBounds(670, 10, 100, 100); // esquina superior derecha
	        contentPane.add(lblImagen);

		
		
		
	}
}
