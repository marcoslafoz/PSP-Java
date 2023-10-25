package ejercicio1x05;

import java.io.IOException;

public class ProcesaValorRetorno {

	public static void main(String[] args) {
		try {
			// Crear un proceso hijo para ejecutar la clase ValorRetorno
			ProcessBuilder processBuilder = new ProcessBuilder("java", "ValorRetorno");
			Process process = processBuilder.start();

			// Esperar a que el proceso hijo termine y recoger su código de finalización
			int exitCode = process.waitFor();

			if (exitCode < 0) {
				// Código de finalización menor que cero
				System.err.println("Error en proceso de numeración");
				// Lanzar una excepción de entrada/salida
				throw new IOException("Error en proceso de numeración");
			} else if (exitCode == 0) {
				// Código de finalización igual a cero
				System.out.println("Proceso finalizado correctamente");
			} else {
				// Código de finalización mayor que cero
				System.out.println("Proceso finalizado con valor de retorno " + exitCode);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
