package BLL;
import javax.swing.*;

import DLL.ControllerMedico;
import DLL.ControllerPaciente;
<<<<<<< HEAD

=======
>>>>>>> Brian
import repository.Encriptador;
import repository.OpcionesMedico;

public class Medico extends Usuario implements Encriptador{
	private int idMedico;
    private int activo = 1; // true 1 false 0
	private String matricula;
<<<<<<< HEAD
    private Especialidad especialidad; // especialidad tipo enum
=======
    private String especialidad; // se cambio el campo especialidad a string eliminar tabla especialidad!!
>>>>>>> Brian
       
    public Medico() {

    }
    
<<<<<<< HEAD
    public Medico(int idMedico, String nombre, String apellido, String matricula, String email, String contrasenia, Especialidad especialidad, int activo) {
=======
    public Medico(int idMedico, String nombre, String apellido, String matricula, String email, String contrasenia, String especialidad, int activo) {
>>>>>>> Brian
        super(nombre, apellido, email, contrasenia); // Hereda de Usuario
        this.idMedico = idMedico;
        this.especialidad = especialidad;
        this.matricula = matricula;
        this.activo = activo;
    }
    // constructor sin id 
<<<<<<< HEAD
    public Medico(String nombre, String apellido, String matricula, String email, String contrasenia, Especialidad especialidad, int activo) {
=======
    public Medico(String nombre, String apellido, String matricula, String email, String contrasenia, String especialidad, int activo) {
>>>>>>> Brian
        super(nombre, apellido, email, contrasenia);
        this.matricula = matricula;
        this.especialidad = especialidad;
        this.activo = activo;
    }
<<<<<<< HEAD
    
   // constructor para editar
    public Medico(String nombre, String apellido, String email, String contrasenia, String matricula) {
        super(nombre, apellido, email, contrasenia);
        this.matricula = matricula;
    }

    // Validación constructir matricula y especialidad
    public Medico(String matricula, Especialidad especialidad) {
=======

    // Validación constructir matricula y especialidad
    public Medico(String matricula, String especialidad) {
>>>>>>> Brian
        if (matricula != null && especialidad != null) {
            this.matricula = matricula;
            this.especialidad = especialidad;
            //this.cantidadConsultas = cantidadConsultas;
        } else {
            this.matricula = "Desconocida";
<<<<<<< HEAD
            this.especialidad = Especialidad.OTRA;
=======
            this.especialidad = "Desconocida";
>>>>>>> Brian
          
        }
    }

    // menu inicial primera entrega ¿BORRAR? consultar con teem
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
    
  //METODO PARA HACER LOGIN DE PACIENTE
    public static Medico login(String email, String contrasenia) {
    	
    	//Paciente usuario = new Paciente() ;
    	if (email.isEmpty() || contrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Hay un error, no puede ser vacio");
		}else {
			//usuario = ControllerPaciente.login(email, contrasenia);
			return ControllerMedico.login(email,contrasenia);
			
		}
    	//return usuario;
    	return null;
    }
    
    //METODO PARA REGISTRAR MEDICO PERO ENVIANDO EL MEDICO ANTES DE IR AL CONTROLADOR
    public static void RegistrarMedico(Medico medico) {
    	
    	JOptionPane.showMessageDialog(null, "Estas registrandote");
    	ControllerMedico.RegistrarMedico(medico);
    	
    }
    
    //METODO REGISTRAR MEDICO QUE LLAMA AL CONTROLADOR
    public static void RegitrarMedico() {
    	
    	ControllerMedico.RegistrarMedico();;
    	
    	
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

   
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    
    @Override
    public String toString() {
        return "Medico{" +
                "idMedico= " + idMedico +
                ", nombre= " + getNombre() +
                ", apellido= " + getApellido() +
                ", matricula= '" + matricula + '\'' +
                ", email= " + getEmail() +
                ", especialidad= '" + especialidad + '\'' +
                ", activo= " + activo +
                '}';
    }
}
