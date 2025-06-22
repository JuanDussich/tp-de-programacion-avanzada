package GUI;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import BLL.Paciente;
import DLL.ControllerPaciente;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class VistaPacientes extends JFrame {

	    private JPanel contentPane;
	    private JTable table;
	    private DefaultTableModel model;
	    private Paciente usuarioSeleccionado;
	    private JTextField inpFiltro;

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
	        setBounds(100, 100, 800, 700);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	        
	        
	        // titulo de la ventana paciente
	        JLabel lblTitulo = new JLabel("Centro De Salud - Pacientes Activos");
	        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
	        lblTitulo.setBounds(10, 10, 800 , 15);
	        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	        contentPane.add(lblTitulo);
	        
	        
	     // Imagen de paciente al costado
	        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/logo_pacientes.png"));
	        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
	        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

	        JLabel lblImagen = new JLabel(iconoEscalado);
	        lblImagen.setBounds(670, 10, 100, 100); // esquina superior derecha
	        contentPane.add(lblImagen);
	        

	        JLabel lblSeleccionado = new JLabel("Seleccionado:");
	        lblSeleccionado.setBounds(10, 70, 760, 20);
	        contentPane.add(lblSeleccionado);

	        // Definir columnas según atributos de Paciente 
	        model = new DefaultTableModel(new String[]{
	        		"Id Paciente", "Nombre", "Apellido", "DNI", "Fecha de Nacimiento", "Email", "Contrase","Estado"}, 0);
	        
	        table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(10, 100, 760, 200);
	        contentPane.add(scrollPane);
	        
	        JButton btnAgregar = new JButton("Agregar");
	        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnAgregar.setBounds(112, 329, 124, 55);
	        contentPane.add(btnAgregar);
	        
	        JButton btnEditar = new JButton("Editar");
	        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnEditar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		
	        	}
	        });
	        btnEditar.setBounds(322, 329, 124, 55);
	        contentPane.add(btnEditar);
	        
	        JButton btnEliminar = new JButton("Eliminar");
	        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnEliminar.setBounds(531, 329, 124, 55);
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
	                    	);  
	                }
	                
	                //JOptionPane.showMessageDialog(null, usuarioSeleccionado);
	                
	                
	            }
	        });
	        
	        
	        
	        /* filtros en la vista de Pacientes */
	        
	        
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
	        
	        /* FIN filtros en la vista de Pacientes */

	        // Cargar datos
	        cargarTabla();
	        
	     // Acción: Agregar usuario
	        btnAgregar.addActionListener(e -> {
	            AgregarPaciente agregar = new AgregarPaciente();
	            agregar.setVisible(true);
	            dispose(); // cerrar vista actual si querés
	        });

	        
	     // Acción: Editar usuario
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
	    
	    // tabla para mostrar filtro paciente
	    private void cargarTablaFiltradaStream(String filtro) {
	    	
	    	LinkedList<Paciente> filtradasPorLetra = ControllerPaciente.mostrarPacientes().stream()
	    			.filter(paciente -> paciente.getEmail() != null && paciente.getEmail().startsWith(filtro))
	    			.collect(Collectors.toCollection(LinkedList::new));

	    	
	        model.setRowCount(0);
	       
	        for (Paciente u : filtradasPorLetra) {
	    
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
