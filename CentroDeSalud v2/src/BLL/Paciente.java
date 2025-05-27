

package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.Conexion;
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
    
    public static Paciente login(String email, String contrasenia) {
    	Paciente usuario = new Paciente() ;
    	if (email.isEmpty() || contrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Hay un error, no puede ser vacio");
		}else {
			usuario = ControllerPaciente.login(email, contrasenia);
			
		}
    	return usuario;
    }

    public static void RegistrarUsuario() {
<<<<<<< HEAD

        String nombre = JOptionPane.showInputDialog("ingresa tu nombre");
        String apellido = JOptionPane.showInputDialog("ingresa tu apellido");
        int  dni = Integer.parseInt(JOptionPane.showInputDialog("ingresa tu dni"));
        String fechaNacimiento = JOptionPane.showInputDialog("ingresa la fecha");

        String email = JOptionPane.showInputDialog("ingresa tu email");
        String contrasenia = JOptionPane.showInputDialog("ingresa tu contrasenia");
        Paciente nuevo = new Paciente(0,nombre,apellido,dni,fechaNacimiento,email,contrasenia);

        LinkedList<Paciente> existentes = mostrarPaciente();
        boolean flag = true;
        for (Usuario existente : existentes) {
            if (existente.getEmail().equals(nuevo.getEmail())) {
                flag = false;
                break;
            }
        }
        if (flag) {
            agregarUsuario(nuevo);
        }else {
            JOptionPane.showMessageDialog(null, "Usuario ya creado");
        }


=======
    	
    	ControllerPaciente.RegistrarPaciente();
    	
    	
>>>>>>> Juan
    }
    
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
                            Paciente.RegistrarUsuario();
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, paciente);
                            break;
                        case 3:
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
                    JOptionPane.showMessageDialog(null, "Saliendo del menu Paciente");
                    break;
            }
        }while (menu != 3);
    }

    // GETTERS Y SETTERS
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
