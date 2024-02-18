package ejercicio1x09;

class Corredor extends Thread {
    private int corredorId;
    private Corredor siguienteCorredor;

    public Corredor(int corredorId, Corredor siguienteCorredor) {
        this.corredorId = corredorId;
        this.siguienteCorredor = siguienteCorredor;
    }

    @Override
    public void run() {
        if (siguienteCorredor != null) {
            System.out.println("Corredor " + corredorId + " comienza . . .");
            try {
                Thread.sleep(1000); // Simula el tiempo que lleva correr
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Paso el testigo al hilo " + siguienteCorredor.corredorId);
            siguienteCorredor.start();
            try {
                siguienteCorredor.join(); // Espera a que el siguiente corredor termine
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Terminé!");
        }
    }
}

public class Actividad1x09 {
    public static void main(String[] args) {
        Corredor corredor1 = new Corredor(1, null);
        Corredor corredor2 = new Corredor(2, corredor1);
        Corredor corredor3 = new Corredor(3, corredor2);
        Corredor corredor4 = new Corredor(4, corredor3);

        System.out.println("Todos los hilos creados.");
        System.out.println("Comienza la carrera!");

        try {
            Thread.sleep(1000); // Agrega un pequeño retraso para asegurar que el primer mensaje se imprima primero
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        corredor1.start();

        try {
            corredor4.join(); // Espera a que el último corredor termine
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Todos los hilos terminaron.");
    }
}
