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

<<<<<<< HEAD
		//GETTERS Y SETTERS
		public ArrayList<String> getNotasMedicas() {
		        return notasMedicas;
		}
=======
    // CONSTRUCTORES

    public HistoriaClinica() {
        this.notasMedicas = new ArrayList<>();
        this.recetas = new ArrayList<>();
    }
>>>>>>> Brian

		public ArrayList<String> getRecetas() {
		        return recetas;
		}

		public void setNotasMedicas(ArrayList<String> notasMedicas) {
		        this.notasMedicas = notasMedicas;
		}

<<<<<<< HEAD
		public void setRecetas(ArrayList<String> recetas) {
		        this.recetas = recetas;
		}
=======
    // MÉTODOS PERSONALIZADOS

    public void agregarNotaMedica(String nota) {
        this.notasMedicas.add(nota);
    }
>>>>>>> Brian

		@Override
		public String toString() {
			return "HistoriaClinica{" + "notasMedicas=" + notasMedicas + ", recetas=" + recetas + '}';
		}

<<<<<<< HEAD
=======
    // GETTERS Y SETTERS

    public int getIdHistorialMedico() {
        return idHistorialMedico;
    }

    public void setIdHistorialMedico(int idHistorialMedico) {
        this.idHistorialMedico = idHistorialMedico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(int turnoId) {
        this.turnoId = turnoId;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getTratamientoId() {
        return tratamientoId;
    }

    public void setTratamientoId(int tratamientoId) {
        this.tratamientoId = tratamientoId;
    }

    public int getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(int medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public List<String> getNotasMedicas() {
        return notasMedicas;
    }

    public void setNotasMedicas(List<String> notasMedicas) {
        this.notasMedicas = notasMedicas;
    }

    public List<String> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<String> recetas) {
        this.recetas = recetas;
    }

    // TO STRING

    @Override
    public String toString() {
        return "HistoriaClinica {" +
                "\n  ID: " + idHistorialMedico +
                "\n  Observaciones: '" + observaciones + '\'' +
                "\n  Fecha: " + fecha +
                "\n  Turno ID: " + turnoId +
                "\n  Paciente ID: " + pacienteId +
                "\n  Tratamiento ID: " + tratamientoId +
                "\n  Medicamento ID: " + medicamentoId +
                "\n  Médico ID: " + medicoId +
                "\n}";
    }
>>>>>>> Brian
}
