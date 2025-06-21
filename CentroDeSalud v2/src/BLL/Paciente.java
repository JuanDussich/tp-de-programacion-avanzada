

package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.Conexion;
import DLL.ControllerAdministrador;
import repository.Encriptador;
import repository.OpcionesPaciente;
import DLL.ControllerPaciente;



public class Paciente extends Usuario implements Encriptador{

	//ATRIBUTOS
    private int id;
    private int dni;
    private String fechaNacimiento;
    
//    private HistoriaClinica historiaClinica;

    // CONSTRUCTOR
    public Paciente() {
    }
    
    public Paciente(int id, String nombre,String apellido,int dni,String fechaNacimiento,String email,String contrasenia) {
        super(nombre,apellido,email,contrasenia);
        this.id = id;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }
    // METODOS
    
    //METODO PARA HACER LOGIN DE PACIENTE
    public static Paciente login(String email, String contrasenia) {
    	
    	//Paciente usuario = new Paciente() ;
    	if (email.isEmpty() || contrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Hay un error, no puede ser vacio");
		}else {
			//usuario = ControllerPaciente.login(email, contrasenia);
			return ControllerPaciente.login(email,contrasenia);
			
		}
    	//return usuario;
    	return null;
    }
    
    //METODO PARA REGISTRAR PACIENTE PERO ENVIANDO EL PACIENTE ANTES DE IR AL CONTROLADOR
    public static void RegistrarPaciente(Paciente paciente) {
    	
    	JOptionPane.showMessageDialog(null, "Estas registrandote");
    	ControllerPaciente.RegistrarPaciente(paciente);
    	
    }
    
    //METODO REGISTRAR PACIENTE QUE LLAMA AL CONTROLADOR
    public static void RegistrarPaciente() {
    	
    	ControllerPaciente.RegistrarPaciente();;
    	
    	
    }
    
    //METODO PARA EDITAR PACIENTE
    public static String EditarPaciente(Paciente usuario) {
		
		if (usuario.getEmail().isEmpty() || usuario.getNombre().isEmpty()|| usuario.getContrasenia().isEmpty()) {
			return "No se pudo editar";
		}else {
			return DLL.ControllerPaciente.EditarPaciente(usuario);
		}
		
	}
    
    //METODO PARA ELIMINAR PACIENTE
    
    
    public void MenuPaciente() {

        int menu;
        String mail;
        String contrasenia;
        Paciente paciente = new Paciente();

        do{
            menu = JOptionPane.showOptionDialog(null, "menu", "Menu Paciente", JOptionPane.DEFAULT_OPTION, 0,  null,OpcionesPaciente.values(), OpcionesPaciente.values());
            switch (menu){
                case 0:
                    // Ver datos personales se va a poder hacer el login y registro en esta parte
                    String[] accionesUsuario = { "Login", "Registro","Ver mi usuario", "Salir" };
                    int opcion;
                    opcion = JOptionPane.showOptionDialog(null,"elija una de las opciones","menu Paciente",0,0,null,accionesUsuario,accionesUsuario[0]);
                    switch (opcion){
                        case 0:
                            mail = JOptionPane.showInputDialog("ingresa tu mail para poder logearte");
                            contrasenia = JOptionPane.showInputDialog("ingresa tu contrasenia");
                            paciente = Paciente.login(mail,contrasenia);
                            
                            break;
                        case 1:
                        	//primer forma de registrar paciente, en esta se crea el paciente dentro del metodo registro
                        	
                        	//Paciente.RegistrarPaciente();
                        	
                        	//segunda forma de registrar paciente, en esta se envia el paciente ya registrado con sus datos
                        	String nombre = JOptionPane.showInputDialog("ingresa tu nombre");
                            String apellido = JOptionPane.showInputDialog("ingresa tu apellido");
                            int  dni = Integer.parseInt(JOptionPane.showInputDialog("ingresa tu dni"));
                            String fechaNacimiento = JOptionPane.showInputDialog("ingresa la fecha");

                            mail = JOptionPane.showInputDialog("ingresa tu email");
                            contrasenia = JOptionPane.showInputDialog("ingresa tu contrasenia");
                            
                            paciente = new Paciente(0,nombre,apellido,dni,fechaNacimiento,mail,contrasenia);
                            Paciente.RegistrarPaciente(paciente);
                            
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, paciente);
                            break;
                        case 3:
                        	JOptionPane.showMessageDialog(null, "a bueno adios master");
                            break;
                    }
                    break;
                case 1:
                    // Ver Medicos
                    break;
                case 2:
                    // Ver turnos
                    break;
                case 3:
                    // Ver historial Medico
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Saliendo del menu Paciente");
                    break;
            }
        }while (menu != 4);
    }

    
    // GETTERS Y SETTERS
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	// dni
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    // fechaNacimiento
    public  String getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento( String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }



    @Override
    public String toString() {
        return "Paciente{" + "id=" + id + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + "} " + super.toString();
    }

}
