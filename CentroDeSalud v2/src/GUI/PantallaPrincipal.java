package GUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public PantallaPrincipal() {
		
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
		
		JButton btnPaciente = new JButton("Menu Paciente");
		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPaciente vista = new PantallaPaciente();
				vista.setVisible(true);
			}
		});
		btnPaciente.setBounds(50, 374, 137, 61);
		contentPane.add(btnPaciente);
		
		JButton btnAdministrador = new JButton("Menu Administrador");
		btnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaAdministrador vista = new PantallaAdministrador();
				vista.setVisible(true);
			}
		});
		btnAdministrador.setBounds(303, 374, 137, 61);
		contentPane.add(btnAdministrador);
		
		JButton btnMedico = new JButton("Menu Medico");
		btnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.PantallaMedico vista = new PantallaMedico();
				vista.setVisible(true);
				
			}
		});
		btnMedico.setBounds(553, 374, 137, 61);
		contentPane.add(btnMedico);
		
		
		// Cargar la imagen
        JLabel Logo = new JLabel("");
		Logo.setIcon(new ImageIcon("C:\\Users\\Kavadie\\Documents\\Escuela Da VINCI\\Programacion Avanzada\\tp-de-programacion-avanzada\\CentroDeSalud v2\\src\\Imagenes\\logo.jpg"));
		Logo.setBounds(239, 0, 137, 137);
		contentPane.add(Logo);
		
		
		JLabel Medico = new JLabel("");
		Medico.setIcon(new ImageIcon("C:\\Users\\Kavadie\\Documents\\Escuela Da VINCI\\Programacion Avanzada\\tp-de-programacion-avanzada\\CentroDeSalud v2\\src\\Imagenes\\Medico.jpg"));
		Medico.setBounds(553, 213, 137, 123);
		contentPane.add(Medico);
		
		JLabel Administrador = new JLabel("");
		Administrador.setIcon(new ImageIcon("C:\\Users\\Kavadie\\Documents\\Escuela Da VINCI\\Programacion Avanzada\\tp-de-programacion-avanzada\\CentroDeSalud v2\\src\\Imagenes\\Admin.jpg"));
		Administrador.setBounds(303, 213, 137, 123);
		contentPane.add(Administrador);
		
		JLabel Paciente = new JLabel("");
		Paciente.setIcon(new ImageIcon("C:\\Users\\Kavadie\\Documents\\Escuela Da VINCI\\Programacion Avanzada\\tp-de-programacion-avanzada\\CentroDeSalud v2\\src\\Imagenes\\paciente.jpg"));
		Paciente.setBounds(50, 213, 137, 123);
		contentPane.add(Paciente);
	}
}
