package Views;

import java.util.Scanner;

public class CajeroView {
   private Scanner scanner;
   public CajeroView(){
     scanner = new Scanner(System.in);
   }

   public void mostrarBienvenida(){
    System.out.println("=============================");
    System.out.println("Bienvenido al cajero automatico del banco del bienestar");
    System.out.println("=============================");
   }

   public String solicitarNumeroCuenta(){
    System.out.println("Ingresa tu numero de cuenta: ");
    return scanner.nextLine();
   }

   public String solcitarPin(){
    System.out.println("Ingresa tu pin: ");
    return scanner.nextLine();
   }

   public void mostrarMenuPrincipal(String titular){
    System.out.println("=============================");
    System.out.println("Bienvenid@ "+titular);
    System.out.println("=============================");
    System.out.println("1.- Consultar saldo ");
    System.out.println("2.- Retirar ");
    System.out.println("3.- Depositar ");

    System.out.println("4.- Salir ");
   }

   public int leerOpcion(){
    try{
        return Integer.parseInt(scanner.nextLine());
    }catch(NumberFormatException e){
        return -1;
    }
   }

   public void mostrarSaldo(double saldo){
    System.out.println("=============================");
    System.out.println("Tu saldo actual es: $"+saldo);
    System.out.println("=============================");
   }

   public double solicitarCantidad(String operacion){
    System.out.println("Ingresa la cantidad a "+operacion+":");
    try{
        return Double.parseDouble(scanner.nextLine());
    }catch (NumberFormatException e){
        return -1;
    }
   }

   public void mostrarMensaje(String mensaje){
    System.out.println("====="+mensaje);
   }

  public void cerrar(){
    scanner.close();
    System.out.println("Hasta luego, gracias por usar nuestro cajero");
}
}

