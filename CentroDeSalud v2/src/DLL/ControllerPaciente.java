package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Medico;
import BLL.Paciente;
import BLL.Usuario;

public class ControllerPaciente {
	
	//ATRIBUTOS
	private static Connection con = Conexion.getInstance().getConnection();
	
	
	//METODOS
	
	//ESTE ES EL METODO DE LOGIN DEL PACIENTE
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
                int activo = rs.getInt("activo");

                paciente =  new Paciente(id,nombre,apellido,dni, fechaNacimiento, email, contrasenia, activo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paciente;
    }
	
	//ESTE ES EL METODO DE AGREGAR PACIENTE
    public static void agregarPaciente(Paciente usuario) {
    	String sql = "INSERT INTO `paciente`( `nombre`, `apellido`, `dni`, `fecha_De_Nacimiento`, `email`, `contrasenia`, `activo`) VALUES (?, ?, ?, ?, ?, ?, ?)";

    	          
        try {
        	PreparedStatement statement = con.prepareStatement(sql);
        	statement.setString(1, usuario.getNombre());
        	statement.setString(2, usuario.getApellido());
        	statement.setInt(3, usuario.getDni());
        	statement.setString(4, usuario.getFechaNacimiento());
        	statement.setString(5, usuario.getEmail());
        	statement.setString(6, usuario.encriptar(usuario.getContrasenia()));
        	statement.setInt(7, 1); // Activo = 1 (true)


            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario agregado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //METODO PARA MOSTRAR PACIENTES
    public static LinkedList<Paciente> mostrarPacientes() {
        LinkedList<Paciente> lista = new LinkedList<>();
        String sql = "SELECT * FROM paciente WHERE activo = TRUE";
        try {
        	PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Paciente paciente = new Paciente(
                rs.getInt("idPaciente"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getInt("dni"),
                rs.getString("fecha_De_nacimiento"),
                rs.getString("email"),
                rs.getString("contrasenia"),
                rs.getInt("activo")
            );
            
            lista.add(paciente);
        }

    } catch (SQLException e) {
        System.err.println("Error al mostrar Pacientes: " + e.getMessage());
    }

    return lista;
}
	
	
	//ESTA ES LA PARTE DE EDITAR PACIENTE control
	public static boolean EditarPaciente(Paciente paciente) {
        String sql = "UPDATE `paciente` SET `nombre`=?,`apellido`=?,`dni`=?,`fecha_De_nacimiento`=?,`email`=?,`contrasenia`=? "
				+ "WHERE dni= ? AND activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getApellido());
            stmt.setInt(3, paciente.getDni()); // 
            stmt.setString(4, paciente.getFechaNacimiento());
            stmt.setString(5, paciente.getEmail());
            stmt.setString(6, paciente.getContrasenia());
            stmt.setInt(7, paciente.getDni()); // lo uso para el condicional del where
            
            
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al editar Paciente: " + e.getMessage());
            return false;
        }
    }

    //ESTE ES EL METODO DE REGISTRAR PACIENTE 
    public static void RegistrarPaciente(Paciente nuevo) {
        LinkedList<Paciente> existentes = mostrarPacientes();
        boolean flag = true;
        for (Paciente existente : existentes) {
            if (existente.getEmail().equals(nuevo.getEmail())) {
                flag = false;
                break;
            }
        }
        if (flag) {
            agregarPaciente(nuevo);
        }else {
            JOptionPane.showMessageDialog(null, "Paciente ya creado");
        }

    }
    
    //METODO DE REGISTRAR PACIENTE PERO EL PACIENTE SE CREA DENTRO DEL METODO
    public static void RegistrarPaciente() {

        String nombre = JOptionPane.showInputDialog("ingresa tu nombre");
        String apellido = JOptionPane.showInputDialog("ingresa tu apellido");
        int  dni = Integer.parseInt(JOptionPane.showInputDialog("ingresa tu dni"));
        String fechaNacimiento = JOptionPane.showInputDialog("ingresa la fecha");

        String email = JOptionPane.showInputDialog("ingresa tu email");
        String contrasenia = JOptionPane.showInputDialog("ingresa tu contrasenia");
        Paciente nuevo = new Paciente(0,nombre,apellido,dni,fechaNacimiento,email,contrasenia, 1);

        LinkedList<Paciente> existentes = mostrarPacientes();
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
    
    

    //ESTE ES EL METODO PARA ELIMINAR PACIENTES
    /**
     * ELIMINACIÓN lógica de un paciente.
     * Cambia el campo 'activo' a FALSE para marcarlo como inactivo, sin borrar físicamente.
     * Retorna true si la operación fue exitosa.
     */
    public static boolean eliminarPaciente(int id) { 
        String sql = "UPDATE paciente SET activo = FALSE WHERE idPaciente = ?"; // 
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar Paciente: " + e.getMessage());
            return false;
        }
    }


}
