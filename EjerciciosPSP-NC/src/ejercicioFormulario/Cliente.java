package ejercicioFormulario;

import java.io.*;
import java.net.*;
import java.util.Scanner;

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
					System.out.println(pregunta + " (Responde si/no):");
					String respuesta = scanner.nextLine().toLowerCase();
					out.writeObject(respuesta);
				} else {
					recibirPreguntas = false;
				}
			}

			// Recibir y mostrar resumen de la encuesta
			String resumen = (String) in.readObject();
			System.out.println(resumen);

			// Cerrar conexiones
			out.close();
			in.close();
			socket.close();
			scanner.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
