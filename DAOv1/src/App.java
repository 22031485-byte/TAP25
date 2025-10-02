// Ya no necesitamos importar java.sql.Connection ni DriverManager
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        
        try {
            // Paso 1: Obtener la instancia del DAO. 
            // Esto, internamente, llama al Singleton de conexión y la establece.
            EstudianteDAO dao = new EstudianteDAOImpl();

            System.out.println("--- PRUEBA DE INSERTAR Y LISTAR ---");

            // Insertar
            dao.insertar(new Estudiante(0, "Oscar Daniel, Nuevo Estudiante Singleton", "oscSingl@itcelaya.edu.mx")); 

            // Listar y mostrar en consola
            for (Estudiante e : dao.listar()) {
                System.out.println(e.getId() + " - " + e.getNombre() + " (" + e.getCorreo() + ")");
            }
            
            // Opcional: Cierra la conexión al finalizar.
            ConexionBD.getInstancia().cerrarConexion();
            
        } catch (Exception e) {
            // Captura cualquier excepción, incluyendo la RuntimeException si la conexión falla.
            System.err.println("¡ERROR EN LA APLICACIÓN!");
            e.printStackTrace();
        }
    }
}