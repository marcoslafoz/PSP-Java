package ejercicio1x04;

public class Productor extends Thread {
    private Compartido dato;  // Referencia al objeto compartido
    private int num;          // Número identificador del productor

    // Constructor que recibe el objeto compartido y el número identificador
    public Productor(Compartido c, int n) {
        this.dato = c;
        this.num = n;
    }

    // Método que se ejecuta cuando se inicia el hilo del productor
    public void run() {
        // Itera para producir 5 valores
        for (int i = 0; i < 5; i++) {
            dato.set(i);  // Establece un valor en el objeto compartido
            System.out.println(i + " => Productor: " + num + ", produce: " + i);
        }

        try {
            sleep(100);  // Simula algún trabajo adicional del productor después de producir los valores
        } catch (InterruptedException e) {
            System.err.println(e.toString());
        }
    }
}
