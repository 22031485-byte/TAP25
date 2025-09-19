package Strategy;

import Controller.CajeroController;
import Models.CajeroModel;
import Views.CajeroView;

public class SalirStrategy implements OperacionCajeroStrategy {
    @Override
    public void ejecutar(CajeroController controller, CajeroModel model, CajeroView view) {
        view.mostrarMensaje("Cerrando sesión...");
        // Llama al método del controlador para cerrar el sistema.
        controller.cerrarSistema();
    }
}