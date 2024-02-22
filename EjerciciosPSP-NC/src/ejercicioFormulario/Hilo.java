package ejercicioFormulario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Hilo implements Runnable {
	private Socket clienteSocket;
	private List<Pregunta> listaPreguntas;

	public Hilo(Socket clienteSocket, List<Pregunta> listaPreguntas) {
		this.clienteSocket = clienteSocket;
		this.listaPreguntas = listaPreguntas;
	}

	@Override
	public void run() {
		try {
			// Establecer canales de comunicación
			ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream());

			String respuestaCliente;

			for (Pregunta p : listaPreguntas) {
				out.writeObject(p.printEnunciado());

				respuestaCliente = ((String) in.readObject()).trim().toLowerCase();

				if (respuestaCliente.equals(p.getRespuesta())) {
					p.aumentarContadorCorrectas();
				} else {
					p.aumentarContadorIncorrectas();
				}

			}

			int contadorPreguntasResumen = 0;
			String textoResumen = "Resumen\n";

			for (Pregunta p : listaPreguntas) {
				contadorPreguntasResumen++;
				textoResumen += ("Pregunta " + contadorPreguntasResumen + ": T(" + p.getContadorCorrectas() + ") F("
						+ p.getContadorIncorrectas() + ")\n");
			}

			out.writeObject(textoResumen);

			// Cerrar conexiones
			out.close();
			in.close();
			clienteSocket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}