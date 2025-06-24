package BLL;
import java.util.ArrayList;

public class ListaPaciente {

    private ArrayList<Paciente> pacientes;

    public ListaPaciente() {
        this.pacientes = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public Paciente buscarPorDni(int dni) {
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) {
                return p;
            }
        }
        return null;
    }

    public void mostrarTodos() {
        for (Paciente p : pacientes) {
            System.out.println(p);
        }
    }

    public boolean actualizarPaciente(int dni, String nuevoNombre, String nuevoApellido, String nuevaFechaNacimiento, String nuevoEmail, String nuevaContrasenia) {
        Paciente paciente = buscarPorDni(dni);
        if (paciente != null) {
            paciente.setNombre(nuevoNombre);
            paciente.setApellido(nuevoApellido);
            paciente.setFechaNacimiento(nuevaFechaNacimiento);
            paciente.setEmail(nuevoEmail);
            paciente.setContrasenia(nuevaContrasenia);
            return true;
        }
        return false;
    }

    public boolean eliminarPorDni(int dni) {
        Paciente paciente = buscarPorDni(dni);
        if (paciente != null) {
            pacientes.remove(paciente);
            return true;
        }
        return false;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public String toString() {
        return "ListaPaciente{" + "pacientes=" + pacientes + '}';
    }
}
