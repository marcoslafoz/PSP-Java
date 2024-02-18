package ejemplos;

class DatoCompartido {
	private int numero;

	public DatoCompartido(int num) {
		numero = num;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int num) {
		numero = num;
	}

	public String toString() {
		return String.valueOf(numero);
	}
}

class ClaseSumar extends Thread {
	DatoCompartido dato;

	ClaseSumar(DatoCompartido dato) {
		this.dato = dato;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			dato.setNumero(dato.getNumero() + 1);
			System.out.println("Dato en sumar vale: " + dato.getNumero());
			try {
				sleep(10);
			} catch (InterruptedException e) {
				System.out.println("ClaseSumar interrumpida");
			}
		}
		System.out.println("Dato fin sumar vale: " + dato.getNumero());
	}
}

class ClaseRestar extends Thread {
	DatoCompartido dato;

	ClaseRestar(DatoCompartido dato) {
		this.dato = dato;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			dato.setNumero(dato.getNumero() - 1);
			System.out.println("Dato en restar vale: " + dato.getNumero());
			try {
				sleep(10);
			} catch (InterruptedException e) {
				System.out.println("ClaseRestar interrumpida");
			}
		}
		System.out.println("Dato fin restar vale: " + dato.getNumero());
	}
}

public class Ejemplo {

	public static void main(String[] args) throws InterruptedException {
		DatoCompartido dato = new DatoCompartido(0);
		ClaseSumar hiloSuma = new ClaseSumar(dato);
		ClaseRestar hiloResta = new ClaseRestar(dato);
		hiloSuma.start(); // Suma 5 unidades al dato compartido
		hiloResta.start(); // Resta 5 unidades al dato compartido
	}
}
