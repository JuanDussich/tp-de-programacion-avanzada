package GUI;

import BLL.Administrador;
import BLL.Medico;
import BLL.Paciente;
import BLL.Turno;
import DLL.ControllerAdministrador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.table.TableModel;

public class PantallaAdministrador extends JFrame {

    private static final long serialVersionUID = 1L;

    // Componentes GUI
    private Paciente usuarioSeleccionado;
    private DefaultTableModel model;
    private DefaultTableModel modelMedico;
    private DefaultTableModel modelAdministrador;
    private DefaultTableModel modelTurno;
    private DefaultTableModel modeloTabla;
	private JPanel contentPane;
	private JTextField inpEmail;
	private JPasswordField inpContrasenia;
	private JTextField inpNombre;
	private JTextField inpApellido;
	private JTextField inpDni;
	private JTextField inpFechaDeNacimiento;
	private JTextField inpMail;
	private JTextField inpContra;
	private Administrador admin;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;

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
		LblTitulo.setBounds(0, 0, 293, 125);
		contentPane.add(LblTitulo);
		
		JLabel Administrador = new JLabel("");
		Administrador.setIcon(new ImageIcon("C:\\Users\\Kavadie\\Documents\\Escuela Da VINCI\\Programacion Avanzada\\tp-de-programacion-avanzada\\CentroDeSalud v2\\src\\Imagenes\\logo.jpg"));
		Administrador.setBounds(276, 0, 137, 137);
		contentPane.add(Administrador);
		
		JButton btnNewButton_5 = new JButton("Cerrar Sesion");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal vista = new PantallaPrincipal();
				vista.setVisible(true);
			}
		});
		btnNewButton_5.setBackground(Color.RED);
		btnNewButton_5.setBounds(423, 65, 119, 23);
		contentPane.add(btnNewButton_5);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(40, 355, 265, 32);
		contentPane.add(lblError);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(24, 159, 692, 342);
		contentPane.add(tabbedPane_1);
		
		
		
		//NOMBRE PARA LA PRIMERA PARTE DE LA TABLA QUE SERIA SOBRE LA CUENTA
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.addTab("Cuenta Registro e inicio de sesion", null, tabbedPane, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Login ", null, panel_1, null);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(10, 11, 200, 14);
		panel_1.add(lblNewLabel_1);
		
		inpEmail = new JTextField();
		inpEmail.setBounds(10, 24, 200, 32);
		panel_1.add(inpEmail);
		inpEmail.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1.setBounds(10, 86, 200, 14);
		panel_1.add(lblNewLabel_1_1);
		
		inpContrasenia = new JPasswordField();
		inpContrasenia.setBounds(10, 104, 200, 32);
		panel_1.add(inpContrasenia);
		
		JButton btnLogin = new JButton("Inciar sesión");
		btnLogin.setBounds(10, 147, 121, 23);
		panel_1.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				admin = BLL.Administrador.login(inpEmail.getText(), inpContrasenia.getText());
				if (admin == null) {
					lblError.setText("No se encontró");
				} else {
					JOptionPane.showMessageDialog(null, "estas logueado");
					JOptionPane.showMessageDialog(null, "Bienvenido "  +  admin.getNombre());
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
		
		JLabel lblNewLabel_1_3 = new JLabel("Email");
		lblNewLabel_1_3.setBounds(174, 21, 140, 14);
		panel_1_1.add(lblNewLabel_1_3);
		
		inpMail = new JTextField();
		inpMail.setColumns(10);
		inpMail.setBounds(174, 33, 140, 32);
		panel_1_1.add(inpMail);
		
		JLabel lblNewLabel_1_4 = new JLabel("Contraseña");
		lblNewLabel_1_4.setBounds(174, 90, 140, 14);
		panel_1_1.add(lblNewLabel_1_4);
		
		inpContra = new JTextField();
		inpContra.setColumns(10);
		inpContra.setBounds(174, 103, 140, 32);
		panel_1_1.add(inpContra);
		
		
		JButton btnNewButton_2_1 = new JButton("Registrarse");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Administrador Registrado = new Administrador(0,inpNombre.getText(), inpApellido.getText(),inpMail.getText(), inpContra.getText());
				
				BLL.Administrador.RegistrarAdministrador(Registrado);
				
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
		btnNewButton_2_1.setBounds(10, 149, 89, 23);
		panel_1_1.add(btnNewButton_2_1);
		
		//NOMBRE PARA LA SEGUNDAA PARTE DE LA TABLA QUE SERIA SOBRE LAS VISTAS PARA ADMINISTRADORES,PACIENTES,MEDICOS Y TURNOS
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.addTab("Registros", null, tabbedPane_2, null);
		
		JPanel RegistroPaciente = new JPanel();
		RegistroPaciente.setLayout(null);
		tabbedPane_2.addTab("Registro Pacientes", null, RegistroPaciente, null);
		
		//ACA ES PARA VER LA TABLA DE PACIENTES FALTA CORREGIR
		model = new DefaultTableModel(new String[]{
	        		"idPaciente", "nombre", "apellido", "dni", "fecha_De_Nacimiento", "email", "contrasenia"}, 0);
		 
		table_1 = new JTable(model);
		table_1.setBounds(0, 0, 682, 253);
		RegistroPaciente.add(table_1);
		
		JButton btnNewButton = new JButton("Ver Pacientes");
		btnNewButton.setBounds(10, 252, 140, 23);
		RegistroPaciente.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (admin == null) {
					JOptionPane.showMessageDialog(null, "no puedes ver la lista porque no estas logueado");
				} else {
					VistaPacientes vista = new VistaPacientes();
					vista.setVisible(true);
				}
				
			}
		});
        // Cargar datos
        cargarTabla();
        
        
		JPanel RegistroMedico = new JPanel();
		RegistroMedico.setLayout(null);
		tabbedPane_2.addTab("Registro medicos", null, RegistroMedico, null);
		
		//ACA ES PARA VER LA TABLA DE MEDICOS 
		modelMedico = new DefaultTableModel(new String[]{
        		"idMedico", "nombre", "apellido", "matricula", "", "email", "contrasenia", "especialidad"}, 0);
	 
		table_2 = new JTable(modelMedico);
		table_2.setBounds(0, 0, 672, 253);
		RegistroMedico.add(table_2);
		
		JButton btnNewButton_2 = new JButton("Ver Medicos");
		btnNewButton_2.setBounds(10, 252, 149, 23);
		RegistroMedico.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (admin == null) {
					JOptionPane.showMessageDialog(null, "no puedes ver la lista porque no estas logueado");
				} else {
					VistaMedico vista = new VistaMedico();
					vista.setVisible(true);
				}
			}
		});
		
		JPanel RegistroAdministradores = new JPanel();
		RegistroAdministradores.setLayout(null);
		tabbedPane_2.addTab("Registro Administradores", null, RegistroAdministradores, null);
		
		modelAdministrador = new DefaultTableModel(new String[]{
        		"idAdministrador", "nombre", "apellido", "email", "contrasenia"}, 0);
		
		//ACA ES PARA VER LA TABLA DE ADMINISTRADORES
		table_3 = new JTable(modelAdministrador);
		table_3.setBounds(0, 0, 672, 253);
		RegistroAdministradores.add(table_3);
		
		JButton btnNewButton_3 = new JButton("Ver Administradores");
		btnNewButton_3.setBounds(10, 252, 150, 23);
		RegistroAdministradores.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (admin == null) {
					JOptionPane.showMessageDialog(null, "no puedes ver la lista porque no estas logueado");
				} else {
					VistaAdministrador vista = new VistaAdministrador();
					vista.setVisible(true);
				}
			}
		});
		cargarTablaAdmin();
		
		
		JPanel RegistroTurno = new JPanel();
		RegistroTurno.setLayout(null);
		tabbedPane_2.addTab("Registro turnos", null, RegistroTurno, null);
		
		//ACA ES PARA VER LA TABLA DE TURNO
		modelTurno = new DefaultTableModel(new String[]{
        		"idTurno", "fechaTurno", "horaTurno", "tipoConsulta", "estado", "paciente_idPaciente", "medico_idMedico" }, 0);
	 
		table_4 = new JTable(modelTurno);
		table_4.setBounds(0, 0, 682, 253);
		RegistroTurno.add(table_4);
		
		JButton btnNewButton_1 = new JButton("Ver turnos");
		btnNewButton_1.setBounds(10, 252, 114, 23);
		RegistroTurno.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FALTA LA VISTA DE TURNOS Y LINKEARLO ACA
				if (admin == null) {
					JOptionPane.showMessageDialog(null, "no puedes ver la lista porque no estas logueado");
				} else {
					VistaTurno vista = new VistaTurno();
					vista.setVisible(true);
				}
			}
		});
		cargarTablaTurno();
		
		JButton btnNewButton_1_1 = new JButton("Ver Perfil");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, admin);
			}
		});
		btnNewButton_1_1.setBounds(423, 31, 89, 23);
		contentPane.add(btnNewButton_1_1);
		cargarTablaMedico();
		
    }
    
    private void cargarTabla() {
        model.setRowCount(0);
        LinkedList<Paciente> usuarios = DLL.ControllerPaciente.mostrarPacientes();
        for (Paciente u : usuarios) {
            model.addRow(new Object[]{
            		u.getId(), u.getNombre(), u.getApellido(), u.getDni(),u.getFechaNacimiento(),u.getEmail(),u.getContrasenia()
            		});
        }
    }
    private void cargarTablaAdmin() {
        modelAdministrador.setRowCount(0);
        LinkedList<Administrador> usuarios = DLL.ControllerAdministrador.mostrarAdministrador();
        for (Administrador u : usuarios) {
            modelAdministrador.addRow(new Object[]{
            		u.getId(), u.getNombre(), u.getApellido(),u.getEmail(),u.getContrasenia(), u.getActivo()
            		});
        }
    }
    private void cargarTablaMedico() {
        modelMedico.setRowCount(0);
        LinkedList<Medico> usuarios = DLL.ControllerMedico.mostrarMedicos();
        for (Medico u : usuarios) {
            modelMedico.addRow(new Object[]{
            		u.getId(), u.getNombre(), u.getApellido(), u.getMatricula(),u.getEmail(),u.getContrasenia(),u.getEspecialidad()
            		});
        }
    }
    private void cargarTablaTurno() {
        modelTurno.setRowCount(0);
        LinkedList<Turno> turno = DLL.ControllerTurno.listarTurnos();
        for (Turno u : turno) {
            modelTurno.addRow(new Object[]{
            		//FALTA TERMINAR ESTA PARTE DE ACA
            		u.getIdTurno(), u.getFechaTurno(), u.getHoraTurno(), u.getTipoConsulta(),u.getEstado(),u.getIdPaciente(),u.getIdMedico()
            		});
        }
    }
}
