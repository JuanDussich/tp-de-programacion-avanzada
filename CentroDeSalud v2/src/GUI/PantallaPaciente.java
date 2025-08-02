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
	private JTextField inpNombre;
	private JTextField inpApellido;
	private JTextField inpDni;
	private JTextField inpFechaDeNacimiento;
	private JTextField inpMail;
	private JTextField inpContra;
	private JTextField inpObraSocial;
	private Paciente logueado;
	private int idPacienteLogueado;


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
		
		
		JButton btnVerTurnos = new JButton("Ver turnos");
		btnVerTurnos.setBounds(597, 230, 119, 23);
		contentPane.add(btnVerTurnos);

		btnVerTurnos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("CLICK EN VER TURNOS DETECTADO"); // Confirmación de clic

		        VistaTurnoPaciente ventanaTurnos = new VistaTurnoPaciente(logueado.getId());
		        ventanaTurnos.setVisible(true);
		    }
		});
		
		JButton btnNewButton_4 = new JButton("Solicitar turno");
		btnNewButton_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        if (logueado != null) {
		            int idPaciente = logueado.getId(); // id del paciente logueado
		            SolicitarTurno solicitar = new SolicitarTurno(idPaciente); // 👈 usa constructor para paciente
		            solicitar.setVisible(true);
		        } else {
		            JOptionPane.showMessageDialog(null, "Error: No hay paciente logueado.");
		        }
		    }
		});

		btnNewButton_4.setBounds(597, 190, 119, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Cerrar Sesion");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal vista = new PantallaPrincipal();
				vista.setVisible(true);
			}
		});
		
		btnNewButton_5.setBackground(new Color(255, 0, 0));
		btnNewButton_5.setBounds(488, 50, 119, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_1 = new JButton("Ver Perfil");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String perfil = "Nombre: " + logueado.getNombre() +
		                "\nApellido: " + logueado.getApellido() +
		                "\nDNI: " + logueado.getDni() +
		                "\nFecha de nacimiento: " + logueado.getFechaNacimiento() +
		                "\nEmail: " + logueado.getEmail() +
		                "\nObra Social: " + logueado.getObraSocial();

		JOptionPane.showMessageDialog(null, perfil, "Perfil del Paciente", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1.setBounds(488, 16, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(39, 173, 329, 306);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Login ", null, panel_1, null);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(10, 59, 200, 14);
		panel_1.add(lblNewLabel_1);
				
		inpEmail = new JTextField();
		inpEmail.setBounds(10, 84, 200, 32);
		panel_1.add(inpEmail);
		inpEmail.setColumns(10);
				
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1.setBounds(10, 127, 200, 14);
		panel_1.add(lblNewLabel_1_1);
						
		inpContrasenia = new JPasswordField();
		inpContrasenia.setBounds(10, 152, 200, 32);
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
				
				logueado = BLL.Paciente.login(inpEmail.getText(), inpContrasenia.getText());
				if (logueado == null) {
					lblError.setText("No se encontró");
				} else {
					idPacienteLogueado = logueado.getId();
					JOptionPane.showMessageDialog(null, "estas logueado");
					JOptionPane.showMessageDialog(null, "Bienvenido "  +  logueado.getNombre());
					//VistaUsuarios tabla = new VistaUsuarios();
					//tabla.setVisible(true);
					//dispose();
				}
			}
		});
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		tabbedPane.addTab("Registrarse", null, panel_1_1, null);
		
		
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Nombre");
		lblNewLabel_1_3_2.setBounds(10, 21, 140, 14);
		panel_1_1.add(lblNewLabel_1_3_2);
		
		inpNombre = new JTextField();
		inpNombre.setColumns(10);
		inpNombre.setBounds(10, 33, 140, 32);
		panel_1_1.add(inpNombre);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Apellido");
		lblNewLabel_1_3_1.setBounds(10, 90, 140, 14);
		panel_1_1.add(lblNewLabel_1_3_1);
		
		inpApellido = new JTextField();
		inpApellido.setColumns(10);
		inpApellido.setBounds(10, 103, 140, 32);
		panel_1_1.add(inpApellido);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Dni");
		lblNewLabel_1_3_3.setBounds(10, 146, 140, 14);
		panel_1_1.add(lblNewLabel_1_3_3);
		
		inpDni = new JTextField();
		inpDni.setColumns(10);
		inpDni.setBounds(10, 163, 140, 32);
		panel_1_1.add(inpDni);
		
		JLabel lblNewLabel_1_2 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_1_2.setBounds(174, 21, 140, 14);
		panel_1_1.add(lblNewLabel_1_2);
		
		inpFechaDeNacimiento = new JTextField();
		inpFechaDeNacimiento.setColumns(10);
		inpFechaDeNacimiento.setBounds(174, 33, 140, 32);
		panel_1_1.add(inpFechaDeNacimiento);
		
		JLabel lblNewLabel_1_3 = new JLabel("Email");
		lblNewLabel_1_3.setBounds(174, 90, 140, 14);
		panel_1_1.add(lblNewLabel_1_3);
		
		inpMail = new JTextField();
		inpMail.setColumns(10);
		inpMail.setBounds(174, 103, 140, 32);
		panel_1_1.add(inpMail);
		
		JLabel lblNewLabel_1_4 = new JLabel("Contraseña");
		lblNewLabel_1_4.setBounds(174, 146, 140, 14);
		panel_1_1.add(lblNewLabel_1_4);
		
		inpContra = new JTextField();
		inpContra.setColumns(10);
		inpContra.setBounds(174, 162, 140, 32);
		panel_1_1.add(inpContra);
		
		JLabel lblObraSocial = new JLabel("Obra Social");
		lblObraSocial.setBounds(10, 206, 140, 14);
		panel_1_1.add(lblObraSocial);

		inpObraSocial = new JTextField();
		inpObraSocial.setColumns(10);
		inpObraSocial.setBounds(10, 222, 140, 32);
		panel_1_1.add(inpObraSocial);

		
		
		JButton btnNewButton_2_1 = new JButton("Registrarse");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Paciente Registrado = new Paciente(
					    0,
					    inpNombre.getText(),
					    inpApellido.getText(),
					    Integer.parseInt(inpDni.getText()),
					    inpFechaDeNacimiento.getText(),
					    inpMail.getText(),
					    inpContra.getText(),
					    1,
					    inpObraSocial.getText()
					);
				
				BLL.Paciente.RegistrarPaciente(Registrado);
				
				if (Registrado == null) {
					lblError.setText("No se encontró");
				} else {
					JOptionPane.showMessageDialog(null, "estas logueado");
					JOptionPane.showMessageDialog(null, "Bienvenido "  +  Registrado.getNombre());
					//VistaUsuarios tabla = new VistaUsuarios();
					//tabla.setVisible(true);
					//dispose();
				}
			}
		});
		btnNewButton_2_1.setBounds(174, 220, 110, 23);
		panel_1_1.add(btnNewButton_2_1);
		
		
		
		
		
		
		
	}
}
