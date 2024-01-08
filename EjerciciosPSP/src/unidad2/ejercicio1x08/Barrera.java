package unidad2.ejercicio1x08;

public class Barrera {
    private Coche[] coches;     // Array que almacena los coches en el estacionamiento
    private int plazasLibres;    // Contador de plazas libres en el estacionamiento

    // Constructor que inicializa el estacionamiento con un número específico de plazas
    public Barrera(int numPlazas) {
        this.coches = new Coche[numPlazas];
        this.plazasLibres = numPlazas;
    }

    // Método sincronizado para que varios hilos no puedan entrar al mismo tiempo
    // Asigna un coche a una plaza disponible y devuelve el número de la plaza asignada
    public synchronized int entrar(Coche coche) {
        int plazaAsignada = -1;

        // Verifica si hay plazas libres
        if (plazasLibres > 0) {
            // Itera sobre las plazas disponibles
            for (int i = 0; i < coches.length; i++) {
                // Encuentra la primera plaza disponible
                if (coches[i] == null) {
                    plazaAsignada = i;
                    coches[i] = coche;    // Asigna el coche a la plaza
                    plazasLibres--;       // Reduce el contador de plazas libres
                    break;
                }
            }
        }

        return plazaAsignada;   // Devuelve el número de la plaza asignada o -1 si no hay plazas disponibles
    }

    // Método sincronizado para que varios hilos no puedan salir al mismo tiempo
    // Libera la plaza especificada y aumenta el contador de plazas libres
    public synchronized int salir(int plaza) {
        coches[plaza] = null;    // Libera la plaza
        plazasLibres++; 
        return plaza;         // Aumenta el contador de plazas libres
    }

    // Método sincronizado para mostrar el estado actual del estacionamiento
    public synchronized void mostrarEstado() {
        System.out.print("Parking: ");
        for (int i = 0; i < coches.length; i++) {
            if (coches[i] != null) {
                // Muestra información del coche en la plaza
                System.out.print("[C-" + coches[i].getCocheId() + "] ");
            } else {
                // Muestra una plaza vacía
                System.out.print("[0] ");
            }
        }
        System.out.println();
    }
}
