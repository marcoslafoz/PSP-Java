package ejercicio1x02;

import java.io.*; 
import java.net.*; 

public class Actividad1x02Servidor { 
	
	public static void main(String[] arg) throws IOException { 
		
		int numeroPuerto = 60002;// Puerto 
		ServerSocket servidor = new ServerSocket(numeroPuerto); 
		String cad=""; 
		
		System.out.println("Esperando Conexión..."); 
		Socket c1ienteConectado = servidor.accept(); 
		System.out.println("Cliente conectado..."); 
		
		// Se crea flujo de salida al cliente
		PrintWriter fsalida = new PrintWriter (c1ienteConectado.getOutputStream(),true); 
		
		// Se crea flujo de entrada del cliente
		BufferedReader fentrada = new BufferedReader (new InputStreamReader(c1ienteConectado.getInputStream())); 
		
		while ((cad=fentrada.readLine())!= null)//recibo cad del cliente 
		{ 
			fsalida.println(cad.toUpperCase()); //envio cadena al cliente 
			System.out.println("Recibiendo: " + cad); 
			if(cad.equals("*")) break; 
		} 
		
		// Se cierran flujos y sockets
		System.out.println("Cerrando conexión..."); 
		fentrada.close(); 
		fsalida.close(); 
		c1ienteConectado.close(); 
		servidor.close(); 
	
	}//Fin de main 
	
}//Fin de Ejemplo2Servidor