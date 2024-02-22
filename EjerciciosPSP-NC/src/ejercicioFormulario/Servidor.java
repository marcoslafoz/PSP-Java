package ejercicioFormulario;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
	public static void main(String[] args) {
		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(ServerConfig.puertoServidor);
			System.out.println("Servidor iniciado. Esperando clientes...");

			List<Pregunta> listaPreguntas = new ArrayList<>();

			listaPreguntas.add(new Pregunta("¿Te gusta el color azul?", "si"));
			listaPreguntas.add(new Pregunta("¿Has viajado al extranjero?", "si"));
			listaPreguntas.add(new Pregunta("¿Te gusta el color azul?", "si"));

			while (true) {
				Socket clienteSocket = serverSocket.accept();
				System.out.println("Nuevo cliente conectado");

				// Crear un nuevo hilo para manejar el cliente
				Thread clientThread = new Thread(new Hilo(clienteSocket, listaPreguntas));
				clientThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
