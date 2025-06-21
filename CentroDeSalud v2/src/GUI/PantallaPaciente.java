package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Paciente;
import BLL.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.Panel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PantallaPaciente extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpEmail;
	private JPasswordField inpContrasenia;

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

	/**
	 * Create the frame.
	 */
	public PantallaPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel LblTitulo = new JLabel("Centro De Salud");
		LblTitulo.setForeground(Color.GRAY);
		LblTitulo.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 30));
		LblTitulo.setBounds(0, 0, 293, 107);
		contentPane.add(LblTitulo);
		
		JLabel Paciente = new JLabel("");
		Paciente.setIcon(new ImageIcon("C:\\Users\\Kavadie\\Documents\\Escuela Da VINCI\\Programacion Avanzada\\tp-de-programacion-avanzada\\CentroDeSalud v2\\src\\Imagenes\\paciente.jpg"));
		Paciente.setBounds(327, 11, 137, 107);
		contentPane.add(Paciente);
		
		
		JButton btnNewButton = new JButton("Ver turnos");
		btnNewButton.setBounds(609, 251, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ver Perfil");
		btnNewButton_1.setBounds(560, 173, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_4 = new JButton("Solicitar turno");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton_4.setBounds(480, 251, 119, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Cerrar Sesion");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal vista = new PantallaPrincipal();
				vista.setVisible(true);
			}
		});
		
		btnNewButton_5.setBackground(new Color(255, 0, 0));
		btnNewButton_5.setBounds(539, 50, 119, 23);
		contentPane.add(btnNewButton_5);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(40, 173, 424, 266);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(10, 65, 200, 14);
		panel_1.add(lblNewLabel_1);
				
		inpEmail = new JTextField();
		inpEmail.setBounds(10, 90, 200, 32);
		panel_1.add(inpEmail);
		inpEmail.setColumns(10);
				
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1.setBounds(10, 133, 200, 14);
		panel_1.add(lblNewLabel_1_1);
						
		inpContrasenia = new JPasswordField();
		inpContrasenia.setBounds(10, 158, 200, 32);
		panel_1.add(inpContrasenia);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(40, 355, 265, 32);
		contentPane.add(lblError);
		
		JButton btnLogin = new JButton("Inciar sesión");
		btnLogin.setBounds(10, 204, 121, 23);
		panel_1.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Paciente logueado = BLL.Paciente.login(inpEmail.getText(), inpContrasenia.getText());
				if (logueado == null) {
					lblError.setText("No se encontró");
				} else {
					JOptionPane.showMessageDialog(null, "estas logueado");
					//VistaUsuarios tabla = new VistaUsuarios();
					//tabla.setVisible(true);
					//dispose();
				}
			}
		});
		
		
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		tabbedPane.addTab("New tab", null, panel_1_1, null);
		
		JButton btnNewButton_2_1 = new JButton("Registrarse");
		btnNewButton_2_1.setBounds(10, 201, 89, 23);
		panel_1_1.add(btnNewButton_2_1);
	}
}
