package GUI;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import BLL.Medico;
import java.awt.Font;

public class EditarMedico extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpNombre;
    private JTextField inpApellido;
    private JTextField inpMail;
    private JTextField inpContrasenia; //

    // Constructor que recibe un objeto Medico a editar
    public EditarMedico(Medico usuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 547);
        
        // Panel principal de contenido
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Etiqueta para el campo Nombre
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(116, 80, 96, 20);
        contentPane.add(lblNombre);

        // Campo de texto para el nombre
        inpNombre = new JTextField(usuario.getNombre());
        inpNombre.setBounds(116, 105, 184, 20);
        inpNombre.setColumns(10);
        contentPane.add(inpNombre);

        // Etiqueta para el campo Apellido
        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setBounds(116, 135, 96, 20);
        contentPane.add(lblApellido);

        // Campo de texto para el apellido
        inpApellido = new JTextField(usuario.getApellido());
        inpApellido.setBounds(116, 160, 184, 20);
        inpApellido.setColumns(10);
        contentPane.add(inpApellido);

        // Etiqueta para el campo Mail
        JLabel lblMail = new JLabel("Mail");
        lblMail.setBounds(116, 190, 96, 14);
        contentPane.add(lblMail);

        // Campo de texto para el mail
        inpMail = new JTextField(usuario.getEmail());
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


        // Label para mostrar mensajes al usuario (exito o error)
        //JLabel lblMensaje = new JLabel("");
        //lblMensaje.setBounds(55, 341, 277, 14);
        //contentPane.add(lblMensaje);
        // Label para mostrar mensajes al usuario (exito o error)
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
                // Actualiza el objeto usuario con los valores editados
                usuario.setNombre(inpNombre.getText());
                usuario.setEmail(inpMail.getText());
                usuario.setContrasenia(inpContrasenia.getText());

                // Llama al método de la lógica de negocio para actualizar en la BD y obtiene el mensaje resultado
                String mensaje = Medico.EditarMedico(usuario);

                // Muestra el mensaje en la interfaz
                lblMensaje.setText(mensaje);
            }
        });
        contentPane.add(btnEditar);

        // Botón para volver a la ventana anterior sin guardar cambios
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(258, 352, 119, 50);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana VistaMedico y cierra esta ventana
                VistaMedico vista = new VistaMedico();
                vista.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnVolver);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("Centro De Salud - Editar Medico");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblTitulo.setBounds(20, 11, 400, 53);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);
    }
}
