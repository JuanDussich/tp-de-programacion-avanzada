package GUI;

import BLL.Turno;
import DLL.ControllerTurno;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class VistaTurnoPaciente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Turno turnoSeleccionado;
    private int idPaciente;

    public VistaTurnoPaciente(int idPacienteLogueado) {
        this.idPaciente = idPacienteLogueado;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Mis Turnos Solicitados");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(10, 10, 760, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);

        model = new DefaultTableModel(new String[]{
                "Nro. Turno", "Fecha", "Hora", "Tipo", "Estado",
                "Médico ID", "Especialidad", "Motivo", "Resultado"
        }, 0);

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 60, 760, 350);
        contentPane.add(scrollPane);

        JButton btnCancelar = new JButton("Cancelar Turno");
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCancelar.setBounds(320, 430, 160, 40);
        contentPane.add(btnCancelar);

        // Acción botón cancelar
        btnCancelar.addActionListener(e -> {
            if (turnoSeleccionado != null) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Desea cancelar el turno nro. " + turnoSeleccionado.getIdTurno() + "?",
                        "Confirmar cancelación",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ControllerTurno.eliminarTurno(turnoSeleccionado.getIdTurno());
                    cargarTurnosPaciente();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un turno para cancelar.");
            }
        });

        // Detectar selección de fila
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                turnoSeleccionado = new Turno();
                turnoSeleccionado.setIdTurno((int) model.getValueAt(row, 0));
                turnoSeleccionado.setFechaTurno((java.time.LocalDate) model.getValueAt(row, 1));
                turnoSeleccionado.setHoraTurno((java.time.LocalTime) model.getValueAt(row, 2));
                turnoSeleccionado.setTipoConsulta((String) model.getValueAt(row, 3));
                turnoSeleccionado.setEstado((String) model.getValueAt(row, 4));
                turnoSeleccionado.setIdMedico((int) model.getValueAt(row, 5));
                turnoSeleccionado.setEspecialidad((String) model.getValueAt(row, 6));
                turnoSeleccionado.setMotivoConsulta((String) model.getValueAt(row, 7));
                turnoSeleccionado.setResultadoConsulta((String) model.getValueAt(row, 8));
            }
        });

        cargarTurnosPaciente();
    }

    private void cargarTurnosPaciente() {
        model.setRowCount(0);
        LinkedList<Turno> turnos = ControllerTurno.listarTurnos().stream()
                .filter(t -> t.getIdPaciente() == idPaciente)
                .collect(Collectors.toCollection(LinkedList::new));

        for (Turno t : turnos) {
            model.addRow(new Object[]{
                    t.getIdTurno(),
                    t.getFechaTurno(),
                    t.getHoraTurno(),
                    t.getTipoConsulta(),
                    t.getEstado(),
                    t.getIdMedico(),
                    t.getEspecialidad(),
                    t.getMotivoConsulta(),
                    t.getResultadoConsulta()
            });
        }
    }
}
