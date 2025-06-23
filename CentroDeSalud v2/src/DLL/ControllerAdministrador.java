package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import BLL.Administrador;
import BLL.Usuario;
import repository.*;

public class ControllerAdministrador {
	
		//ATRIBUTOS
		private static Connection con = Conexion.getInstance().getConnection();
		
		
		//METODOS
		
		public static Administrador login(String email, String contrasenia) {
	        Administrador administrador = new Administrador();
	        JOptionPane.showMessageDialog(null, administrador);
	        try {
	            PreparedStatement stmt = con.prepareStatement(
	                    "SELECT * FROM administrador WHERE email = ? AND contrasenia = ?"
	            );
	            stmt.setString(1, email);
	            stmt.setString(2,administrador.encriptar(contrasenia));
	            //stmt.setString(2, contrasenia);
	            

	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                int id = rs.getInt("idAdministrador");
	                String nombre = rs.getString("nombre");
	                String apellido = rs.getString("apellido");

	                administrador =  new Administrador(id,nombre,apellido, email,contrasenia);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return administrador;
	    }

	    public static void agregarAdministrador(Administrador usuario) {
	        try {
	            PreparedStatement statement = con.prepareStatement(
	            		// ** correccion administrador -- correccion pasaba 5 parametros**
	                    "INSERT INTO `administrador`( `nombre`, `apellido`, `email`, `contrasenia`) VALUES (?,?,?,?)"
	            );
	            statement.setString(1, usuario.getNombre());
	            statement.setString(2, usuario.getApellido());
	            statement.setString(3, usuario.getEmail());
	            statement.setString(4, usuario.encriptar(usuario.getContrasenia()));
	            //statement.setString(6, usuario.getContrasenia());

	            int filas = statement.executeUpdate();
	            if (filas > 0) {
	                System.out.println("Administrador agregado correctamente.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static void RegistrarAdministrador() {

	        String nombre = JOptionPane.showInputDialog("ingresa tu nombre");
	        String apellido = JOptionPane.showInputDialog("ingresa tu apellido");

	        String email = JOptionPane.showInputDialog("ingresa tu email");
	        String contrasenia = JOptionPane.showInputDialog("ingresa tu contrasenia");
	        
	        Administrador nuevo = new Administrador(0,nombre,apellido,email,contrasenia);

	        LinkedList<Administrador> existentes = mostrarAdministrador();
	        boolean flag = true;
	        for (Usuario existente : existentes) {
	            if (existente.getEmail().equals(nuevo.getEmail())) {
	                flag = false;
	                break;
	            }
	        }
	        if (flag) {
	            agregarAdministrador(nuevo);
	        }else {
	            JOptionPane.showMessageDialog(null, "Usuario ya creado");
	        }


	    }
	    public static LinkedList<Administrador> mostrarAdministrador() {
	        LinkedList<Administrador> usuarios = new LinkedList<>();
	        try {
	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM administrador");
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("idAdministrador");
	                String nombre = rs.getString("nombre");
	                String apellido = rs.getString("apellido");
	                String email = rs.getString("email");
	                String contrasenia = rs.getString("contrasenia");

	                usuarios.add(new Administrador(id, nombre,apellido, email,contrasenia));

	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return usuarios;
	    }

}

