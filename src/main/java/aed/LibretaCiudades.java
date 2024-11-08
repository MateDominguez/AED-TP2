package aed;

import java.util.ArrayList;

public class LibretaCiudades {

    private Ciudad[] ciudades;
    private Heap<Ciudad> heapSuperavit;
    private ArrayList<Integer> ciudadesMayorPerdida;
    private ArrayList<Integer> ciudadesMayorGanancia;

    public LibretaCiudades(int cantCiudades) { //O(c)
        SuperavitComparator comp = new SuperavitComparator();
        ciudadesMayorGanancia = new ArrayList<>();
        ciudadesMayorPerdida = new ArrayList<>();
        ciudades = new Ciudad[cantCiudades];
        for (int i = 0; i < cantCiudades; i++) {
            Ciudad ciudad = new Ciudad(i);
            ciudades[i] = ciudad;
        }
        this.heapSuperavit = new Heap<>(ciudades, comp);

    }

    public void sumarGanancia(int ciudad, int valor) { //O(log n)
        int mayorGananciaActual = 0;
        if (!ciudadesMayorGanancia.isEmpty()) {
            mayorGananciaActual = ciudades[ciudadesMayorGanancia.get(0)].ganancia();
        }
        ciudades[ciudad].modificarGanancia(ciudades[ciudad].ganancia() + valor);
        actualizarCiudadesMayorGanancia(ciudad, mayorGananciaActual);
        actualizarSuperavit(ciudad);
    }

    private void actualizarCiudadesMayorGanancia(int ciudad, int mayorGanancia) { //O(log n)
        if (ciudadesMayorGanancia.isEmpty()) {
            ciudadesMayorGanancia.add(ciudad); //O(log n)
        } else {
            if (ciudades[ciudad].ganancia() > mayorGanancia) {
                ciudadesMayorGanancia.clear(); //O(1)
                ciudadesMayorGanancia.add(ciudad); //O(log n)
            } else if (ciudades[ciudad].ganancia() == mayorGanancia) {
                ciudadesMayorGanancia.add(ciudad); //O(log n)
            }
        }
    }

    public void sumarPerdida(int ciudad, int valor) { //O(log n)
        int mayorPerdidaActual = 0;
        if (!ciudadesMayorPerdida.isEmpty()) {
            mayorPerdidaActual = ciudades[ciudadesMayorPerdida.get(0)].perdida();
        }
        ciudades[ciudad].modificarPerdida(ciudades[ciudad].perdida() + valor);
        actualizarCiudadesMayorPerdida(ciudad, mayorPerdidaActual);
        actualizarSuperavit(ciudad);
    }

    private void actualizarCiudadesMayorPerdida(int ciudad, int mayorPerdida) { //O(log n)
        if (ciudadesMayorPerdida.isEmpty()) {
            ciudadesMayorPerdida.add(ciudad);
        } else {
            if (ciudades[ciudad].perdida() > mayorPerdida) {
                ciudadesMayorPerdida.clear();
                ciudadesMayorPerdida.add(ciudad);
            } else if (ciudades[ciudad].perdida() == mayorPerdida) {
                ciudadesMayorPerdida.add(ciudad);
            }
        }
    }

    private void actualizarSuperavit(int ciudad) { //O(log n)
        heapSuperavit.actualizar(ciudades[ciudad].handleSuperavit());
    }

    public int ciudadMayorSuperavit() { //O(1)
        return heapSuperavit.verMayorPrioridad().id(); //O(1)
    }

    public ArrayList<Integer> listaCiudadesMayorPerdida() {
        return ciudadesMayorPerdida;
    }

    public ArrayList<Integer> listaCiudadesMayorGanancia() {
        return ciudadesMayorGanancia;
    }

}
