package BLL;

import javax.swing.*;
import repository.OpcionesTurno;
import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {

    // ATRIBUTOS
    private int idTurno;
    private String tipoConsulta; // antes motivoTurno
    private LocalDate fechaTurno;
    private LocalTime horaTurno;
    private String estado;
    private int idPaciente;
    private int idMedico;

    // CONSTRUCTORES
    public Turno() {
    }

    public Turno(String tipoConsulta, LocalDate fechaTurno, LocalTime horaTurno, String estado, int idPaciente, int idMedico) {
        this.tipoConsulta = tipoConsulta;
        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
        this.estado = estado;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
    }

    public Turno(int idTurno, String tipoConsulta, LocalDate fechaTurno, LocalTime horaTurno, String estado, int idPaciente, int idMedico) {
        this(tipoConsulta, fechaTurno, horaTurno, estado, idPaciente, idMedico);
        this.idTurno = idTurno;
    }

    // MÉTODOS
    public void menuTurno() {
        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menu Turno",
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
                    JOptionPane.showMessageDialog(null, "Saliendo del menú Turno.");
                    break;
                default:
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

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
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

    // TO STRING
    @Override
    public String toString() {
        return "Turno{" +
                "idTurno=" + idTurno +
                ", tipoConsulta='" + tipoConsulta + '\'' +
                ", fechaTurno=" + fechaTurno +
                ", horaTurno=" + horaTurno +
                ", estado='" + estado + '\'' +
                ", idPaciente=" + idPaciente +
                ", idMedico=" + idMedico +
                '}';
    }
}
