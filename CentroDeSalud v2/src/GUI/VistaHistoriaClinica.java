package GUI;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Image;
import java.time.LocalDate;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;
import java.util.ArrayList;


import BLL.HistoriaClinica;
import DLL.ControllerHistoriaClinica;

public class VistaHistoriaClinica extends JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComponent contentPane;	
	private DefaultTableModel model;
	private JTable table;
    private HistoriaClinica historiaSeleccionada;  // HC seleccionada en la tabla
    private JTextField inpFiltro;
	
	public static void main(String[] args) {
	        EventQueue.invokeLater(() -> {
	            try {
	                VistaHistoriaClinica frame = new VistaHistoriaClinica();
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }

		

	public VistaHistoriaClinica() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 800, 700);

	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	        
	        // titulo de la ventana HClinica
	        JLabel lblTitulo = new JLabel("Centro De Salud - Historia Clinica");
	        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
	        lblTitulo.setBounds(10, 10, 800 , 15);
	        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	        contentPane.add(lblTitulo);
	        
        
	     // Imagen de Historia clinica  
	        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/historia_clinica.png"));
	        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

	        JLabel lblImagen = new JLabel(iconoEscalado);
	        lblImagen.setBounds(670, 10, 100, 100); // esquina superior derecha
	        contentPane.add(lblImagen);
	        // opcion seleccion
	        JLabel lblSeleccionado = new JLabel("Seleccionado:");
	        lblSeleccionado.setBounds(10, 70, 760, 15);
	        contentPane.add(lblSeleccionado);

	        // Definir columnas según atributos de Historia cliniaca (int idHistorialMedico, String observaciones, LocalDate fecha, int turnoId,
            //int pacienteId, int tratamientoId, int medicamentoId, int medicoId)
	        
	        model = new DefaultTableModel(new String[]{
					"ID Historial Medico", "Observaciones", "Fecha", "ID Turno" , "ID Paciente", "ID Tratamiento", "ID Medicamento", "ID Medico", "Detalle consulta "
	        }, 0);
		
	        table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(10, 100, 760, 300);
	        contentPane.add(scrollPane);
	        
	     // Botones
	        JButton btnAgregar = new JButton("Agregar");
	        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnAgregar.setBounds(80, 420, 124, 40);
	        contentPane.add(btnAgregar);

	        JButton btnEditar = new JButton("Editar");
	        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnEditar.setBounds(320, 420, 124, 40);
	        contentPane.add(btnEditar);
	        
	        JButton btnEliminar = new JButton("Eliminar");
	        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        btnEliminar.setBounds(560, 420, 124, 40);
	        contentPane.add(btnEliminar);
	        
	        
	        // Al seleccionar fila, actualizar la variable historiaSeleccionada y mostrar datos en el label
	        table.getSelectionModel().addListSelectionListener(e -> {
	            if (!e.getValueIsAdjusting()) {
	                int row = table.getSelectedRow();
	                if (row != -1) {
	                	
	                    // Crear un objeto HC con datos de la fila seleccionada
	                	//(int idHistorialMedico, String observaciones, LocalDate fecha, int turnoId,
	                    //int pacienteId, int tratamientoId, int medicamentoId, int medicoId)
	                    historiaSeleccionada = new HistoriaClinica(
	                        (int) model.getValueAt(row, 0),               // id hc (int)
	                        (String) model.getValueAt(row, 1),            // obs (String)
	                        (LocalDate) model.getValueAt(row, 2),            // fecha (LocalDate)
	                        (int) model.getValueAt(row, 3),            // id turno (int)
	                        (int) model.getValueAt(row, 4),            // id paciente (int)
	                        (int) model.getValueAt(row, 5),            // id tratamiento (int)
	                        (int) model.getValueAt(row, 6),            	 // id medicamento (int)
	                        (int) model.getValueAt(row, 7),                // id medico (int)
	                        (String) model.getValueAt(row, 8)
	                    );

	                    // Mostrar datos seleccionados en el label
	                    lblSeleccionado.setText("Seleccionado: ID=" + historiaSeleccionada.getIdHistorialMedico()
	                        + ", Fecha=" + historiaSeleccionada.getFecha()
	                        + ", ID Paciente=" + historiaSeleccionada.getPacienteId()
	                        + ", ID Medico=" + historiaSeleccionada.getMedicoId()
						
	                    );
	                }
	            }
	        });
		
	        
	        /* filtros en la vista de HC */
	        
	        
	        inpFiltro = new JTextField(); //112, 329, 124, 55
	        inpFiltro.setBounds(80, 511, 142, 30);
	        contentPane.add(inpFiltro);
	        inpFiltro.setColumns(10);
	        inpFiltro.setVisible(true);
	        JButton btnFiltroGeneral = new JButton("Filtrar");
	        btnFiltroGeneral.setVisible(true);
	        btnFiltroGeneral.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		cargarTablaFiltradaStream(inpFiltro.getText());
	        	}
	        });
	        btnFiltroGeneral.setBounds(320, 491, 124, 30);
	        contentPane.add(btnFiltroGeneral);
	        
	        JLabel lblNewLabel = new JLabel("Filtrar por id Historia Clinica?:");
	        lblNewLabel.setBounds(80, 481, 142, 30);
	        contentPane.add(lblNewLabel);
	        
	        JButton reinicio = new JButton("Reiniciar filtro");
	        reinicio.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		inpFiltro.setText("");
	        		 cargarTabla();
	        		
	        	}
	        });
	        reinicio.setBounds(320, 531, 124, 30);
	        contentPane.add(reinicio);
	        
	        /* FIN filtros en la vista de HC */
	        
	     // Cargar datos de la BD en la tabla al iniciar la ventana
	        cargarTabla();
	        
	        
	     // Acción botón Agregar: Mostrar diálogo para ingresar datos y luego registrar nueva HC
	        btnAgregar.addActionListener(e -> {
	            AgregarHistoriaClinica agregar = new AgregarHistoriaClinica(); // abre nueva ventana crear la vista!!
	            agregar.setVisible(true);
	            dispose(); // opcional: cerrar la ventana actual
	        });


	        // Acción botón Editar: abrir ventana de edición si hay HC seleccionado
	        btnEditar.addActionListener(e -> {
	            if (historiaSeleccionada != null) {
	                EditarHistoriaClinica editar = new EditarHistoriaClinica(historiaSeleccionada);
	                editar.setVisible(true);
	                dispose(); // cerrar ventana actual
	            } else {
	                JOptionPane.showMessageDialog(null, "Seleccione una Historia Clinica.");
	            }
	        });
	        
	     // Acción botón Eliminar: ELIMINA HC
	        btnEliminar.addActionListener(e -> {
	            if (historiaSeleccionada != null) {
	                int confirm = JOptionPane.showConfirmDialog(
	                    null,
	                    "¿Está seguro que desea eliminar esta Historia Clínica?",
	                    "Confirmar eliminación",
	                    JOptionPane.YES_NO_OPTION
	                );

	                if (confirm == JOptionPane.YES_OPTION) {
	                    try {
	                    	// creamos una instancia del controlador antes de usar el metodo
	                        ControllerHistoriaClinica controller = new ControllerHistoriaClinica();
	                        controller.eliminarHistoriaClinica(historiaSeleccionada.getIdHistorialMedico());
	                        JOptionPane.showMessageDialog(null, "Historia Clínica eliminada correctamente.");
	                        cargarTabla(); // Actualiza la tabla después de la eliminación
	                    } catch (Exception ex) {
	                        JOptionPane.showMessageDialog(null, "Error al eliminar Historia Clínica.");
	                        ex.printStackTrace();
	                    }
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Seleccione una Historia Clínica para eliminar.");
	            }
	        });

	    
	} // fin metodo VistaHistoriaClinica()
	
	// Método para cargar Las hc desde la base de datos y actualizar la tabla.
	private void cargarTabla() {
	    model.setRowCount(0); // limpiar tabla

	    if (PantallaMedico.logueado == null) {
	        JOptionPane.showMessageDialog(null, "No hay médico logueado.");
	        return;
	    }

	    ArrayList<HistoriaClinica> historiaClinica = ControllerHistoriaClinica.obtenerHistoriasClinicas().stream()
	        .filter(hc -> hc.getMedicoId() == PantallaMedico.logueado.getId())
	        .collect(Collectors.toCollection(ArrayList::new));

	    for (HistoriaClinica hc : historiaClinica) {
	        model.addRow(new Object[]{
	            hc.getIdHistorialMedico(),
	            hc.getObservaciones(),
	            hc.getFecha(),
	            hc.getTurnoId(),
	            hc.getPacienteId(),
	            hc.getTratamientoId(),
	            hc.getMedicamentoId(),
	            hc.getMedicoId(),
	            hc.getDetalleConsulta()
	        });
	    }
	}


    
   // tabla para mostrat filtro HC
	private void cargarTablaFiltradaStream(String filtro) {
	    model.setRowCount(0); // limpiar tabla

	    if (PantallaMedico.logueado == null) {
	        JOptionPane.showMessageDialog(null, "No hay médico logueado.");
	        return;
	    }

	    ArrayList<HistoriaClinica> filtradas = ControllerHistoriaClinica.obtenerHistoriasClinicas().stream()
	        .filter(hc -> hc.getMedicoId() == PantallaMedico.logueado.getId())
	        .filter(hc -> String.valueOf(hc.getIdHistorialMedico()).startsWith(filtro))
	        .collect(Collectors.toCollection(ArrayList::new));

	    for (HistoriaClinica hc : filtradas) {
	        model.addRow(new Object[]{
	            hc.getIdHistorialMedico(),
	            hc.getObservaciones(),
	            hc.getFecha(),
	            hc.getTurnoId(),
	            hc.getPacienteId(),
	            hc.getTratamientoId(),
	            hc.getMedicamentoId(),
	            hc.getMedicoId(),
	            hc.getDetalleConsulta()
	        });
	    }
	}

} // fin clase
