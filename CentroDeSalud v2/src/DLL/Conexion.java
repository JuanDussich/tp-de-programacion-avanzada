package DLL;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    //ACA FALTA AGREGAR EL NOMBRE DE LA BASE DE DATOS
    private static String URL ="jdbc:mysql://localhost:3306/centro_de_salud";
    private static String USER = "root";
    private static String PASSWORD ="";

    private static Connection conect;
    private static Conexion instance;
    
    private Conexion() {
        try {
            conect =  (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Se conectó");
        } catch (SQLException e) {
            System.out.println("No se conectó");

        }
    }
    public static Conexion getInstance() {
        if(instance ==null) {
            instance = new Conexion();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return conect;
    }

}