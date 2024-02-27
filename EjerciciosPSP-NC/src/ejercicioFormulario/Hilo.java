package ejercicioFormulario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Hilo extends Thread {
	private Socket clienteSocket;
	private List<Pregunta> listaPreguntas;

	public Hilo(Socket clienteSocket, List<Pregunta> listaPreguntas) {
		this.clienteSocket = clienteSocket;
		this.listaPreguntas = listaPreguntas;
	}

	@Override
	public void run() {
		PrintWriter fsalida;
		BufferedReader fentrada;
		try {

			fsalida = new PrintWriter(clienteSocket.getOutputStream(), true);
			fentrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

			String respuestaCliente;

			for (Pregunta p : listaPreguntas) {
				fsalida.println(p.printEnunciado());

				respuestaCliente = ((String) fentrada.readLine()).trim().toLowerCase();

				if (respuestaCliente.equals(p.getRespuesta())) {
					p.aumentarContadorCorrectas();
				} else {
					p.aumentarContadorIncorrectas();
				}

			}

			fsalida.println("FIN");

			int contadorPreguntasResumen = 0;
			String textoResumen = "Resumen ->";

			for (Pregunta p : listaPreguntas) {
				contadorPreguntasResumen++;
				textoResumen += (" P-" + contadorPreguntasResumen + " T(" + p.getContadorCorrectas() + ")F(" + p.getContadorIncorrectas() + ")");
			}

			fsalida.println(textoResumen);

			// Cerrar conexiones
			fsalida.close();
			fentrada.close();
			clienteSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}