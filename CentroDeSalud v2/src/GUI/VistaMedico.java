package GUI;

import javax.swing.*;


import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Medico;
import BLL.Especialidad;
import BLL.Usuario;
import DLL.ControllerMedico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class VistaMedico extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7945647544248119807L;
	private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Medico usuarioSeleccionado;  // Médico seleccionado en la tabla
    private JTextField inpFiltro;

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
        setBounds(100, 100, 800, 700);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // titulo de la ventana medico
        JLabel lblTitulo = new JLabel("Centro De Salud - Medicos Activos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblTitulo.setBounds(10, 10, 800 , 15);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);
        
        
		// Imagenes
		/*  prueba 1
		 * JLabel lblNewLabel = new JLabel(""); //lblNewLabel.setIcon(new
		 * ImageIcon("src/img/medico2.jpg")); lblNewLabel.setIcon(new
		 * ImageIcon(getClass().getResource("/img/medico2.png")));
		 * lblNewLabel.setBounds(393, 144, 260, 213); contentPane.add(lblNewLabel);
		 */
        
        
     // Imagen de medico al costado
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/logo_medico.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

        JLabel lblImagen = new JLabel(iconoEscalado);
        lblImagen.setBounds(670, 10, 100, 100); // esquina superior derecha
        contentPane.add(lblImagen);


        
        // seleccion
        JLabel lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setBounds(10, 70, 760, 15);
        contentPane.add(lblSeleccionado);

        // Definir columnas según atributos de Medico (id, nombre, apellido, matricula, email, contrasenia, especialidad, activo)
        model = new DefaultTableModel(new String[]{
				"ID Medico", "Nombre", "Apellido", "Matricula" , "Email", "Contraseña", "Especialidad", "Estado"
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
                	Especialidad especialidadEnum = (Especialidad) model.getValueAt(row, 6);
                	
                    // Crear un objeto Medico con datos de la fila seleccionada
                    usuarioSeleccionado = new Medico(
                        (int) model.getValueAt(row, 0),               // idMedico (int)
                        (String) model.getValueAt(row, 1),            // nombre (String)
                        (String) model.getValueAt(row, 2),            // apellido (String)
                        (String) model.getValueAt(row, 3),            // matricula (String)
                        (String) model.getValueAt(row, 4),            // email (String)
                        (String) model.getValueAt(row, 5),            // contrasenia (String)
                        especialidadEnum,            				  // especialidad (String)
                        (int) model.getValueAt(row, 7)                // activo (int)
                    );

                    // Mostrar datos seleccionados en el label
                    lblSeleccionado.setText("Seleccionado: ID=" + usuarioSeleccionado.getId()
                        + ", Nombre=" + usuarioSeleccionado.getNombre()
                        + ", Apellido=" + usuarioSeleccionado.getApellido()
                        + ", Matrícula=" + usuarioSeleccionado.getMatricula()
					/*
					 * + ", Email=" + usuarioSeleccionado.getEmail() + ", Especialidad=" +
					 * usuarioSeleccionado.getEspecialidad() + ", Activo=" +
					 * usuarioSeleccionado.getActivo()
					 */
                    );
                }
            }
        });
        

		/* filtros en la vista de medico */
        
        
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
        
        /* FIN filtros en la vista de medico */
        
        
        
        
        // Cargar datos de la BD en la tabla al iniciar la ventana
        cargarTabla();

        // Acción botón Agregar: Mostrar diálogo para ingresar datos y luego registrar nuevo médico
		/*
		 * btnAgregar.addActionListener(e -> { JTextField nombreField = new
		 * JTextField(); JTextField apellidoField = new JTextField(); JTextField
		 * matriculaField = new JTextField(); JTextField emailField = new JTextField();
		 * JPasswordField contraseniaField = new JPasswordField(); JTextField
		 * especialidadField = new JTextField();
		 * 
		 * Object[] fields = { "Nombre:", nombreField, "Apellido:", apellidoField,
		 * "Matrícula:", matriculaField, "Email:", emailField, "Contraseña:",
		 * contraseniaField, "Especialidad:", especialidadField };
		 * 
		 * int option = JOptionPane.showConfirmDialog(null, fields, "Agregar Médico",
		 * JOptionPane.OK_CANCEL_OPTION); if (option == JOptionPane.OK_OPTION) { Medico
		 * nuevo = new Medico( 0, // id (se genera en la base) nombreField.getText(),
		 * apellidoField.getText(), matriculaField.getText(), emailField.getText(), new
		 * String(contraseniaField.getPassword()), especialidadField.getText(), 1 //
		 * activo por defecto = 1 (true) ); ControllerMedico.RegistrarMedico(nuevo);
		 * cargarTabla(); } });
		 */
        
        btnAgregar.addActionListener(e -> {
            AgregarMedico agregar = new AgregarMedico(); // abre nueva ventana
            agregar.setVisible(true);
            dispose(); // opcional: cerrar la ventana actual
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
    
    
    // tabla para mostrat filtro medico
    private void cargarTablaFiltradaStream(String filtro) {
    	
    	LinkedList<Medico> filtradasPorLetra = ControllerMedico.mostrarMedicos().stream()
    			.filter(medico -> medico.getEmail() != null && medico.getEmail().startsWith(filtro))
    			.collect(Collectors.toCollection(LinkedList::new));

    	
        model.setRowCount(0);
       
        for (Medico u : filtradasPorLetra) {
    
            model.addRow(new Object[]{
            		u.getId(),
                    u.getNombre(),
                    u.getApellido(),
                    u.getMatricula(),
                    u.getEmail(),
                    u.getContrasenia(),
                    u.getEspecialidad(),
                    u.getActivo()
            });
    		
        }
    }

}
