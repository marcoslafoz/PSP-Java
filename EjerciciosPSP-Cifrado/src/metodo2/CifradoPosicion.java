package metodo2;

public class CifradoPosicion {
	private static final String ALFABETO_IMPAR = "ABCDEFGHIJKLMN�OPQRSTUVWXYZ";
	private static final String ALFABETO_PAR = "ABCDEFGHIJKLMN�OPQRSTUVWXYZ";

	private static final String SUSTITUCION_IMPAR = "aeimptxbfknquYcgk�rvzdhlosw";
	private static final String SUSTITUCION_PAR = "nrwbglpuzej�sXchmqvafkotydi";

	public static String cifrar(String texto) {
		StringBuilder resultado = new StringBuilder();
		texto = texto.toUpperCase(); // Convertir el texto a may�sculas para manejarlo uniformemente

		for (int i = 0; i < texto.length(); i++) {
			char caracter = texto.charAt(i);
			if (Character.isLetter(caracter)) {
				String alfabeto;
				String sustitucion;

				// Determinar el alfabeto y la sustituci�n seg�n la posici�n
				if ((i + 1) % 2 == 0) {
					alfabeto = ALFABETO_PAR;
					sustitucion = SUSTITUCION_PAR;
				} else {
					alfabeto = ALFABETO_IMPAR;
					sustitucion = SUSTITUCION_IMPAR;
				}

				int posicion = alfabeto.indexOf(caracter);
				if (posicion != -1) { // Car�cter encontrado en el alfabeto
					resultado.append(sustitucion.charAt(posicion));
				} else { // Si no se encuentra en el alfabeto, mantener el car�cter original
					resultado.append(caracter);
				}
			} else { // Si no es una letra, mantener el car�cter original
				resultado.append(caracter);
			}
		}

		return resultado.toString();
	}

	public static String descifrar(String texto) {
		StringBuilder resultado = new StringBuilder();
		texto = texto.toLowerCase(); // Convertir el texto a min�sculas para manejarlo uniformemente

		for (int i = 0; i < texto.length(); i++) {
			char caracter = texto.charAt(i);
			if (Character.isLetter(caracter)) {
				String alfabeto;
				String sustitucion;

				// Determinar el alfabeto y la sustituci�n seg�n la posici�n
				if ((i + 1) % 2 == 0) {
					alfabeto = ALFABETO_PAR;
					sustitucion = SUSTITUCION_PAR;
				} else {
					alfabeto = ALFABETO_IMPAR;
					sustitucion = SUSTITUCION_IMPAR;
				}

				int posicion = sustitucion.indexOf(caracter);
				
				if (posicion != -1) { // Car�cter encontrado en la sustituci�n
					resultado.append(alfabeto.charAt(posicion));
				} else { // Si no se encuentra en la sustituci�n, mantener el car�cter original
					resultado.append(caracter);
				}

			} else { // Si no es una letra, mantener el car�cter original
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