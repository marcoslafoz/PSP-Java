package actividad1x03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Proceso hijo
 */

public class PasaMayusculas {
	public static void main(String[] args) throws IOException {

		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

		PrintStream salida = new PrintStream(System.out);
		String linea = entrada.readLine();

		while (linea != null) {
			salida.println(linea.toUpperCase());
			salida.flush();
			linea = entrada.readLine();
		}
		System.exit(0);
	}

}
