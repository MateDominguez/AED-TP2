package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {

    private ArrayList<T> elems;
    private Comparator<T> comp;
    private int cantElems;

    public Heap(T[] elementos, Comparator<T> comp) {
        this.comp = comp;
        this.cantElems = elementos.length;
        this.elems = heapify(elementos);
    }

    private ArrayList<T> heapify(T[] elementos) {
        ArrayList<T> heap = new ArrayList<>();
        for (int i = 0; i < elementos.length; i++) {
            actualizarHandle(elementos[i], i);
            heap.add(elementos[i]);
        }
        for (int i = ((cantElems / 2) - 1); i >= 0; i--) {
            T actual = heap.get(i);
            T hijoConMayorOrdenPrioridad = heap.get(hijo_izq(i));
            int nuevoIndice = hijo_izq(i);
            //Busco hijo mas grande
            if (hijo_der(i) < cantElems && comp.compare(heap.get(hijo_der(i)), hijoConMayorOrdenPrioridad) > 0) {
                hijoConMayorOrdenPrioridad = heap.get(hijo_der(i));
                nuevoIndice = hijo_der(i);
            }
            // Me fijo si el padre es mas grande/chico, si es asi los cambia de lugar
            if (comp.compare(hijoConMayorOrdenPrioridad, actual) > 0) {
                heap.set(i, hijoConMayorOrdenPrioridad);
                heap.set(nuevoIndice, actual);
                actualizarHandle(hijoConMayorOrdenPrioridad, i);
                actualizarHandle(actual, nuevoIndice);
            }
        }
        return heap;
    }

    private int hijo_izq(int i) {
        return (2 * i + 1);
    }

    private int hijo_der(int i) {
        return (2 * i + 2);
    }

    private int padre(int i) {
        return ((i - 1) / 2);
    }

    private void siftup(int i) { // O(log n)
        if (i > 0) {
            T actual = elems.get(i);
            T padre = elems.get(padre(i));
            // Me fijo si el padre es mas grande/chico, si es asi los cambia de lugar
            if (comp.compare(actual, padre) > 0) {
                elems.set(padre(i), actual);
                elems.set(i, padre);
                // Si rs de tipo Traslado, modifica los handles
                actualizarHandle(actual, padre(i));
                actualizarHandle(padre, i);
                siftup(padre(i));
            }
        }
    }

    private void siftdown(int indiceActual) { // O(log n)
        if (indiceActual <= ((cantElems / 2) - 1)) {
            T actual = elems.get(indiceActual);
            T hijoConMayorOrdenPrioridad = elems.get(hijo_izq(indiceActual));
            int nuevoIndice = hijo_izq(indiceActual);
            // Busco hijo mas grande
            if (hijo_der(indiceActual) < cantElems && comp.compare(elems.get(hijo_der(indiceActual)), hijoConMayorOrdenPrioridad) > 0) {
                hijoConMayorOrdenPrioridad = elems.get(hijo_der(indiceActual));
                nuevoIndice = hijo_der(indiceActual);
            }
            // Me fijo si el padre es mas grande/chico, si es asi los cambia de lugar
            if (comp.compare(hijoConMayorOrdenPrioridad, actual) > 0) {
                elems.set(indiceActual, hijoConMayorOrdenPrioridad);
                elems.set(nuevoIndice, actual);
                actualizarHandle(hijoConMayorOrdenPrioridad, indiceActual);
                actualizarHandle(actual, nuevoIndice);
                siftdown(nuevoIndice);
            }
        }
    }

    private void actualizarHandle(T elemento, int indice) { // O(1)
        if (elemento instanceof Traslado) {
            if (comp instanceof TimeStampComparator) {
                Traslado elem = (Traslado) elemento;
                elem.modificarHandleTimeStamp(indice);
            } else if (comp instanceof ReditoComparator) {
                Traslado elem = (Traslado) elemento;
                elem.modificarHandleRedito(indice);
            }
        } else if (elemento instanceof Ciudad) {
            Ciudad elem = (Ciudad) elemento;
            elem.modificarHandleSuperavit(indice);
        }
    }

    public void actualizar(int i) { // O(log n)
        siftup(i);
        siftdown(i);
    }

    public void eliminar(int i) { // O(log n)
        elems.set(i, elems.get(cantElems - 1));
        cantElems--;
        siftdown(i);
    }

    public void agregar(T elemento) { // O(log n)
        if (cantElems == elems.size()) {
            elems.add(elemento);
        } else {
            elems.set(cantElems, elemento);
        }
        siftup(cantElems);
        cantElems++;
    }

    public T verMayorPrioridad() { // O(1)
        return elems.get(0);
    }

    public T obtenerMayorPrioridad() { // O(log n)
        T res = elems.get(0);
        eliminar(0);
        return res;
    }

    public int cantElems() { // O(1)
        return cantElems;
    }
}
