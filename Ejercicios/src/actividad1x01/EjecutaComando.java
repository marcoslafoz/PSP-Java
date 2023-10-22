/**
 * Programa que ejecutara un comando como proceso hijo
 * Ej programs arguments: cmd /c calc
 */

package actividad1x01;

import java.io.IOException;

public class EjecutaComando {
    public static void main(String[] args) {
        
    	//Si no hay argumentos de ejecuccion mostrara un aviso y cerrara el programa
    	if (args.length == 0) {
            System.out.println("Uso: java EjecutaComando <comando> [opciones]");
            System.exit(1);
        }

        // Crear un vector que contendrá el comando y sus opciones
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
               
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al ejecutar el comando: " + e.getMessage());
        }
    }
}
