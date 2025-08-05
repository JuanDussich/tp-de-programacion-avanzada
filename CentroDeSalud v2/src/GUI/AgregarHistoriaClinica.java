package GUI;

import BLL.HistoriaClinica;
import DLL.ControllerHistoriaClinica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AgregarHistoriaClinica extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField inpObservaciones;
    private JTextField inpFecha;
    private JTextField inpTurnoId;
    private JTextField inpPacienteId;
    private JTextField inpTratamientoId;
    private JTextField inpMedicamentoId;
    private JTextField inpMedicoId;
    private JTextField inpDetalle;

    public AgregarHistoriaClinica() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Centro De Salud - Nueva Historia Clínica");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(10, 10, 410, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);

        JLabel lblObservaciones = new JLabel("Observaciones:");
        lblObservaciones.setBounds(50, 60, 120, 25);
        contentPane.add(lblObservaciones);

        inpObservaciones = new JTextField();
        inpObservaciones.setBounds(180, 60, 200, 25);
        contentPane.add(inpObservaciones);

        JLabel lblFecha = new JLabel("Fecha (yyyy-MM-dd):");
        lblFecha.setBounds(50, 100, 120, 25);
        contentPane.add(lblFecha);

        inpFecha = new JTextField();
        inpFecha.setBounds(180, 100, 200, 25);
        contentPane.add(inpFecha);

        JLabel lblTurnoId = new JLabel("ID Turno:");
        lblTurnoId.setBounds(50, 140, 120, 25);
        contentPane.add(lblTurnoId);

        inpTurnoId = new JTextField();
        inpTurnoId.setBounds(180, 140, 200, 25);
        contentPane.add(inpTurnoId);

        JLabel lblPacienteId = new JLabel("ID Paciente:");
        lblPacienteId.setBounds(50, 180, 120, 25);
        contentPane.add(lblPacienteId);

        inpPacienteId = new JTextField();
        inpPacienteId.setBounds(180, 180, 200, 25);
        contentPane.add(inpPacienteId);

        JLabel lblTratamientoId = new JLabel("ID Tratamiento:");
        lblTratamientoId.setBounds(50, 220, 120, 25);
        contentPane.add(lblTratamientoId);

        inpTratamientoId = new JTextField();
        inpTratamientoId.setBounds(180, 220, 200, 25);
        contentPane.add(inpTratamientoId);

        JLabel lblMedicamentoId = new JLabel("ID Medicamento:");
        lblMedicamentoId.setBounds(50, 260, 120, 25);
        contentPane.add(lblMedicamentoId);

        inpMedicamentoId = new JTextField();
        inpMedicamentoId.setBounds(180, 260, 200, 25);
        contentPane.add(inpMedicamentoId);

        JLabel lblMedicoId = new JLabel("ID Médico:");
        lblMedicoId.setBounds(50, 300, 120, 25);
        contentPane.add(lblMedicoId);

        inpMedicoId = new JTextField();
        inpMedicoId.setBounds(180, 300, 200, 25);
        contentPane.add(inpMedicoId);

        JLabel lblDetalle = new JLabel("Detalle Consulta:");
        lblDetalle.setBounds(50, 340, 120, 25);
        contentPane.add(lblDetalle);

        inpDetalle = new JTextField();
        inpDetalle.setBounds(180, 340, 200, 25);
        contentPane.add(inpDetalle);

        JLabel lblMensaje = new JLabel("");
        lblMensaje.setBounds(50, 380, 330, 30);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblMensaje);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(20, 424, 120, 40);
        contentPane.add(btnGuardar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(160, 424, 120, 40);
        contentPane.add(btnLimpiar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(300, 424, 120, 40);
        contentPane.add(btnVolver);

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String obs = inpObservaciones.getText();
                    LocalDate fecha = LocalDate.parse(inpFecha.getText());
                    int turnoId = Integer.parseInt(inpTurnoId.getText());
                    int pacienteId = Integer.parseInt(inpPacienteId.getText());
                    int tratamientoId = Integer.parseInt(inpTratamientoId.getText());
                    int medicamentoId = Integer.parseInt(inpMedicamentoId.getText());
                    int medicoId = Integer.parseInt(inpMedicoId.getText());
                    String detalle = inpDetalle.getText();

                    HistoriaClinica nueva = new HistoriaClinica(
                        obs, fecha, turnoId, pacienteId, tratamientoId, medicamentoId, medicoId, detalle
                    );

                    ControllerHistoriaClinica controller = new ControllerHistoriaClinica();
                    controller.crearHistoriaClinica(nueva);

                    lblMensaje.setText("Historia clínica registrada correctamente.");
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    lblMensaje.setText("Error: IDs deben ser números enteros.");
                } catch (DateTimeParseException ex) {
                    lblMensaje.setText("Error: Fecha inválida. Use formato yyyy-MM-dd.");
                } catch (Exception ex) {
                    lblMensaje.setText("Error al registrar la historia clínica.");
                    ex.printStackTrace();
                }
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                lblMensaje.setText("");
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Solo cierra esta ventana, la pantalla del médico debe mantenerse visible
            }
        });
    }

    private void limpiarCampos() {
        inpObservaciones.setText("");
        inpFecha.setText("");
        inpTurnoId.setText("");
        inpPacienteId.setText("");
        inpTratamientoId.setText("");
        inpMedicamentoId.setText("");
        inpMedicoId.setText("");
        inpDetalle.setText("");
    }
}