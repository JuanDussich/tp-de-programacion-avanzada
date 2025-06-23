package BLL;
import javax.swing.*;

import repository.OpcionesAdministrador;
import DLL.ControllerAdministrador;
import repository.*;

public class Administrador extends Usuario implements Encriptador {
	
		//ATRIBUTOS
		private int id;
		private int activo;
	    // CONSTRUCTOR
	    public Administrador() {
	    }

	    public Administrador(int id, String nombre, String apellido, String mail, String contrasenia) {
	        super(nombre,apellido,mail,contrasenia);
	        this.id = id;

	    }
	    
	    public Administrador(int id, String nombre, String apellido, String mail, String contrasenia, int activo) {
	        super(nombre,apellido,mail,contrasenia);
	        this.id = id;
	        this.setActivo(activo);

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
							//esta es una de las formas de registrar
							
							//Administrador.RegistrarAdministrador();
							
							//esta es otra de las formas de registrar
							String nombre = JOptionPane.showInputDialog("ingresa tu nombre");
					        String apellido = JOptionPane.showInputDialog("ingresa tu apellido");
					        
					        mail = JOptionPane.showInputDialog("ingresa tu email");
					        contrasenia = JOptionPane.showInputDialog("ingresa tu contrasenia");
					        admin = new Administrador(0,nombre,apellido,mail,contrasenia);
					        Administrador.RegistrarAdministrador(admin);
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
	    
	    //METODO QUE LLAMA AL LOGIN DEL CONTROLADOR DE ADMINISTRADOR
	    public static Administrador login(String email, String contrasenia) {
	    	
	    	//Administrador usuario = new Administrador() ;
	    	if (email.isEmpty() || contrasenia.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Hay un error, no puede ser vacio");
			}else {
				//usuario = ControllerAdministrador.login(email, contrasenia);
				return ControllerAdministrador.login(email,contrasenia);
			}
	    	//return usuario;
	    	return null;
	    }

	    //METODO REGISTRAR USUARIO QUE LLAMA AL CONTROLADOR
	    public static void RegistrarAdministrador() {
	    	
	    	ControllerAdministrador.RegistrarAdministrador();;
	    	
	    	
	    }
	    
	    //METODO PARA REGISTRAR ADMINISTRADOR QUE LLAMA AL CONTROLADOR Y LE ENVIA EL ADMINISTRADOR YA CREADO ANTES DE ENVIARLO AL METODO
	    public static void RegistrarAdministrador(Administrador admin) {
	    	
	    	JOptionPane.showMessageDialog(null, "Estas registrandote");
	    	ControllerAdministrador.RegistrarAdministrador(admin);
	    	
	    }
	    
	    //METODO PARA EDITAR ADMINISTRADOR verificar
	    public static String EditarAdministrador(Administrador administrador) {
	        if (administrador.getEmail().isEmpty() || administrador.getNombre().isEmpty() 
	        		|| administrador.getContrasenia().isEmpty()) {
	            return "No se pudo editar";
	        } else {
	            boolean exito = DLL.ControllerAdministrador.EditarAdministrador(administrador);
	            if (exito) {
	                return "Administrador actualizado correctamente";
	            } else {
	                return "No se pudo actualizar el Administrador";
	            }
	        }
	    }
		
	    //METODO PARA ELIMINAR ADMINISTRADOR verificar
	    public static void EliminarAdministrador(int id) {
	    	
	    	JOptionPane.showMessageDialog(null, "Estas eliminando un Administrador");
	    	ControllerAdministrador.EliminarAdministrador(id);
	    	
	    }

		public int getActivo() {
			return activo;
		}

		public void setActivo(int activo) {
			this.activo = activo;
		}
}
