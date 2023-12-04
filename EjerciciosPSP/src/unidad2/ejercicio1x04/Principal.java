package unidad2.ejercicio1x04;

public class Principal {

	public static void main(String[] args) {
		Compartido dato = new Compartido();
		Productor p = new Productor(dato, 1);
		Consumidor c = new Consumidor(dato, 1);
		p.start();
		c.start();

	}
}
