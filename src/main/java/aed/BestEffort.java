package aed;

import java.util.ArrayList;

public class BestEffort {

    private LibretaTraslados lTraslados;
    private LibretaCiudades lCiudades;

    public BestEffort(int cantCiudades, Traslado[] traslados) { 
        this.lTraslados = new LibretaTraslados(traslados);
        this.lCiudades = new LibretaCiudades(cantCiudades);
    }

    public void registrarTraslados(Traslado[] traslados) {   //O(t log t)
        for (int i = 0; i < traslados.length; i++) {
            lTraslados.agregarTraslado(traslados[i]);
        }
    }

    public int[] despacharMasRedituables(int n) { //O(n (log t + log c))
        int cantDespachos;
        if (n > lTraslados.cantTraslados()) {
            cantDespachos = lTraslados.cantTraslados();
        } else {
            cantDespachos = n;
        }
        int[] res = new int[cantDespachos];
        for (int i = 0; i < cantDespachos; i++) {
            Traslado trasladoDespachado = lTraslados.trasladoMasRedituable();
            res[i] = trasladoDespachado.id();
            lCiudades.sumarGanancia(trasladoDespachado.origen(), trasladoDespachado.gananciaNeta());
            lCiudades.sumarPerdida(trasladoDespachado.destino(), trasladoDespachado.gananciaNeta());
            lTraslados.despacharRedituable();
        }
        return res;
    }

    public int[] despacharMasAntiguos(int n) { //O(n (log t + log c))
        int cantDespachos;
        if (n > lTraslados.cantTraslados()) {
            cantDespachos = lTraslados.cantTraslados();
        } else {
            cantDespachos = n;
        }
        int[] res = new int[cantDespachos];
        for (int i = 0; i < cantDespachos; i++) {
            Traslado trasladoDespachado = lTraslados.trasladoMasAntiguo();
            res[i] = trasladoDespachado.id();
            lCiudades.sumarGanancia(trasladoDespachado.origen(), trasladoDespachado.gananciaNeta());
            lCiudades.sumarPerdida(trasladoDespachado.destino(), trasladoDespachado.gananciaNeta());
            lTraslados.despacharAntiguo();
        }
        return res;
    }

    public int ciudadConMayorSuperavit() { //O(1)
        return lCiudades.ciudadMayorSuperavit();
    }

    public ArrayList<Integer> ciudadesConMayorGanancia() { //O(1)
        return lCiudades.listaCiudadesMayorGanancia();
    }

    public ArrayList<Integer> ciudadesConMayorPerdida() { //O(1)
        return lCiudades.listaCiudadesMayorPerdida();
    }

    public int gananciaPromedioPorTraslado() { //O(1)
        return lTraslados.gananciaPromedio();
    }

}
