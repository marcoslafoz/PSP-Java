package ejemplos.transferencias;

public class Transferencia extends Thread{
	private Cuenta cnt1;
	private Cuenta cnt2;
	private double cantidad;
	
	public Transferencia(Cuenta cnt1, Cuenta cnt2, double cantidad) {
		this.cnt1 = cnt1;
		this.cnt2 = cnt2;
		this.cantidad = cantidad;
	}
	
	@Override
	public void run() {
		//Se determina el orden de bloqueo de las cuentas para evitar interbloqueo
		Cuenta cMayor, cMenor;
		if(cnt1.getNUMERO().compareTo(cnt2.getNUMERO())<0) {
			cMayor = cnt1;
			cMenor = cnt2;
		}
		else {
			cMayor = cnt2;
			cMenor = cnt1;
		}
		synchronized (cMayor) {
			synchronized(cMenor) {
				cnt1.sacar(cantidad);
				cnt2.ingresar(cantidad);
			}
		}
	}

}
