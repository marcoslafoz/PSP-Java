package unidad2.ejercicio1x06;

public class Escritor extends Thread {
    private MonitorArray monitor;  // Referencia al monitor compartido

    // Constructor que recibe el monitor
    public Escritor(MonitorArray monitor) {
        this.monitor = monitor;
    }

    // Método que se ejecuta cuando se inicia el hilo del escritor
    @Override
    public void run() {
        int valor = 1;  // Valor inicial
        while (true) {  // Bucle infinito para escrituras continuas
            monitor.escribir(valor++);  // Llamada al método de escritura del monitor
            try {
                Thread.sleep(100); // Puedes ajustar el tiempo de espera entre escrituras
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
