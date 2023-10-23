package actividad1x03;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Proceso padre
 */

public class Mayusculas {
	public static void main(String[] args) throws IOException {

		BufferedReader entradaHijo = null;
		String numero, linea;

		try {
			File directorio = new File("bin");
			ProcessBuilder pb = new ProcessBuilder("java", "actividad1x03.PasaMayusculas");
			pb.directory(directorio);
			Process hijo = pb.start();

			entradaHijo = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
			PrintStream salidaHijo = new PrintStream(hijo.getOutputStream());
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Escribe una cadena");
			linea = teclado.readLine();

			while (!linea.equals("fin")) {
				salidaHijo.println(linea); // Envía la línea al proceso hijo
				salidaHijo.flush();
				numero = entradaHijo.readLine();
				if (numero != null) {
					System.out.println(numero);
				}
				System.out.println("Escribe algo");
				linea = teclado.readLine();
			}

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
