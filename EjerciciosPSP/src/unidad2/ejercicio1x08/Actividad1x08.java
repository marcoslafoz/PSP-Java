package unidad2.ejercicio1x08;

import java.util.Random;

class Barrera {
    private int[] plazas;
    private int plazasLibres;

    public Barrera(int numPlazas) {
        this.plazas = new int[numPlazas];
        this.plazasLibres = numPlazas;
    }

    public synchronized int entrar() {
        int plazaAsignada = -1;

        if (plazasLibres > 0) {
            for (int i = 0; i < plazas.length; i++) {
                if (plazas[i] == 0) {
                    plazaAsignada = i;
                    plazas[i] = (int) Thread.currentThread().getId();
                    plazasLibres--;
                    break;
                }
            }
        }

        return plazaAsignada;
    }

    public synchronized void salir(int plaza) {
        plazas[plaza] = 0;
        plazasLibres++;
    }

    public synchronized void mostrarEstado() {
        System.out.print("Parking: ");
        for (int i : plazas) {
            System.out.print("[" + i + "] ");
        }
        System.out.println();
    }
}

class Coche extends Thread {
    private int cocheId;
    private Barrera barrera;

    public Coche(int cocheId, Barrera barrera) {
        this.cocheId = cocheId;
        this.barrera = barrera;
    }

    @Override
    public void run() {
        System.out.println("Coche " + cocheId + " intenta entrar en parking");

        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int plaza = barrera.entrar();

        if (plaza != -1) {
            System.out.println("Parking: Coche " + cocheId + " aparcado en " + plaza);
            barrera.mostrarEstado();

            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            barrera.salir(plaza);
            System.out.println("Coche " + cocheId + " saliendo");
            barrera.mostrarEstado();
        } else {
            System.out.println("Coche " + cocheId + " esperando");
        }
    }
}

public class Actividad1x08 {
    private static final int NUMERO_SITIOS = 5;
    private static final int NUMERO_COCHES = 5;

    public static void main(String[] args) {
        Barrera barrera = new Barrera(NUMERO_SITIOS);

        Coche[] coches = new Coche[NUMERO_COCHES];
        for (int i = 0; i < NUMERO_COCHES; i++) {
            coches[i] = new Coche(i, barrera);
            coches[i].start();
        }
    }
}
