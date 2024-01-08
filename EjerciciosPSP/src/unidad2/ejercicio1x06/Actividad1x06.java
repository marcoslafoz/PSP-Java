package unidad2.ejercicio1x06;

public class Actividad1x06 {
    public static void main(String[] args) {
        // Crear un monitor compartido con un arreglo de tamaño 100
        MonitorArray monitor = new MonitorArray(100);

        // Crear instancias de Escritor y Lector con el monitor compartido
        Escritor escritor = new Escritor(monitor);
        Lector lector = new Lector(monitor);

        // Iniciar los hilos del escritor y del lector
        escritor.start();
        lector.start();
    }
}
