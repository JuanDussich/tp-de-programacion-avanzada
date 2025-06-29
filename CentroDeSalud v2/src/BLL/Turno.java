package BLL;

import javax.swing.*;
import repository.OpcionesTurno;
import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {

    // ATRIBUTOS (coinciden con la BD)
    private int idTurno;
    private LocalDate fechaTurno;
    private LocalTime horaTurno;
    private String tipoConsulta;
    private String estado;
    private int idPaciente;
    private int idMedico;
    private String especialidad;
    private String motivoConsulta;
    private String resultadoConsulta;

    // CONSTRUCTORES
    public Turno() {}

    public Turno(String tipoConsulta, LocalDate fechaTurno, LocalTime horaTurno, String estado, int idPaciente,
                 int idMedico, String especialidad, String motivoConsulta, String resultadoConsulta) {
        this.tipoConsulta = tipoConsulta;
        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
        this.estado = estado;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.especialidad = especialidad;
        this.motivoConsulta = motivoConsulta;
        this.resultadoConsulta = resultadoConsulta;
    }

    public Turno(int idTurno, String tipoConsulta, LocalDate fechaTurno, LocalTime horaTurno, String estado, int idPaciente,
                 int idMedico, String especialidad, String motivoConsulta, String resultadoConsulta) {
        this(tipoConsulta, fechaTurno, horaTurno, estado, idPaciente, idMedico, especialidad, motivoConsulta, resultadoConsulta);
        this.idTurno = idTurno;
    }

    // MÉTODO OPCIONAL DE INTERACCIÓN CON EL USUARIO
    public void menuTurno() {
        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menú Turno",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    OpcionesTurno.values(), OpcionesTurno.values()[0]);

            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Turno pendiente.");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Turno confirmado.");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Turno cancelado.");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Turno atendido.");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Adiós master.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
                    break;
            }

        } while (opcion != 4);
    }


    // GETTERS Y SETTERS

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public LocalTime getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(LocalTime horaTurno) {
        this.horaTurno = horaTurno;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(String resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    // TO STRING
    @Override
    public String toString() {
        return "Turno{" +
                "idTurno=" + idTurno +
                ", fechaTurno=" + fechaTurno +
                ", horaTurno=" + horaTurno +
                ", tipoConsulta='" + tipoConsulta + '\'' +
                ", estado='" + estado + '\'' +
                ", idPaciente=" + idPaciente +
                ", idMedico=" + idMedico +
                ", especialidad='" + especialidad + '\'' +
                ", motivoConsulta='" + motivoConsulta + '\'' +
                ", resultadoConsulta='" + resultadoConsulta + '\'' +
                '}';
    }
}
