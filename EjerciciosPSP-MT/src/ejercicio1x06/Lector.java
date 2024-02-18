package ejercicio1x06;

public class Lector extends Thread {
    private MonitorArray monitor;  // Referencia al monitor compartido

    // Constructor que recibe el monitor
    public Lector(MonitorArray monitor) {
        this.monitor = monitor;
    }

    // Método que se ejecuta cuando se inicia el hilo del lector
    @Override
    public void run() {
        while (true) {  // Bucle infinito para lecturas continuas
            monitor.leer();  // Llamada al método de lectura del monitor
            try {
                Thread.sleep(100); // Puedes ajustar el tiempo de espera entre lecturas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
