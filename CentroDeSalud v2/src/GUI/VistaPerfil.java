package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPerfil extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public VistaPerfil(String titulo, String nombre, String apellido, String email, String[] camposAdicionales) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        setTitle(titulo);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título principal con el mismo estilo que las otras pantallas
        JLabel lblTituloPrincipal = new JLabel("Centro De Salud");
        lblTituloPrincipal.setForeground(Color.GRAY);
        lblTituloPrincipal.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 30));
        lblTituloPrincipal.setBounds(0, 0, 460, 107);
        contentPane.add(lblTituloPrincipal);

        // Panel principal con información del perfil
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBounds(20, 120, 440, 240);
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panelPrincipal.setLayout(null);
        contentPane.add(panelPrincipal);

        // Título del perfil
        JLabel lblTitulo = new JLabel("INFORMACIÓN DEL PERFIL");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setForeground(Color.GRAY);
        lblTitulo.setBounds(0, 0, 410, 25);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lblTitulo);

        // Información básica
        int y = 35;
        
        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNombre.setBounds(20, 35, 100, 20);
        panelPrincipal.add(lblNombre);
        
        JLabel lblValorNombre = new JLabel(nombre);
        lblValorNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblValorNombre.setBounds(130, 35, 250, 20);
        panelPrincipal.add(lblValorNombre);
        
        y += 25;
        
        // Apellido
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblApellido.setBounds(20, 65, 100, 20);
        panelPrincipal.add(lblApellido);
        
        JLabel lblValorApellido = new JLabel(apellido);
        lblValorApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblValorApellido.setBounds(130, 65, 250, 20);
        panelPrincipal.add(lblValorApellido);
        
        y += 25;
        
        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEmail.setBounds(20, 95, 100, 20);
        panelPrincipal.add(lblEmail);
        
        JLabel lblValorEmail = new JLabel(email);
        lblValorEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblValorEmail.setBounds(130, 95, 250, 20);
        panelPrincipal.add(lblValorEmail);
        
        y += 25;
        
        // Campos adicionales
        if (camposAdicionales != null) {
            for (int i = 0; i < camposAdicionales.length; i += 2) {
                if (i + 1 < camposAdicionales.length) {
                    JLabel lblCampo = new JLabel(camposAdicionales[i] + ":");
                    lblCampo.setFont(new Font("Tahoma", Font.BOLD, 12));
                    lblCampo.setBounds(20, y, 100, 20);
                    panelPrincipal.add(lblCampo);
                    
                    JLabel lblValor = new JLabel(camposAdicionales[i + 1]);
                    lblValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
                    lblValor.setBounds(130, y, 250, 20);
                    panelPrincipal.add(lblValor);
                    
                    y += 25;
                }
            }
        }

        // Botón cerrar con el mismo estilo que los otros botones
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(165, 200, 100, 30);
        btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelPrincipal.add(btnCerrar);
    }
} 