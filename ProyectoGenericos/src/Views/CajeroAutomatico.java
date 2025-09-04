package Views;

import Controller.CajeroController;
import Models.CajeroModel;

/**
 * Clase de arranque (main) para el cajero automático.
 * Crea las instancias de modelo, vista y controlador y arranca el sistema.
 * 
 * Uso: ejecutar esta clase para iniciar la aplicación de consola.
 * 
 * @author Leonardo
 * @version 1.0
 * @since 2025-09-04
 */

public class CajeroAutomatico {

      /**
     * Punto de entrada de la aplicación.
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        
        CajeroModel model = new CajeroModel();
        CajeroView view = new CajeroView();
        CajeroController controller = new CajeroController(model, view);
        controller.iniciarSistema();
    }
}