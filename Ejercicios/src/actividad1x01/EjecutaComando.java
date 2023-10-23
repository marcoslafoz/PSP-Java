package actividad1x01;

import java.io.IOException;

/**
 * Esta clase representa un programa que ejecutará un comando como un proceso
 * hijo. Se le pueden pasar argumentos para especificar el comando y sus
 * opciones.
 */
public class EjecutaComando {
	public static void main(String[] args) {
		// Si no se proporcionan argumentos de ejecución, mostrar un aviso y cerrar el
		// programa
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
