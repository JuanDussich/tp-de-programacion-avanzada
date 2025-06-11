package GUI;

import BLL.Medico;
import DLL.ControllerMedico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PantallaMedico extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1496404291286514983L;
	// Componentes de la GUI
    private JTable tablaMedicos;
    private DefaultTableModel modeloTabla;
    private JButton btnEditar, btnEliminar, btnRefrescar;

    public PantallaMedico() {
        // Configura la ventana principal
        setTitle("Gestión de Médicos");
        setSize(800, 400);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crea el modelo de la tabla con las columnas
        modeloTabla = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Apellido", "Matrícula", "Email", "Contrasenia", "Especialidad", "Activo"}, 0
        );
        tablaMedicos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaMedicos); // Scroll para la tabla

        // Panel inferior con los botones
        JPanel panelBotones = new JPanel();
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnRefrescar = new JButton("Refrescar");
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRefrescar);

        // Agrega componentes al layout principal
        add(scroll, BorderLayout.CENTER);       // Tabla en el centro
        add(panelBotones, BorderLayout.SOUTH);  // Botones abajo

        // Carga los médicos en la tabla
        cargarMedicos();

        // -------------------- Acción del botón EDITAR --------------------
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tablaMedicos.getSelectedRow(); // Obtiene fila seleccionada
                if (fila >= 0) {
                    // Extrae datos básicos desde la tabla
                	int id = (int) modeloTabla.getValueAt(fila, 0);
                	String nombre = (String) modeloTabla.getValueAt(fila, 1);
                	String apellido = (String) modeloTabla.getValueAt(fila, 2);
                	String matricula = (String) modeloTabla.getValueAt(fila, 3);
                	String email = (String) modeloTabla.getValueAt(fila, 4);
                	String contrasenia = (String) modeloTabla.getValueAt(fila, 5);
                    /*// Solicita la contraseña del médico (simulada)
                    String contrasenia = JOptionPane.showInputDialog("Ingrese la contraseña del médico (obligatorio):");
                    if (contrasenia == null || contrasenia.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La contraseña es obligatoria.");
                        return;
                    }*/

                    // Pide nueva especialidad
                    String nuevaEspecialidad = JOptionPane.showInputDialog("Nueva especialidad:");
                    if (nuevaEspecialidad == null || nuevaEspecialidad.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La especialidad no puede quedar vacía.");
                        return;
                    }

                    // Obtener estado activo desde columna 7 (si contiene "Activo"/"De Baja")
                    int activo = modeloTabla.getValueAt(fila, 7).toString().equalsIgnoreCase("Activo") ? 1 : 0;
                    
                    // Crear nuevo objeto médico con los datos modificados
                    Medico medico = new Medico(id, nombre, apellido, matricula, email,contrasenia, nuevaEspecialidad,  activo);
                    //medico.setCantidadConsultas(nuevaCantidad);

                    // Llama al controlador para actualizar
                    if (ControllerMedico.EditarMedico(medico)) {
                        JOptionPane.showMessageDialog(null, "Médico actualizado correctamente");
                        cargarMedicos(); // Recarga la tabla
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar médico");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un médico de la tabla.");
                }
            }
        });

        // -------------------- Acción del botón ELIMINAR --------------------
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tablaMedicos.getSelectedRow();
                if (fila >= 0) {
                    int id = (int) modeloTabla.getValueAt(fila, 0);

                    // Confirmación de eliminación lógica
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea dar de baja al médico?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (ControllerMedico.eliminarMedico(id)) {
                            JOptionPane.showMessageDialog(null, "Médico dado de baja.");
                            cargarMedicos(); // Actualiza la tabla
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al eliminar médico.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un médico.");
                }
            }
        });

        // -------------------- Acción del botón REFRESCAR --------------------
        btnRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarMedicos(); // Recarga la lista de médicos
            }
        });

        setVisible(true); // Muestra la ventana
    }

    // -------------------- Método para cargar médicos a la tabla --------------------
    private void cargarMedicos() {
        modeloTabla.setRowCount(0); // Limpia la tabla

        LinkedList<Medico> medicos = ControllerMedico.mostrarMedicos();
        for (Medico m : medicos) {
            if (m.getActivo() == 1) { // Solo muestra médicos activos
                modeloTabla.addRow(new Object[]{
                    m.getId(), 
                    m.getNombre(), 
                    m.getApellido(),
                    m.getMatricula(), 
                    m.getEmail(), 
                    m.getContrasenia(), 
                    m.getEspecialidad(), 
                    m.getActivo() == 1 ? "Activo" : "De Baja"}
                    );
            }
        }
    }

}
