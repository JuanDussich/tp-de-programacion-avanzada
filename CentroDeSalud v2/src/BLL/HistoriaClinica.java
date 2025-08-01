package BLL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoriaClinica {


    private int idHistorialMedico;
    private String observaciones;
    private LocalDate fecha;
    private String detalleConsulta;

    private int turnoId;
    private int pacienteId;
    private int tratamientoId;
    private int medicamentoId;
    private int medicoId;

    private List<String> notasMedicas;
    private List<String> recetas;



    public HistoriaClinica() {
        this.notasMedicas = new ArrayList<>();
        this.recetas = new ArrayList<>();
    }

    public HistoriaClinica(String observaciones, LocalDate fecha, int turnoId, int pacienteId,
                           int tratamientoId, int medicamentoId, int medicoId, String detalleConsulta) {
        this();
        this.observaciones = observaciones;
        this.fecha = fecha;
        this.turnoId = turnoId;
        this.pacienteId = pacienteId;
        this.tratamientoId = tratamientoId;
        this.medicamentoId = medicamentoId;
        this.medicoId = medicoId;
        this.detalleConsulta = detalleConsulta;
    }

    public HistoriaClinica(int idHistorialMedico, String observaciones, LocalDate fecha, int turnoId,
                           int pacienteId, int tratamientoId, int medicamentoId, int medicoId, String detalleConsulta) {
        this(observaciones, fecha, turnoId, pacienteId, tratamientoId, medicamentoId, medicoId, detalleConsulta);
        this.idHistorialMedico = idHistorialMedico;
    }



    public void agregarNotaMedica(String nota) {
        this.notasMedicas.add(nota);
    }

    public void agregarReceta(String receta) {
        this.recetas.add(receta);
    }

    public boolean perteneceAlMedico(int idMedico) {
        return this.medicoId == idMedico;
    }

    public boolean datosCompletos() {
        return observaciones != null && !observaciones.isEmpty() &&
               fecha != null &&
               detalleConsulta != null && !detalleConsulta.isEmpty();
    }

    public boolean fechaEsValida() {
        return fecha != null && !fecha.isAfter(LocalDate.now());
    }

    // GETTERS Y SETTERS

    public int getIdHistorialMedico() {
        return idHistorialMedico;
    }

    public void setIdHistorialMedico(int idHistorialMedico) {
        this.idHistorialMedico = idHistorialMedico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(int turnoId) {
        this.turnoId = turnoId;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getTratamientoId() {
        return tratamientoId;
    }

    public void setTratamientoId(int tratamientoId) {
        this.tratamientoId = tratamientoId;
    }

    public int getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(int medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public List<String> getNotasMedicas() {
        return notasMedicas;
    }

    public void setNotasMedicas(List<String> notasMedicas) {
        this.notasMedicas = notasMedicas;
    }

    public List<String> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<String> recetas) {
        this.recetas = recetas;
    }

    public String getDetalleConsulta() {
        return detalleConsulta;
    }

    public void setDetalleConsulta(String detalleConsulta) {
        this.detalleConsulta = detalleConsulta;
    }


    @Override
    public String toString() {
        return "HistoriaClinica {" +
                "\n  ID: " + idHistorialMedico +
                "\n  Observaciones: '" + observaciones + '\'' +
                "\n  Detalle de Consulta: '" + detalleConsulta + '\'' +
                "\n  Fecha: " + fecha +
                "\n  Turno ID: " + turnoId +
                "\n  Paciente ID: " + pacienteId +
                "\n  Tratamiento ID: " + tratamientoId +
                "\n  Medicamento ID: " + medicamentoId +
                "\n  Médico ID: " + medicoId +
                "\n}";
    }
}