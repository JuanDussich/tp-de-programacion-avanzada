package BLL;
import java.util.ArrayList;

public class HistoriaClinica {
		
		//ATRIBUTOS
		private ArrayList<String> notasMedicas;
		private ArrayList<String> recetas;

		//CONSTRUCTOR
		public HistoriaClinica() {
			this.notasMedicas = new ArrayList<>();
			this.recetas = new ArrayList<>();
		}

		//METODOS
		public void agregarNotaMedica(String nota) {
		        notasMedicas.add(nota);
		}

		public void agregarReceta(String receta) {
		        recetas.add(receta);
		}

		//GETTERS Y SETTERS
		public ArrayList<String> getNotasMedicas() {
		        return notasMedicas;
		}

		public ArrayList<String> getRecetas() {
		        return recetas;
		}

		public void setNotasMedicas(ArrayList<String> notasMedicas) {
		        this.notasMedicas = notasMedicas;
		}

		public void setRecetas(ArrayList<String> recetas) {
		        this.recetas = recetas;
		}

		@Override
		public String toString() {
			return "HistoriaClinica{" + "notasMedicas=" + notasMedicas + ", recetas=" + recetas + '}';
		}

}
