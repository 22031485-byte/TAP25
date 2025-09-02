public class Cliente {
    private String pin;
    private String nombre;
    private double saldo;

    public Cliente(String pin, String nombre, double saldo) {
        this.pin = pin;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public String getPin() {
        return pin;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
