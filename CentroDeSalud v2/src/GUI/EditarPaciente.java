package GUI;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BLL.Paciente;
import java.awt.Font;

public class EditarPaciente extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpNombre;
	private JTextField inpApellido;
	private JTextField inpMail;
	private JTextField inpContrasenia;
	
	public EditarPaciente(Paciente usuario) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Etiqueta para el campo Nombre
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(116, 80, 96, 20); // posición más arriba
		contentPane.add(lblNombre);

		// Campo de texto para el nombre
		inpNombre = new JTextField();
		inpNombre.setText(usuario.getNombre());
		inpNombre.setBounds(116, 105, 184, 20);
		inpNombre.setColumns(10);
		contentPane.add(inpNombre);

		// Etiqueta para el campo Apellido
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(116, 135, 96, 20);
		contentPane.add(lblApellido);

		// Campo de texto para el apellido
		inpApellido = new JTextField();
		inpApellido.setText(usuario.getApellido());
		inpApellido.setBounds(116, 160, 184, 20);
		inpApellido.setColumns(10);
		contentPane.add(inpApellido);

		// Etiqueta para el campo Email
		JLabel lblMail = new JLabel("Email");
		lblMail.setBounds(116, 190, 96, 14);
		contentPane.add(lblMail);

		// Campo de texto para el email
		inpMail = new JTextField();
		inpMail.setText(usuario.getEmail());
		inpMail.setBounds(116, 215, 184, 20);
		inpMail.setColumns(10);
		contentPane.add(inpMail);

		// Etiqueta para el campo Contraseña
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(116, 245, 96, 14);
		contentPane.add(lblContrasenia);

		// Campo de texto para la contraseña
		inpContrasenia = new JTextField(usuario.getContrasenia());
		inpContrasenia.setBounds(116, 270, 184, 20);
		inpContrasenia.setColumns(10);
		contentPane.add(inpContrasenia);

         // Label para mostrar mensajes al usuario (éxito o error)
        JLabel lblMensaje = new JLabel("");
        lblMensaje.setBounds(55, 310, 277, 30);            // posicion de la etiqueta del mensjae
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);  // texto centrado horizontalmente
        lblMensaje.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // padding interno
        contentPane.add(lblMensaje);
		
     	// Botón para guardar los cambios realizados
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(65, 352, 113, 51);
		
		btnEditar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				//Paciente editar = usuario;
			    usuario.setNombre(inpNombre.getText());
			    usuario.setApellido(inpApellido.getText());
			    usuario.setEmail(inpMail.getText());
			    usuario.setContrasenia(inpContrasenia.getText());
				
				String mensaje = Paciente.EditarPaciente(usuario);
				
				lblMensaje.setText(mensaje);
				
			}
		});
		contentPane.add(btnEditar);
		
		// Botón para volver a la ventana anterior sin guardar cambios
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(258, 352, 119, 50);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana VistaPaciente y cierra esta ventana
                VistaPacientes vista = new VistaPacientes();
                vista.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnVolver);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("Centro De Salud - Editar Paciente");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblTitulo.setBounds(20, 11, 400, 53);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);
    }
}
		
		
	
