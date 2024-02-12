package unidad3.ejercicio1x02;

import java.io.*; 
import java.net.*;

public class Actividad1x02Cliente { 
	
	public static void main(String[] args) throws IOException { 
		
		String host = "localhost"; 
		int puerto = 60002;// puerto remoto 
		Socket cliente = new Socket(host, puerto); 
		
		// Se crea flujo de salida al servidor
		PrintWriter fsalida = new PrintWriter (cliente.getOutputStream(), true); 
		
		// Se crea flujo de entrada del servidor
		BufferedReader fentrada = new BufferedReader (new InputStreamReader(cliente.getInputStream())); 
		
		// Se crea flujo de entrada estándar (teclado)
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in)); 
		String cadena, eco=""; 
		System.out.print("Introduce cadena: "); 
		cadena = in.readLine();//lectura por teclado 
		while(cadena !=null){ 
			fsalida.println(cadena); // cadena al servidor 
			eco=fentrada.readLine(); // cadena del servidor 
			System.out.println(" =>ECO: "+eco); 
			System.out.print("Introduce cadena: "); 
			cadena = in.readLine();//lectura por teclado 
		}
		
		// Se cierran flujos y sockets
		fsalida.close(); 
		fentrada.close(); 
		System.out.println("Fin del envío... "); 
		in.close(); 
		cliente.close(); 
		
		
		
	}//fin de main 
	
}//Fin de Ejemplo2Cliente