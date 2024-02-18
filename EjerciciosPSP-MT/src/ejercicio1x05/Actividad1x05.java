package ejercicio1x05;

import java.util.concurrent.Semaphore;

class Saludo extends Thread {
    private static int contador = 1;
    private int id;
    private Semaphore semaforo;

    public Saludo(Semaphore semaforo) {
        this.id = contador++;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            semaforo.acquire(); // Adquiere el semáforo, bloqueando si es necesario
            System.out.println("Hola desde el hilo " + id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release(); // Libera el semáforo
        }
    }
}

public class Actividad1x05 {
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(1); // Semáforo con un permiso

        Saludo saludo1 = new Saludo(semaforo);
        Saludo saludo2 = new Saludo(semaforo);

        saludo2.start(); // Inicia el segundo hilo
        saludo1.start(); // Inicia el primer hilo

        // Espera a que ambos hilos terminen
        try {
            saludo2.join();
            saludo1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
