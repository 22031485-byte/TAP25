package Strategy;

import Controller.CajeroController;
import Models.CajeroModel;
import Views.CajeroView;

public class DepositoStrategy implements OperacionCajeroStrategy {
    @Override
    public void ejecutar(CajeroController controller, CajeroModel model, CajeroView view) {
        double cantidad = view.solicitarCantidad("depositar");
        if (cantidad <= 0) {
            view.mostrarMensaje("Error en la cantidad. La cantidad debe ser positiva.");
            return;
        }
        if (model.realizarDeposito(cantidad)) {
            view.mostrarMensaje("Depósito exitoso de $" + cantidad);
        } else {
            view.mostrarMensaje("No se pudo realizar el depósito.");
        }
    }
}