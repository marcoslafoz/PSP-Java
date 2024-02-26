package ejercicio1x03;

import java.io.*;
import java.net.*;

public class Servidor {

	public static void main(String[] arg) throws IOException {

		final int PUERTO = 60003;// Puerto
		ServerSocket servidor = new ServerSocket(PUERTO);

		System.out.println("Esperando Conexión...");
		Socket c1ienteConectado = servidor.accept();
		System.out.println("Cliente conectado...");

		// Se crea flujo de salida al cliente
		PrintWriter fsalida = new PrintWriter(c1ienteConectado.getOutputStream(), true);

		// Se crea flujo de entrada del cliente
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(c1ienteConectado.getInputStream()));

		String cad = fentrada.readLine();

		while (cad != null)// recibo cad del cliente
		{
			
			fsalida.println(Integer.parseInt(cad) * Integer.parseInt(cad)); // envio cadena al cliente

			if (cad.equals("*"))
				break;
		}

		// Se cierran flujos y sockets
		System.out.println("Cerrando conexión...");
		fentrada.close();
		fsalida.close();
		c1ienteConectado.close();
		servidor.close();

	}

}