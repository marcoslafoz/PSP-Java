package unidad2.ejemplos.semaforo;

import java.util.concurrent.Semaphore;

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
	Semaphore semaforo;

	ClaseSumar(Semaphore semaforo, DatoCompartido dato) {
		this.dato = dato;
		this.semaforo = semaforo;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				semaforo.acquire();
				dato.setNumero(dato.getNumero() + 1);
				System.out.println("Dato en sumar vale: " + dato.getNumero());
				sleep(10);
				semaforo.release();
			} catch (InterruptedException e) {
				System.out.println("ClaseSumar interrumpida");
			}
		}
		System.out.println("Dato fin sumar vale: " + dato.getNumero());
	}
}

class ClaseRestar extends Thread {
	DatoCompartido dato;
	Semaphore semaforo;

	ClaseRestar(Semaphore semaforo, DatoCompartido dato) {
		this.dato = dato;
		this.semaforo = semaforo;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				semaforo.acquire();
				dato.setNumero(dato.getNumero() - 1);
				System.out.println("Dato en restar vale: " + dato.getNumero());
				sleep(10);
				semaforo.release();
			} catch (InterruptedException e) {
				System.out.println("ClaseRestar interrumpida");
			}
		}
		System.out.println("Dato fin restar vale: " + dato.getNumero());
	}
}

public class EjemploSemaforo {

	public static void main(String[] args) throws InterruptedException {

		Semaphore semaforo = new Semaphore(1);
		DatoCompartido dato = new DatoCompartido(0);
		ClaseSumar hilo1 = new ClaseSumar(semaforo, dato);
		ClaseRestar hilo2 = new ClaseRestar(semaforo, dato);
		hilo1.start();
		hilo2.start();
	}

}