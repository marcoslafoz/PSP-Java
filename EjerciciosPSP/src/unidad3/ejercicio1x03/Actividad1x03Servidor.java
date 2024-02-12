package unidad3.ejercicio1x03;

import java.io.*;
import java.net.*;

public class Actividad1x03Servidor {

    public static void main(String[] arg) throws IOException {

        int numeroPuerto = 60003; // Puerto
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        int cad = 1;

        System.out.println("Esperando Conexión...");
        Socket clienteConectado = servidor.accept();
        System.out.println("Cliente conectado...");

        // Se crea flujo de salida al cliente
        DataOutputStream fsalida = new DataOutputStream(clienteConectado.getOutputStream());

        // Se crea flujo de entrada del cliente
        DataInputStream fentrada = new DataInputStream(clienteConectado.getInputStream());

        while (cad != 0) {
            cad = fentrada.readInt(); 
            System.out.println("Recibiendo: " + cad);

            fsalida.writeInt(1);; // Envío cadena al cliente
        }

        // Se cierran flujos y sockets
        System.out.println("Cerrando conexión...");
        fentrada.close();
        fsalida.close();
        clienteConectado.close();
        servidor.close();

    }// Fin de main

}// Fin de EjemploServidor
