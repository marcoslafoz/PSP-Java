package ejercicio1x08;

import java.net.*;

public class Actividad1x08A {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getLocalHost();
            byte[] sendData = "token".getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 12345);
            socket.send(sendPacket);
            System.out.println("Mensaje enviado a B: token");
            
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Mensaje recibido desde B: " + receivedMessage);
            
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

