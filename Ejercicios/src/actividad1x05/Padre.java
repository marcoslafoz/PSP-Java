package actividad1x05;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Padre {

    public static void main(String[] args) {
        // Declaración de variables
        BufferedReader entradaHijo = null;
        PrintStream salidaHijo = null;
        String numero, linea;

        try {
            // Configuración del directorio donde se encuentra el proceso hijo
            File directorioBin = new File("bin");
            Runtime runtime = Runtime.getRuntime();

            Process hijo = null;

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escribe un numero primo");
            linea = teclado.readLine();

            while (!linea.equals("fin")) {
                // Iniciar el proceso hijo
                hijo = runtime.exec("java actividad1x05.Hijo", null, directorioBin);

                entradaHijo = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
                salidaHijo = new PrintStream(hijo.getOutputStream());

                salidaHijo.println(linea);
                salidaHijo.flush(); // Asegura que los datos se han enviado
                numero = entradaHijo.readLine();

                while (!numero.equals("finproceso")) {
                    // Mostrar la respuesta del proceso hijo
                    if (numero.equals("dato_invalido")) {
                        System.out.println("Dato no válido");
                    } else {
                        System.out.println(numero);
                    }
                    numero = entradaHijo.readLine();
                }

                System.out.println("Escribe algo ");
                // Vuelve a preguntar
                linea = teclado.readLine();
            }

            hijo.waitFor(); // Espera a que el proceso hijo termine

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Error al esperar al proceso hijo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (entradaHijo != null) {
                    entradaHijo.close();
                }
                if (salidaHijo != null) {
                    salidaHijo.close();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
