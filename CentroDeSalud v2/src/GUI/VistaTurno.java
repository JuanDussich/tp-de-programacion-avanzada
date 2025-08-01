package GUI;

import BLL.Turno;
import DLL.ControllerTurno;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class VistaTurno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Turno turnoSeleccionado;
    private JTextField inpFiltroIdTurno;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VistaTurno frame = new VistaTurno();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VistaTurno() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("Centro De Salud - Turnos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblTitulo.setBounds(10, 10, 760, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);

        // Imagen de turno
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/turno.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        JLabel lblImagen = new JLabel(iconoEscalado);
        lblImagen.setBounds(700, 10, 60, 60);
        contentPane.add(lblImagen);

        // Tabla con columnas de Turno
        model = new DefaultTableModel(new String[]{
                "Nro. Turno", "Fecha", "Hora", "Tipo", "Estado",
                "Paciente ID", "Médico ID", "Especialidad",
                "Motivo", "Resultado"
        }, 0);

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 760, 300);
        contentPane.add(scrollPane);

        // Botón Agregar
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregar.setBounds(80, 420, 120, 40);
        contentPane.add(btnAgregar);

        // Botón Editar
        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEditar.setBounds(320, 420, 120, 40);
        contentPane.add(btnEditar);

        // Botón Eliminar
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminar.setBounds(560, 420, 120, 40);
        contentPane.add(btnEliminar);

        // Campo de texto para filtrar por ID turno
        inpFiltroIdTurno = new JTextField();
        inpFiltroIdTurno.setBounds(80, 511, 120, 30);
        contentPane.add(inpFiltroIdTurno);
        inpFiltroIdTurno.setColumns(10);

        JLabel lblFiltro = new JLabel("Filtrar por Nro. Turno:");
        lblFiltro.setBounds(80, 481, 131, 20);
        contentPane.add(lblFiltro);

        // Botón para aplicar filtro
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(320, 491, 140, 30);
        contentPane.add(btnFiltrar);

        // Botón para reiniciar filtro
        JButton btnReiniciarFiltro = new JButton("Reiniciar filtro");
        btnReiniciarFiltro.setBounds(320, 531, 140, 30);
        contentPane.add(btnReiniciarFiltro);

		/*
		 * // Agregue este boton para probar solicitar turno, pase parametros fijo id
		 * usuario JButton btnSolicitar = new JButton("Solicitar Turno");
		 * btnSolicitar.setFont(new Font("Tahoma", Font.BOLD, 11));
		 * btnSolicitar.setBounds(560, 486, 120, 40); // Ubicalo donde quieras
		 * contentPane.add(btnSolicitar);
		 * 
		 * // Acción para abrir la ventana SolicitarTurno
		 * btnSolicitar.addActionListener(e -> { int idPaciente = 123; // el id paciente
		 * tendria que mandar el del login SolicitarTurno solicitar = new
		 * SolicitarTurno(idPaciente); solicitar.setVisible(true); // });
		 */
        
        // Acción del botón Agregar: abre ventana AgregarTurno
        btnAgregar.addActionListener(e -> {
            AgregarTurno agregar = new AgregarTurno();
            agregar.setVisible(true);
            dispose();
        });

        // Acción del botón Editar: abre ventana EditarTurno si hay turno seleccionado
        btnEditar.addActionListener(e -> {
            if (turnoSeleccionado != null) {
                EditarTurno editar = new EditarTurno(turnoSeleccionado);
                editar.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un turno para editar.");
            }
        });

        // Acción del botón Eliminar: elimina el turno seleccionado con confirmación
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

        // Acción botón filtrar: filtra la tabla por ID turno (si el campo está vacío carga todo)
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

        // Acción botón reiniciar filtro: limpia filtro y recarga todos los turnos
        btnReiniciarFiltro.addActionListener(e -> {
            inpFiltroIdTurno.setText("");
            cargarTurnos();
        });

        // Detectar selección de fila en la tabla para almacenar turno seleccionado
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

        // Cargar todos los turnos inicialmente
        cargarTurnos();
    }

    // Método para cargar todos los turnos desde la BD
    private void cargarTurnos() {
        model.setRowCount(0);
        LinkedList<Turno> turnos = ControllerTurno.listarTurnos();
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

    // Método para cargar turnos filtrados por ID (exacto)
    private void cargarTurnosFiltrados(int filtroId) {
        model.setRowCount(0);
        LinkedList<Turno> filtrados = ControllerTurno.listarTurnos().stream()
                .filter(t -> t.getIdTurno() == filtroId)
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
