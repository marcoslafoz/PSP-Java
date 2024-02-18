package ejercicio1x10;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cliente extends Thread {
    private static int contadorClientes = 1;
    private int numeroCliente;
    private Semaphore semaforoAtencion;
    private Semaphore semaforoSalida;
    private CuentaCompartida cuentaCompartida;

    public Cliente(Semaphore semaforoAtencion, Semaphore semaforoSalida, CuentaCompartida cuentaCompartida) {
        this.numeroCliente = contadorClientes++;
        this.semaforoAtencion = semaforoAtencion;
        this.semaforoSalida = semaforoSalida;
        this.cuentaCompartida = cuentaCompartida;
    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente " + numeroCliente + " espera su turno");
            semaforoAtencion.acquire(); // Esperar para ser atendido
            System.out.println("Cliente " + numeroCliente + " es atendido");

            // Simular tiempo de atención aleatorio
            int tiempoAtencion = generarTiempoAtencion();
            Thread.sleep(tiempoAtencion);

            // Simular importe a pagar aleatorio
            double cuenta = generarCuenta();
            System.out.println("Tiempo de atención: " + tiempoAtencion + " ms");
            System.out.println("Cuenta: " + cuenta);

            // Sincronizar la cuenta con el frutero
            cuentaCompartida.agregarCuenta(cuenta);

            semaforoSalida.release(); // Cliente puede salir de la tienda
            System.out.println("Cliente " + numeroCliente + " sale de la tienda");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int generarTiempoAtencion() {
        // Generar tiempo de atención entre 1 y 2.5 segundos
        Random rand = new Random();
        return rand.nextInt(1501) + 1000; // Entre 1000 y 2500 ms
    }

    private double generarCuenta() {
        // Generar importe aleatorio
        Random rand = new Random();
        return rand.nextDouble() * 5; // Entre 0 y 5
    }
}