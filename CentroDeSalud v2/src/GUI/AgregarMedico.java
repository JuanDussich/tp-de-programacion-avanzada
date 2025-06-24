package GUI;

import BLL.Medico;
import BLL.Especialidad;

import DLL.ControllerMedico;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarMedico extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpNombre, inpApellido, inpMatricula, inpEmail;
    private JComboBox<Especialidad> inpEspecialidad;
    private JPasswordField inpContrasenia;

    public AgregarMedico() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Centro De Salud - Nuevo Médico");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblTitulo.setBounds(10, 10, 400, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);

        //int y = 60;

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 60, 100, 20);
        contentPane.add(lblNombre);

        inpNombre = new JTextField();
        inpNombre.setBounds(150, 60, 200, 20);
        contentPane.add(inpNombre);

        //y += 40;

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(50, 100, 100, 20);
        contentPane.add(lblApellido);

        inpApellido = new JTextField();
        inpApellido.setBounds(150, 100, 200, 20);
        contentPane.add(inpApellido);

        //y += 40;

        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setBounds(50, 140, 100, 20);
        contentPane.add(lblMatricula);

        inpMatricula = new JTextField();
        inpMatricula.setBounds(150, 140, 200, 20);
        contentPane.add(inpMatricula);

        //y += 40;

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 180, 100, 20);
        contentPane.add(lblEmail);

        inpEmail = new JTextField();
        inpEmail.setBounds(150, 180, 200, 20);
        contentPane.add(inpEmail);

        //y += 40;

        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setBounds(50, 220, 100, 20);
        contentPane.add(lblContrasenia);

        inpContrasenia = new JPasswordField();
        inpContrasenia.setBounds(150, 220, 200, 20);
        contentPane.add(inpContrasenia);

        //y += 40;

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setBounds(50, 260, 100, 20);
        contentPane.add(lblEspecialidad);

        inpEspecialidad = new JComboBox<>(Especialidad.values());
        inpEspecialidad.setBounds(150, 260, 200, 20);
        contentPane.add(inpEspecialidad);

        //y += 50;

        JLabel lblMensaje = new JLabel("");
        lblMensaje.setBounds(50, 310, 300, 30);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblMensaje);

        //y += 50;

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(80, 360, 120, 40);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(220, 360, 120, 40);
        contentPane.add(btnVolver);

        // Acción botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		
                Medico nuevo = new Medico(
                    0,
                    inpNombre.getText(),
                    inpApellido.getText(),
                    inpMatricula.getText(),
                    inpEmail.getText(),
                    new String(inpContrasenia.getPassword()),
                    (Especialidad) inpEspecialidad.getSelectedItem(),
                    1 // Activo por defecto
                );

                ControllerMedico.RegistrarMedico(nuevo);
                lblMensaje.setText("Médico registrado correctamente");
                limpiarCampos();
            	} catch (Exception ex) {
                    lblMensaje.setText("Error al registrar paciente. Revisá los datos.");
                }
            }
        });


        // Acción botón Volver
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaMedico vista = new VistaMedico();
                vista.setVisible(true);
                dispose();
            }
        });
    }

    private void limpiarCampos() {
        inpNombre.setText("");
        inpApellido.setText("");
        inpMatricula.setText("");
        inpEmail.setText("");
        inpContrasenia.setText("");
        inpEspecialidad.setSelectedIndex(-1); // Limpia la selección del combo
    }
}
