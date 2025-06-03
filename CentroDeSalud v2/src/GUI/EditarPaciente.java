package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BLL.Paciente;
import java.awt.Font;

public class EditarPaciente extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpNombre;
	private JTextField inpMail;
	private JTextField inpContraseña;
	
	public EditarPaciente(Paciente usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("nombre");
		lblNewLabel.setBounds(116, 108, 96, 20);
		contentPane.add(lblNewLabel);
		
		inpNombre = new JTextField();
		inpNombre.setText(usuario.getNombre());
		inpNombre.setBounds(116, 139, 184, 20);
		contentPane.add(inpNombre);
		inpNombre.setColumns(10);
		
		inpMail = new JTextField();
		inpMail.setText(usuario.getEmail());
		inpMail.setColumns(10);
		inpMail.setBounds(116, 206, 184, 20);
		contentPane.add(inpMail);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(116, 181, 96, 14);
		contentPane.add(lblMail);
	    inpContraseña = new JTextField();
		inpContraseña.setText(usuario.getContrasenia());
		inpContraseña.setColumns(10);
		inpContraseña.setBounds(116, 279, 184, 20);
		contentPane.add(inpContraseña);
		JButton btnNewButton = new JButton("Editar");

		JLabel lblMensaje = new JLabel("");
		lblMensaje.setBounds(55, 341, 277, 14);
		contentPane.add(lblMensaje);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Paciente editar = usuario;
				editar.setNombre(inpNombre.getText());
				editar.setEmail(inpMail.getText());
				editar.setContrasenia(inpContraseña.getText());
				String mensaje = Paciente.EditarPaciente(editar);
				lblMensaje.setText(mensaje);
				
			}
		});
		btnNewButton.setBounds(65, 352, 113, 51);
		contentPane.add(btnNewButton);
		
		JLabel lblContrasea = new JLabel("contraseña");
		lblContrasea.setBounds(116, 254, 96, 14);
		contentPane.add(lblContrasea);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaPacientes vista = new VistaPacientes();
				vista.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(258, 352, 119, 50);
		contentPane.add(btnNewButton_1);
		
		JLabel lblCentroDeSalud = new JLabel("Centro De Salud Editar Paciente");
		lblCentroDeSalud.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblCentroDeSalud.setBounds(65, 11, 312, 53);
		contentPane.add(lblCentroDeSalud);
		
		
		
	}
}
