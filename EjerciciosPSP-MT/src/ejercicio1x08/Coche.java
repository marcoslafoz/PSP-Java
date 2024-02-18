package ejercicio1x08;

import java.util.Random;

public class Coche extends Thread {
	private int cocheId; // Identificador único del coche
	private Barrera barrera; // Referencia a la barrera de estacionamiento

	// Constructor que recibe el identificador del coche y la referencia a la
	// barrera
	public Coche(int cocheId, Barrera barrera) {
		this.cocheId = cocheId;
		this.barrera = barrera;
	}

	// Método para obtener el identificador del coche
	public int getCocheId() {
		return cocheId;
	}

	// Método principal que se ejecuta cuando el hilo del coche es iniciado
	@Override
	public void run() {
		System.out.println("Coche " + cocheId + " intenta entrar en parking");

		try {
			// Simula el tiempo que el coche tarda en llegar al estacionamiento
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Intenta entrar al estacionamiento, obteniendo la plaza asignada
		int plaza = barrera.entrar(this);

		if (plaza != -1) {
			// Si se asigna una plaza, muestra información y estado del estacionamiento
			System.out.println("Parking: Coche " + cocheId + " aparcado en " + plaza);
			barrera.mostrarEstado();

			try {
				// Simula el tiempo que el coche permanece estacionado
				Thread.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// El coche sale del estacionamiento y muestra el nuevo estado
			int nPlaza = barrera.salir(plaza);
			System.out.println("Coche " + cocheId + " saliendo de la plaza " + nPlaza);
			barrera.mostrarEstado();
		} else {
			// Si no se asigna una plaza, indica que el coche está esperando
			System.out.println("Coche " + cocheId + " esperando");
		}
	}

}
