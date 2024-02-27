package ejercicio1x07;

import java.net.*;

public class Actividad1x07B {
    public static void main(String[] args) {
        
    	InetAddress address;
    	DatagramSocket socket;
    	try {
            socket = new DatagramSocket();
            address = InetAddress.getLocalHost();
            
            String mensaje = "hola";
            
            byte[] sendData = mensaje.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 12345);
            
            socket.send(sendPacket);
            
            System.out.println("Mensaje enviado a B: " + mensaje);
            
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

