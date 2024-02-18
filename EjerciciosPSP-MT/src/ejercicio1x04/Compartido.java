package ejercicio1x04;

public class Compartido {
    private int numero;         // Variable para almacenar el número compartido
    private boolean disponible = false; // Indica si el número está disponible o no

    // Método para obtener el número compartido
    public synchronized int get() {
        // Mientras el número no esté disponible, espera
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.toString());
            }
        }

        disponible = false;  // Marca el número como no disponible
        notifyAll();         // Notifica a todos los hilos en espera que pueden intentar adquirir el número
        return numero;       // Devuelve el número
    }

    // Método para establecer el número compartido
    public synchronized void set(int n) {
        // Mientras el número esté disponible, espera
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.toString());
            }
        }

        numero = n;          // Establece el número
        disponible = true;   // Marca el número como disponible
        notifyAll();         // Notifica a todos los hilos en espera que pueden intentar adquirir el número
    }
}
