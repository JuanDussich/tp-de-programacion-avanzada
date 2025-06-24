package DLL;
import BLL.Turno;
import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;
public class ControllerTurno {
   private static Connection con = Conexion.getInstance().getConnection();
   // CREATE
   public static void agregarTurno(Turno turno) {
       try {
           Statement stmt = con.createStatement();
           String sql = "INSERT INTO turno (fechaTurno, horaTurno, tipoConsulta, estado, paciente_idPaciente, medico_idMedico, especialidad, motivoConsulta, resultadoConsulta) " +
                   "VALUES ('" + turno.getFechaTurno() + "', '" + turno.getHoraTurno() + "', '" + turno.getTipoConsulta() + "', '" +
                   turno.getEstado() + "', " + turno.getIdPaciente() + ", " + turno.getIdMedico() + ", '" +
                   turno.getEspecialidad() + "', '" + turno.getMotivoConsulta() + "', '" + turno.getResultadoConsulta() + "')";
           stmt.executeUpdate(sql);
           JOptionPane.showMessageDialog(null, "Turno agregado correctamente.");
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al agregar el turno: " + e.getMessage());
       }
   }
   // READ
   public static LinkedList<Turno> listarTurnos() {
       LinkedList<Turno> lista = new LinkedList<>();
       try {
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT * FROM turno");
           while (rs.next()) {
               Turno t = new Turno();
               t.setIdTurno(rs.getInt("idTurno"));
               t.setFechaTurno(rs.getDate("fechaTurno").toLocalDate());
               t.setHoraTurno(rs.getTime("horaTurno").toLocalTime());
               t.setTipoConsulta(rs.getString("tipoConsulta"));
               t.setEstado(rs.getString("estado"));
               t.setIdPaciente(rs.getInt("paciente_idPaciente"));
               t.setIdMedico(rs.getInt("medico_idMedico"));
               t.setEspecialidad(rs.getString("especialidad"));
               t.setMotivoConsulta(rs.getString("motivoConsulta"));
               t.setResultadoConsulta(rs.getString("resultadoConsulta"));
               lista.add(t);
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al listar turnos: " + e.getMessage());
       }
       return lista;
   }
   //UPDATE
   
   public static boolean modificarTurno(Turno turno) {
       String sql = "UPDATE turno SET fechaTurno = ?, horaTurno = ?, tipoConsulta = ?, estado = ?, " +
                    "paciente_idPaciente = ?, medico_idMedico = ?, especialidad = ?, motivoConsulta = ?, resultadoConsulta = ? " +
                    "WHERE idTurno = ?";
       try {
           PreparedStatement stmt = con.prepareStatement(sql);
           stmt.setDate(1, Date.valueOf(turno.getFechaTurno()));
           stmt.setTime(2, Time.valueOf(turno.getHoraTurno()));
           stmt.setString(3, turno.getTipoConsulta());
           stmt.setString(4, turno.getEstado());
           stmt.setInt(5, turno.getIdPaciente());
           stmt.setInt(6, turno.getIdMedico());
           stmt.setString(7, turno.getEspecialidad());
           stmt.setString(8, turno.getMotivoConsulta());
           stmt.setString(9, turno.getResultadoConsulta());
           stmt.setInt(10, turno.getIdTurno()); // Este es el filtro
           int filas = stmt.executeUpdate();
           return filas > 0;
       } catch (SQLException e) {
           System.err.println("Error al modificar el turno: " + e.getMessage());
           return false;
       }
   }
   // DELETE
   public static void eliminarTurno(int id) {
       try {
           int confirmar = JOptionPane.showConfirmDialog(null, "¿Estás seguro que querés eliminar el turno con ID " + id + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
           if (confirmar == JOptionPane.YES_OPTION) {
               Statement stmt = con.createStatement();
               int filas = stmt.executeUpdate("DELETE FROM turno WHERE idTurno=" + id);
               if (filas > 0) {
                   JOptionPane.showMessageDialog(null, "Turno eliminado correctamente.");
               } else {
                   JOptionPane.showMessageDialog(null, "No se encontró un turno con ese ID.");
               }
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error al eliminar el turno: " + e.getMessage());
       }
   }
}
