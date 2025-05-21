package BLL;
import java.util.ArrayList;

public class ListaMedico {
	
	//ATRIBUTOS
		private ArrayList<Medico> medicos;
		// CONSTRUCTORES
		public ListaMedico() {
		        this.medicos = new ArrayList<>();
		}
		// METODOS
		public void agregarMedico(Medico medico) {
		        medicos.add(medico);
		}

		public void eliminarMedico(Medico medico) {
		        medicos.remove(medico);
		}

		// GETTERS Y SETTERS
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
