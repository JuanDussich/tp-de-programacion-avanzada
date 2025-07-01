package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BLL.HistoriaClinica;
import DLL.ControllerHistoriaClinica;

public class EditarHistoriaClinica extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inpObservaciones;

    public EditarHistoriaClinica(HistoriaClinica hc) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Editar Observaciones");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 330, 30);
        contentPane.add(lblTitulo);

        JLabel lblObservaciones = new JLabel("Observaciones:");
        lblObservaciones.setBounds(50, 80, 100, 25);
        contentPane.add(lblObservaciones);

        inpObservaciones = new JTextField(hc.getObservaciones());
        inpObservaciones.setBounds(160, 80, 200, 25);
        contentPane.add(inpObservaciones);

        JLabel lblMensaje = new JLabel("");
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setBounds(50, 120, 310, 25);
        contentPane.add(lblMensaje);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(80, 170, 120, 40);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(230, 170, 120, 40);
        contentPane.add(btnVolver);

        // Acción guardar cambios
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nuevasObservaciones = inpObservaciones.getText();
                ControllerHistoriaClinica controller = new ControllerHistoriaClinica();
                controller.actualizarHistoriaClinica(hc.getIdHistorialMedico(), nuevasObservaciones);
                lblMensaje.setText("Observaciones actualizadas.");
            }
        });

        // Acción volver a la vista principal
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VistaHistoriaClinica vista = new VistaHistoriaClinica();
                vista.setVisible(true);
                dispose();
            }
        });
    }
   }
