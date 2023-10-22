package actividad1x04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CalculaPrimo {
	public static void main(String[] args) throws IOException {
		
		BufferedReader entrada = null;
		
		try {
			entrada = new BufferedReader(new InputStreamReader(System.in));
			PrintStream salida = new PrintStream(System.out);
			String linea = entrada.readLine();
			int num = Integer.parseInt(linea);
			boolean primo = esPrimo(num);
			if (primo) {
				salida.println("El numero " + num + " es primo");
				salida.flush();
			} else {
				salida.println(num + " no es primo numeros primos menores que " + num);
				salida.flush();
				for (int i = 1; i < num; i++) {
					primo = esPrimo(i);
					if (primo) {
						salida.println("El numero " + i + " es primo");
						salida.flush();
					}

				}
			}
		} finally {

		}
	}

	public static boolean esPrimo(int num) {
		int rep = 0;
		for (int i = 1; i <= num / 2; i++) {
			if (num % i == 0) {
				rep++;
			}
		}
		if (rep < 2) { return true; }
		return false;

	}

}