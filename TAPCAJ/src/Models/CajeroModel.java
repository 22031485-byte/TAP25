package Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Representa el modelo de datos del cajero automático.
 * Refactorizado para manejar la inmutabilidad de la clase Cuenta.
 */
public class CajeroModel {
    private Map<String, Cuenta> cuentas;
    private Cuenta cuentaActual;

    public CajeroModel() {
        cuentas = new HashMap<>();
        inicializarCuentas();
    }

    private void inicializarCuentas() {
        cuentas.put("12345", new Cuenta.Builder()
            .conNumeroCuenta("12345")
            .conPin("1111")
            .conSaldo(5000)
            .conTitular("Juan Perez")
            .build());

        cuentas.put("13579", new Cuenta.Builder()
            .conNumeroCuenta("13579")
            .conPin("2222")
            .conSaldo(100000)
            .conTitular("Xochilt")
            .build());

        cuentas.put("10009", new Cuenta.Builder()
            .conNumeroCuenta("10009")
            .conPin("3333")
            .conSaldo(999999)
            .conTitular("Leonardo")
            .build());
    }

    public boolean autenticar(String numeroCuenta, String pin) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null && cuenta.validarPin(pin)) {
            this.cuentaActual = cuenta;
            return true;
        }
        return false;
    }

    public Cuenta getCuentaActual() {
        return this.cuentaActual;
    }

    public double consultarSaldo() {
        return this.cuentaActual != null ? cuentaActual.getSaldo() : 0;
    }

    public String consultarPIN() {
        return this.cuentaActual != null ? cuentaActual.getPin() : null;
    }

    /**
     * Realiza un retiro sobre la cuenta actual, actualizando la referencia si es exitoso.
     */
    public boolean realizarRetiro(double cantidad) {
        if (cuentaActual == null) {
            return false;
        }
        // Llama al método retirar de Cuenta y guarda la nueva instancia
        Cuenta cuentaActualizada = cuentaActual.retirar(cantidad);
        if (cuentaActualizada != cuentaActual) { // Comprueba si el retiro fue exitoso
            cuentas.put(cuentaActualizada.getNumeroCuenta(), cuentaActualizada);
            cuentaActual = cuentaActualizada;
            return true;
        }
        return false;
    }

    /**
     * Realiza un depósito sobre la cuenta actual, actualizando la referencia si es exitoso.
     */
    public boolean realizarDeposito(double cantidad) {
        if (cuentaActual == null) {
            return false;
        }
        // Llama al método depositar de Cuenta y guarda la nueva instancia
        Cuenta cuentaActualizada = cuentaActual.depositar(cantidad);
        if (cuentaActualizada != cuentaActual) { // Comprueba si el depósito fue exitoso
            cuentas.put(cuentaActualizada.getNumeroCuenta(), cuentaActualizada);
            cuentaActual = cuentaActualizada;
            return true;
        }
        return false;
    }

    /**
     * Realiza una transferencia. Ahora maneja la inmutabilidad de ambas cuentas.
     */
    public boolean transferir(String numeroCuentaDestino, double cantidad) {
        if (cuentaActual == null || cantidad <= 0) return false;

        Cuenta destino = cuentas.get(numeroCuentaDestino);
        if (destino == null) return false;
        if (destino.getNumeroCuenta().equals(cuentaActual.getNumeroCuenta())) return false;

        // Intentar retirar de la cuenta actual
        Cuenta cuentaOrigenActualizada = cuentaActual.retirar(cantidad);
        if (cuentaOrigenActualizada != cuentaActual) {
            // Si el retiro es exitoso, depositar en la cuenta de destino
            Cuenta cuentaDestinoActualizada = destino.depositar(cantidad);
            
            // Actualizar ambas cuentas en el mapa
            cuentas.put(cuentaOrigenActualizada.getNumeroCuenta(), cuentaOrigenActualizada);
            cuentas.put(cuentaDestinoActualizada.getNumeroCuenta(), cuentaDestinoActualizada);
            
            // Actualizar la referencia a la cuenta actual
            cuentaActual = cuentaOrigenActualizada;
            return true;
        }
        return false;
    }

    /**
     * Cambia el PIN de la cuenta actual, actualizando la referencia si es exitoso.
     */
    public boolean cambiarPinActual(String pinActual, String nuevoPin) {
        if (cuentaActual == null) return false;
        
        Cuenta cuentaActualizada = cuentaActual.cambiarPin(pinActual, nuevoPin);
        if (cuentaActualizada != cuentaActual) {
            cuentas.put(cuentaActualizada.getNumeroCuenta(), cuentaActualizada);
            cuentaActual = cuentaActualizada;
            return true;
        }
        return false;
    }

    public boolean cuentaExistente(String numeroCuenta) {
        return cuentas.containsKey(numeroCuenta);
    }
}