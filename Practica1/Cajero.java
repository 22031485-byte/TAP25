public class Cajero {
    private Cliente cliente;

    public Cajero(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return cliente.getNombre();
    }

    public double getSaldo() {
        return cliente.getSaldo();
    }

    public void verSaldo() {
        System.out.println("Su saldo es: $" + cliente.getSaldo());
    }

    public void retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= cliente.getSaldo()) {
            cliente.setSaldo(cliente.getSaldo() - cantidad);
            System.out.println("Retiro exitoso. Nuevo saldo: $" + cliente.getSaldo());
        } else {
            System.out.println("Fondos insuficientes");
        }
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            cliente.setSaldo(cliente.getSaldo() + cantidad);
            System.out.println("Depósito exitoso. Nuevo saldo: $" + cliente.getSaldo());
        } else {
            System.out.println("Cantidad inválida.");
        }
    }
}

