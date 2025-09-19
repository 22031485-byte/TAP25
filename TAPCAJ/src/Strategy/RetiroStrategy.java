package Strategy;

import Controller.CajeroController;
import Models.CajeroModel;
import Views.CajeroView;

public class RetiroStrategy implements OperacionCajeroStrategy {
    @Override
    public void ejecutar(CajeroController controller, CajeroModel model, CajeroView view) {
        double cantidad = view.solicitarCantidad("retirar");
        if (cantidad <= 0) {
            view.mostrarMensaje("Error en la cantidad. La cantidad debe ser positiva.");
            return;
        }

        if (model.realizarRetiro(cantidad)) {
            view.mostrarMensaje("Retiro exitoso de $" + cantidad);
        } else {
            view.mostrarMensaje("Fondos insuficientes o error al realizar el retiro.");
        }
    }
}