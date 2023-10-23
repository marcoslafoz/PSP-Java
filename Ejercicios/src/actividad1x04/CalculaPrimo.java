package actividad1x04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CalculaPrimo {
    public static void main(String[] args) throws IOException {
        BufferedReader entrada = null;

        try {
            entrada = new BufferedReader(new InputStreamReader(System.in));
            PrintStream salida = new PrintStream(System.out);
            String linea = entrada.readLine();
            int num = Integer.parseInt(linea);
            boolean primo = esPrimo(num);

            if (primo) {
                // Si el número es primo, muestra un mensaje indicando que es primo.
                salida.println("El numero " + num + " es primo");
                salida.flush();
            } else {
                // Si el número no es primo, muestra un mensaje y busca números primos menores que él.
                salida.println(num + " no es primo, números primos menores que " + num);
                salida.flush();

                // Itera a través de los números menores que el número dado y verifica si son primos.
                for (int i = 1; i < num; i++) {
                    primo = esPrimo(i);

                    if (primo) {
                        // Si es primo, muestra un mensaje indicando que es primo.
                        salida.println("El numero " + i + " es primo");
                        salida.flush();
                    }
                }
            }
        } finally {
            // No se realiza ninguna operación de cierre aquí, ya que no se abren recursos que necesiten ser cerrados.
        }
    }

    // Método para verificar si un número es primo.
    public static boolean esPrimo(int num) {
        int rep = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                rep++;
            }
        }
        if (rep < 2) {
            return true; // Devuelve verdadero si el número es primo
        }
        return false; // Devuelve falso si el número no es primo
    }
}
