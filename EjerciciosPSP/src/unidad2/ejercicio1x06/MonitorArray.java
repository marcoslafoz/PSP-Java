package unidad2.ejercicio1x06;

public class MonitorArray {
    private int[] array;  // Arreglo compartido

    // Constructor que inicializa el arreglo con un tamaño específico
    public MonitorArray(int size) {
        this.array = new int[size];
    }

    // Método sincronizado para escribir un valor en todas las posiciones del arreglo
    public synchronized void escribir(int valor) {
        // Itera sobre el arreglo y establece el mismo valor en todas las posiciones
        for (int i = 0; i < array.length; i++) {
            array[i] = valor;
        }
        // Muestra un mensaje indicando que se está escribiendo el valor y notifica a los lectores
        System.out.println("Escritor: Escribiendo el valor " + valor);
        notifyAll();
    }

    // Método sincronizado para leer los valores del arreglo y verificar si son todos iguales
    public synchronized boolean leer() {
        int valor = array[0];

        // Itera sobre el arreglo para comparar todos los valores con el primero
        for (int i = 1; i < array.length; i++) {
            if (array[i] != valor) {
                // Muestra un mensaje si encuentra valores diferentes y retorna false
                System.out.println("Lector: Error, los valores no son iguales");
                return false;
            }
        }

        // Muestra un mensaje si todos los valores son iguales y retorna true
        System.out.println("Lector: Correcto, todos los valores son iguales (" + valor + ")");
        return true;
    }
}
