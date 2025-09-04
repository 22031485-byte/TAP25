package Controller;

import Models.CajeroModel;
import Views.CajeroView;

public class CajeroController {
   private CajeroModel model;
   private CajeroView view;
   private boolean sistemaActivo;

/**
    * Crea un controlador para el cajero con el modelo y la vista especificados.
    *
    * @param model el modelo que gestiona los datos de las cuentas
    * @param view  la vista para interactuar con el usuario
    */

   public CajeroController(CajeroModel model, CajeroView view){
    this.model = model;
    this.view = view;
    this.sistemaActivo = true;
   }

   /**
    * Inicia el flujo principal del sistema: muestra bienvenida, autentica al usuario
    * y muestra el menú principal hasta que se salga del sistema.
    */

   public void iniciarSistema(){
    view.mostrarBienvenida();
    while (sistemaActivo) {
        if (autenticarUsuario()) {
            ejecutarMenuPrincipal();
        }else{
            view.mostrarMensaje("Credenciales incorrectas ");
        }
    }
    view.mostrarMensaje("Gracias por usar nuestro cajero ");
   }

   /**
    * Solicita credenciales a la vista y las valida contra el modelo.
    *
    * @return {@code true} si la autenticación fue exitosa; {@code false} en caso contrario
    */

   private boolean autenticarUsuario(){
    String numeroCuenta = view.solicitarNumeroCuenta();
    String pin = view.solcitarPin();
    return model.autenticar(numeroCuenta, pin);
   }

   /**
    * Muestra el menú principal y procesa opciones en un ciclo hasta que el usuario
    * cierre la sesión o salga del sistema.
    */

   private void ejecutarMenuPrincipal(){
     boolean sessionActiva = true;
     while (sessionActiva) {
        view.mostrarMenuPrincipal(model.getCuentaActual().getTitular());
        int opcion = view.leerOpcion();
        switch (opcion) {
            case 1:
            consultarSaldo();
                
            break;

            case 2:
            this.realizarRetiro();
            break;

            case 3:
            this.realizarDeposito();
            break;

            case 4:
            this.realizarTransferencia();
            break;

            case 5:
            this.realizarCambioPin();
            break;

            case 6:
            this.mostrarPin();
            case 7:
                this.cerrarSistema();
                sessionActiva = false;   // salir del menú
                sistemaActivo = false;   // apagar el sistema completo
                break;

            default:
                view.mostrarMensaje("Opción inválida, intenta de nuevo");
        }
     }
 }

 /**
  * Muestra el saldo de la cuenta actual.
  */

 public void consultarSaldo(){
    double saldo = model.consultarSaldo();
    view.mostrarSaldo(saldo);
 }

public void mostrarPin(){
    String pin = model.consultarPIN();  // usar String, no double
    if(pin != null){
        view.mostrarMensaje("Tu PIN actual es: " + pin);
    } else {
        view.mostrarMensaje("No hay cuenta activa.");
    }
}
  /**
  * Ejecuta la operación de retiro solicitando la cantidad a la vista y usando el modelo.
  */

 public void realizarRetiro(){
    double cantidad = view.solicitarCantidad("Retirar");
    if (cantidad <= 0) {
        view.mostrarMensaje("Error en la cantidad ");
        return;
    }

    if (model.realizarRetiro(cantidad)) {
        view.mostrarMensaje("Retiro exitoso de "+cantidad);
    }else{
        view.mostrarMensaje("Fondos insuficientes ");
    }
  }

  /**
  * Ejecuta la operación de depósito solicitando la cantidad a la vista y usando el modelo.
  */

 public void realizarDeposito(){
    double cantidad = view.solicitarCantidad("Deposito ");
    if (cantidad <= 0) {
        view.mostrarMensaje("Error en la cantidad ");
        return;
    }
    if (model.realizarDeposito(cantidad)) {
        view.mostrarMensaje("Deposito exitoso por la cantidad de "+cantidad);
    }else{
        view.mostrarMensaje("Fondos insuficientes ");
    }
 }

 /**
  * Ejecuta la operación de depósito solicitando la cantidad a la vista y usando el modelo.
  */

  public void realizarTransferencia(){
    String cuentaDestino = view.solicitarCuentaDestino();

    if (!model.cuentaExistente(cuentaDestino)) {
        view.mostrarMensaje("La cuenta destino no existe");
        return;
    }

    String cuentaOrigen = model.getCuentaActual().getNumeroCuenta();
    if (cuentaOrigen.equals(cuentaDestino)) {
        view.mostrarMensaje("No puedes transferir a la MISMA cuenta");
        return;
    }

    double cantidad = view.solicitarCantidad("transferir");
    if (cantidad <= 0) {
        view.mostrarMensaje("Error en la cantidad ");
        return;
    }


    boolean ok = model.transferir(cuentaDestino, cantidad);
    if (ok) {
        view.mostrarMensaje("Transferencia exitosa de $" + cantidad + " a la cuenta " + cuentaDestino);
    } else {
        view.mostrarMensaje("No se pudo completar la transferencia (fondos insuficientes o datos invalidos)");
    }
   }

   /**
    * Realiza el flujo de cambio de PIN: pide PIN actual, nuevo y confirmación,
    * valida y delega la actualización al modelo.
    */
   
public void realizarCambioPin(){
    String pinActual = view.solcitarPin();
    String nuevo = view.solicitarNuevoPin();
    String confirm = view.solicitarConfirmacionPin();

    if (!nuevo.equals(confirm)) {
        view.mostrarMensaje("El nuevo PIN y la confirmación no coinciden.");
        return;
    }

    if (!nuevo.matches("\\d{4}")) {
        view.mostrarMensaje("El PIN debe contener exactamente 4 dígitos.");
        return;
    }

    boolean actualizado = model.cambiarPinActual(pinActual, nuevo);
    if (actualizado) {
        view.mostrarMensaje("PIN actualizado correctamente. Ahora puedes confirmar en 'Ver PIN'.");
    } else {
        view.mostrarMensaje("Error: PIN actual incorrecto o no se pudo actualizar.");
    }
}


 /**
  * Pide al modelo y la vista cerrar el sistema (cierres, recursos, etc.).
  */
 
 public void cerrarSistema(){
    view.cerrar();
 }
}
