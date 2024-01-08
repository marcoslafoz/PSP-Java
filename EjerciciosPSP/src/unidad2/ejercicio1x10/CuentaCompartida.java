package unidad2.ejercicio1x10;

public class CuentaCompartida {
    private double totalCaja = 0;

    public synchronized void agregarCuenta(double cuenta) {
        totalCaja += cuenta;
    }

    public double obtenerTotalCaja() {
        return totalCaja;
    }
}