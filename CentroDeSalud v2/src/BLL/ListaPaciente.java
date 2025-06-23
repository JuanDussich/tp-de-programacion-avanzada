package BLL;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.ControllerPaciente;

public class ListaPaciente {

    public void registrarPaciente() {
        ControllerPaciente.RegistrarPaciente();
    }

    public void agregarPaciente(Paciente paciente) {
        ControllerPaciente.agregarPaciente(paciente);
    }

    public LinkedList<Paciente> mostrarTodos() {
        return ControllerPaciente.mostrarPacientes();
    }

    //ACA HAY UN ERROR EN LA FORMA EN LA QUE SE ESTA LLAMANDO A ACTUALIZAR PACIENTE
    public boolean actualizarPaciente(Paciente paciente) {
        String mensaje = ControllerPaciente.EditarPaciente(paciente);
        if (mensaje.equals("Usuario editado correctamente.")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, mensaje);
            return false;
        }
    }

    public Paciente buscarPorDni(int dni) {
        for (Paciente p : mostrarTodos()) {
            if (p.getDni() == dni) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarPorDni(int dni) {
        return false;
    }

}
