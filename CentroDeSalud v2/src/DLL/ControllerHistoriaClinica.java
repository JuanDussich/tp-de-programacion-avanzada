package DLL;

import BLL.HistoriaClinica;
import java.sql.*;
import java.util.ArrayList;

public class ControllerHistoriaClinica {

    private Connection conn;

    public ControllerHistoriaClinica() {
        try {
            conn = Conexion.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public void crearHistoriaClinica(String observaciones, String fecha,
                                     int turnoId, int pacienteId, int tratamientoId,
                                     int medicamentoId, int medicoId) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO historia_clinica (observaciones, fecha, turno_idTurno, turno_Paciente_idPaciente, " +
                         "tratamiento_idTratamiento, medicamento_idMedicamento, medico_idMedico) VALUES (" +
                         "'" + observaciones + "', '" + fecha + "', " + turnoId + ", " + pacienteId + ", " +
                         tratamientoId + ", " + medicamentoId + ", " + medicoId + ")";
            stmt.executeUpdate(sql);
            System.out.println("Historia clínica creada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public ArrayList<HistoriaClinica> obtenerHistoriasClinicas() {
        ArrayList<HistoriaClinica> lista = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM historia_clinica");

            while (rs.next()) {
                HistoriaClinica hc = new HistoriaClinica();
                hc.agregarNotaMedica(rs.getString("observaciones"));
                hc.agregarReceta("Medicamento ID: " + rs.getInt("medicamento_idMedicamento")); // ejemplo
                lista.add(hc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // UPDATE
    public void actualizarHistoriaClinica(int id, String nuevasObservaciones) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE historia_clinica SET observaciones = '" + nuevasObservaciones +
                         "' WHERE idHistorial_Medico = " + id;
            stmt.executeUpdate(sql);
            System.out.println("Historia clínica actualizada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void eliminarHistoriaClinica(int id) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM historia_clinica WHERE idHistorial_Medico = " + id;
            stmt.executeUpdate(sql);
            System.out.println("Historia clínica eliminada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
