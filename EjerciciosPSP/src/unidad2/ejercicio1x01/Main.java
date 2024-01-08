package unidad2.ejercicio1x01;
import java.util.Scanner;
public class Main { 
	public static void main(String [] args) throws InterruptedException { 
		
		 try (Scanner scanner = new Scanner(System.in)) {
			//System.out.println("Introduce hasta que numero desea hacer la sucesión de Fibonacci");
			//int nValue = scanner.nextInt();
			
			HiloFibonacci hilo1 = new HiloFibonacci("HiloFibonacci1", 10);
			hilo1.start();
			HiloFibonacci hilo2 = new HiloFibonacci("HiloFibonacci2", 5);
			hilo2.start();
			hilo1.join();
		}
		 System.out.println("Fin del programa principal");
	}

}

class HiloFibonacci extends Thread{
	private String nombre;
	private int nValue;
	
	HiloFibonacci(String nombre, int nValue){
		this.nombre = nombre;
		this.nValue = nValue;
	}

	@Override
	public void run() {
		Fibonacci.FibonacciSequence(nValue, nombre);
	}
}
