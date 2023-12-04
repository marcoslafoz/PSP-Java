package unidad2.ejemplos;

public class HiloRunnable {

	public static void main(String[] args) {
		Thread hilo1 = new Thread(new Hilo("Hilo 1"));
		Thread hilo2 = new Thread(new Hilo("Hilo 2"));
		hilo1.start();
		hilo2.start();
		System.out.println("Fin del programa principal");
	}

}

class Hilo implements Runnable {
	private String nombre;

	Hilo(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		System.out.println("Saludos desde el hilo creado:" + this.nombre);
	}

}
