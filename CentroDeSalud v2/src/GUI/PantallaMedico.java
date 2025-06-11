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
    
    public PantallaMedico() {
   
    }

}
