package ejercicio1x03;

import java.io.*;
import java.net.*;

import entrada.Teclado;

public class Cliente {

	public static void main(String[] args) throws IOException {

		final int PUERTO = 60003;// puerto remoto
		Socket cliente = new Socket("localhost", PUERTO);

		// Se crea flujo de salida al servidor
		PrintWriter fsalida = new PrintWriter(cliente.getOutputStream(), true);

		// Se crea flujo de entrada del servidor
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

		// Se crea flujo de entrada estándar (teclado)
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String cadena = Teclado.leerCadena("Introduce un número: ");

		while (cadena != null) {
			
			fsalida.println(cadena); // cadena al servidor
			System.out.println(" =>ECO: " + fentrada.readLine());
			cadena = Teclado.leerCadena("Introduce un número: ");
		}

		// Se cierran flujos y sockets
		fsalida.close();
		fentrada.close();

		System.out.println("Fin del envío... ");

		in.close();
		cliente.close();

	}
}