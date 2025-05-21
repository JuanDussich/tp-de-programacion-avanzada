package BLL;
import javax.swing.*;

import repository.OpcionesAdministrador;

public class Administrador extends Usuario {
	
		//ATRIBUTOS
		
	    // CONSTRUCTOR
	    public Administrador() {
	    }

	    public Administrador(String nombre, String apellido, String mail, String contrasenia) {
	        super(nombre,apellido,mail,contrasenia);
	        

	    }
	    
	    //GETTERS Y SETTERS

	    // METODOS
	    public void MenuAdministrador() {
	        int opcion;
	        do{
	            opcion = JOptionPane.showOptionDialog(null, "menu", "menu principal", JOptionPane.DEFAULT_OPTION, 0,  null,OpcionesAdministrador.values(), OpcionesAdministrador.values());
	            switch (opcion){
	                case 0:
	                    JOptionPane.showMessageDialog(null, "aca vas a poder ver la informacion del administrador");
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
	        }while (opcion != 3);
	    }
	    
	    
		

}
