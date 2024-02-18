package ejercicio1x10;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Frutero extends Thread {
    private CuentaCompartida cuentaCompartida;
    private long tiempoTotal = 0;
    private Semaphore semaforoAtencion;
    private Semaphore semaforoSalida;

    public Frutero(Semaphore semaforoAtencion, Semaphore semaforoSalida, CuentaCompartida cuentaCompartida) {
        this.semaforoAtencion = semaforoAtencion;
        this.semaforoSalida = semaforoSalida;
        this.cuentaCompartida = cuentaCompartida;
    }

    @Override
    public void run() {
        try {
            while (true) {
                
                semaforoSalida.acquire(); // Esperar a que el cliente salga de la tienda

                // Simular tiempo de espera aleatorio
                int tiempoEspera = generarTiempoEspera();
                Thread.sleep(tiempoEspera);

                // Obtener la cuenta del objeto compartido
                double cuenta = cuentaCompartida.obtenerTotalCaja();
               
                System.out.println("Cuenta generada: " + cuenta);

                // Actualizar totales
                tiempoTotal += tiempoEspera;
                System.out.println("_________________________________");
                semaforoAtencion.release(); // Indicar que está disponible para atender
            }

        } catch (InterruptedException e) {
            System.out.println("Frutería cerrada por falta de clientes. Tiempo total: " + tiempoTotal +
                    ". Total caja: " + cuentaCompartida.obtenerTotalCaja());
        }
    }

    private int generarTiempoEspera() {
        // Generar tiempo de espera entre 1 y 1.5 segundos
        Random rand = new Random();
        return rand.nextInt(501) + 1000; // Entre 1000 y 1500 ms
    }
}