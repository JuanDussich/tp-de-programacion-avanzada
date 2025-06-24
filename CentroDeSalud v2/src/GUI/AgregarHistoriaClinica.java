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
    private JTextField inpFecha;           // formato esperado: yyyy-MM-dd
    private JTextField inpTurnoId;
    private JTextField inpPacienteId;
    private JTextField inpTratamientoId;
    private JTextField inpMedicamentoId;
    private JTextField inpMedicoId;

    public AgregarHistoriaClinica() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 550);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Centro De Salud - Nueva Historia Clínica");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(10, 10, 410, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);

        //int y = 60;

        JLabel lblObservaciones = new JLabel("Observaciones:");
        lblObservaciones.setBounds(50, 60, 120, 25);
        contentPane.add(lblObservaciones);

        inpObservaciones = new JTextField();
        inpObservaciones.setBounds(180, 60, 200, 25);
        contentPane.add(inpObservaciones);

        //y += 40;
        JLabel lblFecha = new JLabel("Fecha (yyyy-MM-dd):");
        lblFecha.setBounds(50, 100, 120, 25);
        contentPane.add(lblFecha);

        inpFecha = new JTextField();
        inpFecha.setBounds(180, 100, 200, 25);
        contentPane.add(inpFecha);

        //y += 40;
        JLabel lblTurnoId = new JLabel("ID Turno:");
        lblTurnoId.setBounds(50, 140, 120, 25);
        contentPane.add(lblTurnoId);

        inpTurnoId = new JTextField();
        inpTurnoId.setBounds(180, 140, 200, 25);
        contentPane.add(inpTurnoId);

        //y += 40;
        JLabel lblPacienteId = new JLabel("ID Paciente:");
        lblPacienteId.setBounds(50, 180, 120, 25);
        contentPane.add(lblPacienteId);

        inpPacienteId = new JTextField();
        inpPacienteId.setBounds(180, 180, 200, 25);
        contentPane.add(inpPacienteId);

        //y += 40;
        JLabel lblTratamientoId = new JLabel("ID Tratamiento:");
        lblTratamientoId.setBounds(50, 220, 120, 25);
        contentPane.add(lblTratamientoId);

        inpTratamientoId = new JTextField();
        inpTratamientoId.setBounds(180, 220, 200, 25);
        contentPane.add(inpTratamientoId);

        //y += 40;
        JLabel lblMedicamentoId = new JLabel("ID Medicamento:");
        lblMedicamentoId.setBounds(50, 260, 120, 25);
        contentPane.add(lblMedicamentoId);

        inpMedicamentoId = new JTextField();
        inpMedicamentoId.setBounds(180, 260, 200, 25);
        contentPane.add(inpMedicamentoId);

        //y += 40;
        JLabel lblMedicoId = new JLabel("ID Médico:");
        lblMedicoId.setBounds(50, 300, 120, 25);
        contentPane.add(lblMedicoId);

        inpMedicoId = new JTextField();
        inpMedicoId.setBounds(180, 300, 200, 25);
        contentPane.add(inpMedicoId);

        //y += 50;

        JLabel lblMensaje = new JLabel("");
        lblMensaje.setBounds(50, 350, 330, 30);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblMensaje);

        //y += 40;

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(80, 390, 120, 40);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(220, 390, 120, 40);
        contentPane.add(btnVolver);

        // Acción botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String obs = inpObservaciones.getText();
                    LocalDate fecha = LocalDate.parse(inpFecha.getText()); // Puede lanzar DateTimeParseException
                    int turnoId = Integer.parseInt(inpTurnoId.getText());
                    int pacienteId = Integer.parseInt(inpPacienteId.getText());
                    int tratamientoId = Integer.parseInt(inpTratamientoId.getText());
                    int medicamentoId = Integer.parseInt(inpMedicamentoId.getText());
                    int medicoId = Integer.parseInt(inpMedicoId.getText());

                    HistoriaClinica nueva = new HistoriaClinica(
                        0, // id que genera la BD
                        obs,
                        fecha,
                        turnoId,
                        pacienteId,
                        tratamientoId,
                        medicamentoId,
                        medicoId
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

        // Acción botón Volver
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaHistoriaClinica vista = new VistaHistoriaClinica();
                vista.setVisible(true);
                dispose();
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
    }
}
