package GUI;

import BLL.Administrador;
import DLL.ControllerAdministrador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PantallaAdministrador extends JFrame {

    private static final long serialVersionUID = 1L;

    // Componentes GUI
    private JTable tablaAdministradores;
    private DefaultTableModel modeloTabla;
    private JButton btnEditar, btnEliminar, btnRefrescar;
	private JPanel contentPane;

    public PantallaAdministrador() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel LblTitulo = new JLabel("Centro De Salud");
		LblTitulo.setForeground(Color.GRAY);
		LblTitulo.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 30));
		LblTitulo.setBounds(24, 11, 293, 137);
		contentPane.add(LblTitulo);
		
		JLabel Administrador = new JLabel("");
		Administrador.setIcon(new ImageIcon("C:\\Users\\Kavadie\\Documents\\Escuela Da VINCI\\Programacion Avanzada\\tp-de-programacion-avanzada\\CentroDeSalud v2\\src\\Imagenes\\logo.jpg"));
		Administrador.setBounds(324, 11, 137, 137);
		contentPane.add(Administrador);
		
		JButton btnNewButton = new JButton("Ver Pacientes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(321, 235, 140, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ver turnos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(372, 298, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Ver Medicos");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_2.setBounds(539, 298, 89, 23);
		contentPane.add(btnNewButton_2);
		
		
		JButton btnNewButton_3 = new JButton("Ver Administradores");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_3.setBounds(539, 235, 140, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("Cerrar Sesion");
		btnNewButton_5.setBackground(Color.RED);
		btnNewButton_5.setBounds(506, 76, 119, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_4 = new JButton("Login");
		btnNewButton_4.setBounds(24, 235, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_6 = new JButton("Registro");
		btnNewButton_6.setBounds(131, 235, 89, 23);
		contentPane.add(btnNewButton_6);
    	
    }
}
