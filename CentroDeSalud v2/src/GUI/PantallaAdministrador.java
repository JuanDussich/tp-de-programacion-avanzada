package GUI;

import BLL.Administrador;
import DLL.ControllerAdministrador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PantallaAdministrador extends JFrame {

    private static final long serialVersionUID = 1L;

    // Componentes GUI
    private JTable tablaAdministradores;
    private DefaultTableModel modeloTabla;
    private JButton btnEditar, btnEliminar, btnRefrescar;

    public PantallaAdministrador() {
        setTitle("Gestión de Administradores");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Columnas: ID, Nombre, Apellido, Email, Contraseña (encriptada), Estado
        modeloTabla = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Apellido", "Email", "Contraseña", "Estado"}, 0
        );
        tablaAdministradores = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaAdministradores);

        JPanel panelBotones = new JPanel();
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnRefrescar = new JButton("Refrescar");
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRefrescar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarAdministradores();

        // Acción botón EDITAR
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tablaAdministradores.getSelectedRow();
                if (fila >= 0) {
                    int id = (int) modeloTabla.getValueAt(fila, 0);
                    String nombre = (String) modeloTabla.getValueAt(fila, 1);
                    String apellido = (String) modeloTabla.getValueAt(fila, 2);
                    String email = (String) modeloTabla.getValueAt(fila, 3);
                    String contrasenia = (String) modeloTabla.getValueAt(fila, 4);
                    // Puedes solicitar contraseña nueva o mantener la actual
                    String nuevaContrasenia = JOptionPane.showInputDialog("Nueva contraseña (dejar vacío para mantener actual):");
                    if (nuevaContrasenia == null) {
                        return; // Cancelar edición
                    }
                    if (nuevaContrasenia.trim().isEmpty()) {
                        nuevaContrasenia = contrasenia; // Mantener contraseña actual
                    }

                    // Estado: podemos agregar un checkbox o pedir texto "Activo" / "Inactivo"
                    String estadoStr = JOptionPane.showInputDialog("Estado (Activo / Inactivo):");
                    if (estadoStr == null || (!estadoStr.equalsIgnoreCase("Activo") && !estadoStr.equalsIgnoreCase("Inactivo"))) {
                        JOptionPane.showMessageDialog(null, "Estado inválido. Use 'Activo' o 'Inactivo'.");
                        return;
                    }
                    int estado = estadoStr.equalsIgnoreCase("Activo") ? 1 : 0;

                    Administrador admin = new Administrador(id, nombre, apellido, email, nuevaContrasenia);
                    // Si tienes un campo estado en Administrador, deberías setearlo aquí (no aparece en tu código DLL, así que se asume que no existe)
                    // Si no está en el modelo, podrías manejar sólo el activo para mostrar.

                    // Llamar a método de edición (necesitas implementarlo en ControllerAdministrador)
                    boolean exito = ControllerAdministrador.editarAdministrador(admin);
                    if (exito) {
                        JOptionPane.showMessageDialog(null, "Administrador actualizado correctamente");
                        cargarAdministradores();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar administrador");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un administrador.");
                }
            }
        });

        // Acción botón ELIMINAR
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tablaAdministradores.getSelectedRow();
                if (fila >= 0) {
                    int id = (int) modeloTabla.getValueAt(fila, 0);
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar al administrador?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean exito = ControllerAdministrador.eliminarAdministrador(id);
                        if (exito) {
                            JOptionPane.showMessageDialog(null, "Administrador eliminado.");
                            cargarAdministradores();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al eliminar administrador.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un administrador.");
                }
            }
        });

        // Acción botón REFRESCAR
        btnRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarAdministradores();
            }
        });

        setVisible(true);
    }

    private void cargarAdministradores() {
        modeloTabla.setRowCount(0);
        LinkedList<Administrador> admins = ControllerAdministrador.mostrarAdministrador();
        for (Administrador admin : admins) {
            modeloTabla.addRow(new Object[] {
                admin.getId(), admin.getNombre(), admin.getApellido(), admin.getEmail(), admin.getContrasenia(), "Activo"
            });
        }
    }
}
