package GUI;

import BLL.Especialidad;
import BLL.Medico;
import BLL.Paciente;
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
	private JTextField inpEmail;
	private JPasswordField inpContrasenia;
	private JTextField inpNombre;
	private JTextField inpApellido;
	private JTextField inpMatricula;
	private JTextField inpEspecialidad;
	private JTextField inpMail;
	private JTextField inpContra;
	private Medico logueado;

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
		btnNewButton_5.setBounds(449, 79, 119, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_2 = new JButton("Ver turnos");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton_2.setBounds(360, 159, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("solicitar turno");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_3.setBounds(471, 159, 97, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Ver historiales medicos");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VistaHistoriaClinica vista = new VistaHistoriaClinica();
				vista.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(578, 159, 148, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_6 = new JButton("Modificar Historiales medicos");
		btnNewButton_6.setBounds(556, 11, 160, 23);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_1_1 = new JButton("Ver Perfil");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, logueado);
			}
		});
		btnNewButton_1_1.setBounds(449, 39, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 133, 329, 368);
		contentPane.add(tabbedPane);
		
		
		//ESTA ES LA OTRA PARTE DE LA TABLA QUE ES LA TABLA PARA INICIAR SESION
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Login", null, panel_1, null);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(10, 142, 200, 14);
		panel_1.add(lblNewLabel_1);
				
		inpEmail = new JTextField();
		inpEmail.setBounds(10, 169, 200, 32);
		panel_1.add(inpEmail);
		inpEmail.setColumns(10);
				
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1.setBounds(10, 223, 200, 14);
		panel_1.add(lblNewLabel_1_1);
						
		inpContrasenia = new JPasswordField();
		inpContrasenia.setBounds(10, 248, 200, 32);
		panel_1.add(inpContrasenia);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(40, 355, 265, 32);
		contentPane.add(lblError);
		
		JButton btnLogin = new JButton("Inciar sesión");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				logueado = BLL.Medico.login(inpEmail.getText(), inpContrasenia.getText());
				if (logueado == null) {
					lblError.setText("No se encontró");
				} else {
					JOptionPane.showMessageDialog(null, "estas logueado");
					JOptionPane.showMessageDialog(null, "Bienvenido "  +  logueado.getNombre());
					//VistaUsuarios tabla = new VistaUsuarios();
					//tabla.setVisible(true);
					//dispose();
				}
				
			}
		});
		btnLogin.setBounds(10, 306, 121, 23);
		panel_1.add(btnLogin);
		
		JLabel Administrador_1 = new JLabel("");
		Administrador_1.setIcon(new ImageIcon("C:\\Users\\Kavadie\\Documents\\Escuela Da VINCI\\Programacion Avanzada\\tp-de-programacion-avanzada\\CentroDeSalud v2\\src\\Imagenes\\Medico.jpg"));
		Administrador_1.setBounds(187, 0, 137, 137);
		panel_1.add(Administrador_1);
		
		
		
		
		//ESTA ES LA SEGUNDA TABLA Y ES LA TABLA DE REGISTRO
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		tabbedPane.addTab("Registrarse", null, panel_1_1, null);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Nombre");
		lblNewLabel_1_3_2.setBounds(10, 11, 140, 14);
		panel_1_1.add(lblNewLabel_1_3_2);
		
		inpNombre = new JTextField();
		inpNombre.setColumns(10);
		inpNombre.setBounds(10, 21, 140, 32);
		panel_1_1.add(inpNombre);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Apellido");
		lblNewLabel_1_3_1.setBounds(10, 64, 140, 14);
		panel_1_1.add(lblNewLabel_1_3_1);
		
		inpApellido = new JTextField();
		inpApellido.setColumns(10);
		inpApellido.setBounds(10, 81, 140, 32);
		panel_1_1.add(inpApellido);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Matricula");
		lblNewLabel_1_3_3.setBounds(10, 124, 140, 14);
		panel_1_1.add(lblNewLabel_1_3_3);
		
		inpMatricula = new JTextField();
		inpMatricula.setColumns(10);
		inpMatricula.setBounds(10, 137, 140, 32);
		panel_1_1.add(inpMatricula);
		
		JLabel lblNewLabel_1_2 = new JLabel("Especialidad");
		lblNewLabel_1_2.setBounds(174, 64, 140, 14);
		panel_1_1.add(lblNewLabel_1_2);
		
		//LO QUE DEBERIA DE CAMBIAR AMALIA SERIA ESTO DE ACA QUE ESTA PARTE DE ACA ES PARA REGISTRAR LA ESPECIALIDAD
		//inpEspecialidad = new JTextField();
		//inpEspecialidad.setColumns(10);
		//inpEspecialidad.setBounds(174, 81, 140, 32);
		//panel_1_1.add(inpEspecialidad);
		
		JComboBox Combodoctores = new JComboBox();
		for (Especialidad esp : Especialidad.values()) {
            Combodoctores.addItem(esp.name());
        }
		
		Combodoctores.setBounds(176, 81, 138, 32);
		panel_1_1.add(Combodoctores);
		
		JLabel lblNewLabel_1_3 = new JLabel("Email");
		lblNewLabel_1_3.setBounds(10, 180, 140, 14);
		panel_1_1.add(lblNewLabel_1_3);
		
		inpMail = new JTextField();
		inpMail.setColumns(10);
		inpMail.setBounds(10, 195, 140, 32);
		panel_1_1.add(inpMail);
		
		JLabel lblNewLabel_1_4 = new JLabel("Contraseña");
		lblNewLabel_1_4.setBounds(174, 11, 140, 14);
		panel_1_1.add(lblNewLabel_1_4);
		
		inpContra = new JTextField();
		inpContra.setColumns(10);
		inpContra.setBounds(174, 21, 140, 32);
		panel_1_1.add(inpContra);
		
		JButton btnNewButton_2_1 = new JButton("Registrarse");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Medico Registrado = new Medico(0,inpNombre.getText(), inpApellido.getText(),inpMatricula.getText(),inpMail.getText(), inpContra.getText(),Especialidad.valueOf(Combodoctores.getSelectedItem().toString()),1);
				BLL.Medico.RegistrarMedico(Registrado);
				
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
		btnNewButton_2_1.setBounds(10, 306, 89, 23);
		panel_1_1.add(btnNewButton_2_1);
		
		
    	
    }
}
