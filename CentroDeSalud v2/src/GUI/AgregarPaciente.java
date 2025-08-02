package GUI;

import javax.swing.*;

import com.toedter.calendar.JDateChooser; // oara calendario
import javax.swing.border.EmptyBorder;

import BLL.Paciente;
import DLL.ControllerPaciente;
import repository.Validaciones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;



public class AgregarPaciente extends JFrame implements Validaciones {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpNombre, inpApellido, inpDni,inpEmail;
    private JPasswordField inpContrasenia;
    private JDateChooser dateChooser;
    private JTextField inpObraSocial;


    public AgregarPaciente() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Centro De Salud - Nuevo Paciente");
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

       // y += 40;

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(50, 100, 120, 20);
        contentPane.add(lblApellido);

        inpApellido = new JTextField();
        inpApellido.setBounds(180, 100, 220, 20);
        contentPane.add(inpApellido);

        //y += 40;

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(50, 140, 120, 20);
        contentPane.add(lblDni);

        inpDni = new JTextField();
        inpDni.setBounds(180, 140, 220, 20);
        contentPane.add(inpDni);

        //y += 40;

        JLabel lblFecha = new JLabel("Fecha Nac. (AAAA-MM-DD):");
        lblFecha.setBounds(50, 180, 180, 20);
        contentPane.add(lblFecha);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(230, 180, 170, 20);
        dateChooser.setDateFormatString("yyyy-MM-dd"); // formato de la base
        contentPane.add(dateChooser);

        //y += 40;

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 220, 120, 20);
        contentPane.add(lblEmail);

        inpEmail = new JTextField();
        inpEmail.setBounds(180, 220, 220, 20);
        contentPane.add(inpEmail);

        //y += 40;

        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setBounds(50, 260, 120, 20);
        contentPane.add(lblContrasenia);

        inpContrasenia = new JPasswordField();
        inpContrasenia.setBounds(180, 260, 220, 20);
        contentPane.add(inpContrasenia);
        
      //y += 40;
        
        JLabel lblObraSocial = new JLabel("Obra Social:");
        lblObraSocial.setBounds(50, 300, 120, 20);
        contentPane.add(lblObraSocial);

        inpObraSocial = new JTextField();
        inpObraSocial.setBounds(180, 300, 220, 20);
        contentPane.add(inpObraSocial);


        //y += 50;

        JLabel lblMensaje = new JLabel("");
        lblMensaje.setBounds(50, 310, 360, 30);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblMensaje);

       // y += 50;

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100, 360, 120, 40);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(250, 360, 120, 40);
        contentPane.add(btnVolver);

        // Acción botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	                	
                	Paciente nuevo = new Paciente(
                		    0,
                		    inpNombre.getText(),
                		    inpApellido.getText(),
                		    validardni(inpDni.getText()),
                		    new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate()),
                		    inpEmail.getText(),
                		    new String(inpContrasenia.getPassword()),
                		    1, // activo
                		    inpObraSocial.getText()
                		);


                    ControllerPaciente.RegistrarPaciente(nuevo);
                    lblMensaje.setText("Paciente registrado correctamente.");
                    limpiarCampos();

                } catch (Exception ex) {
                    lblMensaje.setText("Error al registrar paciente. Revisá los datos."); //aca da el mensaje que no guardo
                	//ex.printStackTrace(); // prueba ara ver el error
                }
            }
        });

        // Acción botón Volver
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaPacientes vista = new VistaPacientes(); // <- cambiá por el nombre real
                vista.setVisible(true);
                dispose();
            }
        });
    }

    private void limpiarCampos() {
        inpNombre.setText("");
        inpApellido.setText("");
        inpDni.setText("");
        dateChooser.setDate(null);
        inpEmail.setText("");
        inpContrasenia.setText("");
        inpObraSocial.setText("");

    }
}
