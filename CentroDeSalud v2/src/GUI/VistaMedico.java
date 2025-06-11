package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Medico;
import DLL.ControllerMedico;

import java.awt.*;
import java.util.LinkedList;

public class VistaMedico extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Medico usuarioSeleccionado;  // Médico seleccionado en la tabla

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VistaMedico frame = new VistaMedico();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VistaMedico() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setBounds(10, 10, 760, 20);
        contentPane.add(lblSeleccionado);

        // Definir columnas según atributos de Medico (id, nombre, apellido, matricula, email, contrasenia, especialidad, activo)
        model = new DefaultTableModel(new String[]{
            "ID Medico", "Nombre", "Apellido", "Matricula", "Email", "Contraseña", "Especialidad", "Activo"
        }, 0);

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 760, 200);
        contentPane.add(scrollPane);

        // Botones
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregar.setBounds(112, 269, 124, 55);
        contentPane.add(btnAgregar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEditar.setBounds(322, 269, 124, 55);
        contentPane.add(btnEditar);
        

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminar.setBounds(531, 269, 124, 55);
        contentPane.add(btnEliminar);

        // Al seleccionar fila, actualizar la variable usuarioSeleccionado y mostrar datos en el label
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    // Crear un objeto Medico con datos de la fila seleccionada
                    usuarioSeleccionado = new Medico(
                        (int) model.getValueAt(row, 0),               // idMedico (int)
                        (String) model.getValueAt(row, 1),            // nombre (String)
                        (String) model.getValueAt(row, 2),            // apellido (String)
                        (String) model.getValueAt(row, 3),            // matricula (String)
                        (String) model.getValueAt(row, 4),            // email (String)
                        (String) model.getValueAt(row, 5),            // contrasenia (String)
                        (String) model.getValueAt(row, 6),            // especialidad (String)
                        (int) model.getValueAt(row, 7)                 // activo (int)
                    );

                    // Mostrar datos seleccionados en el label
                    lblSeleccionado.setText("Seleccionado: ID=" + usuarioSeleccionado.getId()
                        + ", Nombre=" + usuarioSeleccionado.getNombre()
                        + ", Apellido=" + usuarioSeleccionado.getApellido()
                        + ", Matrícula=" + usuarioSeleccionado.getMatricula()
                        + ", Email=" + usuarioSeleccionado.getEmail()
                        + ", Especialidad=" + usuarioSeleccionado.getEspecialidad()
                        + ", Activo=" + usuarioSeleccionado.getActivo()
                    );
                }
            }
        });

        // Cargar datos de la BD en la tabla al iniciar la ventana
        cargarTabla();

        // Acción botón Agregar: Mostrar diálogo para ingresar datos y luego registrar nuevo médico
        btnAgregar.addActionListener(e -> {
            JTextField nombreField = new JTextField();
            JTextField apellidoField = new JTextField();
            JTextField matriculaField = new JTextField();
            JTextField emailField = new JTextField();
            JPasswordField contraseniaField = new JPasswordField();
            JTextField especialidadField = new JTextField();

            Object[] fields = {
                "Nombre:", nombreField,
                "Apellido:", apellidoField,
                "Matrícula:", matriculaField,
                "Email:", emailField,
                "Contraseña:", contraseniaField,
                "Especialidad:", especialidadField
            };

            int option = JOptionPane.showConfirmDialog(null, fields, "Agregar Médico", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                Medico nuevo = new Medico(
                    0, // id (se genera en la base)
                    nombreField.getText(),
                    apellidoField.getText(),
                    matriculaField.getText(),
                    emailField.getText(),
                    new String(contraseniaField.getPassword()),
                    especialidadField.getText(),
                    1 // activo por defecto = 1 (true)
                );
                ControllerMedico.RegistrarMedico(nuevo);
                cargarTabla();
            }
        });

        // Acción botón Editar: abrir ventana de edición si hay médico seleccionado
        btnEditar.addActionListener(e -> {
            if (usuarioSeleccionado != null) {
                EditarMedico editar = new EditarMedico(usuarioSeleccionado);
                editar.setVisible(true);
                dispose(); // cerrar ventana actual
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario.");
            }
        });

        // Acción botón Eliminar: cambiar activo a falso para el médico seleccionado
        btnEliminar.addActionListener(e -> {
            if (usuarioSeleccionado != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este médico?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean eliminado = ControllerMedico.eliminarMedico(usuarioSeleccionado.getId());
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Médico eliminado correctamente.");
                        cargarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar médico.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario para eliminar.");
            }
        });

    }

    /**
     * Método para cargar los médicos desde la base de datos y actualizar la tabla.
     */
    private void cargarTabla() {
        model.setRowCount(0); // limpiar tabla
        LinkedList<Medico> medico = ControllerMedico.mostrarMedicos(); // corregido: plural
        for (Medico m : medico) {
            model.addRow(new Object[]{
                m.getId(),
                m.getNombre(),
                m.getApellido(),
                m.getMatricula(),
                m.getEmail(),
                m.getContrasenia(),
                m.getEspecialidad(),
                m.getActivo()
            });
        }
    }
}
