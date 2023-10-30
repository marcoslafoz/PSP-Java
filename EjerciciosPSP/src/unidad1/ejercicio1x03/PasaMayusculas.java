package unidad1.ejercicio1x03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Esta clase representa el proceso hijo que convierte las líneas de texto en
 * mayúsculas y las envía de vuelta.
 */
public class PasaMayusculas {
	public static void main(String[] args) throws IOException {
		// Preparar flujos de entrada y salida
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		PrintStream salida = new PrintStream(System.out);

		// Leer la primera línea de entrada
		String linea = entrada.readLine();

		// Procesar líneas hasta que no haya más entradas
		while (linea != null) {
			salida.println(linea.toUpperCase()); // Convierte la línea a mayúsculas y la imprime
			salida.flush();
			linea = entrada.readLine(); // Leer la siguiente línea
		}

		// Finalizar el proceso hijo
		System.exit(0);
	}
}
