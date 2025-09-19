package Strategy;

import Controller.CajeroController;
import Models.CajeroModel;
import Views.CajeroView;

/**
 * Interfaz que define la estrategia para una operación del cajero.
 * Todas las operaciones concretas (retirar, depositar, etc.) deben implementarla.
 */
public interface OperacionCajeroStrategy {
    void ejecutar(CajeroController controller, CajeroModel model, CajeroView view);
}