package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Paciente;
import BLL.Usuario;

public class ControllerPaciente {
	
	//ATRIBUTOS
	private static Connection con = Conexion.getInstance().getConnection();
	
	
	//METODOS
	
	public static Paciente login(String email, String contrasenia) {
        Paciente paciente = new Paciente();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM paciente WHERE email = ? AND contrasenia = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2,paciente.encriptar(contrasenia));
            //stmt.setString(2, contrasenia);
            

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idPaciente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int dni = rs.getInt("dni");
                String fechaNacimiento = rs.getString("fecha_De_nacimiento");

                paciente =  new Paciente(id,nombre,apellido,dni, fechaNacimiento, email,contrasenia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paciente;
    }
	
	
	public static String EditarPaciente(Paciente usuario) {
		try {
			PreparedStatement statement = con
					.prepareStatement(
							"UPDATE `paciente` SET `nombre`=?,`apellido`=?,`dni`=?,`fecha_De_nacimiento`=?,`email`=?,`contrasenia`=? WHERE id= ?");
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getApellido());
			statement.setInt(3, usuario.getDni());
			statement.setString(4, usuario.getFechaNacimiento());
			statement.setString(5, usuario.getEmail());
			statement.setString(6,usuario.encriptar(usuario.getContrasenia()));
			statement.setInt(7,usuario.getId());

			int filas = statement.executeUpdate();
			if (filas > 0) {
				return "Usuario editado correctamente.";
			}
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			return "Error mail existente";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";
	}
	

    public static void agregarPaciente(Paciente usuario) {
        try {
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO `paciente`( `nombre`, `apellido`, `dni`, `fecha_De_Nacimiento`, `email`, `contrasenia`) VALUES (?,?,?,?,?,?)"
            );
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setInt(3,usuario.getDni());
            statement.setString(4, usuario.getFechaNacimiento());
            statement.setString(5, usuario.getEmail());
            statement.setString(6, usuario.encriptar(usuario.getContrasenia()));
            //statement.setString(6, usuario.getContrasenia());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario agregado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void RegistrarPaciente() {

        String nombre = JOptionPane.showInputDialog("ingresa tu nombre");
        String apellido = JOptionPane.showInputDialog("ingresa tu apellido");
        int  dni = Integer.parseInt(JOptionPane.showInputDialog("ingresa tu dni"));
        String fechaNacimiento = JOptionPane.showInputDialog("ingresa la fecha");

        String email = JOptionPane.showInputDialog("ingresa tu email");
        String contrasenia = JOptionPane.showInputDialog("ingresa tu contrasenia");
        Paciente nuevo = new Paciente(0,nombre,apellido,dni,fechaNacimiento,email,contrasenia);

        LinkedList<Paciente> existentes = mostrarPaciente();
        boolean flag = true;
        for (Usuario existente : existentes) {
            if (existente.getEmail().equals(nuevo.getEmail())) {
                flag = false;
                break;
            }
        }
        if (flag) {
            agregarPaciente(nuevo);
        }else {
            JOptionPane.showMessageDialog(null, "Usuario ya creado");
        }


    }
    
    public static LinkedList<Paciente> mostrarPaciente() {
        LinkedList<Paciente> usuarios = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM paciente");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idPaciente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int dni = rs.getInt("dni");
                String fechaNacimiento = rs.getString("fecha_De_nacimiento");
                String email = rs.getString("email");
                String contrasenia = rs.getString("contrasenia");

                usuarios.add(new Paciente(id, nombre,apellido,dni ,fechaNacimiento, email,contrasenia));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

}
