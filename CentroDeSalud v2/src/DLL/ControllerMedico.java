package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import BLL.Medico;
import BLL.Especialidad;
import BLL.Usuario;

public class ControllerMedico {

    private static Connection con = Conexion.getInstance().getConnection();

    public static Medico buscarPorId(int idMedico) {
        String sql = "SELECT * FROM medico WHERE idMedico = ? AND activo = TRUE";
        try {
            PreparedStatement stmt = Conexion.getInstance().getConnection().prepareStatement(sql);
            stmt.setInt(1, idMedico);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Especialidad esp = Especialidad.valueOf(rs.getString("especialidad"));
                return new Medico(
                    rs.getInt("idMedico"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("matricula"),
                    rs.getString("email"),
                    rs.getString("contrasenia"),
                    esp,
                    rs.getInt("activo")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar médico por ID: " + e.getMessage());
        }
        return null;
    }

    
    public static Medico login(String email, String contrasenia) {
        String sql = "SELECT * FROM medico WHERE email = ? AND contrasenia = ? AND activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, contrasenia);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Especialidad especialidadEnum = Especialidad.valueOf(rs.getString("especialidad"));
                Medico medico = new Medico(
                    rs.getInt("idMedico"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("matricula"),
                    rs.getString("email"),
                    rs.getString("contrasenia"),
                    especialidadEnum,
                    rs.getInt("activo")
                );
                return medico;
            }
        } catch (SQLException e) {
            System.err.println("Error al iniciar sesión: " + e.getMessage());
        }
        return null;
    }

    public static void agregarMedico(Medico medico) {
        String sql = "INSERT INTO medico (nombre, apellido, matricula, email, contrasenia, especialidad, activo) VALUES ( ?, ?, ?, ?, ?, ?, TRUE)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, medico.getNombre());
            stmt.setString(2, medico.getApellido());
            stmt.setString(3, medico.getMatricula());
            stmt.setString(4, medico.getEmail());
            stmt.setString(5, medico.getContrasenia());
            stmt.setString(6, medico.getEspecialidad().name());

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

    public static LinkedList<Medico> mostrarMedicos() {
        LinkedList<Medico> lista = new LinkedList<>();
        String sql = "SELECT * FROM medico WHERE activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Especialidad especialidadEnum = Especialidad.valueOf(rs.getString("especialidad"));
                Medico medico = new Medico(
                    rs.getInt("idMedico"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("matricula"),
                    rs.getString("email"),
                    rs.getString("contrasenia"),
                    especialidadEnum,
                    rs.getInt("activo")
                );
                lista.add(medico);
            }

        } catch (SQLException e) {
            System.err.println("Error al mostrar médicos: " + e.getMessage());
        }

        return lista;
    }

    public static LinkedList<Medico> obtenerTodos() {
        LinkedList<Medico> lista = new LinkedList<>();
        try {
            String query = "SELECT * FROM medico WHERE activo = 1";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Medico medico = new Medico(
                    rs.getInt("idMedico"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("matricula"),
                    rs.getString("email"),
                    rs.getString("contrasenia"),
                    Especialidad.valueOf(rs.getString("especialidad")),
                    rs.getInt("activo")
                );
                lista.add(medico);
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener médicos: " + e.getMessage());
        }
        return lista;
    }

    public static boolean EditarMedico(Medico medico) {
        String sql = "UPDATE medico SET nombre = ?, apellido = ?, email = ?, contrasenia = ? "
                + "WHERE matricula = ? "
                + "AND activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, medico.getNombre());
            stmt.setString(2, medico.getApellido());
            stmt.setString(3, medico.getEmail());
            stmt.setString(4, medico.getContrasenia());
            stmt.setString(5, medico.getMatricula());

            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al editar paciente: " + e.getMessage());
            return false;
        }
    }

    public static void RegistrarMedico(Medico nuevo) {
        LinkedList<Medico> existentes = mostrarMedicos();
        boolean flag = true;
        for (Medico existente : existentes) {
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

    public static void RegistrarMedico() {
        String nombre = JOptionPane.showInputDialog("ingresa tu nombre");
        String apellido = JOptionPane.showInputDialog("ingresa tu apellido");
        String matricula = JOptionPane.showInputDialog("ingresa tu dni");
        Especialidad especialidadEnum = Especialidad.valueOf(JOptionPane.showInputDialog("ingresa la fecha"));

        String email = JOptionPane.showInputDialog("ingresa tu email");
        String contrasenia = JOptionPane.showInputDialog("ingresa tu contrasenia");
        int estado = Integer.parseInt(JOptionPane.showInputDialog("ingrese 1 o 0 para ver si esta activo o no"));
        Medico nuevo = new Medico(0,nombre,apellido,matricula,email,contrasenia,especialidadEnum,estado);

        LinkedList<Medico> existentes = mostrarMedicos();
        boolean flag = true;
        for (Usuario existente : existentes) {
            if (existente.getEmail().equals(nuevo.getEmail())) {
                flag = false;
                break;
            }
        }
        if (flag) {
            agregarMedico(nuevo);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario ya creado");
        }
    }

    public static boolean eliminarMedico(int id) {
        String sql = "UPDATE medico SET activo = FALSE WHERE idMedico = ?";
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

    public static Medico buscarPorMatricula(String matricula) {
        String sql = "SELECT * FROM medico WHERE matricula = ? AND activo = TRUE";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, matricula);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Especialidad especialidadEnum = Especialidad.valueOf(rs.getString("especialidad"));
                Medico medico = new Medico(
                    rs.getInt("idMedico"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("matricula"),
                    rs.getString("email"),
                    rs.getString("contrasenia"),
                    especialidadEnum,
                    rs.getInt("activo")
                );
                return medico;
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar médico: " + e.getMessage());
        }

        return null;
    }
}
