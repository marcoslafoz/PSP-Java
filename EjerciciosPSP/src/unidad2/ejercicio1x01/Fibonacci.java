package unidad2.ejercicio1x01;

public class Fibonacci {
    public static void FibonacciSequence(int n, String name) {
        if (n <= 0) {
            System.out.println("El número debe ser mayor que cero.");
            return;
        }

        String acumulador = "";
        
        int a = 0, b = 1;
        acumulador += ("Hilo: " + name + ", Secuencia de Fibonacci hasta " + n + ": ");
        //System.out.print(a);

        while (b <= n) {
        	 acumulador += (", " + b);
            int temp = a + b;
            a = b;
            b = temp;
        }

        System.out.println(acumulador);
    }
}
