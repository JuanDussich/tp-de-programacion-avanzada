package BLL;
import javax.swing.*;

import repository.Encriptador;
import repository.OpcionesMedico;

public class Medico extends Usuario implements Encriptador{
	private int id;
    private boolean activo = true;
	private String matricula;
    private String especialidad;
    private int cantidadConsultas;

    
    public Medico() {

    }
    
    public Medico(int id, String nombre, String apellido, String email, String contrasenia, String especialidad, String matricula, boolean activo) {
        super(nombre, apellido, email, contrasenia); // Hereda de Usuario
        this.id = id;
        this.especialidad = especialidad;
        this.matricula = matricula;
        this.activo = activo;
    }
    // Validación 
    public Medico(String matricula, String especialidad, int cantidadConsultas) {
        if (matricula != null && especialidad != null && cantidadConsultas >= 0) {
            this.matricula = matricula;
            this.especialidad = especialidad;
            this.cantidadConsultas = cantidadConsultas;
        } else {
            this.matricula = "Desconocida";
            this.especialidad = "Desconocida";
            this.cantidadConsultas = 0;
        }
    }

    
    public void MenuMedico() {
        int opcion;

        // Bucle que muestra el menú hasta que el médico elija "Salir"
        do {
            // Mostrar opciones del menú
            opcion = JOptionPane.showOptionDialog(
                null,
                "Seleccione una opción:",
                "Menú Médico",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                OpcionesMedico.values(),
                OpcionesMedico.VER_AGENDA
            );

            switch (opcion) {
                case 0: // VER_AGENDA
                    mostrarAgenda();
                    break;
                case 1: // CARGAR_RECETA
                    cargarReceta();
                    break;
                case 2: // CONSULTAR_PACIENTE
                    consultarPaciente();
                    break;
                case 3: // SALIR
                    JOptionPane.showMessageDialog(null, "Saliendo del menú Médico...");
                    break;
                default:
                    // Si se cierra el diálogo
                    opcion = 3; // Fuerza la salida
                    break;
            }
        } while (opcion != 3);
    }

    // Simula la visualización de turnos en agenda
   private void mostrarAgenda() {
    	String agenda = 
    		    "Agenda del día:\n" +
    		    "- 08:00 - Juan Pérez\n" +
    		    "- 08:30 - María Gómez\n" +
    		    "- 09:00 - Luis Martínez";
        JOptionPane.showMessageDialog(null, agenda, "Agenda", JOptionPane.INFORMATION_MESSAGE);
    }
    

    // Simula la carga de una receta para un paciente
    private void cargarReceta() {
        String paciente = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
        String receta = JOptionPane.showInputDialog("Ingrese la receta (medicamentos, dosis, etc):");

        if (paciente != null && receta != null) {
            JOptionPane.showMessageDialog(null, "Receta cargada exitosamente para " + paciente);
            // Aquí podrías guardar la receta en una base de datos
        } else {
            JOptionPane.showMessageDialog(null, "Ingreso cancelado.");
        }
    }

    // Simula la consulta de información básica de un paciente
    private void consultarPaciente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente a consultar:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            // Simulación de datos del paciente
            String datos = "Nombre: " + nombre + "\nEdad: 45\nDiagnóstico: Hipertensión";
            JOptionPane.showMessageDialog(null, datos, "Datos del Paciente", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Nombre no válido.");
        }
    }



    // ------------------- MÉTODOS GETTERS & SETTERS -------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

   
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public int getCantidadConsultas() {
        return cantidadConsultas;
    }

    public void setCantidadConsultas(int cantidadConsultas) {
        if (cantidadConsultas >= 0) {
            this.cantidadConsultas = cantidadConsultas;
        } else {
            this.cantidadConsultas = 0; // Establece un valor predeterminado si es negativo
        }
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", activo=" + activo +
                ", nombre=" + getNombre() +
                ", apellido=" + getApellido() +
                ", email=" + getEmail() +
                ", especialidad='" + especialidad + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
