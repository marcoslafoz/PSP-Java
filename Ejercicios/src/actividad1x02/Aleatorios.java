package actividad1x02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Aleatorios {
	public static void main(String[] args) throws IOException {

		// Declaración de variables
		BufferedReader teclado = null;
		BufferedReader entrada = null;
		File directorio = new File("bin");
		PrintStream ps = null;

		try {

			// Configuración del proceso hijo para ejecutar la clase CalculaPrimo
			ProcessBuilder pb = new ProcessBuilder("java", "actividad1x02.GeneraAleatorio");
			pb.directory(directorio);

			// Preparación de lectura desde la entrada estándar
			teclado = new BufferedReader(new InputStreamReader(System.in));

			// Pedimos una linea
			System.out.println("Introduce una linea> ");
			String linea = teclado.readLine();

			while (!linea.equals("fin")) {

				Process hijo = pb.start();

				entrada = new BufferedReader(new InputStreamReader(hijo.getInputStream()));

				ps = new PrintStream(hijo.getOutputStream());

				ps.println(linea); // Enviar el número al proceso hijo
				ps.flush(); // Limpiar el flujo de salida

				String respuesta = entrada.readLine();

				while (respuesta != null) {
					System.out.println(respuesta); // Mostrar la respuesta del proceso hijo
					respuesta = entrada.readLine();
				}

				System.out.println("Introduce otra línea>");
				linea = teclado.readLine();

			}

		} finally {
			ps.close();
			entrada.close();
			System.out.println("Fin del programa");
		}

	}
}
