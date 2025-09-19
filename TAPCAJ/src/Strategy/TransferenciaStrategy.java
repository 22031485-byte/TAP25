package Strategy;

import Controller.CajeroController;
import Models.CajeroModel;
import Views.CajeroView;

public class TransferenciaStrategy implements OperacionCajeroStrategy {
    @Override
    public void ejecutar(CajeroController controller, CajeroModel model, CajeroView view) {
        String cuentaDestino = view.solicitarCuentaDestino();
        
        if (!model.cuentaExistente(cuentaDestino)) {
            view.mostrarMensaje("La cuenta destino no existe.");
            return;
        }

        String cuentaOrigen = model.getCuentaActual().getNumeroCuenta();
        if (cuentaOrigen.equals(cuentaDestino)) {
            view.mostrarMensaje("No puedes transferir a la MISMA cuenta.");
            return;
        }

        double cantidad = view.solicitarCantidad("transferir");
        if (cantidad <= 0) {
            view.mostrarMensaje("Error en la cantidad. Debe ser un monto positivo.");
            return;
        }

        boolean ok = model.transferir(cuentaDestino, cantidad);
        if (ok) {
            view.mostrarMensaje("Transferencia exitosa de $" + cantidad + " a la cuenta " + cuentaDestino);
        } else {
            view.mostrarMensaje("No se pudo completar la transferencia (fondos insuficientes o datos inválidos).");
        }
    }
}