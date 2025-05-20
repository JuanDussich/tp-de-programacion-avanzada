import javax.swing.*;
import java.awt.*;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog((Component)null, "hola esto es el main");
        Administrador admin = new Administrador();
        Paciente paciente = new Paciente();
        Medico medico = new Medico();

        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog((Component)null, "Bienvenido", "Centro de Salud", -1, 0, (Icon)null, OpcionMain.values(), OpcionMain.values());
            switch (opcion) {
                case 0:
                    admin.MenuAdministrador();
                    break;
                case 1:
                    paciente.MenuPaciente();
                    break;
                case 2:
                    medico.MenuMedico();
                    break;
                case 3:
                    JOptionPane.showMessageDialog((Component)null, "a bueno adios master");
            }
        } while(opcion != 3);
        
        JOptionPane.showMessageDialog(null, "esto es en la rama agustin");

    }
}