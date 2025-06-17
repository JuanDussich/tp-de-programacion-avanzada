package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Administrador;
import BLL.Paciente;
import BLL.Usuario;
import repository.*;

public class ControllerAdministrador {
	
		//ATRIBUTOS
		private static Connection con = Conexion.getInstance().getConnection();
		
		
		//METODOS
		
		//METODO LOGIN DEL ADMINISTRADOR
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

		//METODO AGREGAR ADMINISTRADOR
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
	    
	  //ESTA ES LA PARTE DE EDITAR ADMINISTRADOR
		public static String EditarAdministrador(Administrador Admin) {
			try {
				PreparedStatement statement = con
						.prepareStatement(
								"UPDATE `administrador` SET `nombre`=?,`apellido`=?,`email`=?,`contrasenia`=? WHERE idAdministrador= ?");
				statement.setString(1, Admin.getNombre());
				statement.setString(2, Admin.getApellido());
				statement.setString(5, Admin.getEmail());
				statement.setString(6,Admin.encriptar(Admin.getContrasenia()));
				statement.setInt(7,Admin.getId());

				int filas = statement.executeUpdate();
				if (filas > 0) {
					return "Administrador editado correctamente.";
				}
				
			}catch (MySQLIntegrityConstraintViolationException e) {
				return "Error mail existente";
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return "Error";
		}
		
		//ESTA ES LA PARTE DE MOSTRAR ADMINISTRADOR
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

	 // Método para eliminar un administrador (físicamente)
	    public static boolean EliminarAdministrador(int id) {
	        try {
	            PreparedStatement stmt = con.prepareStatement(
	                "DELETE FROM administrador WHERE idAdministrador = ?"
	            );
	            stmt.setInt(1, id);

	            int filas = stmt.executeUpdate();
	            return filas > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}

