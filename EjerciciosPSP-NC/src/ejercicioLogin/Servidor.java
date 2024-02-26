package ejercicioLogin;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket clienteSocket = null;
		List<Usuario> listaUsuarios = new ArrayList<>();	

		try {
			serverSocket = new ServerSocket(ServerConfig.puertoServidor);
			System.out.println("Servidor iniciado. Esperando clientes...");

			while (true) {
				clienteSocket = serverSocket.accept();
				System.out.println("Nuevo cliente conectado");

				Hilo hilo = new Hilo(clienteSocket, listaUsuarios);
			 
				hilo.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
