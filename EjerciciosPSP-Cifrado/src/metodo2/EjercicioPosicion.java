package metodo2;

import entrada.Teclado;

public class EjercicioPosicion {
	public static void main(String[] args) {

		try {
			int opcion = -1;
			String texto;

			while (opcion != 0) {
				System.out.println("1. Cifrar texto");
				System.out.println("2. Descifrar texto");
				System.out.println("0. Salir");
				opcion = Teclado.leerEntero("Introduce una opción: ");

				switch (opcion) {

				//////////////////// CIFRAR //////////////////////
				case 1:

					texto = CifradoPosicion.cifrar(Teclado.leerCadena("Introduce el texto a cifrar: "));
					System.out.println("RESULTADO CIFRADO: " + texto);
					break;

				//////////////////// DESCIFRAR //////////////////////
				case 2:

					texto = CifradoPosicion.descifrar(Teclado.leerCadena("Introduce el texto a descifrar: "));
					System.out.println("RESULTADO DESCIFRADO: " + texto);

					break;
				//////////////////// CIFRAR CESAR //////////////////////
				case 3:

					texto = CifradoPosicion.cifrarCesar(Teclado.leerCadena("Introduce el texto a cifrar: "), 10);
					System.out.println("RESULTADO CIFRADO: " + texto);
					break;

				//////////////////// DESCIFRAR CESAR //////////////////////
				case 4:

					texto = CifradoPosicion.descifrarCesar(Teclado.leerCadena("Introduce el texto a descifrar: "), 10);
					System.out.println("RESULTADO DESCIFRADO: " + texto);

					break;

				case 0:

					System.out.println("Saliendo del programa.");
					break;

				//////////////////// DEFAULT //////////////////////
				default:
					System.out.println("\nOpcion inválida.");
					break;

				}
			}
		} catch (Exception e) {
			System.out.println("Error");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
