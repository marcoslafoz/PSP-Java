package ejercicio1x04;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket socket = serverSocket.accept();
             InputStream inputStream = socket.getInputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                System.out.write(buffer, 0, bytesRead);
            }
            System.out.println(" Archivo recibido del cliente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
