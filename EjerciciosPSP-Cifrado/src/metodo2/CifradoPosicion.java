package metodo2;

public class CifradoPosicion {
	private static final String ALFABETO_IMPAR = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	private static final String ALFABETO_PAR = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

	private static final String SUSTITUCION_IMPAR = "aeimptxbfknquYcgkñrvzdhlosw";
	private static final String SUSTITUCION_PAR = "nrwbglpuzejñsXchmqvafkotydi";

	public static String cifrar(String texto) {
		StringBuilder resultado = new StringBuilder();
		texto = texto.toUpperCase(); // Convertir el texto a mayúsculas para manejarlo uniformemente

		for (int i = 0; i < texto.length(); i++) {
			char caracter = texto.charAt(i);
			if (Character.isLetter(caracter)) {
				String alfabeto;
				String sustitucion;

				// Determinar el alfabeto y la sustitución según la posición
				if ((i + 1) % 2 == 0) {
					alfabeto = ALFABETO_PAR;
					sustitucion = SUSTITUCION_PAR;
				} else {
					alfabeto = ALFABETO_IMPAR;
					sustitucion = SUSTITUCION_IMPAR;
				}

				int posicion = alfabeto.indexOf(caracter);
				if (posicion != -1) { // Carácter encontrado en el alfabeto
					resultado.append(sustitucion.charAt(posicion));
				} else { // Si no se encuentra en el alfabeto, mantener el carácter original
					resultado.append(caracter);
				}
			} else { // Si no es una letra, mantener el carácter original
				resultado.append(caracter);
			}
		}

		return resultado.toString();
	}

	public static String descifrar(String texto) {
		StringBuilder resultado = new StringBuilder();
		texto = texto.toLowerCase(); // Convertir el texto a minúsculas para manejarlo uniformemente

		for (int i = 0; i < texto.length(); i++) {
			char caracter = texto.charAt(i);
			if (Character.isLetter(caracter)) {
				String alfabeto;
				String sustitucion;

				// Determinar el alfabeto y la sustitución según la posición
				if ((i + 1) % 2 == 0) {
					alfabeto = ALFABETO_PAR;
					sustitucion = SUSTITUCION_PAR;
				} else {
					alfabeto = ALFABETO_IMPAR;
					sustitucion = SUSTITUCION_IMPAR;
				}

				int posicion = sustitucion.indexOf(caracter);
				
				if (posicion != -1) { // Carácter encontrado en la sustitución
					resultado.append(alfabeto.charAt(posicion));
				} else { // Si no se encuentra en la sustitución, mantener el carácter original
					resultado.append(caracter);
				}

			} else { // Si no es una letra, mantener el carácter original
				resultado.append(caracter);
			}
		}

		return resultado.toString();
	}

	public static String cifrarCesar(String texto, int desplazamiento) {
		StringBuilder resultado = new StringBuilder();

		for (int i = 0; i < texto.length(); i++) {
			char caracter = texto.charAt(i);
			if (Character.isLetter(caracter)) {
				char inicio = Character.isLowerCase(caracter) ? 'a' : 'A';
				caracter = (char) (((caracter - inicio + desplazamiento) % 26) + inicio);
			}
			resultado.append(caracter);
		}

		return resultado.toString();
	}

	public static String descifrarCesar(String texto, int desplazamiento) {
		StringBuilder resultado = new StringBuilder();

		for (int i = 0; i < texto.length(); i++) {
			char caracter = texto.charAt(i);
			if (Character.isLetter(caracter)) {
				char inicio = Character.isLowerCase(caracter) ? 'a' : 'A';
				// Se aplica el desplazamiento negativo para descifrar
				caracter = (char) (((caracter - inicio - desplazamiento + 26) % 26) + inicio);
			}
			resultado.append(caracter);
		}

		return resultado.toString();
	}

}