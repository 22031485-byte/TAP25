package Controller;

import Models.CajeroModel;
import Views.CajeroView;
import Strategy.*; // Importamos el nuevo paquete con las estrategias

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador principal del cajero automático. Refactorizado con el patrón Strategy.
 */
public class CajeroController {
    private CajeroModel model;
    private CajeroView view;
    private boolean sistemaActivo;
    private Map<Integer, OperacionCajeroStrategy> operaciones; // Mapa para las estrategias

    public CajeroController(CajeroModel model, CajeroView view) {
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
        this.operaciones = new HashMap<>();
        inicializarOperaciones(); // Nuevo método para cargar las estrategias
    }

    private void inicializarOperaciones() {
        operaciones.put(1, new ConsultarSaldoStrategy());
        operaciones.put(2, new RetiroStrategy());
        operaciones.put(3, new DepositoStrategy());
        operaciones.put(4, new TransferenciaStrategy()); // Necesitas crear esta clase
        operaciones.put(5, new CambioPinStrategy()); // Necesitas crear esta clase
        operaciones.put(6, new ConsultarPinStrategy()); // Necesitas crear esta clase
        operaciones.put(7, new SalirStrategy()); // Necesitas crear esta clase
    }

    public void iniciarSistema() {
        view.mostrarBienvenida();
        while (sistemaActivo) {
            if (autenticarUsuario()) {
                ejecutarMenuPrincipal();
            } else {
                view.mostrarMensaje("Credenciales incorrectas.");
            }
        }
        view.mostrarMensaje("Gracias por usar nuestro cajero.");
    }

    private boolean autenticarUsuario() {
        String numeroCuenta = view.solicitarNumeroCuenta();
        String pin = view.solicitarPin();
        return model.autenticar(numeroCuenta, pin);
    }

    private void ejecutarMenuPrincipal() {
        boolean sesionActiva = true;
        while (sesionActiva) {
            view.mostrarMenuPrincipal(model.getCuentaActual().getTitular());
            int opcion = view.leerOpcion();
            
            OperacionCajeroStrategy estrategia = operaciones.get(opcion);
            if (estrategia != null) {
                estrategia.ejecutar(this, model, view);
                if (opcion == 7) {
                    sesionActiva = false;
                    sistemaActivo = false;
                }
            } else {
                view.mostrarMensaje("Opción inválida, intenta de nuevo.");
            }
        }
    }

    // Los métodos para cada operación (consultarSaldo, realizarRetiro, etc.) se eliminan de aquí.
    // Ahora residen en sus respectivas clases de estrategia.
    
    // Y para que no se pierda la funcionalidad de cerrar sistema
    public void cerrarSistema() {
        sistemaActivo = false;
        view.cerrar();
    }
}