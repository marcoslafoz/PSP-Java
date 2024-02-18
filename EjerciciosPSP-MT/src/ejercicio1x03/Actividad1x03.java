package ejercicio1x03;

class Dato {
	private String cadena;

	public Dato(String cadena) {
		this.cadena = cadena;
	}

	public String getCadena() {
		return cadena;
	}

	public synchronized void setCadena(String cadena) {
		this.cadena = cadena;
	}
}

class Productor extends Thread {
	private Dato dato;

	public Productor(Dato dato) {
		this.dato = dato;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			synchronized (dato) {
				while (!dato.getCadena().equals("PING")) {
					try {
						dato.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("PING");
				dato.setCadena("PONG");
				dato.notify(); // Notificar al consumidor que hay un nuevo dato disponible
			}
		}
	}
}

class Consumidor extends Thread {
	private Dato dato;

	public Consumidor(Dato dato) {
		this.dato = dato;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			synchronized (dato) {
				while (!dato.getCadena().equals("PONG")) {
					try {
						dato.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("PONG");
				dato.setCadena("PING");
				dato.notify(); // Notificar al productor que el dato ha sido consumido
			}
		}
	}
}

public class Actividad1x03 {
	public static void main(String[] args) {
		Dato dato = new Dato("PING");

		Productor productor = new Productor(dato);
		Consumidor consumidor = new Consumidor(dato);

		productor.start();
		consumidor.start();
	}
}
