package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import BLL.Medico;

public class ControllerMedico {

    private static Connection con = Conexion.getInstance().getConnection();

    /**
     * LOGIN: Permite iniciar sesión sólo si el médico está activo.
     * Busca en la tabla 'medicos' por email, contraseña y que esté activo.
     * Retorna un objeto Medico si encuentra coincidencia, sino null.
     */
    public static Medico login(String email, String contrasenia) {
        String sql = "SELECT * FROM medico WHERE email = ? AND contrasenia = ? AND activo = TRUE";
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
                    rs.getString("matricula"),
                    rs.getString("email"),
                    rs.getString("contrasenia"),
                    rs.getString("especialidad"),
                    rs.getInt("activo")
                );
                
                return medico;
            }
        } catch (SQLException e) {
            System.err.println("Error al iniciar sesión: " + e.getMessage());
        }
        return null;
    }

    /**
     * AGREGAR un nuevo médico a la base de datos.
     * Inserta los datos recibidos en el objeto Medico en la tabla 'medicos'.
     * El campo 'activo' se setea en TRUE por defecto al crear.
     */
    public static void agregarMedico(Medico medico) {
        String sql = "INSERT INTO medico (nombre, apellido, matricula, email, contrasenia, especialidad, activo) VALUES ( ?, ?, ?, ?, ?, ?, TRUE)";
        try {
        	PreparedStatement stmt = con.prepareStatement(sql);
        	stmt.setString(1, medico.getNombre());
        	stmt.setString(2, medico.getApellido());
        	stmt.setString(3, medico.getMatricula());  // WHERE matricula = ?
        	stmt.setString(4, medico.getEmail());
        	stmt.setString(5, medico.getContrasenia());
        	stmt.setString(6, medico.getEspecialidad());

                       
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

    /**
     * MOSTRAR todos los médicos activos.
     * Recupera todos los registros con activo = TRUE y los devuelve en una lista.
     */
    public static LinkedList<Medico> mostrarMedicos() {
        LinkedList<Medico> lista = new LinkedList<>();
        String sql = "SELECT * FROM medico WHERE activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico(
                    rs.getInt("idMedico"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("matricula"),
                    rs.getString("email"),
                    rs.getString("contrasenia"),
                    rs.getString("especialidad"),
                    rs.getInt("activo")
                );
                
                lista.add(medico);
            }

        } catch (SQLException e) {
            System.err.println("Error al mostrar médicos: " + e.getMessage());
        }

        return lista;
    }

    /**
     * EDITAR datos de un médico identificado por su matrícula.
     * Actualiza el nombre, apellido, email, contraseña, especialidad 
     * para el médico con esa matrícula y que esté activo.
     * Retorna true si se actualizó correctamente, false si no.
     */
    public static boolean EditarMedico(Medico medico) {
        String sql = "UPDATE medico SET nombre = ?, apellido = ?, matricula = ?, email = ?, contrasenia = ?, especialidad = ? "
                   + "WHERE matricula = ? AND activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, medico.getNombre());
            stmt.setString(2, medico.getApellido());
            stmt.setString(3, medico.getMatricula()); // nuevo valor
            stmt.setString(4, medico.getEmail());
            stmt.setString(5, medico.getContrasenia());
            stmt.setString(6, medico.getEspecialidad());
            stmt.setString(7, medico.getMatricula()); // valor para el WHERE

            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al editar médico: " + e.getMessage());
            return false;
        }
    }


    /**
     * REGISTRAR un médico nuevo.
     * Verifica que no exista otro médico con el mismo email antes de agregar.
     * Muestra mensaje si ya existe email duplicado.
     */
    public static void RegistrarMedico(Medico nuevo) {
        LinkedList<Medico> existentes = mostrarMedicos(); // Método corregido
        boolean flag = true;
        for (Medico existente : existentes) { // Tipo corregido en el foreach
            if (existente.getEmail().equals(nuevo.getEmail())) {
                flag = false;
                break;
            }
        }
        if (flag) {
            agregarMedico(nuevo);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario ya creado con ese email");
        }
    }

    /**
     * ELIMINACIÓN lógica de un médico.
     * Cambia el campo 'activo' a FALSE para marcarlo como inactivo, sin borrar físicamente.
     * Retorna true si la operación fue exitosa.
     */
    public static boolean eliminarMedico(int id) { 
        String sql = "UPDATE medico SET activo = FALSE WHERE idMedico = ?"; // Campo corregido
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar médico: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * BUSCAR un médico activo por matrícula.
     * Devuelve el objeto Medico si lo encuentra, o null si no.
     */
    public static Medico buscarPorMatricula(String matricula) {
        String sql = "SELECT * FROM medico WHERE matricula = ? AND activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, matricula);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Medico medico = new Medico(
                    rs.getInt("idMedico"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("matricula"),
                    rs.getString("email"),
                    rs.getString("contrasenia"),
                    rs.getString("especialidad"),
                    rs.getInt("activo")
                );
                // medico.setCantidadConsultas(rs.getInt("cantidadConsultas")); // Si usas este campo
                return medico;
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar médico: " + e.getMessage());
        }

        return null;
    }
}
