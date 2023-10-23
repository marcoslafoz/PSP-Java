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
            File directorio = new File("bin");
            Runtime runtime = Runtime.getRuntime();

            Process hijo = null;

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escribe un numero primo");
            linea = teclado.readLine();

            while (!linea.equals("fin")) {
                // Iniciar el proceso hijo
                hijo = runtime.exec("java actividad1x05.Hijo", null, new File("bin"));

                entradaHijo = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
                salidaHijo = new PrintStream(hijo.getOutputStream());

                salidaHijo.println(linea);
                salidaHijo.flush(); // Asegura que los datos se han enviado
                numero = entradaHijo.readLine();

                while (!numero.equals("finproceso")) {
                    // Mostrar la respuesta del proceso hijo
                    System.out.println(numero);
                    numero = entradaHijo.readLine();
                }

                System.out.println("Escribe algo ");
                // Vuelve a preguntar
                linea = teclado.readLine();
            }

            hijo.destroy();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (entradaHijo != null) {
                    entradaHijo.close();
                }
                if (salidaHijo != null) {
                    salidaHijo.close();
                }
                System.out.println("Fin del programa");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
