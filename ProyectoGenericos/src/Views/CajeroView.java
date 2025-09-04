package Views;

import java.util.Scanner;

/**
 * Clase responsable de interacción con el usuario por consola.
 * Provee métodos para mostrar menús, solicitar datos y mostrar resultados.
 * No contiene lógica de negocio.
 * 
 * @author Leonardo
 * @version 1.0
 * @since 2025-09-04
 */

public class CajeroView {
   private Scanner scanner;
   public CajeroView(){
     scanner = new Scanner(System.in);
   }

      /** Muestra la bienvenida al usuario */

   public void mostrarBienvenida(){
    System.out.println("=============================");
    System.out.println("Bienvenido al cajero automatico del banco del bienestar");
    System.out.println("=============================");
   }

    /**
    * Solicita al usuario el número de cuenta.
    * @return el número de cuenta ingresado por el usuario
    */

   public String solicitarNumeroCuenta(){
    System.out.println("Ingresa tu numero de cuenta: ");
    return scanner.nextLine();
   }

     /**
    * Solicita el PIN al usuario.
    * @return el PIN ingresado
    */

   public String solcitarPin(){
    System.out.println("Ingresa tu pin: ");
    return scanner.nextLine();
   }

    /**
    * Muestra el menú principal con las opciones disponibles.
    * @param titular nombre del titular para mostrar en el saludo
    */

   public void mostrarMenuPrincipal(String titular){
    System.out.println("=============================");
    System.out.println("Bienvenid@ "+titular);
    System.out.println("=============================");
    System.out.println("1.- Consultar saldo ");
    System.out.println("2.- Retirar ");
    System.out.println("3.- Depositar ");
    System.out.println("4.-Tranferencia");
    System.out.println("5.-Cambiar PIN");
    System.out.println("6.-Consultar NIP");
    System.out.println("7.- Salir ");
   }

   /**
    * Lee una opción numérica del usuario.
    * @return la opción ingresada o -1 si el valor no es numérico
    */

   public int leerOpcion(){
    try{
        return Integer.parseInt(scanner.nextLine());
    }catch(NumberFormatException e){
        return -1;
    }
   }

   /**
    * Solicita el nuevo PIN al usuario.
    * @return nuevo PIN ingresado
    */

   public String solicitarNuevoPin(){
    System.out.println("Ingresa el NUEVO pin ");
    return scanner.nextLine();
}

 /**
    * Solicita la confirmación del nuevo PIN.
    * @return la confirmación del nuevo PIN
    */

public String solicitarConfirmacionPin(){
    System.out.println("Confirma el NUEVO pin: ");
    return scanner.nextLine();
}

/**
    * Muestra el saldo en consola.
    * @param saldo saldo a mostrar
    */

   public void mostrarSaldo(double saldo){
    System.out.println("=============================");
    System.out.println("Tu saldo actual es: $"+saldo);
    System.out.println("=============================");
   }

   public void mostrarPin(double pin){
    System.out.println("=============================");
    System.out.println("Tu PIN es :" +pin);
    System.out.println("=============================");
   }

     /**
    * Solicita una cantidad para la operación indicada.
    * @param operacion texto que describe la operación (ej. "retirar")
    * @return la cantidad ingresada o -1 en caso de error de formato
    */

   public double solicitarCantidad(String operacion){
    System.out.println("Ingresa la cantidad a "+operacion+":");
    try{
        return Double.parseDouble(scanner.nextLine());
    }catch (NumberFormatException e){
        return -1;
    }
   }

     /**
    * Muestra un mensaje simple por consola.
    * @param mensaje texto a mostrar
    */

   public void mostrarMensaje(String mensaje){
    System.out.println("====="+mensaje);
   }

    /**
    * Solicita número de cuenta destino para transferencias.
    * @return cuenta destino ingresada por el usuario
    */

   public String solicitarCuentaDestino(){
    System.out.println("Ingresa el numero de la cuenta a la que quieres transferir");
    return scanner.nextLine();

   }

      /** Cierra recursos (Scanner) y muestra despedida. */

  public void cerrar(){
    scanner.close();
    System.out.println("Hasta luego, gracias por usar nuestro cajero");
}
}

