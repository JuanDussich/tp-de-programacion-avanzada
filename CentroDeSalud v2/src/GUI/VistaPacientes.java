package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BLL.Paciente;
import BLL.Usuario;
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

	        JLabel lblSeleccionado = new JLabel("Seleccionado:");
	        lblSeleccionado.setBounds(10, 10, 760, 20);
	        contentPane.add(lblSeleccionado);

	        model = new DefaultTableModel(new String[]{
	        		"idPaciente", "nombre", "apellido", "dni", "fecha_De_Nacimiento", "email", "contrasenia"}, 0);
	        table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(10, 40, 760, 200);
	        contentPane.add(scrollPane);
	        
	        JButton btnAgregar = new JButton("Agregar");
	        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnAgregar.setBounds(112, 269, 124, 55);
	        contentPane.add(btnAgregar);
	        
	        JButton btnEditar = new JButton("Editar");
	        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnEditar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		
	        	}
	        });
	        btnEditar.setBounds(322, 269, 124, 55);
	        contentPane.add(btnEditar);
	        
	        JButton btnEliminar = new JButton("Eliminar");
	        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnEliminar.setBounds(531, 269, 124, 55);
	        contentPane.add(btnEliminar);


	       
	      
	        // Acción al seleccionar fila
	        table.getSelectionModel().addListSelectionListener(e -> {
	            if (!e.getValueIsAdjusting()) {
	                int row = table.getSelectedRow();
	                if (row != -1) {
	                    usuarioSeleccionado = new Paciente(
	                        (int) model.getValueAt(row, 0),
	                        (String) model.getValueAt(row, 1),
	                        (String) model.getValueAt(row, 2),
	                        (int) model.getValueAt(row, 3),
	                        (String) model.getValueAt(row, 4),
	                        (String) model.getValueAt(row, 5),
	                        (String) model.getValueAt(row, 6)
	                        
	                        

	                    );
	                    lblSeleccionado.setText("Seleccionado: ID=" + usuarioSeleccionado.getId() 
	                    	+ ", Nombre=" + usuarioSeleccionado.getNombre()
	                    	+ ", Apellido=" + usuarioSeleccionado.getApellido()
	                    	+ ", Dni=" + usuarioSeleccionado.getDni()
	                    	+ ", Fecha de nacimiento=" + usuarioSeleccionado.getFechaNacimiento()
	                    	+ ", Email=" + usuarioSeleccionado.getEmail());
	                     
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
	
	            Object[] fields = {
	                "Nombre:", nombreField,
	                "Apellido:", apellidoField,
	                "Dni:", dniField,
	                "Fecha de nacimiento:", fecha_De_NacimientoField,
	                "Email", emailField,
	                "Contraseña:", contraseniaField
	            };
	
	            int option = JOptionPane.showConfirmDialog(null, fields, "Agregar Usuario", JOptionPane.OK_CANCEL_OPTION);
	            //FIJATE DE ARREGLAR ACA PORQUE EN EL METODO DE REGISTRO YA TE PIDE LOS DATOS, FIJATE DE VER CUAL SE QUEDA Y CUAL SE VA
	            if (option == JOptionPane.OK_OPTION) {
	                Paciente nuevo = new Paciente(0,
	                        nombreField.getText(),
	                        apellidoField.getText(),
	                        Integer.parseInt(dniField.getText()),
	                        fecha_De_NacimientoField.getText(),
	                        emailField.getText(),
	                        new String(contraseniaField.getPassword())
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
	        
	    }

	    private void cargarTabla() {
	        model.setRowCount(0);
	        LinkedList<Paciente> usuarios = DLL.ControllerPaciente.mostrarPaciente();
	        for (Paciente u : usuarios) {
	            model.addRow(new Object[]{
	            		u.getId(), u.getNombre(), u.getApellido(), u.getDni(),u.getFechaNacimiento(),u.getEmail(),u.getContrasenia()
	            		});
	        }
	    }
}
