package unidad3.ejercicio1x03;

import java.io.*;
import java.net.*;

public class Actividad1x03Cliente {

    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int puerto = 60003; // Puerto remoto
        Socket cliente = new Socket(host, puerto);

        // Se crea flujo de salida al servidor
        DataOutputStream fsalida = new DataOutputStream(cliente.getOutputStream());

        // Se crea flujo de entrada del servidor
        DataInputStream fentrada = new DataInputStream(cliente.getInputStream());

        // Se crea flujo de entrada estándar (teclado)
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cadena, eco ;
        System.out.print("Introduce cadena: ");
        cadena = in.read(); // Lectura por teclado

        while (cadena != 0) {
            fsalida.writeInt(cadena); 
            eco = fentrada.readInt(); 
            System.out.println(" =>ECO: " + eco);
            System.out.print("Introduce cadena: ");
            cadena = in.read(); // Lectura por teclado
        }

        // Se cierran flujos y sockets
        fsalida.close();
        fentrada.close();
        System.out.println("Fin del envío... ");
        in.close();
        cliente.close();

    }// Fin de main

}// Fin de EjemploCliente
