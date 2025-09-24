package Models;

public class Cuenta {

    private final String numeroCuenta;
    private final String pin;
    private final double saldo;
    private final String titular;

    // 1. Constructor privado para forzar el uso del Builder
    private Cuenta(Builder builder) {
        this.numeroCuenta = builder.numeroCuenta;
        this.pin = builder.pin;
        this.saldo = builder.saldo;
        this.titular = builder.titular;
    }

    // Getters y otros métodos...
    public String getNumeroCuenta() { return numeroCuenta;
     }
    public String getPin() { return pin;
     }
    public double getSaldo() { return saldo;
     }
    public String getTitular() { return titular;
     }
    
    public boolean validarPin(String pinIngresado) {
        return this.pin.equals(pinIngresado);
    }
    
    // NOTA: Los métodos que modifican el estado (retirar, depositar, cambiarPin)
    // ahora deben ser manejados de forma diferente si el objeto es inmutable.
    // Un enfoque es devolver un nuevo objeto con el estado modificado.
    
    public Cuenta retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= this.saldo) {
            return new Cuenta.Builder()
                .conNumeroCuenta(this.numeroCuenta)
                .conPin(this.pin)
                .conSaldo(this.saldo - cantidad)
                .conTitular(this.titular)
                .build();
        }
        return this; // Devuelve el mismo objeto si el retiro falla
    }
    
    public Cuenta depositar(double cantidad) {
        if (cantidad > 0) {
            return new Cuenta.Builder()
                .conNumeroCuenta(this.numeroCuenta)
                .conPin(this.pin)
                .conSaldo(this.saldo + cantidad)
                .conTitular(this.titular)
                .build();
        }
        return this;
    }
    
    public Cuenta cambiarPin(String pinActual, String nuevoPin) {
        if (pinActual == null || nuevoPin == null || !this.pin.equals(pinActual) || !nuevoPin.matches("\\d{4}")) {
            return this;
        }
        return new Cuenta.Builder()
            .conNumeroCuenta(this.numeroCuenta)
            .conPin(nuevoPin)
            .conSaldo(this.saldo)
            .conTitular(this.titular)
            .build();
    }
    
    // 2. Clase Builder estática y anidada
    public static class Builder {
        private String numeroCuenta;
        private String pin;
        private double saldo = 0.0; // Valor por defecto
        private String titular;

        public Builder conNumeroCuenta(String numeroCuenta) {
            this.numeroCuenta = numeroCuenta;
            return this;
        }

        public Builder conPin(String pin) {
            this.pin = pin;
            return this;
        }

        public Builder conSaldo(double saldo) {
            this.saldo = saldo;
            return this;
        }

        public Builder conTitular(String titular) {
            this.titular = titular;
            return this;
        }

        // 3. Método build() para construir el objeto final
        public Cuenta build() {
            if (numeroCuenta == null || pin == null || titular == null) {
                throw new IllegalStateException("Número de cuenta, PIN y titular son campos obligatorios.");
            }
            return new Cuenta(this);
        }
    }
}
