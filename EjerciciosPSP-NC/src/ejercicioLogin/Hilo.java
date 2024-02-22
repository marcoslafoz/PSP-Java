package ejercicioLogin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Hilo implements Runnable {
	private Socket clienteSocket;

	public Hilo(Socket clienteSocket) {
		this.clienteSocket = clienteSocket;
	}

	@Override
	public void run() {
		try {
			// Establecer canales de comunicación
			ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream());

			out.writeObject("Introduce el nombre: ");
			String nombreUsuario = (String) in.readObject();

			out.writeObject("Introduce el departamento: ");
			String departamentoUsuario = (String) in.readObject();

			out.writeObject("Introduce LA edad: ");
			int edadUsuario = Integer.parseInt((String) in.readObject());

			out.writeObject("FIN");

			Usuario usuario = new Usuario(nombreUsuario, departamentoUsuario, edadUsuario);

			// Agregar nuevo usuario a la lista
			Servidor.actualizarListaUsuarios(usuario);

			// Cerrar conexiones
			out.close();
			in.close();
			clienteSocket.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
