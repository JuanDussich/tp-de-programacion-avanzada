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

    // UPDATE
    public static void modificarTurno(int id) {
        try {
            String nuevaFecha = JOptionPane.showInputDialog("Nueva fecha (YYYY-MM-DD):");
            String nuevaHora = JOptionPane.showInputDialog("Nueva hora (HH:MM):");
            String nuevoTipo = JOptionPane.showInputDialog("Nuevo tipo de consulta:");
            String nuevoEstado = JOptionPane.showInputDialog("Nuevo estado:");
            int nuevoIdPaciente = Integer.parseInt(JOptionPane.showInputDialog("Nuevo ID de paciente:"));
            int nuevoIdMedico = Integer.parseInt(JOptionPane.showInputDialog("Nuevo ID de médico:"));
            String nuevaEspecialidad = JOptionPane.showInputDialog("Nueva especialidad:");
            String nuevoMotivo = JOptionPane.showInputDialog("Nuevo motivo de consulta:");
            String nuevoResultado = JOptionPane.showInputDialog("Nuevo resultado de consulta:");

            Statement stmt = con.createStatement();
            String sql = "UPDATE turno SET fechaTurno='" + nuevaFecha + "', horaTurno='" + nuevaHora + "', tipoConsulta='" + nuevoTipo +
                    "', estado='" + nuevoEstado + "', paciente_idPaciente=" + nuevoIdPaciente + ", medico_idMedico=" + nuevoIdMedico +
                    ", especialidad='" + nuevaEspecialidad + "', motivoConsulta='" + nuevoMotivo + "', resultadoConsulta='" + nuevoResultado +
                    "' WHERE idTurno=" + id;
            int filas = stmt.executeUpdate(sql);

            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Turno modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un turno con ese ID.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el turno: " + e.getMessage());
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
