package BLL;
import java.util.ArrayList;

public class ListaPaciente {
	
	//ATRIBUTOS
		private ArrayList<Paciente> pacientes;
		// CONSTRUCTORES
		
		public ListaPaciente() {
		        this.pacientes = new ArrayList<>();
		}

		// METODOS
		public void agregarPaciente(Paciente paciente) {
		        pacientes.add(paciente);
		}

		public void eliminarPaciente(Paciente paciente) {
		        pacientes.remove(paciente);
		}

		// GETTERS Y SETTERS
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
