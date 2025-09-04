package Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Representa el modelo de datos del cajero automático.
 * Mantiene las cuentas en memoria (HashMap), la cuenta actualmente autenticada
 * y proporciona operaciones para consultar saldo, retirar, depositar,
 * transferir y cambiar PIN.
 * 
 * Nota: la persistencia no está implementada; todo se mantiene en memoria.
 * 
 * @author Leonardo
 * @version 1.0
 * @since 2025-09-04
 */

public class CajeroModel {
    private Map<String, Cuenta<String>> cuentas;
    private Cuenta<String> cuentaActual;

     /**
     * Inicializa el modelo y carga cuentas de ejemplo en memoria.
     */
    public CajeroModel(){
        cuentas = new HashMap<>();
        inicializarCuentas();
    }

    /**
     * Crea cuentas de ejemplo y las agrega al mapa interno.
     * Método privado usado sólo en inicialización.
     */
    private void inicializarCuentas(){
        cuentas.put("12345",  new Cuenta<>("12345", "1111", 5000, "Juan Perez"));
        cuentas.put("13579",  new Cuenta<>("13579", "2222", 100000, "Xochilt"));
        cuentas.put("10009",  new Cuenta<>("10009", "3333", 999999, "Leonardo"));
    }

     /**
     * Intenta autenticar con número de cuenta y PIN.
     *
     * @param numeroCuenta número de la cuenta
     * @param pin          PIN a validar
     * @return {@code true} si la autenticación es correcta y la cuenta queda como actual
     */

    public boolean autenticar(String numeroCuenta, String pin){
        Cuenta<String> cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null && cuenta.validarPin(pin)) {
            this.cuentaActual = cuenta;
            return true;
            
        }
        return false;
    }

      /**
     * Devuelve la cuenta actualmente autenticada.
     *
     * @return la cuenta actual o {@code null} si no hay sesión iniciada
     */

    public Cuenta<String> getCuentaActual(){
        return this.cuentaActual;
    }

    
    /**
     * Consulta el saldo de la cuenta actual.
     *
     * @return saldo actual o 0 si no hay cuenta autenticada
     */

    public double consultarSaldo(){
        return this.cuentaActual != null ? cuentaActual.getSaldo():0;
    }

    public String consultarPIN(){
    return this.cuentaActual != null ? cuentaActual.getPin() : null;
}

    /**
     * Realiza un retiro sobre la cuenta actual.
     *
     * @param cantidad monto a retirar
     * @return {@code true} si el retiro fue exitoso; {@code false} en caso contrario
     */

    public boolean realizarRetiro(double cantidad){
        return cuentaActual != null && cuentaActual.retirar(cantidad);
    }

    /**
     * Realiza un depósito sobre la cuenta actual.
     *
     * @param cantidad monto a depositar
     * @return {@code true} si el depósito fue exitoso; {@code false} en caso contrario
     */

    public boolean realizarDeposito(double cantidad){
        if (cuentaActual != null && cantidad > 0) {
            cuentaActual.depositar(cantidad);
            return true;
            
        }
        return false;
    }

     /**
     * Realiza una transferencia de la cuenta actual a la cuenta destino.
     *
     * @param numeroCuentaDestino número de la cuenta destino
     * @param cantidad            monto a transferir
     * @return {@code true} si la transferencia fue exitosa; {@code false} en caso contrario
     */

    public boolean transferir(String numeroCuentaDestino, double cantidad){
        if (cuentaActual == null || cantidad <= 0) return false;

        Cuenta<String> destino = cuentas.get(numeroCuentaDestino);
        if (destino == null) return false; // no existe
        if (destino.getNumeroCuenta().equals(cuentaActual.getNumeroCuenta())) return false;

        // Retira primero; si hay fondos, deposita en destino
        if (cuentaActual.retirar(cantidad)) {
            destino.depositar(cantidad);
            return true;
        }
        return false;
    }

     /**
     * Cambia el PIN de la cuenta actualmente autenticada si el PIN actual coincide.
     *
     * @param pinActual PIN actual
     * @param nuevoPin  nuevo PIN deseado
     * @return {@code true} si se actualizó correctamente; {@code false} en caso contrario
     */
    
    public boolean cambiarPinActual(String pinActual, String nuevoPin){
        if (cuentaActual == null) return false;
        return cuentaActual.cambiarPin(pinActual, nuevoPin);
    }

   /**
     * Indica si una cuenta con el número dado existe en el sistema.
     *
     * @param numeroCuenta número de cuenta a buscar
     * @return {@code true} si existe; {@code false} en caso contrario
     */

    public boolean cuentaExistente(String numeroCuenta){
        return cuentas.containsKey(numeroCuenta);
    }


}
