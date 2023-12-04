package unidad2.ejemplos;

public class VariosHilosConEspera {

	public static void main(String[] args) throws InterruptedException {

		HiloSaludo[] hilos = new HiloSaludo[8];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new HiloSaludo("Hilo " + i, 500);
		}

		// Se arranca el primer hilo y se espera a que acabe la ejecución
		hilos[0].start();
		hilos[0].join();

		// Se arranca dos hilos
		hilos[1].start();
		hilos[2].start();

		// Se espera a que terminen los dos hilos
		hilos[1].join();
		hilos[2].join();

		// Se arranca un hilo y se interrumpe
		hilos[3].start();
		hilos[3].interrupt();

		// Se arrancan el resto de los hilos
		for (int i = 4; i < hilos.length; i++) {
			hilos[i].start();
		}
	}

}

class HiloSaludo extends Thread {
	String nombre;
	int tiempo;

	public HiloSaludo(String nombre, int tiempo) {
		this.nombre = nombre;
		this.tiempo = tiempo;
	}

	public void run() {
		System.out.println("Hola, soy " + nombre);
		try {
			sleep(tiempo);
		} catch (InterruptedException e) {
			System.out.println("Soy " + nombre + " y me han interrumpido");
		}
		System.out.println("Adios, soy " + nombre);
	}
}