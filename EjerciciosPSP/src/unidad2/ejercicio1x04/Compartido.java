package unidad2.ejercicio1x04;

public class Compartido {
	private int numero;
	private boolean disponible = false; //elemento vac�a
	
	public synchronized int get(){
		while(!disponible){
			try{
				wait();
			}
			catch(InterruptedException e){
	        	System.err.println(e.toString());
	        }
		}
		disponible = false;
		notifyAll();
		return numero;
		 
	}
	
	public synchronized void set (int n){
		while(disponible){
			try{
				wait();
			}
			catch(InterruptedException e){
	        	System.err.println(e.toString());
	        }
		}
		numero = n;
		disponible = true;
		notifyAll();
	}
}
