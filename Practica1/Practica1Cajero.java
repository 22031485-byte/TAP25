import java.util.ArrayList;
import java.util.Scanner;

public class Practica1Cajero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("1234", "Juan", 1000.0));
        clientes.add(new Cliente("5678", "Maria", 2500.0));

        int intentos = 0;
        Cliente clienteActual = null;


        System.out.println(" Bienvenido al Cajero ");

        while (intentos < 3 && clienteActual == null) {
            System.out.print("Ingrese su PIN: ");
            String pin = scanner.nextLine();

            for (Cliente c : clientes) {
                if (c.getPin().equals(pin)) {
                    clienteActual = c;
                    break;
                }
            }

            if (clienteActual == null) {
                System.out.println("PIN incorrecto.");
                intentos++;
            }
        }

        if (clienteActual == null) {
            System.out.println("Demasiados intentos fallidos. Adiós.");
            return;
        }

        Cajero cajero = new Cajero(clienteActual);
        System.out.println("Bienvenido, " + cajero.getNombre());

        boolean salir = false;
        while (!salir) {
            System.out.println("\n1. Ver saldo");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    cajero.verSaldo();
                    break;
                case 2:
                    System.out.print("Ingrese cantidad a retirar: ");
                    try {
                        double retiro = Double.parseDouble(scanner.nextLine());
                        cajero.retirar(retiro);
                    } catch (NumberFormatException e) {
                        System.out.println("Cantidad inválida. Por favor ingrese un número válido.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese cantidad a depositar: ");
                    try {
                        double deposito = Double.parseDouble(scanner.nextLine());
                        cajero.depositar(deposito);
                    } catch (NumberFormatException e) {
                        System.out.println("Cantidad inválida. Por favor ingrese un número válido.");
                    }
                    break;
                case 4:
                    salir = true;
                    System.out.println("Gracias por usar el cajero.");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }
}
