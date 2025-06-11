package BLL;
import javax.swing.*;

import repository.Encriptador;
import repository.OpcionesMedico;

public class Medico extends Usuario implements Encriptador{
	private int idMedico;
    private int activo = 1; // true 1 false 0
	private String matricula;
    private String especialidad;
    //private int cantidadConsultas;

    
    public Medico() {

    }
    
    public Medico(int idMedico, String nombre, String apellido, String matricula, String email, String contrasenia, String especialidad, int activo) {
        super(nombre, apellido, email, contrasenia); // Hereda de Usuario
        this.idMedico = idMedico;
        this.especialidad = especialidad;
        this.matricula = matricula;
        this.activo = activo;
    }
    // Validación 
    public Medico(String matricula, String especialidad) {
        if (matricula != null && especialidad != null) {
            this.matricula = matricula;
            this.especialidad = especialidad;
            //this.cantidadConsultas = cantidadConsultas;
        } else {
            this.matricula = "Desconocida";
            this.especialidad = "Desconocida";
           // this.cantidadConsultas = 0;
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
                	JOptionPane.showMessageDialog(null, "Mostrar Agenda.");
                    break;
                case 1: // CARGAR_RECETA
                	JOptionPane.showMessageDialog(null, "Cargar receta");
                    break;
                case 2: // CONSULTAR_PACIENTE
                	JOptionPane.showMessageDialog(null, "Consultar Paciente");
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

    public static String EditarMedico(Medico usuario) {
        if (usuario.getEmail().isEmpty() || usuario.getNombre().isEmpty() || usuario.getContrasenia().isEmpty()) {
            return "No se pudo editar";
        } else {
            boolean exito = DLL.ControllerMedico.EditarMedico(usuario);
            if (exito) {
                return "Medico actualizado correctamente";
            } else {
                return "No se pudo actualizar el medico";
            }
        }
    }
    
    // ------------------- MÉTODOS GETTERS & SETTERS -------------------

    public int getId() {
        return idMedico;
    }

    public void setId(int id) {
        this.idMedico = id;
    }
    
    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
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
    
    /*public int getCantidadConsultas() {
        return cantidadConsultas;
    }

    public void setCantidadConsultas(int cantidadConsultas) {
        if (cantidadConsultas >= 0) {
            this.cantidadConsultas = cantidadConsultas;
        } else {
            this.cantidadConsultas = 0; // Establece un valor predeterminado si es negativo
        }
    }*/

    @Override
    public String toString() {
        return "Medico{" +
                "idMedico=" + idMedico +
                ", activo=" + activo +
                ", nombre=" + getNombre() +
                ", apellido=" + getApellido() +
                ", email=" + getEmail() +
                ", especialidad='" + especialidad + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
