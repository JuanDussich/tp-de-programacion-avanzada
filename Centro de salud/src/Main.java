import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main  {
    public static void main(String[] args) {
        
        Administrador admin = new Administrador();
        Paciente paciente = new Paciente();
        Medico medico = new Medico();

        int opcion;
        do{
            opcion = JOptionPane.showOptionDialog(null, "Bienvenido", "Centro de Salud", JOptionPane.DEFAULT_OPTION, 0,  null,OpcionMain.values(), OpcionMain.values());
            switch (opcion){
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
                    JOptionPane.showMessageDialog(null, "a bueno adios master");
                    break;
            }
        }while (opcion != 3);



    }
}