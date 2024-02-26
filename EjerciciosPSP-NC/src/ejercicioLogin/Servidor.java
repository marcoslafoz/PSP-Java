package ejercicioLogin;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
	
	//Esto lo instanciamos dentro del main
	private static List<Usuario> listaUsuarios = new ArrayList<>();

	//Estos metodos los quitamos
	public static void actualizarListaUsuarios(Usuario usuario) {
		listaUsuarios.add(usuario);
		System.out.println("Usuario añadido -> " + usuario.toString());
	}

	public static List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	
	
	public static void main(String[] args) {
		ServerSocket serverSocket; // = null

		try {
			serverSocket = new ServerSocket(ServerConfig.puertoServidor);
			System.out.println("Servidor iniciado. Esperando clientes...");

			Socket clienteSocket;
			while (true) {
				clienteSocket = serverSocket.accept();
				System.out.println("Nuevo cliente conectado");

				// Crear un nuevo hilo para manejar el cliente
				// 
				//TODO: Hilo hilo = new Hilo (socket, listaUsuarios)
				Thread clientThread = new Thread(new Hilo(clienteSocket));  
				clientThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
