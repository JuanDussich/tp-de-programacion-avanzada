package GUI;

import BLL.Medico;
import DLL.ControllerMedico;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PantallaMedico extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1496404291286514983L;
	// Componentes de la GUI
    private JTable tablaMedicos;
    private DefaultTableModel modeloTabla;
    private JButton btnEditar, btnEliminar, btnRefrescar;
	private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VistaMedico frame = new VistaMedico();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public PantallaMedico() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel LblTitulo = new JLabel("Centro De Salud");
		LblTitulo.setForeground(Color.GRAY);
		LblTitulo.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 30));
		LblTitulo.setBounds(0, 0, 293, 137);
		contentPane.add(LblTitulo);
		
		JLabel Administrador = new JLabel("");
		Administrador.setIcon(new ImageIcon("C:\\Users\\Kavadie\\Documents\\Escuela Da VINCI\\Programacion Avanzada\\tp-de-programacion-avanzada\\CentroDeSalud v2\\src\\Imagenes\\logo.jpg"));
		Administrador.setBounds(277, 0, 137, 137);
		contentPane.add(Administrador);
		
		JButton btnNewButton_5 = new JButton("Cerrar Sesion");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal vista = new PantallaPrincipal();
				vista.setVisible(true);
			}
		});
		btnNewButton_5.setBackground(Color.RED);
		btnNewButton_5.setBounds(454, 11, 119, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(0, 246, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.setBounds(93, 246, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Ver turnos");
		btnNewButton_2.setBounds(192, 246, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("solicitar turno");
		btnNewButton_3.setBounds(291, 246, 97, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Ver historiales medicos");
		btnNewButton_4.setBounds(398, 246, 148, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_6 = new JButton("Modificar Historiales medicos");
		btnNewButton_6.setBounds(556, 246, 160, 23);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_1_1 = new JButton("Ver Perfil");
		btnNewButton_1_1.setBounds(454, 45, 89, 23);
		contentPane.add(btnNewButton_1_1);
    	
    }

}
