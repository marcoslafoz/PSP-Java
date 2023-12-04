package unidad2.ejercicio1x08;

import java.util.Random;

class Barrera {
    private Coche[] coches;
    private int plazasLibres;

    public Barrera(int numPlazas) {
        this.coches = new Coche[numPlazas];
        this.plazasLibres = numPlazas;
    }

    public synchronized int entrar(Coche coche) {
        int plazaAsignada = -1;

        if (plazasLibres > 0) {
            for (int i = 0; i < coches.length; i++) {
                if (coches[i] == null) {
                    plazaAsignada = i;
                    coches[i] = coche;
                    plazasLibres--;
                    break;
                }
            }
        }

        return plazaAsignada;
    }

    public synchronized void salir(int plaza) {
        coches[plaza] = null;
        plazasLibres++;
    }

    public synchronized void mostrarEstado() {
        System.out.print("Parking: ");
        for (int i = 0; i < coches.length; i++) {
            if (coches[i] != null) {
                System.out.print("[C-" + coches[i].getCocheId() + "] ");
            } else {
                System.out.print("[0] ");
            }
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

        int plaza = barrera.entrar(this);

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

    public int getCocheId() {
        return cocheId;
    }
}

public class Actividad1x08 {
    private static final int NUMERO_SITIOS = 5;
    private static final int NUMERO_COCHES = 20;

    public static void main(String[] args) {
        Barrera barrera = new Barrera(NUMERO_SITIOS);

        Coche[] coches = new Coche[NUMERO_COCHES];
        for (int i = 0; i < NUMERO_COCHES; i++) {
            coches[i] = new Coche(i, barrera);
            coches[i].start();
        }
    }
}
