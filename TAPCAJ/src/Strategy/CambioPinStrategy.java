package Strategy;

import Controller.CajeroController;
import Models.CajeroModel;
import Views.CajeroView;

public class CambioPinStrategy implements OperacionCajeroStrategy {
    @Override
    public void ejecutar(CajeroController controller, CajeroModel model, CajeroView view) {
        String pinActual = view.solicitarPin();
        String nuevoPin = view.solicitarNuevoPin();
        String confirmacionPin = view.solicitarConfirmacionPin();

        if (!nuevoPin.equals(confirmacionPin)) {
            view.mostrarMensaje("El nuevo PIN y la confirmación no coinciden.");
            return;
        }

        if (!nuevoPin.matches("\\d{4}")) {
            view.mostrarMensaje("El PIN debe contener exactamente 4 dígitos.");
            return;
        }

        boolean actualizado = model.cambiarPinActual(pinActual, nuevoPin);
        if (actualizado) {
            view.mostrarMensaje("PIN actualizado correctamente.");
        } else {
            view.mostrarMensaje("Error: PIN actual incorrecto o no se pudo actualizar.");
        }
    }
}