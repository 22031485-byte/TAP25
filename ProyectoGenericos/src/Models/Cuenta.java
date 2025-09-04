package Models;

/**
 * Representa una cuenta bancaria simple con número, PIN, saldo y titular.
 * Proporciona operaciones básicas: validar PIN, retirar, depositar y cambiar PIN.
 * 
 * @param <T> Tipo genérico para el número de cuenta y el titular
 * @author Leonardo
 * @version 1.0
 * @since 2025-09-04
 */
public class Cuenta<T> {

    private T numeroCuenta;  // Número de cuenta genérico (String, Integer, etc.)
    private String pin;      // PIN de 4 dígitos
    private double saldo;    // Saldo en la cuenta
    private T titular;       // Titular de la cuenta (String u otro objeto)

    /**
     * Crea una nueva cuenta con los datos especificados.
     *
     * @param numeroCuenta número identificador de la cuenta
     * @param pin          PIN de 4 dígitos
     * @param saldo        saldo inicial
     * @param titular      nombre o información del titular
     */
    public Cuenta(T numeroCuenta, String pin, double saldo, T titular) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.saldo = saldo;
        this.titular = titular;
    }

    // Getters y Setters
    public T getNumeroCuenta() {
         return numeroCuenta;
     }
    public void setNumeroCuenta(T numeroCuenta) {
         this.numeroCuenta = numeroCuenta;
     }

    public String getPin() { 
        return pin; 
    }
    public void setPin(String pin) {
         this.pin = pin; 
     }

    public double getSaldo() {
         return saldo;
     }
    public void setSaldo(double saldo) {
         this.saldo = saldo;
     }

    public T getTitular() {
         return titular; 
    }
    public void setTitular(T titular) {
         this.titular = titular;
     }

    /**
     * Valida que el PIN ingresado coincida con el almacenado.
     *
     * @param pinIngresado PIN a validar
     * @return {@code true} si coincide; {@code false} en caso contrario
     */
    public boolean validarPin(String pinIngresado) {
        return this.pin.equals(pinIngresado);
    }

    /**
     * Intenta retirar una cantidad del saldo.
     *
     * @param cantidad monto a retirar (debe ser positivo y <= saldo)
     * @return {@code true} si el retiro fue exitoso; {@code false} en caso contrario
     */
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= this.saldo) {
            saldo -= cantidad;
            return true; 
        }
        return false;
    }

    /**
     * Cambia el PIN de la cuenta si el PIN actual coincide y el nuevo PIN cumple
     * la validación (aquí: exactamente 4 dígitos numéricos).
     *
     * @param pinActual PIN actual del usuario
     * @param nuevoPin  nuevo PIN deseado (debe coincidir con la validación)
     * @return {@code true} si el cambio se realizó; {@code false} en caso contrario
     */
    public boolean cambiarPin(String pinActual, String nuevoPin) {
        if (pinActual == null || nuevoPin == null) return false;
        if (!this.pin.equals(pinActual)) return false;
        if (!nuevoPin.matches("\\d{4}")) return false;
        this.pin = nuevoPin;
        return true;
    }

    /**
     * Deposita una cantidad en la cuenta si es positiva.
     *
     * @param cantidad monto a depositar
     */
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
        }
    }
}
