package actividad1x04v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Hijo {

    public static void main(String[] args) throws IOException {
        // Declaración de variables
        BufferedReader entrada = null;
        PrintStream salida = null;

        try {
            // Lo recibe del padre
            entrada = new BufferedReader(new InputStreamReader(System.in));
            // Se manda al padre
            salida = new PrintStream(System.out);
            String linea = entrada.readLine();

            while (linea != null) {
                try {
                    int numprimo = Integer.parseInt(linea);

                    if (!esPrimo(numprimo)) {
                        // Enviar mensaje al padre indicando que el número no es primo
                        salida.println("El número " + numprimo + " no es primo");
                        salida.flush();
                        salida.println("Primos menores que " + numprimo + ": ");
                        salida.flush();

                        for (int x = numprimo; x > 0; x--) {
                            if (esPrimo(x)) {
                                // Enviar los números primos menores al padre
                                salida.println(x);
                                salida.flush();
                            }
                        }
                    } else {
                        // Enviar mensaje al padre indicando que el número es primo
                        salida.println("El número " + numprimo + " es primo");
                        salida.flush();
                    }
                    // Indicar al padre que ha terminado este proceso hijo
                    salida.println("finproceso");
                    salida.flush();
                } catch (NumberFormatException e) {
                    // Enviar mensaje de dato no válido al padre
                    salida.println("dato_invalido");
                    salida.flush();
                }
                // Volvemos a preguntar al Padre
                linea = entrada.readLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            entrada.close();
            salida.close();
        }
    }

    // Función que verifica si un número es primo
    public static boolean esPrimo(int n) {
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
