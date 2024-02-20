package ejercicioFormulario;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EncuestaCliente {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket(ServerConfig.ipServidor, ServerConfig.puertoServidor);

			// Establecer canales de comunicación
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Scanner scanner = new Scanner(System.in);

			// Recibir preguntas y enviar respuestas
			for (int i = 0; i < 3; i++) {
				String pregunta = (String) in.readObject();
				System.out.println(pregunta + " (Responde si/no):");
				String respuesta = scanner.nextLine().toLowerCase();
				out.writeObject(respuesta);
			}

			// Recibir resumen de la encuesta
			System.out.println("\n--- Resumen de la encuesta ---");
			String resumen;
			while (!(resumen = (String) in.readObject()).equals("FIN")) {
				System.out.println(resumen);
				String pregunta = (String) in.readObject();
				System.out.println(pregunta);
				String resultado = (String) in.readObject();
				System.out.println(resultado);
			}

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
