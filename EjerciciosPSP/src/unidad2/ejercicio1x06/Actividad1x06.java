package unidad2.ejercicio1x06;

class MonitorArray {
    private int[] array;

    public MonitorArray(int size) {
        this.array = new int[size];
    }

    public synchronized void escribir(int valor) {
        for (int i = 0; i < array.length; i++) {
            array[i] = valor;
        }
        System.out.println("Escritor: Escribiendo el valor " + valor);
        notifyAll();
    }

    public synchronized boolean leer() {
        int valor = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] != valor) {
                System.out.println("Lector: Error, los valores no son iguales");
                return false;
            }
        }
        System.out.println("Lector: Correcto, todos los valores son iguales (" + valor + ")");
        return true;
    }
}

class Escritor extends Thread {
    private MonitorArray monitor;

    public Escritor(MonitorArray monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        int valor = 1;
        while (true) {
            monitor.escribir(valor++);
            try {
                Thread.sleep(100); // Puedes ajustar el tiempo de espera entre escrituras
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Lector extends Thread {
    private MonitorArray monitor;

    public Lector(MonitorArray monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            monitor.leer();
            try {
                Thread.sleep(100); // Puedes ajustar el tiempo de espera entre lecturas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Actividad1x06 {
    public static void main(String[] args) {
        MonitorArray monitor = new MonitorArray(100);

        Escritor escritor = new Escritor(monitor);
        Lector lector = new Lector(monitor);

        escritor.start();
        lector.start();
    }
}
