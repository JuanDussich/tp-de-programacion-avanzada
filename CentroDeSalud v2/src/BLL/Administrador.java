package BLL;
import javax.swing.*;

import repository.OpcionesAdministrador;
import DLL.ControllerAdministrador;
import DLL.ControllerPaciente;
import repository.*;

public class Administrador extends Usuario implements Encriptador {
	
		//ATRIBUTOS
		private int id;
	    // CONSTRUCTOR
	    public Administrador() {
	    }

	    public Administrador(int id, String nombre, String apellido, String mail, String contrasenia) {
	        super(nombre,apellido,mail,contrasenia);
	        this.id = id;

	    }
	    
	    //GETTERS Y SETTERS
	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
	    // METODOS
	    public void MenuAdministrador() {
	        int menu;
	        String mail;
	        String contrasenia;
	        Administrador admin = new Administrador();
	        do{
	            menu = JOptionPane.showOptionDialog(null, "menu", "menu principal", JOptionPane.DEFAULT_OPTION, 0,  null,OpcionesAdministrador.values(), OpcionesAdministrador.values());
	            switch (menu){
	                case 0:
	                	 String[] accionesUsuario = { "Login", "Registro","Ver mi usuario", "Salir" };
	                     int opcion;
	                     opcion = JOptionPane.showOptionDialog(null,"elija una de las opciones","menu Paciente",0,0,null,accionesUsuario,accionesUsuario[0]);
	                     switch (opcion) {
						case 0:
							JOptionPane.showMessageDialog(null, "login");
							mail = JOptionPane.showInputDialog("ingresa tu mail para poder logearte");
                            contrasenia = JOptionPane.showInputDialog("ingresa tu contrasenia");
                            admin = Administrador.login(mail,contrasenia);
							break;
						case 1:
							JOptionPane.showMessageDialog(null, "registro");
							Administrador.RegistrarUsuario();
							break;
						case 2:
							JOptionPane.showMessageDialog(null, admin);
							break;
							
						default:
							break;
						}
	                    
	                    break;
	                case 1:
	                    JOptionPane.showMessageDialog(null, "aca vas a poder ver la lista de Medicos");
	                    break;
	                case 2:
	                    JOptionPane.showMessageDialog(null, "aca vas a poder ver la lista de Pacientes");
	                    break;
	                case 3:
	                    JOptionPane.showMessageDialog(null, "a bueno adios master");
	                    break;
	            }
	        }while (menu != 3);
	    }

	    @Override
	    public String toString() {
	        return "Administrador{" + "id=" + id + "} " + super.toString();
	    }
	    
	    public static Administrador login(String email, String contrasenia) {
	    	Administrador usuario = new Administrador() ;
	    	if (email.isEmpty() || contrasenia.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Hay un error, no puede ser vacio");
			}else {
				usuario = ControllerAdministrador.login(email, contrasenia);
				
			}
	    	return usuario;
	    }

	    public static void RegistrarUsuario() {
	    	
	    	ControllerAdministrador.RegistrarAdministrador();;
	    	
	    	
	    }
		

}
