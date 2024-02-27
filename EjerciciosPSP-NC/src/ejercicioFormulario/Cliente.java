package ejercicioFormulario;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import entrada.Teclado;

public class Cliente {
	public static void main(String[] args) {
		
		PrintWriter fsalida;
		BufferedReader fentrada;
		Socket socket;

		
		try {
			socket = new Socket(ServerConfig.ipServidor, ServerConfig.puertoServidor);

			fsalida = new PrintWriter(socket.getOutputStream(), true);
			fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// Recibir preguntas y enviar respuestas

			boolean recibirPreguntas = true;

			while (recibirPreguntas) {

				String pregunta = (String) fentrada.readLine();

				if (!pregunta.equals("FIN")) {
					String respuesta = Teclado.leerCadena(pregunta + " (Responde si/no):");
					fsalida.println(respuesta);
				} else {
					recibirPreguntas = false;
				}
			}

			// Recibir y mostrar resumen de la encuesta
			String resumen = (String) fentrada.readLine();
			System.out.println(resumen);

			// Cerrar conexiones
			fsalida.close();
			fentrada.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
