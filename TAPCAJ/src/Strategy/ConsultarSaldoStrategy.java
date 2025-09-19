package Strategy;

import Controller.CajeroController;
import Models.CajeroModel;
import Views.CajeroView;

public class ConsultarSaldoStrategy implements OperacionCajeroStrategy {
    @Override
    public void ejecutar(CajeroController controller, CajeroModel model, CajeroView view) {
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }
}