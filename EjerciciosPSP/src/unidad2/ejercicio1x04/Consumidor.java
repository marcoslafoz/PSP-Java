package unidad2.ejercicio1x04;

public class Consumidor extends Thread {
    private Compartido dato;  // Referencia al objeto compartido
    private int num;          // Número identificador del consumidor

    // Constructor que recibe el objeto compartido y el número identificador
    public Consumidor(Compartido c, int n) {
        this.dato = c;
        this.num = n;
    }

    // Método que se ejecuta cuando se inicia el hilo del consumidor
    public void run() {
        int valor = 0;

        // Itera para consumir 5 valores
        for (int i = 0; i < 5; i++) {
            valor = dato.get();  // Obtiene un valor del objeto compartido
            System.out.println(i + " => Consumidor: " + num + ", consume: " + valor);
        }
    }
}
