package unidad2.ejercicio1x10;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Actividad1x10 {
    public static void main(String[] args) {
        Semaphore semaforoAtencion = new Semaphore(1);
        Semaphore semaforoSalida = new Semaphore(0);
        CuentaCompartida cuentaCompartida = new CuentaCompartida();

        Frutero frutero = new Frutero(semaforoAtencion, semaforoSalida, cuentaCompartida);
        frutero.start();

        // Crear un número aleatorio de clientes
        Random rand = new Random();
        int numClientes = rand.nextInt(10) + 1; // Entre 1 y 10 clientes

        Cliente[] clientes = new Cliente[numClientes];
        for (int i = 0; i < numClientes; i++) {
            clientes[i] = new Cliente(semaforoAtencion, semaforoSalida, cuentaCompartida);
            clientes[i].start();
        }

        try {
            // Esperar a que todos los clientes hayan finalizado
            for (Cliente cliente : clientes) {
                cliente.join();
            }

            // Interrumpir la ejecución del frutero
            frutero.interrupt();
            frutero.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}