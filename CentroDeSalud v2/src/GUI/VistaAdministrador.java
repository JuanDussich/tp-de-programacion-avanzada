package GUI;

import BLL.Administrador;
import DLL.ControllerAdministrador;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class VistaAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;

	// Componentes GUI
	//private JTable tablaAdministradores;
	//private JButton btnEditar, btnEliminar, btnRefrescar;
	private JPanel contentPane;
	private JTable table;
    private DefaultTableModel model;
    private Administrador usuarioSeleccionado;
    private JTextField inpFiltro;


	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				VistaAdministrador frame = new VistaAdministrador();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public VistaAdministrador() {

		//vista administrador

		/*
		 * setTitle("Gestión de Administradores"); setSize(800, 400);
		 * setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * setLayout(new BorderLayout());
		 */

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// titulo de la ventana admin
		JLabel lblTitulo = new JLabel("Centro De Salud - Administradores");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTitulo.setBounds(10, 10, 800, 15);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitulo);

		// Imagen de admin al costado
		ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/logo_admin.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
		ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

		JLabel lblImagen = new JLabel(iconoEscalado);
		lblImagen.setBounds(670, 10, 100, 100); // esquina superior derecha
		contentPane.add(lblImagen);
		
		
		 // seleccion
        JLabel lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setBounds(10, 70, 760, 15);
        contentPane.add(lblSeleccionado);

        // Definir columnas según atributos de admin (id, nombre, apellido,etc)
        model = new DefaultTableModel(new String[]{ //int id, String nombre, String apellido, String mail, String contrasenia
				"ID Administrador", "Nombre", "Apellido", "Email", "Contraseña", "Estado"
        }, 0);

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 760, 200);
        contentPane.add(scrollPane);

        // Botones
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregar.setBounds(112, 329, 124, 55);
        contentPane.add(btnAgregar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEditar.setBounds(322, 329, 124, 55);
        contentPane.add(btnEditar);
        

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminar.setBounds(531, 329, 124, 55);
        contentPane.add(btnEliminar);

     // Al seleccionar fila, actualizar la variable usuarioSeleccionado y mostrar datos en el label
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    // Crear un objeto adm con datos de la fila seleccionada
                    usuarioSeleccionado = new Administrador(
                        (int) model.getValueAt(row, 0),               // id admin (int)
                        (String) model.getValueAt(row, 1),            // nombre (String)
                        (String) model.getValueAt(row, 2),            // apellido (String)
                        (String) model.getValueAt(row, 3),            // email (String)
                        (String) model.getValueAt(row, 4),            // contrasenia (String)
                        (int) model.getValueAt(row, 5) //ESTADO
                    );

                    // Mostrar datos seleccionados en el label
                    lblSeleccionado.setText("Seleccionado: ID=" + usuarioSeleccionado.getId()
                        + ", Nombre=" + usuarioSeleccionado.getNombre()
                        + ", Apellido=" + usuarioSeleccionado.getApellido()
                   
                    );
                }
            }
        });
        
        
        /* filtros en la vista de admin */
        
        
        inpFiltro = new JTextField(); //112, 329, 124, 55
        inpFiltro.setBounds(112, 430, 124, 30);
        contentPane.add(inpFiltro);
        inpFiltro.setColumns(10);
        inpFiltro.setVisible(true);
        JButton btnFiltroGeneral = new JButton("Filtrar nombre");
        btnFiltroGeneral.setVisible(true);
        btnFiltroGeneral.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		cargarTablaFiltradaStream(inpFiltro.getText());
        	}
        });
        btnFiltroGeneral.setBounds(322, 430, 124, 30);
        contentPane.add(btnFiltroGeneral);
        
        JLabel lblNewLabel = new JLabel("Filtrar por nombre:");
        lblNewLabel.setBounds(112, 400, 124, 30);
        contentPane.add(lblNewLabel);
        
        JButton reinicio = new JButton("Reiniciar filtro");
        reinicio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		inpFiltro.setText("");
        		 cargarTabla();
        		
        	}
        });
        reinicio.setBounds(322, 470, 124, 30);
        contentPane.add(reinicio);
        
        /* FIN filtros en la vista de admin */
        
        // Cargar datos de la BD en la tabla al iniciar la ventana
        cargarTabla();
        
        // Acción botón Agregar: Mostrar diálogo para ingresar datos y luego registrar nuevo adm
        btnAgregar.addActionListener(e -> {
            JTextField nombreField = new JTextField();
            JTextField apellidoField = new JTextField();
            JTextField emailField = new JTextField();
            JPasswordField contraseniaField = new JPasswordField();
            
            Object[] fields = {
                "Nombre:", nombreField,
                "Apellido:", apellidoField,
                "Email:", emailField,
                "Contraseña:", contraseniaField,
            };

            int option = JOptionPane.showConfirmDialog(null, fields, "Agregar Administrador", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                Administrador nuevo = new Administrador(
                    0, // id (se genera en la base)
                    nombreField.getText(),
                    apellidoField.getText(),
                    emailField.getText(),
                    new String(contraseniaField.getPassword())
                );
                ControllerAdministrador.RegistrarAdministrador(nuevo);
                cargarTabla();
            }
        });
        
     // Acción botón Editar: abrir ventana de edición si hay admin seleccionado
        btnEditar.addActionListener(e -> {
            if (usuarioSeleccionado != null) {
                EditarAdministrador editar = new EditarAdministrador(usuarioSeleccionado);
                editar.setVisible(true);
                dispose(); // cerrar ventana actual
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario.");
            }
        });
        
        
     // Acción botón Eliminar: cambiar activo a falso para el adm seleccionado
        btnEliminar.addActionListener(e -> {
            if (usuarioSeleccionado != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este Administrador?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean eliminado = ControllerAdministrador.EliminarAdministrador(usuarioSeleccionado.getId());
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Administrador eliminado correctamente.");
                        cargarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar Administrador.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario para eliminar.");
            }
        });
	}// cierre vista
        
    	/**
         * Método para cargar los médicos desde la base de datos y actualizar la tabla.
         */
        private void cargarTabla() {
            model.setRowCount(0); // limpiar tabla
            LinkedList<Administrador> administrador = ControllerAdministrador.mostrarAdministrador(); // corregido: plural
            for (Administrador a : administrador) {
                model.addRow(new Object[]{
                    a.getId(),
                    a.getNombre(),
                    a.getApellido(),
                    a.getEmail(),
                    a.getContrasenia(),
                    a.getActivo()
                });
            }
        }

        
     // tabla para mostrat filtro admin
        private void cargarTablaFiltradaStream(String filtro) {
        	
        	LinkedList<Administrador> filtradasPorLetra = ControllerAdministrador.mostrarAdministrador().stream()
        			.filter(administrador -> administrador.getEmail() != null && administrador.getEmail().startsWith(filtro))
        			.collect(Collectors.toCollection(LinkedList::new));

        	
            model.setRowCount(0);
           
            for (Administrador u : filtradasPorLetra) {
        
                model.addRow(new Object[]{
                		u.getId(),
                        u.getNombre(),
                        u.getApellido(),
                        u.getEmail(),
                        u.getContrasenia(),
                        u.getActivo()
                });
        		
            }
        }
        
        
}// cierre
        
       
