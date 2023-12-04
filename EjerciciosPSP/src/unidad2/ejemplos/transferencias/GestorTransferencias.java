package unidad2.ejemplos.transferencias;

public class GestorTransferencias {
	public static void main(String[] args) {
		Cuenta cuenta1 = new Cuenta(12500, "ES1512345678901234567890");
		Cuenta cuenta2 = new Cuenta(23400, "ES4578901234567890123456");
		
		System.out.printf("\nSaldo inicial de %s: %f", 
				cuenta1.getNUMERO(), cuenta1.getSaldo());
		System.out.printf("\nSaldo inicial de %s: %f", 
				cuenta2.getNUMERO(), cuenta2.getSaldo());
		
		
		//Se crean dos hilos, uno hace transferencia de la primera cuenta a la segunda
		// y otro de la segunda a la primera
		Transferencia hilo1 = new Transferencia(cuenta1, cuenta2, 1250);
		Transferencia hilo2 = new Transferencia(cuenta2, cuenta1, 100);
		
		hilo1.start();
		hilo2.start();
		
		try {
			hilo1.join();
			hilo2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("\nSaldo final de %s: %f", 
				cuenta1.getNUMERO(), cuenta1.getSaldo());
		System.out.printf("\nSaldo final de %s: %f", 
				cuenta2.getNUMERO(), cuenta2.getSaldo());
		
	}
}
