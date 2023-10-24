package actividad1x02;

import java.io.IOException;
import java.util.Random;

/**
 * Esta clase representa un proceso hijo que genera un número aleatorio entre 0
 * y 9 y lo imprime en la salida estándar.
 */
public class GeneraAleatorio {
	public static void main(String[] args) throws IOException {
		// Crear una instancia de la clase Random para generar números aleatorios
		Random rand = new Random();

		// Generar un número aleatorio entre 0 y 9 y mostrarlo
		System.out.println(rand.nextInt(10));
	}
}
