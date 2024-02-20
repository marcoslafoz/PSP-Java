package ejercicioFormulario;

import java.io.*;
import java.net.*;

public class EncuestaServidor {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(ServerConfig.puertoServidor);
			System.out.println("Servidor iniciado. Esperando clientes...");

			// Preguntas de la encuesta
			String[] preguntas = { "¿Te gusta el color azul?", "¿Has viajado al extranjero?", "¿Te gusta la pizza?" };
			int[] respuestasAfirmativas = new int[preguntas.length];
			int[] respuestasNegativas = new int[preguntas.length];

			while (true) {
				Socket clienteSocket = serverSocket.accept();
				System.out.println("Nuevo cliente conectado");

				// Establecer canales de comunicación
				ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream());

				// Enviar preguntas al cliente y recibir respuestas
				for (int i = 0; i < preguntas.length; i++) {
					out.writeObject(preguntas[i]);
					String respuesta = (String) in.readObject();

					// Contabilizar respuestas
					if (respuesta.equalsIgnoreCase("si")) {
						respuestasAfirmativas[i]++;
					} else if (respuesta.equalsIgnoreCase("no")) {
						respuestasNegativas[i]++;
					}
				}

				// Enviar resumen de la encuesta
				for (int i = 0; i < preguntas.length; i++) {
					out.writeObject("Resumen de la encuesta:");
					out.writeObject(preguntas[i]);
					out.writeObject(
							"Afirmativas: " + respuestasAfirmativas[i] + ", Negativas: " + respuestasNegativas[i]);
				}
				out.writeObject("FIN"); // Marcador de fin del resumen

				// Cerrar conexiones
				out.close();
				in.close();
				clienteSocket.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
