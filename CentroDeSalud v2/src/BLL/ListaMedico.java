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

<<<<<<< HEAD
    public void mostrarTodos() {
        for (Medico m : medicos) {
            System.out.println(m);
        }
    }


    public boolean actualizarMedico(String matricula, Especialidad nuevaEspecialidad, int nuevaCantidadConsultas) {
        Medico medico = buscarPorMatricula(matricula);
        if (medico != null) {
            medico.setEspecialidad(nuevaEspecialidad);
            //medico.setCantidadConsultas(nuevaCantidadConsultas);
            return true;
        }
        return false;
    }


    public boolean eliminarPorMatricula(String matricula) {
        Medico medico = buscarPorMatricula(matricula);
        if (medico != null) {
            medicos.remove(medico);
            return true;
        }
        return false;
    }

    public ArrayList<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(ArrayList<Medico> medicos) {
        this.medicos = medicos;
    }

    @Override
    public String toString() {
        return "ListaMedico{" + "medicos=" + medicos + '}';
=======
    public boolean eliminarPorId(int id) {
        return ControllerMedico.eliminarMedico(id);
>>>>>>> Brian
    }
}
