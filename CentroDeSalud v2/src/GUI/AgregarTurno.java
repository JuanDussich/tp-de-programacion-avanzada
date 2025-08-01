package GUI;

import BLL.Turno;
import DLL.ControllerTurno;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;

public class AgregarTurno extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Campos de entrada
    private JTextField inpFecha, inpHora, inpTipo, inpEstado, inpIdPaciente, inpIdMedico, inpEspecialidad, inpMotivo, inpResultado;

    public AgregarTurno() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 630);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Centro de Salud - Nuevo Turno");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(10, 10, 460, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);

        contentPane.add(crearLabel("Fecha (yyyy-MM-dd):", 50, 60));
        inpFecha = crearCampo(200, 60);
        contentPane.add(inpFecha);

        contentPane.add(crearLabel("Hora (HH:mm):", 50, 100));
        inpHora = crearCampo(200, 100);
        contentPane.add(inpHora);

        contentPane.add(crearLabel("Tipo de Consulta:", 50, 140));
        inpTipo = crearCampo(200, 140);
        contentPane.add(inpTipo);

        contentPane.add(crearLabel("Estado:", 50, 180));
        inpEstado = crearCampo(200, 180);
        contentPane.add(inpEstado);

        contentPane.add(crearLabel("ID Paciente:", 50, 220));
        inpIdPaciente = crearCampo(200, 220);
        contentPane.add(inpIdPaciente);

        contentPane.add(crearLabel("ID Médico:", 50, 260));
        inpIdMedico = crearCampo(200, 260);
        contentPane.add(inpIdMedico);

        contentPane.add(crearLabel("Especialidad:", 50, 300));
        inpEspecialidad = crearCampo(200, 300);
        contentPane.add(inpEspecialidad);

        contentPane.add(crearLabel("Motivo Consulta:", 50, 340));
        inpMotivo = crearCampo(200, 340);
        contentPane.add(inpMotivo);

        contentPane.add(crearLabel("Resultado Consulta:", 50, 380));
        inpResultado = crearCampo(200, 380);
        contentPane.add(inpResultado);

        JLabel lblMensaje = new JLabel("");
        lblMensaje.setBounds(50, 420, 380, 30);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblMensaje);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100, 470, 120, 40);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(250, 470, 120, 40);
        contentPane.add(btnVolver);

        btnGuardar.addActionListener(e -> {
            try {
                String tipoConsulta = inpTipo.getText();
                LocalDate fechaTurno = LocalDate.parse(inpFecha.getText());
                LocalTime horaTurno = LocalTime.parse(inpHora.getText());
                String estado = inpEstado.getText();
                int idPaciente = Integer.parseInt(inpIdPaciente.getText());
                int idMedico = Integer.parseInt(inpIdMedico.getText());
                String especialidad = inpEspecialidad.getText();
                String motivoConsulta = inpMotivo.getText();
                String resultadoConsulta = inpResultado.getText();
// private JTextField inpFecha, inpHora, inpTipo, inpEstado, inpIdPaciente, inpIdMedico, inpEspecialidad, inpMotivo, inpResultado;
                Turno nuevoTurno = new Turno(
                    tipoConsulta,
                    fechaTurno,
                    horaTurno,
                    estado,
                    idPaciente,
                    idMedico,
                    especialidad,
                    motivoConsulta,
                    resultadoConsulta
                );

                ControllerTurno.agregarTurno(nuevoTurno);
                JOptionPane.showMessageDialog(null, "Turno agregado correctamente.");
                limpiarCampos();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar turno: " + ex.getMessage());
            }
        });

        btnVolver.addActionListener(e -> {
            VistaTurno vista = new VistaTurno();
            vista.setVisible(true);
            dispose();
        });
    }

    private JLabel crearLabel(String texto, int x, int y) {
        JLabel lbl = new JLabel(texto);
        lbl.setBounds(x, y, 150, 25);
        return lbl;
    }

    private JTextField crearCampo(int x, int y) {
        JTextField campo = new JTextField();
        campo.setBounds(x, y, 200, 25);
        return campo;
    }

    private void limpiarCampos() {
        inpFecha.setText("");
        inpHora.setText("");
        inpTipo.setText("");
        inpEstado.setText("");
        inpIdPaciente.setText("");
        inpIdMedico.setText("");
        inpEspecialidad.setText("");
        inpMotivo.setText("");
        inpResultado.setText("");
    }
}
