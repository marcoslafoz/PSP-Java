package ejemplos.transferencias;

public class Cuenta {

	private double saldo;
	private final String NUMERO ;
	
	
	public Cuenta(double saldo, String numero) {
		this.saldo = saldo;
		this.NUMERO = numero;
	}

	public synchronized double getSaldo() {
		return saldo;
	}

	public synchronized void ingresar(double cantidad) {
		saldo = saldo + cantidad;
	}
	
	public synchronized void sacar(double cantidad) {
		saldo = saldo - cantidad;
	}

	public String getNUMERO() {
		return NUMERO;
	}
	
	

}
