package ejercicioLogin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//extends thrad
public class Hilo implements Runnable {
	private Socket clienteSocket;

	//socjer socket
	//nstancuamos lista usuarios
	//Aqui le pasamos la lista de usuarios
	
	//y le pasamos la lista de usuarios
	public Hilo(Socket clienteSocket) {
		this.clienteSocket = clienteSocket;
		//this.usuarios = usuarios
	}

	@Override
	public void run() {
		
		//TODO: aqui instanciamos esto de abajo y lo ponemos a null
		//printwriter
		//buffered reader
			
		try {
			// Establecer canales de comunicación
			ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream());
						
			//fsalida = new PrintWriter (socket.getOutputStream(), true)
			//fentrada = new BufferedReader (new InputStreamReader(socket.getInputStream()))


			//readLine
			
			out.writeObject("Introduce el nombre: ");
			String nombreUsuario = (String) in.readObject();

			out.writeObject("Introduce el departamento: ");
			String departamentoUsuario = (String) in.readObject();

			out.writeObject("Introduce la edad: ");
			int edadUsuario = Integer.parseInt((String) in.readObject());

			out.writeObject("FIN");

			Usuario usuario = new Usuario(nombreUsuario, departamentoUsuario, edadUsuario);

			// Agregar nuevo usuario a la lista
			Servidor.actualizarListaUsuarios(usuario);
			
			// Enviar resumen
			out.writeObject(usuario.toString());

			// Cerrar conexiones
			out.close();
			in.close();
			clienteSocket.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
