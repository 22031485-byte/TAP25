import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Este archivo demuestra el patrón de diseño Strategy para aplicar descuentos.
 */
public class Context {

    // --- 1. La Interfaz (Estrategia) ---
    /**
     * Esta es la interfaz que define el contrato. Todas las estrategias de descuento deben
     * implementar este método para calcular el precio final.
     */
    public interface EstrategiaDescuento {
        BigDecimal aplicarDescuento(BigDecimal monto);
    }

    // --- 2. Las Estrategias Concretas ---
    /**
     * Estas son las implementaciones específicas de la interfaz. Cada clase es una estrategia.
     */
    
    // Estrategia para clientes regulares: no hay descuento.
    public static class DescuentoClienteRegular implements EstrategiaDescuento {
        @Override
        public BigDecimal aplicarDescuento(BigDecimal monto) {
            System.out.println("Aplicando descuento para cliente regular: 0%");
            return monto;
        }
    }

    // Estrategia para clientes VIP: un 15% de descuento.
    public static class DescuentoClienteVIP implements EstrategiaDescuento {
        private static final BigDecimal TASA_DESCUENTO = new BigDecimal("0.15");

        @Override
        public BigDecimal aplicarDescuento(BigDecimal monto) {
            BigDecimal montoDescuento = monto.multiply(TASA_DESCUENTO);
            BigDecimal montoFinal = monto.subtract(montoDescuento).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Aplicando descuento para cliente VIP: 15%");
            return montoFinal;
        }
    }

    // Estrategia para clientes frecuentes: un 5% de descuento.
    public static class DescuentoClienteFrecuente implements EstrategiaDescuento {
        private static final BigDecimal TASA_DESCUENTO = new BigDecimal("0.05");

        @Override
        public BigDecimal aplicarDescuento(BigDecimal monto) {
            BigDecimal montoDescuento = monto.multiply(TASA_DESCUENTO);
            BigDecimal montoFinal = monto.subtract(montoDescuento).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Aplicando descuento para cliente frecuente: 5%");
            return montoFinal;
        }
    }

    // --- 3. El Contexto ---
    /**
     * Esta clase usa una estrategia para realizar su tarea. No sabe cómo se calcula el descuento,
     * solo sabe que tiene un objeto (una "estrategia") que puede hacerlo.
     */
    public static class CarritoDeCompras {
        private EstrategiaDescuento estrategiaDescuento;

        public void establecerEstrategia(EstrategiaDescuento estrategia) {
            this.estrategiaDescuento = estrategia;
        }

        public BigDecimal pagar(BigDecimal monto) {
            if (this.estrategiaDescuento == null) {
                throw new IllegalStateException("No se ha definido una estrategia de descuento.");
            }
            // El carrito de compras delega la acción a la estrategia que se le asignó.
            return this.estrategiaDescuento.aplicarDescuento(monto);
        }
    }

    // --- 4. El Cliente ---
    /**
     * El método main actúa como el "cliente" que usa todo el sistema.
     * Aquí es donde elegimos la estrategia que queremos usar en cada momento.
     */
    public static void main(String[] args) {
        CarritoDeCompras carrito = new CarritoDeCompras();
        BigDecimal montoOriginal = new BigDecimal("100.00");

        System.out.println("Costo original del producto: $" + montoOriginal);
        System.out.println("----------------------------------------");

        // Caso 1: El cliente es regular. Le asignamos la estrategia "regular".
        carrito.establecerEstrategia(new DescuentoClienteRegular());
        BigDecimal montoFinalRegular = carrito.pagar(montoOriginal);
        System.out.println("Monto final para cliente regular: $" + montoFinalRegular);

        System.out.println("----------------------------------------");

        // Caso 2: El cliente es VIP. Cambiamos la estrategia al vuelo.
        carrito.establecerEstrategia(new DescuentoClienteVIP());
        BigDecimal montoFinalVIP = carrito.pagar(montoOriginal);
        System.out.println("Monto final para cliente VIP: $" + montoFinalVIP);

        System.out.println("----------------------------------------");

        // Caso 3: El cliente es frecuente. De nuevo, cambiamos la estrategia.
        carrito.establecerEstrategia(new DescuentoClienteFrecuente());
        BigDecimal montoFinalFrecuente = carrito.pagar(montoOriginal);
        System.out.println("Monto final para cliente frecuente: $" + montoFinalFrecuente);
    }
}