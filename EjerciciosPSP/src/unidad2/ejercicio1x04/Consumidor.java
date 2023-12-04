package unidad2.ejercicio1x04;

public class Consumidor extends Thread
	{
	    private Compartido dato;
	    private int num;
	 
	   
	    public Consumidor(Compartido c, int n) 
	    {
	        this.dato = c;
	        this.num = n;
	    }
	 
	    public void run() 
	    {
	    	int valor = 0;
	    	for (int i=0; i<5;i++){
	    		valor = dato.get();
	    		System.out.println(i + " => Consumidor: " + num + ", consume: " + valor);
	    	}
	    }
	}