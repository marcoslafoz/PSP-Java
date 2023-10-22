package actividad1x04;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Primo {
	public static void main(String[] args) throws IOException {
		// Declaración de variables
		BufferedReader teclado = null;
		BufferedReader entrada = null;
		File directorio = new File("bin");
		PrintStream ps = null;

		try {
			// Configuración del proceso hijo para ejecutar la clase CalculaPrimo
			ProcessBuilder pb = new ProcessBuilder("java", "actividad1x04.CalculaPrimo");
			pb.directory(directorio);

			// Preparación de lectura desde la entrada estándar
			teclado = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Di un numero");
			String linea = teclado.readLine();

			while (!linea.equals("fin")) {
				// Iniciar el proceso hijo
				Process hijo = pb.start();

				// Preparación de lectura y escritura con el proceso hijo
				entrada = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
				ps = new PrintStream(hijo.getOutputStream());

				ps.println(linea); // Enviar el número al proceso hijo
				ps.flush(); // Limpiar el flujo de salida

				String respuesta = entrada.readLine();

				while (respuesta != null) {
					System.out.println(respuesta); // Mostrar la respuesta del proceso hijo
					respuesta = entrada.readLine();
				}

				System.out.println("Di un numero");
				linea = teclado.readLine();
			}
		} finally {
			// Cerrar flujos al final
			ps.close();
			entrada.close();
		}
	}
}
