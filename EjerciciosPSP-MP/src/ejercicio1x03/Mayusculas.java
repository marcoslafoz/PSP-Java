package ejercicio1x03;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Esta clase representa el proceso principal que interactúa con otro proceso
 * llamado PasaMayusculas. El objetivo es tomar una entrada de usuario, enviarla
 * al proceso hijo y mostrar la salida convertida a mayúsculas.
 */
public class Mayusculas {
	public static void main(String[] args) throws IOException {
		// Declaración de variables
		BufferedReader entradaHijo = null;
		String numero, linea;

		try {
			// Crear un directorio para el proceso hijo
			File directorio = new File("bin");

			// Configurar el proceso hijo usando ProcessBuilder para ejecutar PasaMayusculas
			ProcessBuilder pb = new ProcessBuilder("java", "actividad1x03.PasaMayusculas");
			pb.directory(directorio);
			Process hijo = pb.start();

			// Preparar flujos de entrada y salida para comunicarse con el proceso hijo
			entradaHijo = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
			PrintStream salidaHijo = new PrintStream(hijo.getOutputStream());
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

			// Solicitar al usuario una cadena de texto
			System.out.println("Escribe una cadena");
			linea = teclado.readLine();

			// Comunicación con el proceso hijo hasta que el usuario escriba "fin"
			while (!linea.equals("fin")) {
				salidaHijo.println(linea); // Envía la línea al proceso hijo
				salidaHijo.flush();
				numero = entradaHijo.readLine();
				if (numero != null) {
					System.out.println(numero); // Muestra la respuesta en mayúsculas del proceso hijo
				}
				System.out.println("Escribe algo");
				linea = teclado.readLine();
			}

			// Termina el proceso hijo
			hijo.destroy();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (entradaHijo != null) {
					System.out.println("Fin del programa");
					entradaHijo.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
