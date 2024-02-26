package ejercicio1x04;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        final String FILE_PATH = "data/fichero.txt";
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12345;

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             OutputStream outputStream = socket.getOutputStream();
             FileInputStream fileInputStream = new FileInputStream(FILE_PATH)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("Archivo enviado al servidor.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

