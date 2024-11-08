package aed;

import java.util.ArrayList;

public class LibretaCiudades {

    private Ciudad[] ciudades;
    private Heap<Ciudad> heapSuperavit;
    private ArrayList<Integer> ciudadesMayorPerdida;
    private ArrayList<Integer> ciudadesMayorGanancia;

    public LibretaCiudades(int cantCiudades) {
        SuperavitComparator comp = new SuperavitComparator();
        ciudadesMayorGanancia = new ArrayList<Integer>();
        ciudadesMayorPerdida = new ArrayList<Integer>();
        ciudades = new Ciudad[cantCiudades];
        for (int i = 0; i < cantCiudades; i++) {
            Ciudad ciudad = new Ciudad(i);
            ciudades[i] = ciudad;
        }
        this.heapSuperavit = new Heap<Ciudad>(ciudades, comp);

    }

    public void sumarGanancia(int ciudad, int valor) {
        ciudades[ciudad].modificarGanancia(ciudades[ciudad].ganancia() + valor);
        actualizarCiudadesMayorGanancia(ciudad);
        actualizarSuperavit(ciudad);
    }

    private void actualizarCiudadesMayorGanancia(int ciudad) {
        if (ciudadesMayorGanancia.size() == 0) {
            ciudadesMayorGanancia.add(ciudad);
        } else {
            int mayorGananciaActual = ciudades[ciudadesMayorGanancia.get(0)].ganancia();
            if (ciudades[ciudad].ganancia() > mayorGananciaActual) {
                ciudadesMayorGanancia.clear();
                ciudadesMayorGanancia.add(ciudad);
            } else if (ciudades[ciudad].ganancia() == mayorGananciaActual) {
                ciudadesMayorGanancia.add(ciudad);
            }
        }
    }

    public void sumarPerdida(int ciudad, int valor) {
        ciudades[ciudad].modificarPerdida(ciudades[ciudad].perdida() + valor);
        actualizarCiudadesMayorPerdida(ciudad);
        actualizarSuperavit(ciudad);
    }

    private void actualizarCiudadesMayorPerdida(int ciudad) {
        if (ciudadesMayorPerdida.size() == 0) {
            ciudadesMayorPerdida.add(ciudad);
        } else {
            int mayorPerdidaActual = ciudades[ciudadesMayorPerdida.get(0)].perdida();
            if (ciudades[ciudad].perdida() > mayorPerdidaActual) {
                ciudadesMayorPerdida.clear();
                ciudadesMayorPerdida.add(ciudad);
            } else if (ciudades[ciudad].perdida() == mayorPerdidaActual) {
                ciudadesMayorPerdida.add(ciudad);
            }
        }
    }

    private void actualizarSuperavit(int ciudad) {
        heapSuperavit.actualizar(ciudades[ciudad].handleSuperavit());
    }

    public int ciudadMayorSuperavit() {
        return heapSuperavit.verMayorPrioridad().id();
    }

    public ArrayList<Integer> listaCiudadesMayorPerdida() {
        return ciudadesMayorPerdida;
    }

    public ArrayList<Integer> listaCiudadesMayorGanancia() {
        return ciudadesMayorGanancia;
    }

}
