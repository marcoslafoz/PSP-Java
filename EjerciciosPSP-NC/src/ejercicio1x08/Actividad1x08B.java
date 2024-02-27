package ejercicio1x08;

import java.net.*;

public class Actividad1x08B {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(12345);
            byte[] receiveData = new byte[1024];
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Mensaje recibido desde A: " + receivedMessage);
            
            InetAddress address = receivePacket.getAddress();
            int port = receivePacket.getPort();
            byte[] sendData = "recibido".getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            System.out.println("Mensaje enviado a A: recibido");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}