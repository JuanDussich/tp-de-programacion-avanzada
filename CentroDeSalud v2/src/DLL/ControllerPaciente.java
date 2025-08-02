package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import BLL.Paciente;
import BLL.Usuario;

public class ControllerPaciente {
	
	//ATRIBUTOS
	private static Connection con = Conexion.getInstance().getConnection();
	
	
	//METODOS
	
	//ESTE ES EL METODO DE LOGIN DEL PACIENTE
	public static Paciente login(String email, String contrasenia) {
	    Paciente paciente = null; // ⚠️ DECLARAR acá (esto previene el error)

	    try {
	        PreparedStatement stmt = con.prepareStatement(
	            "SELECT * FROM paciente WHERE email = ? AND contrasenia = ?"
	        );

	        Paciente temp = new Paciente(); // Para encriptar
	        stmt.setString(1, email);
	        stmt.setString(2, temp.encriptar(contrasenia));

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            int id = rs.getInt("idPaciente");
	            String nombre = rs.getString("nombre");
	            String apellido = rs.getString("apellido");
	            int dni = rs.getInt("dni");
	            String fechaNacimiento = rs.getString("fecha_De_nacimiento");
	            int activo = rs.getInt("activo");
	            String obraSocial = rs.getString("obraSocial"); // 👈 nuevo

	            paciente = new Paciente(id, nombre, apellido, dni, fechaNacimiento, email, contrasenia, activo, obraSocial);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return paciente;
	}

	//ESTE ES EL METODO DE AGREGAR PACIENTE
    public static void agregarPaciente(Paciente usuario) {
    	String sql = "INSERT INTO `paciente`( `nombre`, `apellido`, `dni`, `fecha_De_Nacimiento`, `email`, `contrasenia`, `activo`, `obraSocial`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    	          
        try {
        	PreparedStatement statement = con.prepareStatement(sql);
        	statement.setString(1, usuario.getNombre());
        	statement.setString(2, usuario.getApellido());
        	statement.setInt(3, usuario.getDni());
        	statement.setString(4, usuario.getFechaNacimiento());
        	statement.setString(5, usuario.getEmail());
        	statement.setString(6, usuario.encriptar(usuario.getContrasenia()));
        	statement.setInt(7, 1); // Activo = 1 (true)
        	statement.setString(8, usuario.getObraSocial());



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

            while (rs.next()) {
                int id = rs.getInt("idPaciente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int dni = rs.getInt("dni");
                String fechaNacimiento = rs.getString("fecha_De_nacimiento");
                String email = rs.getString("email");
                String contrasenia = rs.getString("contrasenia");
                int activo = rs.getInt("activo");
                String obraSocial = rs.getString("obraSocial"); 

                Paciente paciente = new Paciente(id, nombre, apellido, dni, fechaNacimiento, email, contrasenia, activo, obraSocial);
                lista.add(paciente);
            }

        } catch (SQLException e) {
            System.err.println("Error al mostrar Pacientes: " + e.getMessage());
        }

        return lista;
    }

	
	
	//ESTA ES LA PARTE DE EDITAR PACIENTE control
  //ESTA ES LA PARTE DE EDITAR PACIENTE control
    public static boolean EditarPaciente(Paciente paciente) {
        String sql = "UPDATE `paciente` SET `nombre`=?, `apellido`=?, `dni`=?, `fecha_De_nacimiento`=?, `email`=?, `contrasenia`=?, `obraSocial`=? "
                   + "WHERE dni= ? AND activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getApellido());
            stmt.setInt(3, paciente.getDni());
            stmt.setString(4, paciente.getFechaNacimiento());
            stmt.setString(5, paciente.getEmail());
            stmt.setString(6, paciente.getContrasenia());
            stmt.setString(7, paciente.getObraSocial()); 
            stmt.setInt(8, paciente.getDni()); // condicional WHERE

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
        } else {
            JOptionPane.showMessageDialog(null, "Existe un Paciente creado con ese mail");
        }
    }

    
    //METODO DE REGISTRAR PACIENTE PERO EL PACIENTE SE CREA DENTRO DEL METODO
    public static void RegistrarPaciente() {
        String nombre = JOptionPane.showInputDialog("Ingresa tu nombre");
        String apellido = JOptionPane.showInputDialog("Ingresa tu apellido");
        int dni = Integer.parseInt(JOptionPane.showInputDialog("Ingresa tu DNI"));
        String fechaNacimiento = JOptionPane.showInputDialog("Ingresa tu fecha de nacimiento");
        String email = JOptionPane.showInputDialog("Ingresa tu email");
        String contrasenia = JOptionPane.showInputDialog("Ingresa tu contraseña");
        String obraSocial = JOptionPane.showInputDialog("Ingresa tu obra social"); // ✅ nuevo campo

        Paciente nuevo = new Paciente(0, nombre, apellido, dni, fechaNacimiento, email, contrasenia, 1, obraSocial);

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
        } else {
            JOptionPane.showMessageDialog(null, "Existe un Usuario creado con ese mail");
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

    
    // para validar que el dn sea unico
    public static boolean existeDNI(int dni) {
        String sql = "SELECT COUNT(*) FROM paciente WHERE dni = ? AND activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, dni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
