package ejercicioLogin;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import entrada.Teclado;

public class Cliente {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket(ServerConfig.ipServidor, ServerConfig.puertoServidor);

			// Establecer canales de comunicación
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Scanner scanner = new Scanner(System.in);

			// Recibir preguntas y enviar respuestas
			boolean recibirPreguntas = true;

			while (recibirPreguntas) {
				String pregunta = (String) in.readObject();

				if (!pregunta.equals("FIN")) {
					out.writeObject(Teclado.leerCadena(pregunta));
				} else {
					recibirPreguntas = false;
				}
			}

			// Recibir y mostrar el usuario
			String resumen = (String) in.readObject();
			System.out.println(resumen);


			out.close();
			in.close();
			socket.close();
			scanner.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}