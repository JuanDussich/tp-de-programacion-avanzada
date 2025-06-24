package BLL;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.ControllerMedico;

public class ListaMedico {

    public void registrarMedico(Medico medico) {
        ControllerMedico.RegistrarMedico(medico);
    }

    public void agregarMedico(Medico medico) {
        ControllerMedico.agregarMedico(medico);
    }

    public LinkedList<Medico> mostrarTodos() {
        return ControllerMedico.mostrarMedicos();
    }

    public boolean actualizarMedico(Medico medico) {
        boolean actualizado = ControllerMedico.EditarMedico(medico);
        if (!actualizado) {
            JOptionPane.showMessageDialog(null, "Error al editar médico");
        }
        return actualizado;
    }

    public Medico buscarPorMatricula(String matricula) {
        return ControllerMedico.buscarPorMatricula(matricula);
    }

    public boolean eliminarPorId(int id) {
        return ControllerMedico.eliminarMedico(id);
    }
}
