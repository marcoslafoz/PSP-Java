package unidad2.ejemplos;

public class HiloThread {

	public static void main(String[] args) {
		OtroHilo hilo1 = new OtroHilo("Hilo 1");
		OtroHilo hilo2 = new OtroHilo("Hilo 2");
		hilo1.start();
		hilo2.start();
		System.out.println("Fin del programa principal");
	}

}

class OtroHilo extends Thread {
	private String nombre;

	OtroHilo(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		System.out.println("Saludos desde el hilo creado:" + this.nombre);
	}

}
