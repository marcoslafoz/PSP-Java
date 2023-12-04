package ejemplo;

import entrada.Teclado;

public class Main {
	public static void main(String[] args) {
		
		String cadena = Teclado.leerCadena("Introduce una cadena: ");
		
		System.out.println(cadena);
	}
}	
