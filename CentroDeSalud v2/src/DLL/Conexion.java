package DLL;

import com.mysql.jdbc.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
// CAMBIOS EN CONEXION!!!
    // URL de conexión a la base de datos "centro_de_salud"
    private static final String URL = "jdbc:mysql://localhost:3306/centro_de_salud";
    private static final String USER = "root";      // usuario de la base de datos
    private static final String PASSWORD = "";      // contraseña (defecto)

    // Objeto Connection para manejar la conexión
    private static Connection conect;
    
    // Instancia singleton
    private static Conexion instance;

    // Constructor privado (patrón Singleton)
    private Conexion() {
        try {
            // Conexión a la base de datos
        	conect =  (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("✅ Se conectó correctamente a la base de datos.");
        } catch (SQLException e) {
        	System.out.println("❌ Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Método para obtener la única instancia (singleton)
    public static Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }

    // Devuelve la conexión actual
    public Connection getConnection() {
        return conect;
    }
}
