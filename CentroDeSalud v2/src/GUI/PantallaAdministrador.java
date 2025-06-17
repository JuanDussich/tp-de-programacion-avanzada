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
    	
    }
}
