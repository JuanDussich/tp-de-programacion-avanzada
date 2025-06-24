package GUI;

import BLL.Administrador;
import DLL.ControllerAdministrador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarAdministrador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpNombre, inpApellido, inpEmail;
    private JPasswordField inpContrasenia;

    public AgregarAdministrador() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 450);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Centro De Salud - Nuevo Administrador");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(10, 10, 460, 30);
        contentPane.add(lblTitulo);

        //int y = 60;

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 60, 120, 20);
        contentPane.add(lblNombre);

        inpNombre = new JTextField();
        inpNombre.setBounds(180, 60, 220, 20);
        contentPane.add(inpNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(50, 100, 120, 20);
        contentPane.add(lblApellido);

        inpApellido = new JTextField();
        inpApellido.setBounds(180, 100, 220, 20);
        contentPane.add(inpApellido);

        //y += 40;

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 140, 120, 20);
        contentPane.add(lblEmail);

        inpEmail = new JTextField();
        inpEmail.setBounds(180, 140, 220, 20);
        contentPane.add(inpEmail);

        //y += 40;

        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setBounds(50, 180, 120, 20);
        contentPane.add(lblContrasenia);

        inpContrasenia = new JPasswordField();
        inpContrasenia.setBounds(180, 180, 220, 20);
        contentPane.add(inpContrasenia);

        //y += 50;

        JLabel lblMensaje = new JLabel("");
        lblMensaje.setBounds(50, 230, 360, 30);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblMensaje);

        //y += 50;

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100, 280, 120, 40);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(250, 280, 120, 40);
        contentPane.add(btnVolver);

        // Acción Guardar
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Administrador nuevo = new Administrador(
                        0,
                        inpNombre.getText(),
                        inpApellido.getText(),
                        inpEmail.getText(),
                        new String(inpContrasenia.getPassword())
                    );
                    ControllerAdministrador.RegistrarAdministrador(nuevo);
                    lblMensaje.setText("Administrador registrado correctamente.");
                    limpiarCampos();
                } catch (Exception ex) {
                    lblMensaje.setText("Error al registrar administrador.");
                }
            }
        });

        // Acción Volver
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaAdministrador vista = new VistaAdministrador();
                vista.setVisible(true);
                dispose();
            }
        });
    }

    private void limpiarCampos() {
        inpNombre.setText("");
        inpApellido.setText("");
        inpEmail.setText("");
        inpContrasenia.setText("");
    }
}
