package ejercicioFormulario;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket clienteSocket = null;
		List<Pregunta> listaPreguntas = new ArrayList<>();

		try {
			serverSocket = new ServerSocket(ServerConfig.puertoServidor);
			System.out.println("Servidor iniciado. Esperando clientes...");


			listaPreguntas.add(new Pregunta("¿Te gusta el color azul?", "si"));
			listaPreguntas.add(new Pregunta("¿Has viajado al extranjero?", "si"));
			listaPreguntas.add(new Pregunta("¿Te gusta el color azul?", "si"));
			listaPreguntas.add(new Pregunta("¿Te gusta el color blanco?", "si"));
			listaPreguntas.add(new Pregunta("¿Te gusta el color negro?", "si"));

			while (true) {
				clienteSocket = serverSocket.accept();
				System.out.println("Nuevo cliente conectado");

				// Crear un nuevo hilo para manejar el cliente
				Hilo clientThread = new Hilo(clienteSocket, listaPreguntas);
				clientThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
