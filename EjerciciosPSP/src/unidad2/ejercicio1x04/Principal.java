package unidad2.ejercicio1x04;

public class Principal {

    public static void main(String[] args) {
        // Crear objeto compartido
        Compartido dato = new Compartido();

        // Crear productor y consumidor con el objeto compartido y números identificadores
        Productor p = new Productor(dato, 1);
        Consumidor c = new Consumidor(dato, 1);

        // Iniciar los hilos del productor y del consumidor
        p.start();
        c.start();
    }
}
