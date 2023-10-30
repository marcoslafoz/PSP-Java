package unidad1.ejercicio1x05;

import java.util.Random;

public class ValorRetorno {

	public static void main(String[] args) {
		// Generar un número aleatorio entre -1 y 1
		int randomNumber = generateRandomNumber();

		// Finalizar la ejecución con el número aleatorio como código de terminación
		System.exit(randomNumber);
	}

	private static int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(3) - 1; // -1, 0, o 1
	}
}
