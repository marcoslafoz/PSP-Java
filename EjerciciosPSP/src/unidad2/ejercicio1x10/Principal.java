package unidad2.ejercicio1x10;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Cliente extends Thread {
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

class CuentaCompartida {
    private double totalCaja = 0;

    public synchronized void agregarCuenta(double cuenta) {
        totalCaja += cuenta;
    }

    public double obtenerTotalCaja() {
        return totalCaja;
    }
}

class Frutero extends Thread {
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

public class Principal {
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