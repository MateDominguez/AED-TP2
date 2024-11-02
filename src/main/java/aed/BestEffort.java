package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class BestEffort {

    public class ABB<Integer> {
        private Nodo raiz;
    
        private class Nodo {
            Nodo izquierda;
            Nodo derecha;
            Nodo padre;
            int valor;
            int indice;
    
            public Nodo(int valor, int indice) {
                this.valor = valor;
                this.indice = indice;
            }
        }
    
        public ABB() {
            this.raiz = null;
        }

        public void insertar(int id,int indice){
            Nodo nodo = this.raiz;
            Nodo nuevo = new Nodo(id,indice);
            if (nodo == null) {
                this.raiz = nuevo;
            } else {
                this.insertarRecursivo(nuevo, nodo);
            }
        }
    
        public void insertarRecursivo (Nodo nuevo, Nodo arbol) {
            if (nuevo.valor > arbol.valor) {
                if (arbol.derecha == null) {
                    arbol.derecha = insertarNodo(nuevo, arbol,arbol.derecha);
                }
                else insertarRecursivo(nuevo, arbol.derecha);
            }
            else {
                if (arbol.izquierda == null) {
                    arbol.izquierda = insertarNodo(nuevo, arbol,arbol.izquierda);
                }
                else {
                    insertarRecursivo(nuevo, arbol.izquierda);
                }
            }
        }
        public Nodo insertarNodo(Nodo nuevo, Nodo arbol, Nodo arbolhijo){
            arbolhijo = nuevo;
            nuevo.padre = arbol;
            return nuevo;
        }

        public void eliminar(T elem){
            Nodo nodo = this.raiz;
            raiz = eliminarRecursivo(elem, nodo);
        }
    
        public Nodo eliminarRecursivo(int elem, Nodo arbol){
            if (elem == arbol.valor) {
               if (arbol.izquierda == null) {
                return arbol.derecha;
               }
               else if (arbol.derecha == null) {
                return arbol.izquierda;
               }
               else { 
                arbol.valor = sucesor(arbol.derecha).valor;
                arbol.derecha = eliminarRecursivo(sucesor(arbol.derecha).valor, arbol.derecha);
               }
            }
            else if (elem > arbol.valor) {
                arbol.derecha = eliminarRecursivo(elem, arbol.derecha);
            }
                
            else if (elem < arbol.valor) {
                arbol.izquierda = eliminarRecursivo(elem, arbol.izquierda);
            }
            return arbol;
        }
        
        public Nodo sucesor(Nodo nodo) {
            while (nodo.izquierda != null) {
                nodo = nodo.izquierda;
            }
            return nodo;
           
        }
    }

    private class LibretaTraslados {
        private ArrayList<Traslado> traslados;
        private ABB<Integer> mapeo;

        public LibretaTraslados(){
            traslados = new ArrayList<Traslado>();
        }

        private class timeStampComparator implements Comparator<Traslado>{
            @Override
            public int compare(Traslado t1, Traslado t2){
                return Integer.compare(t1.timestamp,t2.timestamp);
            }
        }
    
        private class reditoComparator implements Comparator<Traslado>{
            @Override
            public int compare(Traslado t1, Traslado t2){
                if (t1.gananciaNeta == t2.gananciaNeta){
                    return Integer.compare(t1.id,t2.id);
                } else {
                    return Integer.compare(t1.gananciaNeta, t2.gananciaNeta);
                }
            }
        }

        private void agregarTrasladoPorTimestamp(Traslado t){
            if (traslados.size() == 0) {
                traslados.add(t);
            } else {
                traslados.add(t);
                timeStampComparator comp = new timeStampComparator();
                Traslado padre = traslados.get(traslados.size() - 2 / 2);
                while (comp.compare(t ,padre) < 0) {
                    Traslado holder = padre;
                    padre = t;
                    t = holder;
                }
            }
        }

        private void agregarTrasladoPorRedito(Traslado t){
            if (traslados.size() == 0) {
                traslados.add(t);
            } else {
                traslados.add(t);
                reditoComparator comp = new reditoComparator();
                Traslado padre = traslados.get(traslados.size() - 2 / 2);
                while (comp.compare(t ,padre) > 0) {
                    Traslado holder = padre;
                    padre = t;
                    t = holder;
                }
            }
        }

        private void eliminar(Traslado t){
            traslados.
        }
    }

    private class LibretaCiudades {
        private ArrayList<Ciudad> ciudades;

        private class Ciudad {
            int id;
            int ganancia;
            int perdida;

            public Ciudad(int id){
                this.id = id;
                ganancia = 0;
                perdida = 0;
            }

            public Ciudad(int id,int gan,int per){
                this.id = id;
                this.ganancia = gan;
                this.perdida = per;
            }
        }

        public LibretaCiudades(){
            ciudades = new ArrayList<Ciudad>();
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

        private void agregarCiudadPorCriterio(Ciudad c, String criterio){
            if (ciudades.size() == 0) {
                ciudades.add(c);
            } else {
                ciudades.add(c);
                if ("ganancia".equals(criterio)) {
                    gananciaComparator comp = new gananciaComparator();
                } else if ("perdida".equals(criterio)) {
                    perdidaComparator comp = new perdidaComparator();
                } else if ("superavit".equals(criterio)) {
                    superavitComparator comp = new superavitComparator();
                }
                Ciudad padre = ciudades.get(ciudades.size() - 2 / 2);
                while (comp.compare(c ,padre) > 0) {
                    Ciudad holder = padre;
                    //new Ciudad(padre.id, padre.ganancia, padre.perdida);
                    padre = c;
                    c = holder;
                }
            }
        }

        private int buscarPorId(int id, LibretaCiudades lCiudades){
            int res = -1;
            for (int i = 0; i < lCiudades.ciudades.size(); i++){
                if (lCiudades.ciudades.get(i).id == id) {
                    res = i;
                }
            }
            return res;
        }

        private void sumarGanancia(Ciudad c, int valor){
            c.ganancia = c.ganancia + valor;
        }

        private void sumarPerdida(Ciudad c, int valor){
            c.perdida = c.perdida + valor;
        }

        private Ciudad obtener(int index){
            return ciudades.get(index);
        }

        private boolean pertenece(Ciudad c){
            return ciudades.contains(c);
        }

        private void definir(int index, Ciudad c){
            ciudades.set(index, c);
        }

        private int longitud(){
            return ciudades.size();
        }
    }

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