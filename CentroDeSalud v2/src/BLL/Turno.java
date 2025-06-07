package BLL;

import javax.swing.*;

import repository.OpcionesTurno;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno{

    //ATRIBUTOS
    private String motivoTurno;
    private LocalDate fechaTurno;
    private LocalTime horaTurno;

    // CONSTRUCTOR
    public Turno() {
    }
    public Turno(String motivoTurno,LocalDate fechaTurno, LocalTime horaTurno) {
        this.motivoTurno = motivoTurno;
        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
    }

    // METODOS
    public void MenuTurno() {
        int opcion;
        do{
            opcion = JOptionPane.showOptionDialog(null, "menu", "Menu Turno", JOptionPane.DEFAULT_OPTION, 0,  null,OpcionesTurno.values(), OpcionesTurno.values());
            switch (opcion){
                case 0:
                    // VerTurnoPendiente
                    JOptionPane.showMessageDialog(null,"aca se va a poder ver si el turno esta pendiente");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null,"aca se va a poder ver si el turno esta Confirmado");
                    // VerTurnoConfirmado
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null,"aca se va a poder ver si el turno esta Cancelado");
                    // VerTurnoCancelado
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null,"aca se va a poder ver si el turno esta Atendido");
                    //VerTurnoAtendido
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Salio del menu Turno");
                    break;
            }
        }while (opcion != 4);
    }

    // GETTERS Y SETTERS

    //motivoTruno
    public String getMotivoTurno() {
        return motivoTurno;
    }

    public void setMotivoTurno(String motivoTurno) {
        this.motivoTurno = motivoTurno;
    }

    //fechaTurno
    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    //horaTurno
    public LocalTime getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(LocalTime horaTurno) {
        this.horaTurno = horaTurno;
    }

    @Override
    public String toString() {
        return "Turno{" + ", motivoTurno='" + motivoTurno + '\'' + ", fechaTurno=" + fechaTurno + ", horaTurno=" + horaTurno + '}';
    }
}
