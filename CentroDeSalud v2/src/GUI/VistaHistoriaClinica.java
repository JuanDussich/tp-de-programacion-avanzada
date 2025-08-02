package GUI;

import BLL.HistoriaClinica;
import DLL.ControllerHistoriaClinica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaHistoriaClinica extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea areaHistorial;

    public VistaHistoriaClinica() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        setTitle("Centro de Salud - Historias Clínicas");

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Listado de Historias Clínicas");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaHistorial);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(btnVolver, BorderLayout.SOUTH);

        cargarHistoriasClinicas();

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AgregarHistoriaClinica().setVisible(true);
                dispose();
            }
        });
    }

    private void cargarHistoriasClinicas() {
        ControllerHistoriaClinica controller = new ControllerHistoriaClinica();
        ArrayList<HistoriaClinica> lista = controller.obtenerHistoriasClinicas();

        StringBuilder sb = new StringBuilder();
        for (HistoriaClinica hc : lista) {
            sb.append("ID: ").append(hc.getIdHistorialMedico()).append("\n");
            sb.append("Observaciones: ").append(hc.getObservaciones()).append("\n");
            sb.append("Detalle Consulta: ").append(hc.getDetalleConsulta()).append("\n");
            sb.append("Fecha: ").append(hc.getFecha()).append("\n");
            sb.append("Paciente ID: ").append(hc.getPacienteId()).append("\n");
            sb.append("Turno ID: ").append(hc.getTurnoId()).append("\n");
            sb.append("Tratamiento ID: ").append(hc.getTratamientoId()).append("\n");
            sb.append("Medicamento ID: ").append(hc.getMedicamentoId()).append("\n");
            sb.append("Médico ID: ").append(hc.getMedicoId()).append("\n");
            sb.append("---------------------------------------------------\n");
        }

        areaHistorial.setText(sb.toString());
    }
}