package ejercicioLogin;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
	private static List<Usuario> listaUsuarios = new ArrayList<>();

	public static void actualizarListaUsuarios(Usuario usuario) {
		listaUsuarios.add(usuario);
		System.out.println("Usuario añadido -> " + usuario.toString());
	}

	public static List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public static void main(String[] args) {
		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(ServerConfig.puertoServidor);
			System.out.println("Servidor iniciado. Esperando clientes...");

			while (true) {
				Socket clienteSocket = serverSocket.accept();
				System.out.println("Nuevo cliente conectado");

				// Crear un nuevo hilo para manejar el cliente
				Thread clientThread = new Thread(new Hilo(clienteSocket));
				clientThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
