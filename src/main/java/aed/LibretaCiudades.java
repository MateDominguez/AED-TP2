package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class LibretaCiudades {
    private Ciudad[] ciudades;
    private Ciudad ciudadMayorSuperavit;
    private ArrayList<Ciudad> ciudadesMayorPerdida;
    private ArrayList<Ciudad> ciudadesMayorGanancia;

    private class Ciudad {
        int ganancia;
        int perdida;

        public Ciudad(){
            ganancia = 0;
            perdida = 0;
        }

        public Ciudad(int gan,int per){
            this.ganancia = gan;
            this.perdida = per;
            }
        }

        public LibretaCiudades(int cantCiudades){
            ciudades = new Ciudad[cantCiudades];
            ciudadesMayorGanancia = new ArrayList<>();
            ciudadesMayorPerdida = new ArrayList<>();
            ciudadMayorSuperavit = null;
        }

        private class gananciaComparator implements Comparator<Ciudad>{
            @Override
            public int compare(Ciudad c1, Ciudad c2){
                return Integer.compare(c1.ganancia,c2.ganancia);
            }
        }

        private class perdidaComparator implements Comparator<Ciudad>{
            @Override
            public int compare(Ciudad c1, Ciudad c2){
                return Integer.compare(c1.perdida,c2.perdida);
            }
        }

        private class superavitComparator implements Comparator<Ciudad>{
            @Override
            public int compare(Ciudad c1, Ciudad c2){
                if (c1.ganancia - c1.perdida == c2.ganancia - c2.perdida) {
                    return Integer.compare(c1.id,c2.id);
                }
                return Integer.compare(c1.ganancia - c1.perdida,c2.ganancia - c2.perdida);
            }
        }

        private void sumarGanancia(int ciudad, int valor){
            int mayorGanancia = 0;
            if (ciudadesMayorGanancia.size() > 0) {
                mayorGanancia = ciudadesMayorGanancia.get(0).ganancia;
            }
            if (ciudades[ciudad] == null) {
                Ciudad nuevaCiudad = new Ciudad();
                nuevaCiudad.ganancia = valor;
                nuevaCiudad.perdida = 0;
                ciudades[ciudad] = nuevaCiudad;
            } else {
                ciudades[ciudad].ganancia = ciudades[ciudad].ganancia + valor;
            }
            if (ciudadesMayorGanancia.size() > 0 && ciudades[ciudad].ganancia > mayorGanancia){
                ciudadesMayorGanancia.clear();
                ciudadesMayorGanancia.add(ciudades[ciudad]);
            } actualizarMayorSuperavit(ciudad);
        }

        private void sumarPerdida(int ciudad, int valor){
            int mayorPerdida = 0;
            if (ciudadesMayorPerdida.size() > 0) {
                mayorPerdida = ciudadesMayorPerdida.get(0).perdida;
            }
            if (ciudades[ciudad] == null) {
                Ciudad nuevaCiudad = new Ciudad();
                nuevaCiudad.ganancia = 0;
                nuevaCiudad.perdida = valor;
                ciudades[ciudad] = nuevaCiudad;
            } else {
                ciudades[ciudad].perdida = ciudades[ciudad].perdida + valor;
            }
            if (ciudadesMayorPerdida.size() > 0 && ciudades[ciudad].perdida > mayorPerdida){
                ciudadesMayorPerdida.clear();
                ciudadesMayorPerdida.add(ciudades[ciudad]);
            } actualizarMayorSuperavit(ciudad);
        }

        private void actualizarMayorSuperavit(int ciudad) {
            if (ciudadMayorSuperavit == null) {
                ciudadMayorSuperavit = ciudades[ciudad];
            } else {
                superavitComparator comp = new superavitComparator();
                if (comp.compare(ciudadMayorSuperavit, ciudades[ciudad]) < 0) {
                    ciudadMayorSuperavit = ciudades[ciudad];
                }
            }
        }

        private Ciudad obtener(int ciudad){
            return ciudades[ciudad];
        }

        private int longitud(){
            return ciudades.length;
        }
    }

