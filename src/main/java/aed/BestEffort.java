package aed;

import java.util.ArrayList;

public class BestEffort {
    private LibretaTraslados lTraslados;
    private LibretaCiudades lCiudades;

    public BestEffort(int cantCiudades, Traslado[] traslados){
        this.lTraslados = new LibretaTraslados(traslados);
        this.lCiudades = new LibretaCiudades(cantCiudades);
    }

    public void registrarTraslados(Traslado[] traslados){
        // Implementar
    }

    public int[] despacharMasRedituables(int n){
        // Implementar
        return null;
    }

    public int[] despacharMasAntiguos(int n){
        // Implementar
        return null;
    }

    public int ciudadConMayorSuperavit(){
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        // Implementar
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }
    
}