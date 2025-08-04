package GUI;

import BLL.Turno;
import BLL.Medico;
import DLL.ControllerTurno;
import DLL.ControllerMedico;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class VistaTurno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Turno turnoSeleccionado;
    private JTextField inpFiltroIdTurno;
    private int idMedico;

    // Constructor para médico logueado
    public VistaTurno(int idMedicoLogueado) {
        this.idMedico = idMedicoLogueado;
        inicializarComponentes();
        cargarTurnos();
    }

    // Constructor vacío para administrador (ver todos los turnos)
    public VistaTurno() {
        this.idMedico = 0; // 0 implica sin filtro por médico
        inicializarComponentes();
        cargarTurnos();
    }

    private void inicializarComponentes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Centro De Salud - Turnos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblTitulo.setBounds(10, 10, 760, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);

        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/turno.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel lblImagen = new JLabel(new ImageIcon(imagenEscalada));
        lblImagen.setBounds(700, 10, 60, 60);
        contentPane.add(lblImagen);

        model = new DefaultTableModel(new String[]{
                "Nro. Turno", "Fecha", "Hora", "Tipo", "Estado",
                "Paciente ID", "Médico ID", "Especialidad",
                "Motivo", "Resultado"
        }, 0);

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 760, 300);
        contentPane.add(scrollPane);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregar.setBounds(80, 420, 120, 40);
        contentPane.add(btnAgregar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEditar.setBounds(320, 420, 120, 40);
        contentPane.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminar.setBounds(560, 420, 120, 40);
        contentPane.add(btnEliminar);

        inpFiltroIdTurno = new JTextField();
        inpFiltroIdTurno.setBounds(80, 511, 120, 30);
        contentPane.add(inpFiltroIdTurno);
        inpFiltroIdTurno.setColumns(10);

        JLabel lblFiltro = new JLabel("Filtrar por Nro. Turno:");
        lblFiltro.setBounds(80, 481, 131, 20);
        contentPane.add(lblFiltro);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(320, 491, 140, 30);
        contentPane.add(btnFiltrar);

        JButton btnReiniciarFiltro = new JButton("Reiniciar filtro");
        btnReiniciarFiltro.setBounds(320, 531, 140, 30);
        contentPane.add(btnReiniciarFiltro);

        btnAgregar.addActionListener(e -> {
            if (idMedico != 0) {
                // Si hay un médico logueado
                Medico medico = ControllerMedico.buscarPorId(idMedico);
                if (medico != null) {
                    AgregarTurno agregar = new AgregarTurno(medico);
                    agregar.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró información del médico logueado.");
                }
            } else {
                // Si accede un administrador
                AgregarTurno agregar = new AgregarTurno();
                agregar.setVisible(true);
                dispose();
            }
        });


        btnEditar.addActionListener(e -> {
            if (turnoSeleccionado != null) {
                EditarTurno editar = new EditarTurno(turnoSeleccionado);
                editar.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un turno para editar.");
            }
        });

        btnEliminar.addActionListener(e -> {
            if (turnoSeleccionado != null) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro que desea eliminar el turno nro. " + turnoSeleccionado.getIdTurno() + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ControllerTurno.eliminarTurno(turnoSeleccionado.getIdTurno());
                    cargarTurnos();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un turno para eliminar.");
            }
        });

        btnFiltrar.addActionListener(e -> {
            String filtroTexto = inpFiltroIdTurno.getText().trim();
            if (filtroTexto.isEmpty()) {
                cargarTurnos();
            } else {
                try {
                    int filtroId = Integer.parseInt(filtroTexto);
                    cargarTurnosFiltrados(filtroId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido para el filtro.");
                }
            }
        });

        btnReiniciarFiltro.addActionListener(e -> {
            inpFiltroIdTurno.setText("");
            cargarTurnos();
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                turnoSeleccionado = new Turno();
                turnoSeleccionado.setIdTurno((int) model.getValueAt(row, 0));
                turnoSeleccionado.setFechaTurno((java.time.LocalDate) model.getValueAt(row, 1));
                turnoSeleccionado.setHoraTurno((java.time.LocalTime) model.getValueAt(row, 2));
                turnoSeleccionado.setTipoConsulta((String) model.getValueAt(row, 3));
                turnoSeleccionado.setEstado((String) model.getValueAt(row, 4));
                turnoSeleccionado.setIdPaciente((int) model.getValueAt(row, 5));
                turnoSeleccionado.setIdMedico((int) model.getValueAt(row, 6));
                turnoSeleccionado.setEspecialidad((String) model.getValueAt(row, 7));
                turnoSeleccionado.setMotivoConsulta((String) model.getValueAt(row, 8));
                turnoSeleccionado.setResultadoConsulta((String) model.getValueAt(row, 9));
            }
        });
    }

    private void cargarTurnos() {
        model.setRowCount(0);
        LinkedList<Turno> turnos = ControllerTurno.listarTurnos().stream()
                .filter(t -> idMedico == 0 || t.getIdMedico() == idMedico)
                .collect(Collectors.toCollection(LinkedList::new));

        for (Turno t : turnos) {
            model.addRow(new Object[]{
                    t.getIdTurno(),
                    t.getFechaTurno(),
                    t.getHoraTurno(),
                    t.getTipoConsulta(),
                    t.getEstado(),
                    t.getIdPaciente(),
                    t.getIdMedico(),
                    t.getEspecialidad(),
                    t.getMotivoConsulta(),
                    t.getResultadoConsulta()
            });
        }
    }

    private void cargarTurnosFiltrados(int filtroId) {
        model.setRowCount(0);
        LinkedList<Turno> filtrados = ControllerTurno.listarTurnos().stream()
                .filter(t -> t.getIdTurno() == filtroId && (idMedico == 0 || t.getIdMedico() == idMedico))
                .collect(Collectors.toCollection(LinkedList::new));

        for (Turno t : filtrados) {
            model.addRow(new Object[]{
                    t.getIdTurno(),
                    t.getFechaTurno(),
                    t.getHoraTurno(),
                    t.getTipoConsulta(),
                    t.getEstado(),
                    t.getIdPaciente(),
                    t.getIdMedico(),
                    t.getEspecialidad(),
                    t.getMotivoConsulta(),
                    t.getResultadoConsulta()
            });
        }
    }
}
