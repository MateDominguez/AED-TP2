package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class LibretaCiudades {
    private Ciudad[] ciudades;
    private Heap<Ciudad> heapSuperavit;
    private ArrayList<Integer> ciudadesMayorPerdida;
    private ArrayList<Integer> ciudadesMayorGanancia;
    
    public LibretaCiudades(int cantCiudades){
        SuperavitComparator comp = new SuperavitComparator();
        ciudadesMayorGanancia = new ArrayList<>();
        ciudadesMayorPerdida = new ArrayList<>();
        ciudades = new Ciudad[cantCiudades];
        for (int i = 0; i < cantCiudades; i++) {
            Ciudad ciudad = new Ciudad(i);
            ciudad.ganancia() = 0;
            ciudad.perdida() = 0;
            ciudades[i] = ciudad;
        }
        this.heapSuperavit = new Heap<>(ciudades, comp);
    }

    private class gananciaComparator implements Comparator<Ciudad>{
        @Override
        public int compare(Ciudad c1, Ciudad c2){
            return Integer.compare(c1.ganancia(),c2.ganancia());
        }
    }

    private class perdidaComparator implements Comparator<Ciudad>{
        @Override
        public int compare(Ciudad c1, Ciudad c2){
            return Integer.compare(c1.perdida(),c2.perdida());
        }
    }

    private void sumarGanancia(int ciudad, int valor){
        ciudades[ciudad].ganancia() = ciudades[ciudad].ganancia() + valor;
        if (ciudadesMayorPerdida.size() == 0) {
            ciudadesMayorPerdida.add(ciudad);
            actualizarSuperavit(ciudad);
            return;
        }
        int mayorGananciaActual = ciudades[ciudadesMayorGanancia.get(0)].ganancia();
        if (ciudadesMayorGanancia.size() > 0 && ciudades[ciudad].ganancia() > mayorGananciaActual){
            ciudadesMayorGanancia.clear();
            ciudadesMayorGanancia.add(ciudad);
        } else {
            ciudadesMayorPerdida.add(ciudad);
        }
        actualizarSuperavit(ciudad);
    }

    private void sumarPerdida(int ciudad, int valor){
        ciudades[ciudad].perdida() = ciudades[ciudad].perdida() + valor;
        if (ciudadesMayorPerdida.size() == 0) {
            ciudadesMayorPerdida.add(ciudad);
            actualizarSuperavit(ciudad);
            return;
        } 
        int mayorPerdidaActual = ciudades[ciudadesMayorPerdida.get(0)].perdida();
        if (ciudadesMayorPerdida.size() > 0 && ciudades[ciudad].perdida() > mayorPerdidaActual){
            ciudadesMayorPerdida.clear();
            ciudadesMayorPerdida.add(ciudad);
        } else {
            ciudadesMayorPerdida.add(ciudad);
        }
        actualizarSuperavit(ciudad);
    }

    public int ciudadMayorSuperavit(){
        return heapSuperavit.verMayorPrioridad().id();
    }

    public ArrayList<Integer> listaCiudadesMayorPerdida() {
        return ciudadesMayorPerdida;
    }

    public ArrayList<Integer> listaCiudadesMayorGanancia() {
        return ciudadesMayorGanancia;
    }

    private void actualizarSuperavit(int ciudad){
        heapSuperavit.actualizar(ciudades[ciudad].handleSuperavit());
    }

    private Ciudad obtener(int ciudad){
        return ciudades[ciudad];
    }

    private int longitud(){
        return ciudades.length;
    }
}

