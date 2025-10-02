import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // 1. Instancia estática de la propia clase (el Singleton)
    private static ConexionBD instancia;
    
    // 2. La conexión a la base de datos
    private Connection conn;

    // --- PARÁMETROS DE CONEXIÓN A MODIFICAR ---
    private static final String URL = "jdbc:mysql://localhost:3306/escuela";
    private static final String USER = "root";
    private static final String PASS = "12345"; // <-- ¡CAMBIA ESTO!
    // ------------------------------------------

    // 3. Constructor privado para evitar que se creen instancias directamente
    private ConexionBD() {
        try {
            // Inicializa la conexión cuando se crea la única instancia
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión a la base de datos establecida.");
        } catch (SQLException e) {
            System.err.println("¡ERROR AL CONECTAR CON LA BASE DE DATOS!");
            e.printStackTrace();
            // Podrías lanzar una RuntimeException o manejar la salida de forma más robusta aquí.
            throw new RuntimeException("No se pudo establecer la conexión a la base de datos.", e);
        }
    }

    // 4. Método estático público para obtener la única instancia
    public static ConexionBD getInstancia() {
        if (instancia == null) {
            // Sincronizar para asegurar que solo un hilo pueda crear la instancia
            // (esto es importante en aplicaciones multihilo)
            synchronized (ConexionBD.class) {
                if (instancia == null) {
                    instancia = new ConexionBD();
                }
            }
        }
        return instancia;
    }

    // 5. Método público para obtener el objeto Connection
    public Connection getConexion() {
        return conn;
    }
    
    // Opcional: Método para cerrar la conexión cuando la aplicación finaliza
    public void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}