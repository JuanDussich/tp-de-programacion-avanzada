package BLL;
import javax.swing.*;

import repository.OpcionesMedico;

public class Medico {
	
	private String matricula;
    private String especialidad;
    private int cantidadConsultas;

    
    public Medico() {

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
        do {
            opcion = JOptionPane.showOptionDialog(null, "menu", "Menu Medico", JOptionPane.DEFAULT_OPTION, 0, null, OpcionesMedico.values(), OpcionesMedico.values());
            switch (opcion) {
                case 0:
                    // Ver datos personales
                    break;
                case 1:
                    // Ver pacientes
                    break;
                case 2:
                    // Ver turnos
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo del menu Medico");
                    break;
            }
        } while (opcion != 3);
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
        return super.toString() + "\nMédico {" + "\n  Matrícula: '" + matricula + '\'' + "\n  Especialidad: '" + especialidad + '\'' + "\n  Cantidad de Consultas: " + cantidadConsultas + "\n}";
    }
}
