package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BLL.Paciente;
import BLL.Usuario;
import DLL.ControllerMedico;
import DLL.ControllerPaciente;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class VistaPacientes extends JFrame {

	    private JPanel contentPane;
	    private JTable table;
	    private DefaultTableModel model;
	    private Paciente usuarioSeleccionado;

	    public static void main(String[] args) {
	        EventQueue.invokeLater(() -> {
	            try {
	                VistaPacientes frame = new VistaPacientes();
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }

	    public VistaPacientes() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 800, 500);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	        
	        
	        // titulo de la ventana paciente
	        JLabel lblTitulo = new JLabel("Centro De Salud - Pacientes Activos");
	        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
	        lblTitulo.setBounds(10, 10, 700 , 15);
	        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	        contentPane.add(lblTitulo);

	        JLabel lblSeleccionado = new JLabel("Seleccionado:");
	        lblSeleccionado.setBounds(10, 40, 760, 20);
	        contentPane.add(lblSeleccionado);

	        // Definir columnas según atributos de Paciente 
	        model = new DefaultTableModel(new String[]{
	        		"Id Paciente", "Nombre", "Apellido", "DNI", "Fecha de Nacimiento", "Email", "Contrase","Estado"}, 0);
	        
	        table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(10, 70, 760, 200);
	        contentPane.add(scrollPane);
	        
	        JButton btnAgregar = new JButton("Agregar");
	        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnAgregar.setBounds(112, 299, 124, 55);
	        contentPane.add(btnAgregar);
	        
	        JButton btnEditar = new JButton("Editar");
	        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnEditar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		
	        	}
	        });
	        btnEditar.setBounds(322, 299, 124, 55);
	        contentPane.add(btnEditar);
	        
	        JButton btnEliminar = new JButton("Eliminar");
	        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnEliminar.setBounds(531, 299, 124, 55);
	        contentPane.add(btnEliminar);


	       
	      
	        // Acción al seleccionar fila
	        table.getSelectionModel().addListSelectionListener(e -> {
	            if (!e.getValueIsAdjusting()) {
	                int row = table.getSelectedRow();
	                if (row != -1) {
	                	// Crear un objeto Paciente con datos de la fila seleccionada
	                    usuarioSeleccionado = new Paciente(
	                        (int) model.getValueAt(row, 0),      // id Paciente
	                        (String) model.getValueAt(row, 1),   // nombre
	                        (String) model.getValueAt(row, 2),   // apellido
	                        (int) model.getValueAt(row, 3),      // dni
	                        (String) model.getValueAt(row, 4),   // fecha nac
	                        (String) model.getValueAt(row, 5),   // email
	                        (String) model.getValueAt(row, 6),   // pass
	                        (int) model.getValueAt(row, 7)       // campo activo
	                        );
	                    
	                    // Paciente(int id, String nombre,String apellido,int dni,String fechaNacimiento,String email,String contrasenia, int activo)
	                    
	                    lblSeleccionado.setText("Seleccionado: ID=" + usuarioSeleccionado.getId() 
	                    	+ ", Nombre=" + usuarioSeleccionado.getNombre()
	                    	+ ", Apellido=" + usuarioSeleccionado.getApellido()
	                    	+ ", Dni=" + usuarioSeleccionado.getDni()
	                    	+ ", Fecha de nacimiento=" + usuarioSeleccionado.getFechaNacimiento()
	                    	+ ", Email=" + usuarioSeleccionado.getEmail()
	                    	+ ", Constraseña=" + usuarioSeleccionado.getContrasenia()
	                    	+ ", Estado=" + usuarioSeleccionado.getActivo()
	                    	);  
	                }
	                
	                //JOptionPane.showMessageDialog(null, usuarioSeleccionado);
	                
	                
	            }
	        });

	        // Cargar datos
	        cargarTabla();
	        
	     // Acción: Agregar usuario
	        btnAgregar.addActionListener(e -> {
	            JTextField nombreField = new JTextField();
	            JTextField apellidoField = new JTextField();
	            JTextField dniField = new JTextField ();
	            JTextField fecha_De_NacimientoField = new JTextField();
	            JTextField emailField = new JTextField();
	            JPasswordField contraseniaField = new JPasswordField();
	            // JTextField ActivoField = new JTextField ();
	
	            Object[] fields = {
	                "Nombre:", nombreField,
	                "Apellido:", apellidoField,
	                "Dni:", dniField,
	                "Fecha nacimiento (AAAA-MM-DD):", fecha_De_NacimientoField,
	                "Email", emailField,
	                "Contraseña:", contraseniaField,
	                //"Activo:", ActivoField
	            };
	
	            int option = JOptionPane.showConfirmDialog(null, fields, "Agregar Paciente", JOptionPane.OK_CANCEL_OPTION);
	            //FIJATE DE ARREGLAR ACA PORQUE EN EL METODO DE REGISTRO YA TE PIDE LOS DATOS, FIJATE DE VER CUAL SE QUEDA Y CUAL SE VA
	            if (option == JOptionPane.OK_OPTION) {
	                Paciente nuevo = new Paciente(
	                		nombreField.getText(),
	                        apellidoField.getText(),
	                        Integer.parseInt(dniField.getText()),
	                        fecha_De_NacimientoField.getText(),
	                        emailField.getText(),
	                        new String(contraseniaField.getPassword())
	                        //Integer.parseInt(ActivoField.getText())
	                );
	
	                ControllerPaciente.RegistrarPaciente(nuevo);
	                //ControllerPaciente.RegistrarPaciente();
	                cargarTabla();
	            }
	        });
	        
	     // Acción: Editar usuario
	        //FIJATE ACA DE VER DONDE O COMO FUNCIONA EL EDITAR PORQUE NO LO ESTAS PUDIENDO IMPLEMENTAR ASI LO ARREGLAS Y PODES TAMBIEN TENER
	        // EL BOTON DE EDITAR 
	        btnEditar.addActionListener(e -> {
	            if (usuarioSeleccionado != null) {
	            	EditarPaciente editar = new EditarPaciente(usuarioSeleccionado);
	               editar.setVisible(true);
	               dispose();
	            	
	            } else {
	                JOptionPane.showMessageDialog(null, "Seleccione un usuario.");
	            }
	        });
	        
        // Acción botón Eliminar: cambiar activo a falso para el médico seleccionado
        btnEliminar.addActionListener(e -> {
            if (usuarioSeleccionado != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este Paciente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean eliminado = ControllerPaciente.eliminarPaciente(usuarioSeleccionado.getId());
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente.");
                        cargarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar Paciente.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un Paciente para eliminar.");
            }
        });
	    }

	    
	    private void cargarTabla() {
	        model.setRowCount(0);
	        LinkedList<Paciente> paciente = DLL.ControllerPaciente.mostrarPacientes();
	        for (Paciente u : paciente) {
	            model.addRow(new Object[]{
	            		u.getId(),
	            		u.getNombre(),
	            		u.getApellido(),
	            		u.getDni(),
	            		u.getFechaNacimiento(),
	            		u.getEmail(),
	            		u.getContrasenia(),
	            		u.getActivo()
	            		});
	        }
	    }
}
