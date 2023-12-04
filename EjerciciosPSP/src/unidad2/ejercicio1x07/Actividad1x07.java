package unidad2.ejercicio1x07;

import java.util.Random;

class Resultados {
    private static int ganancias = 0;
    private static int clientesAtendidos = 0;

    public static synchronized void agregarGanancias(int cantidad) {
        ganancias += cantidad;
    }

    public static synchronized void clienteAtendido() {
        clientesAtendidos++;
    }

    public static void mostrarResumen() {
        System.out.println("Supermercado cerrado.");
        System.out.println("Ganancias: " + ganancias);
        System.out.println("Clientes atendidos: " + clientesAtendidos);
    }
}

class Nodo {
    int clienteId;
    Nodo siguiente;

    public Nodo(int clienteId) {
        this.clienteId = clienteId;
        this.siguiente = null;
    }
}

class Caja {
    private Nodo frente;
    private Nodo fin;

    public synchronized void agregarCliente(int clienteId) {
        Nodo nuevoCliente = new Nodo(clienteId);
        if (frente == null) {
            frente = nuevoCliente;
            fin = nuevoCliente;
        } else {
            fin.siguiente = nuevoCliente;
            fin = nuevoCliente;
        }
        System.out.println("Cliente " + clienteId + " en cola con " + obtenerCola());
    }

    public synchronized int atenderCliente() {
        if (frente == null) {
            return -1; // No hay clientes en la cola
        }
        int clienteId = frente.clienteId;
        frente = frente.siguiente;
        System.out.println("Cliente " + clienteId + " atendido");
        Resultados.clienteAtendido();
        return clienteId;
    }

    public synchronized String obtenerCola() {
        StringBuilder cola = new StringBuilder();
        Nodo actual = frente;
        while (actual != null) {
            cola.append(actual.clienteId).append("-");
            actual = actual.siguiente;
        }
        return cola.toString();
    }
}

class Cliente extends Thread {
    private int clienteId;
    private Caja[] cajas;

    public Cliente(int clienteId, Caja[] cajas) {
        this.clienteId = clienteId;
        this.cajas = cajas;
    }

    @Override
    public void run() {
        System.out.println("Cliente " + clienteId + " realizando compra");

        // Simula el tiempo que tarda en comprar
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Selecciona aleatoriamente una caja
        int numeroCaja = new Random().nextInt(cajas.length);
        Caja caja = cajas[numeroCaja];

        System.out.println("Cliente " + clienteId + " en cola con " + caja.obtenerCola());

        // Se pone en la cola de la caja
        caja.agregarCliente(clienteId);

        // Simula el tiempo que tarda en ser atendido
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Cliente es atendido y realiza el pago
        int pago = new Random().nextInt(50) + 1; // Valor aleatorio entre 1 y 50
        System.out.println("Cliente " + clienteId + " ha pagado: " + pago);
        Resultados.agregarGanancias(pago);

        // Muestra el contenido de la caja después de atender al cliente
        System.out.println("Cliente " + clienteId + " atendido. Cola actual en caja " + numeroCaja + ": " + caja.obtenerCola());
    }
}

public class Actividad1x07 {
    private static final int NUMERO_CLIENTES = 10;
    private static final int NUMERO_CAJAS = 3;

    public static void main(String[] args) {
        Caja[] cajas = new Caja[NUMERO_CAJAS];
        for (int i = 0; i < NUMERO_CAJAS; i++) {
            cajas[i] = new Caja();
        }

        Cliente[] clientes = new Cliente[NUMERO_CLIENTES];
        for (int i = 0; i < NUMERO_CLIENTES; i++) {
            clientes[i] = new Cliente(i, cajas);
            clientes[i].start();
        }

        // Espera a que todos los clientes terminen
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Muestra el resumen
        Resultados.mostrarResumen();
    }
}
