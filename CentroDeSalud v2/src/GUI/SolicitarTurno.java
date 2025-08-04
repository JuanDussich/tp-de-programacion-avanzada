package GUI;

import BLL.Turno;
import BLL.Medico;
import BLL.Especialidad;
import DLL.ControllerTurno;
import DLL.ControllerMedico;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class SolicitarTurno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtMotivo, txtResultado, txtHora, txtTipo;
    private JDateChooser dateChooser;
    private JComboBox<Especialidad> cbEspecialidad;
    private JComboBox<Medico> cbMedico;
    private int idPaciente;

    // Constructor para paciente
    public SolicitarTurno(int idPacienteLogueado) {
        this.idPaciente = idPacienteLogueado;

        setTitle("Solicitar Turno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 520);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Solicitar Turno Médico");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(130, 10, 300, 30);
        contentPane.add(lblTitulo);

        JLabel lblTipo = new JLabel("Tipo de Consulta:");
        lblTipo.setBounds(30, 60, 120, 20);
        contentPane.add(lblTipo);
        txtTipo = new JTextField();
        txtTipo.setBounds(160, 60, 300, 25);
        contentPane.add(txtTipo);

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setBounds(30, 100, 120, 20);
        contentPane.add(lblEspecialidad);
        cbEspecialidad = new JComboBox<>(Especialidad.values());
        cbEspecialidad.setBounds(160, 100, 300, 25);
        contentPane.add(cbEspecialidad);

        JLabel lblMedico = new JLabel("Médico:");
        lblMedico.setBounds(30, 140, 120, 20);
        contentPane.add(lblMedico);
        cbMedico = new JComboBox<>();
        cbMedico.setBounds(160, 140, 300, 25);
        contentPane.add(cbMedico);

        JLabel lblFecha = new JLabel("Fecha (AAAA-MM-DD):");
        lblFecha.setBounds(30, 180, 150, 20);
        contentPane.add(lblFecha);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(190, 180, 270, 25);
        contentPane.add(dateChooser);


        JLabel lblHora = new JLabel("Hora (HH:MM):");
        lblHora.setBounds(30, 220, 120, 20);
        contentPane.add(lblHora);
        txtHora = new JTextField();
        txtHora.setBounds(160, 220, 300, 25);
        contentPane.add(txtHora);

        JLabel lblMotivo = new JLabel("Motivo Consulta:");
        lblMotivo.setBounds(30, 260, 120, 20);
        contentPane.add(lblMotivo);
        txtMotivo = new JTextField();
        txtMotivo.setBounds(160, 260, 300, 25);
        contentPane.add(txtMotivo);

        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(30, 300, 120, 20);
        contentPane.add(lblResultado);
        txtResultado = new JTextField("Pendiente");
        txtResultado.setBounds(160, 300, 300, 25);
        contentPane.add(txtResultado);

        JButton btnSolicitar = new JButton("Solicitar Turno");
        btnSolicitar.setBounds(190, 360, 150, 30);
        contentPane.add(btnSolicitar);

        cbEspecialidad.addActionListener((ActionEvent e) -> {
            Especialidad especialidadSeleccionada = (Especialidad) cbEspecialidad.getSelectedItem();
            List<Medico> medicosFiltrados = ControllerMedico.mostrarMedicos().stream()
                    .filter(m -> m.getEspecialidad() == especialidadSeleccionada)
                    .collect(Collectors.toList());

            cbMedico.removeAllItems();
            for (Medico m : medicosFiltrados) {
                cbMedico.addItem(m);
            }
        });

        btnSolicitar.addActionListener((ActionEvent e) -> {
            try {
                String tipo = txtTipo.getText().trim();
                String especialidad = cbEspecialidad.getSelectedItem().toString();
                String fechaTexto = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate());
                LocalDate fecha = LocalDate.parse(fechaTexto);
                LocalDate fechaTurno = dateChooser.getDate()
                	    .toInstant()
                	    .atZone(java.time.ZoneId.systemDefault())
                	    .toLocalDate();
                LocalTime hora = LocalTime.parse(txtHora.getText().trim());
                String motivo = txtMotivo.getText().trim();
                String resultado = txtResultado.getText().trim();
                Medico medicoSeleccionado = (Medico) cbMedico.getSelectedItem();
                if (medicoSeleccionado == null) throw new Exception("Debe seleccionar un médico.");
                int idMedico = medicoSeleccionado.getId();
                String estado = "Pendiente";

                Turno nuevoTurno = new Turno(
                        tipo,
                        fechaTurno,
                        hora,
                        estado,
                        idPaciente,
                        idMedico,
                        especialidad,
                        motivo,
                        resultado
                );

                ControllerTurno.agregarTurno(nuevoTurno);
                JOptionPane.showMessageDialog(null, "Turno solicitado correctamente.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al solicitar turno: " + ex.getMessage());
            }
        });

        // Inicializar médicos según especialidad al cargar
        cbEspecialidad.setSelectedIndex(0);
        cbEspecialidad.getActionListeners()[0].actionPerformed(null);
    }

    // Constructor adicional para que el médico solicite turnos
    public SolicitarTurno(int idPaciente, int idMedico) {
        this(idPaciente); // Reutiliza interfaz

        Medico medicoUnico = ControllerMedico.mostrarMedicos().stream()
            .filter(m -> m.getId() == idMedico)
            .findFirst()
            .orElse(null);

        if (medicoUnico != null) {
            cbMedico.removeAllItems();
            cbMedico.addItem(medicoUnico);
            cbMedico.setEnabled(false);
            cbEspecialidad.setSelectedItem(medicoUnico.getEspecialidad());
            cbEspecialidad.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Error: Médico no encontrado.");
            dispose();
        }
    }
}
