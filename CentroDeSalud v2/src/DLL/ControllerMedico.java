package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import BLL.Medico;

public class ControllerMedico {

    private static Connection con = Conexion.getInstance().getConnection();

    // LOGIN: sólo permite iniciar sesión si el médico está activo
    public static Medico login(String email, String contrasenia) {
        String sql = "SELECT * FROM medicos WHERE email = ? AND contrasenia = ? AND activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, contrasenia);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	Medico medico = new Medico(
            		    rs.getInt("idMedico"),
            		    rs.getString("nombre"),
            		    rs.getString("apellido"),
            		    rs.getString("email"),
            		    rs.getString("contrasenia"),
            		    rs.getString("especialidad"),
            		    rs.getString("matricula"),
            		    rs.getInt("activo")
            		);
                //medico.setCantidadConsultas(rs.getInt("cantidadConsultas"));
                return medico;
            }
        } catch (SQLException e) {
            System.err.println("Error al iniciar sesión: " + e.getMessage());
        }
        return null;
    }

    // AGREGAR médico nuevo
    public static void agregarMedico(Medico medico) {
        String sql = "INSERT INTO medicos (nombre, apellido, email, contrasenia, matricula, especialidad, cantidadConsultas, activo) VALUES (?, ?, ?, ?, ?, ?, ?, TRUE)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, medico.getNombre());
            stmt.setString(2, medico.getApellido());
            stmt.setString(3, medico.getEmail());
            stmt.setString(4, medico.getContrasenia());
            stmt.setString(5, medico.getMatricula());
            stmt.setString(6, medico.getEspecialidad());
            //stmt.setInt(7, medico.getCantidadConsultas());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Médico agregado correctamente.");
            } else {
                System.out.println("No se pudo agregar el médico.");
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar médico: " + e.getMessage());
        }
    }

    // MOSTRAR médicos activos
    public static LinkedList<Medico> mostrarMedicos() {
        LinkedList<Medico> lista = new LinkedList<>();
        String sql = "SELECT * FROM medicos WHERE activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	Medico medico = new Medico(
            		    rs.getInt("idMedico"),
            		    rs.getString("nombre"),
            		    rs.getString("apellido"),
            		    rs.getString("email"),
            		    rs.getString("contrasenia"),
            		    rs.getString("especialidad"),
            		    rs.getString("matricula"),
            		    rs.getInt("activo")
            		);
               // medico.setCantidadConsultas(rs.getInt("cantidadConsultas"));
                lista.add(medico);
            }

        } catch (SQLException e) {
            System.err.println("Error al mostrar médicos: " + e.getMessage());
        }

        return lista;
    }

    // EDITAR datos del médico identificado por matrícula
    public static boolean editarMedico(Medico medico) {
        String sql = "UPDATE medicos SET nombre = ?, apellido = ?, email = ?, contrasenia = ?, especialidad = ?, cantidadConsultas = ? WHERE matricula = ? AND activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, medico.getNombre());
            stmt.setString(2, medico.getApellido());
            stmt.setString(3, medico.getEmail());
            stmt.setString(4, medico.getContrasenia());
            stmt.setString(5, medico.getEspecialidad());
            //stmt.setInt(6, medico.getCantidadConsultas());
            stmt.setString(7, medico.getMatricula());

            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al editar médico: " + e.getMessage());
            return false;
        }
    }

    // ELIMINACIÓN lógica: marcar como inactivo
    public static boolean eliminarMedico(int id) { 
        String sql = "UPDATE medicos SET activo = FALSE WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);  // <-- corregido aquí
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar médico: " + e.getMessage());
            return false;
        }
    }
    
    // BUSCAR médico activo por matrícula
    public static Medico buscarPorMatricula(String matricula) {
        String sql = "SELECT * FROM medicos WHERE matricula = ? AND activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, matricula);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	Medico medico = new Medico(
            		    rs.getInt("idMedico"),
            		    rs.getString("nombre"),
            		    rs.getString("apellido"),
            		    rs.getString("email"),
            		    rs.getString("contrasenia"),
            		    rs.getString("especialidad"),
            		    rs.getString("matricula"),
            		    rs.getInt("activo")
            		);
            	//medico.setCantidadConsultas(rs.getInt("cantidadConsultas"));
                return medico;
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar médico: " + e.getMessage());
        }

        return null;
    }
}
