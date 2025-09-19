package Strategy;

import Controller.CajeroController;
import Models.CajeroModel;
import Views.CajeroView;

public class ConsultarPinStrategy implements OperacionCajeroStrategy {
    @Override
    public void ejecutar(CajeroController controller, CajeroModel model, CajeroView view) {
        String pin = model.consultarPIN();
        if (pin != null) {
            view.mostrarMensaje("Tu PIN actual es: " + pin);
        } else {
            view.mostrarMensaje("No hay cuenta activa.");
        }
    }
}