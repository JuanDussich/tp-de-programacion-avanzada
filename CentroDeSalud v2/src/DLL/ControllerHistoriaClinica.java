package DLL;

import BLL.HistoriaClinica;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerHistoriaClinica {

    private static Connection con = Conexion.getInstance().getConnection();


    public void crearHistoriaClinica(HistoriaClinica hc) {
        String sql = "INSERT INTO historia_clinica (observaciones, fecha, turnoId, pacienteId, tratamientoId, medicamentoId, medicoId, detalleConsulta) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, hc.getObservaciones());
            stmt.setDate(2, Date.valueOf(hc.getFecha()));
            stmt.setInt(3, hc.getTurnoId());
            stmt.setInt(4, hc.getPacienteId());
            stmt.setInt(5, hc.getTratamientoId());
            stmt.setInt(6, hc.getMedicamentoId());
            stmt.setInt(7, hc.getMedicoId());
            stmt.setString(8, hc.getDetalleConsulta());
            stmt.executeUpdate();

            System.out.println("Historia clínica creada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<HistoriaClinica> obtenerHistoriasClinicas() {
        ArrayList<HistoriaClinica> lista = new ArrayList<>();
        String sql = "SELECT * FROM historia_clinica";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HistoriaClinica hc = new HistoriaClinica(
                    rs.getInt("idHistorialMedico"),
                    rs.getString("observaciones"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getInt("turnoId"),
                    rs.getInt("pacienteId"),
                    rs.getInt("tratamientoId"),
                    rs.getInt("medicamentoId"),
                    rs.getInt("medicoId"),
                    rs.getString("detalleConsulta")
                );
                lista.add(hc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public void actualizarHistoriaClinica(int id, String nuevasObservaciones) {
        String sql = "UPDATE historia_clinica SET observaciones = ? WHERE idHistorialMedico = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nuevasObservaciones);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Historia clínica actualizada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void eliminarHistoriaClinica(int id) {
        String sql = "DELETE FROM historia_clinica WHERE idHistorialMedico = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Historia clínica eliminada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}