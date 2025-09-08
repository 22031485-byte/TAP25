package EjemploPractica2TAP;

class Caja<T> {
    private T contenido; 

    public void guardar(T nuevoContenido) {
        this.contenido = nuevoContenido;
    }


    public T sacar() {
        return contenido;
    }
}

public class Genericos {
    public static void main(String[] args) {

        Caja<String> cajaDeJuguete = new Caja<>();
        cajaDeJuguete.guardar("Muñeco");
        System.out.println("La caja de juguete tiene: " + cajaDeJuguete.sacar());

        Caja<Integer> cajaDeNumero = new Caja<>();
        cajaDeNumero.guardar(123);
        System.out.println("La caja de número tiene: " + cajaDeNumero.sacar());

        Caja<Boolean> cajaDePeluche = new Caja<>();
        cajaDePeluche.guardar(true);
        System.out.println("¿La caja tiene peluche? " + cajaDePeluche.sacar());
    }
}
