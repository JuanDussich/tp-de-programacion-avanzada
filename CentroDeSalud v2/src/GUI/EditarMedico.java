package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import BLL.Medico;
import java.awt.Font;

public class EditarMedico extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpNombre;
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
        lblNombre.setBounds(116, 108, 96, 20);
        contentPane.add(lblNombre);

        // Campo de texto para el nombre, cargado con el valor actual del usuario
        inpNombre = new JTextField(usuario.getNombre());
        inpNombre.setBounds(116, 139, 184, 20);
        contentPane.add(inpNombre);
        inpNombre.setColumns(10);

        // Etiqueta para el campo Mail
        JLabel lblMail = new JLabel("Mail");
        lblMail.setBounds(116, 181, 96, 14);
        contentPane.add(lblMail);

        // Campo de texto para el mail, cargado con el valor actual
        inpMail = new JTextField(usuario.getEmail());
        inpMail.setBounds(116, 206, 184, 20);
        contentPane.add(inpMail);
        inpMail.setColumns(10);

        // Etiqueta para el campo Contraseña
        JLabel lblContrasenia = new JLabel("Contraseña");
        lblContrasenia.setBounds(116, 254, 96, 14);
        contentPane.add(lblContrasenia);

        // Campo de texto para la contraseña, cargado con el valor actual
        inpContrasenia = new JTextField(usuario.getContrasenia());
        inpContrasenia.setBounds(116, 279, 184, 20);
        contentPane.add(inpContrasenia);
        inpContrasenia.setColumns(10);

        // Label para mostrar mensajes al usuario (éxito o error)
        JLabel lblMensaje = new JLabel("");
        lblMensaje.setBounds(55, 341, 277, 14);
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
        lblTitulo.setBounds(65, 11, 312, 53);
        contentPane.add(lblTitulo);
    }
}
