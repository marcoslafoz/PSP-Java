package ejercicio1x07;

import java.net.*;

public class Actividad1x07A {
    public static void main(String[] args) {
    	DatagramSocket socket ;
        try {
            byte[] receiveData = new byte[1024];
            
            socket = new DatagramSocket(12345);
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Mensaje recibido desde A: " + receivedMessage);
            
            if(receivedMessage.contains("hola")) {
            	
            	InetAddress address = receivePacket.getAddress();
            	int port = receivePacket.getPort();
            	byte[] sendData = "adios".getBytes();
            	
            	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            	socket.send(sendPacket);
            }
            
            
            System.out.println("Mensaje enviado a A: adios");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}