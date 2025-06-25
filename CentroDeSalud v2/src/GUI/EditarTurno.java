package GUI;

import BLL.Turno;
import DLL.ControllerTurno;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class EditarTurno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField txtIdTurno;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtTipoConsulta;
    private JTextField txtEstado;
    private JTextField txtIdPaciente;
    private JTextField txtIdMedico;
    private JTextField txtEspecialidad;
    private JTextField txtMotivoConsulta;
    private JTextField txtResultadoConsulta;

    private Turno turnoOriginal;

    public EditarTurno(Turno turno) {
        this.turnoOriginal = turno;

        setTitle("Editar Turno - ID " + turno.getIdTurno());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10,10,10,10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Etiquetas y campos para cada propiedad del turno

        JLabel lblIdTurno = new JLabel("ID Turno:");
        lblIdTurno.setBounds(10, 10, 120, 25);
        contentPane.add(lblIdTurno);

        txtIdTurno = new JTextField(String.valueOf(turno.getIdTurno()));
        txtIdTurno.setBounds(140, 10, 250, 25);
        txtIdTurno.setEditable(false); // No editable porque es PK
        contentPane.add(txtIdTurno);

        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(10, 45, 120, 25);
        contentPane.add(lblFecha);

        txtFecha = new JTextField(turno.getFechaTurno().toString());
        txtFecha.setBounds(140, 45, 250, 25);
        contentPane.add(txtFecha);

        JLabel lblHora = new JLabel("Hora (HH:MM:SS):");
        lblHora.setBounds(10, 80, 120, 25);
        contentPane.add(lblHora);

        txtHora = new JTextField(turno.getHoraTurno().toString());
        txtHora.setBounds(140, 80, 250, 25);
        contentPane.add(txtHora);

        JLabel lblTipoConsulta = new JLabel("Tipo Consulta:");
        lblTipoConsulta.setBounds(10, 115, 120, 25);
        contentPane.add(lblTipoConsulta);

        txtTipoConsulta = new JTextField(turno.getTipoConsulta());
        txtTipoConsulta.setBounds(140, 115, 250, 25);
        contentPane.add(txtTipoConsulta);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(10, 150, 120, 25);
        contentPane.add(lblEstado);

        txtEstado = new JTextField(turno.getEstado());
        txtEstado.setBounds(140, 150, 250, 25);
        contentPane.add(txtEstado);

        JLabel lblIdPaciente = new JLabel("ID Paciente:");
        lblIdPaciente.setBounds(10, 185, 120, 25);
        contentPane.add(lblIdPaciente);

        txtIdPaciente = new JTextField(String.valueOf(turno.getIdPaciente()));
        txtIdPaciente.setBounds(140, 185, 250, 25);
        contentPane.add(txtIdPaciente);

        JLabel lblIdMedico = new JLabel("ID Médico:");
        lblIdMedico.setBounds(10, 220, 120, 25);
        contentPane.add(lblIdMedico);

        txtIdMedico = new JTextField(String.valueOf(turno.getIdMedico()));
        txtIdMedico.setBounds(140, 220, 250, 25);
        contentPane.add(txtIdMedico);

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setBounds(10, 255, 120, 25);
        contentPane.add(lblEspecialidad);

        txtEspecialidad = new JTextField(turno.getEspecialidad());
        txtEspecialidad.setBounds(140, 255, 250, 25);
        contentPane.add(txtEspecialidad);

        JLabel lblMotivoConsulta = new JLabel("Motivo Consulta:");
        lblMotivoConsulta.setBounds(10, 290, 120, 25);
        contentPane.add(lblMotivoConsulta);

        txtMotivoConsulta = new JTextField(turno.getMotivoConsulta());
        txtMotivoConsulta.setBounds(140, 290, 250, 25);
        contentPane.add(txtMotivoConsulta);

        JLabel lblResultadoConsulta = new JLabel("Resultado Consulta:");
        lblResultadoConsulta.setBounds(10, 325, 120, 25);
        contentPane.add(lblResultadoConsulta);

        txtResultadoConsulta = new JTextField(turno.getResultadoConsulta());
        txtResultadoConsulta.setBounds(140, 325, 250, 25);
        contentPane.add(txtResultadoConsulta);

        // Botón Guardar cambios
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(80, 380, 120, 40);
        contentPane.add(btnGuardar);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(250, 380, 120, 40);
        contentPane.add(btnCancelar);

        // Acción botón Guardar
        btnGuardar.addActionListener(e -> {
            try {
                // Validar y obtener datos
                LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
                LocalTime hora = LocalTime.parse(txtHora.getText().trim());
                String tipoConsulta = txtTipoConsulta.getText().trim();
                String estado = txtEstado.getText().trim();
                int idPaciente = Integer.parseInt(txtIdPaciente.getText().trim());
                int idMedico = Integer.parseInt(txtIdMedico.getText().trim());
                String especialidad = txtEspecialidad.getText().trim();
                String motivoConsulta = txtMotivoConsulta.getText().trim();
                String resultadoConsulta = txtResultadoConsulta.getText().trim();

                // Crear objeto Turno actualizado
                Turno turnoEditado = new Turno();
                turnoEditado.setIdTurno(turnoOriginal.getIdTurno()); // mismo ID
                turnoEditado.setFechaTurno(fecha);
                turnoEditado.setHoraTurno(hora);
                turnoEditado.setTipoConsulta(tipoConsulta);
                turnoEditado.setEstado(estado);
                turnoEditado.setIdPaciente(idPaciente);
                turnoEditado.setIdMedico(idMedico);
                turnoEditado.setEspecialidad(especialidad);
                turnoEditado.setMotivoConsulta(motivoConsulta);
                turnoEditado.setResultadoConsulta(resultadoConsulta);

                // Guardar cambios en BD
                boolean modificado = ControllerTurno.modificarTurno(turnoEditado);

                if (modificado) {
                    JOptionPane.showMessageDialog(null, "Turno actualizado correctamente.");
                    // Volver a la vista principal
                    VistaTurno vistaTurno = new VistaTurno();
                    vistaTurno.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar turno.");
                }

            } catch (DateTimeParseException dtpe) {
                JOptionPane.showMessageDialog(null, "Formato de fecha u hora inválido.");
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos para ID Paciente y Médico.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Acción botón Cancelar: volver a la vista sin guardar
        btnCancelar.addActionListener(e -> {
            VistaTurno vistaTurno = new VistaTurno();
            vistaTurno.setVisible(true);
            dispose();
        });
    }
}
