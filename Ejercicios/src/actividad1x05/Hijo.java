package actividad1x05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Hijo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader entrada = null;
		PrintStream salida = null;

		try {
			// Lo recibe del padre
			entrada = new BufferedReader(new InputStreamReader(System.in));
			// Se manda al padre
			salida = new PrintStream(System.out);
			String linea = entrada.readLine();

			while (linea != null) {

				int numprimo = Integer.parseInt(linea);

				if (!esPrimo(numprimo)) {

					salida.println("El numero " + numprimo + " no es primo");
					// flush: asegurarnos de que se ha enviado
					salida.flush();
					salida.println("Primos menores que " + numprimo + ": ");
					salida.flush();
					// Primos menores que x
					for (int x = numprimo; x > 0; x--) {

						if (esPrimo(x)) {

							salida.println(x);
							salida.flush();
						}
					}

				} else {

					salida.println("El numero " + numprimo + " es primo");
					salida.flush();
				}
				// Termina la clase hijo
				salida.println("finproceso");
				salida.flush();
				// Volvemos a preguntar a Padre
				linea = entrada.readLine();

			}

			System.exit(0);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			entrada.close();
			salida.close();
		}
	}

	public static boolean esPrimo(int n) {

		if (n == 2)
			return true;

		if (n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

}