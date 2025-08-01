package GUI;

import BLL.Turno;
import DLL.ControllerTurno;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;

public class SolicitarTurno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtEspecialidad, txtMotivo, txtResultado, txtHora, txtFecha, txtTipo, txtIdMedico;

    private int idPaciente; // Se recibe al crear la pantalla

    public SolicitarTurno(int idPacienteLogueado) {
        this.idPaciente = idPacienteLogueado;

        setTitle("Solicitar Turno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Solicitar Turno Médico");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(100, 10, 300, 30);
        contentPane.add(lblTitulo);

        JLabel lblTipo = new JLabel("Tipo de Consulta:");
        lblTipo.setBounds(30, 60, 120, 20);
        contentPane.add(lblTipo);
        txtTipo = new JTextField();
        txtTipo.setBounds(160, 60, 200, 25);
        contentPane.add(txtTipo);

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setBounds(30, 100, 120, 20);
        contentPane.add(lblEspecialidad);
        txtEspecialidad = new JTextField();
        txtEspecialidad.setBounds(160, 100, 200, 25);
        contentPane.add(txtEspecialidad);

        JLabel lblFecha = new JLabel("Fecha (AAAA-MM-DD):");
        lblFecha.setBounds(30, 140, 150, 20);
        contentPane.add(lblFecha);
        txtFecha = new JTextField();
        txtFecha.setBounds(190, 140, 170, 25);
        contentPane.add(txtFecha);

        JLabel lblHora = new JLabel("Hora (HH:MM):");
        lblHora.setBounds(30, 180, 120, 20);
        contentPane.add(lblHora);
        txtHora = new JTextField();
        txtHora.setBounds(160, 180, 200, 25);
        contentPane.add(txtHora);

        JLabel lblMotivo = new JLabel("Motivo Consulta:");
        lblMotivo.setBounds(30, 220, 120, 20);
        contentPane.add(lblMotivo);
        txtMotivo = new JTextField();
        txtMotivo.setBounds(160, 220, 200, 25);
        contentPane.add(txtMotivo);

        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(30, 260, 120, 20);
        contentPane.add(lblResultado);
        txtResultado = new JTextField("Pendiente"); // Por defecto
        txtResultado.setBounds(160, 260, 200, 25);
        contentPane.add(txtResultado);

        JLabel lblIdMedico = new JLabel("ID Médico:");
        lblIdMedico.setBounds(30, 300, 120, 20);
        contentPane.add(lblIdMedico);
        txtIdMedico = new JTextField();
        txtIdMedico.setBounds(160, 300, 200, 25);
        contentPane.add(txtIdMedico);

        JButton btnSolicitar = new JButton("Solicitar Turno");
        btnSolicitar.setBounds(160, 350, 150, 30);
        contentPane.add(btnSolicitar);

        btnSolicitar.addActionListener((ActionEvent e) -> {
            try {
                String tipo = txtTipo.getText().trim();
                String especialidad = txtEspecialidad.getText().trim();
                LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
                LocalTime hora = LocalTime.parse(txtHora.getText().trim());
                String motivo = txtMotivo.getText().trim();
                String resultado = txtResultado.getText().trim();
                int idMedico = Integer.parseInt(txtIdMedico.getText().trim());
                String estado = "Pendiente";

                Turno nuevoTurno = new Turno(
                        tipo,
                        fecha,
                        hora,
                        estado,
                        idPaciente,   // Viene del constructor
                        idMedico,
                        especialidad,
                        motivo,
                        resultado
                );

                ControllerTurno.agregarTurno(nuevoTurno);
                JOptionPane.showMessageDialog(null, "Turno solicitado correctamente.");
                dispose(); // Cerrar ventana

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al solicitar turno: " + ex.getMessage());
            }
        });
    }
}
