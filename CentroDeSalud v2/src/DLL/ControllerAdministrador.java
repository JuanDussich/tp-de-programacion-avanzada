package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Administrador;
import BLL.Usuario;
import repository.*;

public class ControllerAdministrador {
	
		//ATRIBUTOS
		private static Connection con = Conexion.getInstance().getConnection();
		
		
		//METODOS
		
		//METODO LOGIN DEL ADMINISTRADOR
		public static Administrador login(String email, String contrasenia) {
	        Administrador administrador = new Administrador();
	        //JOptionPane.showMessageDialog(null, administrador);
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

		//METODO AGREGAR ADMINISTRADOR (ok)
	    public static void agregarAdministrador(Administrador usuario) {
	    	 String sql = "INSERT INTO `administrador`( `nombre`, `apellido`, `email`, `contrasenia`) VALUES (?,?,?,?)";
	        try {
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, usuario.getNombre());
	            stmt.setString(2, usuario.getApellido());
	            stmt.setString(3, usuario.getEmail());
	            stmt.setString(4, usuario.encriptar(usuario.getContrasenia()));
	            //statement.setString(6, usuario.getContrasenia());

	            int filas = stmt.executeUpdate();
	            if (filas > 0) {
	                System.out.println("Administrador agregado correctamente.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    //METODO DE REGISTRAR ADMINISTRADOR CREANDOLO EN EL METODO
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
	            JOptionPane.showMessageDialog(null, "Administrador ya creado");
	        }


	    }
	    
	    //ESTE ES EL METODO DE REGISTRAR ADMINISTRADOR PERO SE ENVIA EL ADMINISTRADOR DESDE FUERA DEL METODO
	    public static void RegistrarAdministrador(Administrador nuevo) {
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
	            JOptionPane.showMessageDialog(null, "Administrador ya creado");
	        }


	    }
	    
	  //ESTA ES LA PARTE DE EDITAR ADMINISTRADOR (ok)
		public static boolean EditarAdministrador(Administrador administrador) {
			String sql = "UPDATE administrador SET nombre = ?, apellido = ?, email = ?, contrasenia = ?"
	                   + "WHERE idAdministrador = ? ";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, administrador.getNombre());
				stmt.setString(2, administrador.getApellido());
				stmt.setString(3, administrador.getEmail());
				stmt.setString(4,administrador.encriptar(administrador.getContrasenia()));
				stmt.setInt(5,administrador.getId());

				int filas = stmt.executeUpdate();
	            return filas > 0;
	        } catch (SQLException e) {
	            System.err.println("Error al editar Administrador: " + e.getMessage());
	            return false;
	        }
	    }
		
		//ESTA ES LA PARTE DE MOSTRAR ADMINISTRADOR ver!!
	    public static LinkedList<Administrador> mostrarAdministrador() {
	        LinkedList<Administrador> lista = new LinkedList<>();
	        String sql = "SELECT * FROM administrador";
	        try {
	            PreparedStatement stmt = con.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	            	Administrador administrador = new Administrador(
	                rs.getInt("idAdministrador"),
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                rs.getString("email"),
	                rs.getString("contrasenia"),
	                rs.getInt("activo")
	            	);
	                
	                lista.add(administrador);
	               
	            }
	        }catch (SQLException e) {
	            System.err.println("Error al mostrar médicos: " + e.getMessage());
	        }
	        return lista;
	    }

	 // Método para eliminar un administrador (logicamente)
	    public static boolean EliminarAdministrador(int id) {
	        String sql = "UPDATE administrador SET activo = FALSE WHERE idAdministrador = ?"; // Campo corregido
	        try {
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setInt(1, id);
	            int filas = stmt.executeUpdate();
	            return filas > 0;
	        } catch (SQLException e) {
	            System.err.println("Error al eliminar Administrador: " + e.getMessage());
	            return false;
	        }
	    }
}

