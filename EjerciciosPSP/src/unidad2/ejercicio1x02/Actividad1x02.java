package unidad2.ejercicio1x02;

class Tic extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("TIC");
			try {
				Thread.sleep(100); // Puedes ajustar el tiempo de sleep según tus preferencias
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Tac extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("TAC");
			try {
				Thread.sleep(100); // Puedes ajustar el tiempo de sleep según tus preferencias
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class Actividad1x02 {
	public static void main(String[] args) {
		Tic ticThread = new Tic();
		Tac tacThread = new Tac();

		ticThread.start();
		tacThread.start();
	}
}
