package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class BestEffort {

    public BestEffort(int cantCiudades, Traslado[] traslados){
        LibretaTraslados envios = new LibretaTraslados();
        LibretaCiudades ciudades = new LibretaCiudades();

        for (int i = 0; i < traslados.length; i++){
            //envios.add(traslados[i]);
            if (ciudades.pertenece(buscar)){
                ciudades.set(traslados[i].origen, ciudades.get(buscarPorId(origen, ciudades)));
            }
        }
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