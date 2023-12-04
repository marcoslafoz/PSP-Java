package unidad2.ejercicio1x04;

public class Productor extends Thread {
	private Compartido dato;
	private int num;

	public Productor(Compartido c, int n) {
		this.dato = c;
		this.num = n;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			dato.set(i);
			System.out.println(i + " => Productor: " + num + ", produce: " + i);
		}
		try {
			sleep(100);
		} catch (InterruptedException e) {
			System.err.println(e.toString());
		}
	}
}