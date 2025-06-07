package BLL;
import java.util.ArrayList;

public class ListaMedico {


    private ArrayList<Medico> medicos;


    public ListaMedico() {
        this.medicos = new ArrayList<>();
    }


    public void agregarMedico(Medico medico) {
        medicos.add(medico);
    }


    public Medico buscarPorMatricula(String matricula) {
        for (Medico m : medicos) {
            if (m.getMatricula().equalsIgnoreCase(matricula)) {
                return m;
            }
        }
        return null;
    }

    public void mostrarTodos() {
        for (Medico m : medicos) {
            System.out.println(m);
        }
    }


    public boolean actualizarMedico(String matricula, String nuevaEspecialidad, int nuevaCantidadConsultas) {
        Medico medico = buscarPorMatricula(matricula);
        if (medico != null) {
            medico.setEspecialidad(nuevaEspecialidad);
            medico.setCantidadConsultas(nuevaCantidadConsultas);
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
    }
}
