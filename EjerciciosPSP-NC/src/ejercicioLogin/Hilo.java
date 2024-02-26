package ejercicioLogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

//extends thrad
public class Hilo extends Thread {
	private Socket clienteSocket;
	private List<Usuario> listaUsuarios;

	public Hilo(Socket clienteSocket, List<Usuario> listaUsuarios) {
		this.clienteSocket = clienteSocket;
		this.listaUsuarios = listaUsuarios;

	}

	@Override
	public void run() {

		// TODO: aqui instanciamos esto de abajo y lo ponemos a null
		// printwriter
		// buffered reader

		PrintWriter fsalida;
		BufferedReader fentrada;

		try {
			fsalida = new PrintWriter(clienteSocket.getOutputStream(), true);
			fentrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

			fsalida.println("Introduce el nombre: ");
			String nombreUsuario = fentrada.readLine();

			fsalida.println("Introduce el departamento: ");
			String departamentoUsuario = fentrada.readLine();

			fsalida.println("Introduce la edad: ");
			int edadUsuario = Integer.parseInt(fentrada.readLine());

			fsalida.println("FIN");

			Usuario usuario = new Usuario(nombreUsuario, departamentoUsuario, edadUsuario);

			// Agregar nuevo usuario a la lista
			listaUsuarios.add(usuario);

			// Enviar resumen
			fsalida.println(listaUsuarios.toString());
			

			// Cerrar conexiones
			fsalida.close();
			fentrada.close();
			clienteSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
