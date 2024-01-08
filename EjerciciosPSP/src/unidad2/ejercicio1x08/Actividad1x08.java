package unidad2.ejercicio1x08;

public class Actividad1x08 {
    private static final int NUMERO_SITIOS = 3;
    private static final int NUMERO_COCHES = 10;

    public static void main(String[] args) {
        Barrera barrera = new Barrera(NUMERO_SITIOS);

        Coche[] coches = new Coche[NUMERO_COCHES];
        for (int i = 0; i < NUMERO_COCHES; i++) {
            coches[i] = new Coche(i, barrera);
            coches[i].start();
        }
    }
}
