package actividad1x01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjecutaComando {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java EjecutaComando <comando> [opciones]");
            System.exit(1);
        }

        // Crear un arreglo que contendrá el comando y sus opciones
        String[] command = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            command[i] = args[i];
        }

        try {
            // Crear un proceso hijo para ejecutar el comando
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // Esperar a que el proceso hijo termine
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("El comando se ejecutó correctamente.");
            } else {
                System.out.println("Error al ejecutar el comando. Código de salida: " + exitCode);
                // Leer los errores de la salida estándar del proceso hijo
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line;
                while ((line = errorReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al ejecutar el comando: " + e.getMessage());
        }
    }
}
