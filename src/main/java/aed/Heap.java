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
    }//usamos el metodo Heapify. El cual tiene complejidad O(|T|). Despues solo asignamos cosas.

    private ArrayList<T> heapify(T[] elementos) { 
        ArrayList<T> heap = new ArrayList<>();
        for (int i = 0; i < elementos.length; i++) { //O(|elementos|)
            actualizarHandle(elementos[i], i);
            heap.add(elementos[i]);
        }
        for (int i = ((cantElems / 2) - 1); i >= 0; i--) { //O(1). iterno n veces algo que cuesta O(1)
            T actual = heap.get(i);
            T hijoConMayorOrdenPrioridad = heap.get(hijo_izq(i));
            int nuevoIndice = hijo_izq(i);
            //Busco hijo mas grande. O(1)
            if (hijo_der(i) < cantElems && comp.compare(heap.get(hijo_der(i)), hijoConMayorOrdenPrioridad) > 0) {
                hijoConMayorOrdenPrioridad = heap.get(hijo_der(i));
                nuevoIndice = hijo_der(i);
            }
            // Me fijo si el padre es mas grande/chico, si es asi los cambia de lugar. O(1)
            if (comp.compare(hijoConMayorOrdenPrioridad, actual) > 0) {
                heap.set(i, hijoConMayorOrdenPrioridad);
                heap.set(nuevoIndice, actual);
                actualizarHandle(hijoConMayorOrdenPrioridad, i);
                actualizarHandle(actual, nuevoIndice);
            }
        }
        return heap;
    }//O(|elementos|). Asignaciones, comparaciones, operaciones basicas. La complejidad viene de agregar al heap todos los elementos de la lista uno por uno.

    private int hijo_izq(int i) {
        return (2 * i + 1);
    }

    private int hijo_der(int i) {
        return (2 * i + 2);
    }

    private int padre(int i) {
        return ((i - 1) / 2);
    }//tanto hijo_izq, hijo_der como padre tienen complejidad O(1)

    private void siftup(int i) { 
        if (i > 0) {
            T actual = elems.get(i);
            T padre = elems.get(padre(i));
            // Me fijo si el padre es mas grande/chico, si es asi los cambia de lugar
            if (comp.compare(actual, padre) > 0) {
                elems.set(padre(i), actual);
                elems.set(i, padre);
                // Modifica los handles del padre y el hijo con la nueva posicion
                actualizarHandle(actual, padre(i));
                actualizarHandle(padre, i);
                siftup(padre(i));
            }
        }
    } //O(log n)

    private void siftdown(int indiceActual) { 
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
    }// O(log n)

    private void actualizarHandle(T elemento, int indice) { 
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
    }// O(1)

    public void actualizar(int i) { 
        siftup(i);
        siftdown(i);
    } //tanto el metodo siftup como el metodo siftdown son de orden O(log n).
    //O(log n)

    public void eliminar(int i) { 
        elems.set(i, elems.get(cantElems - 1));
        cantElems--;
        siftdown(i);
    }// O(log n). La complejidad sale de hacer siftdown.

    public void agregar(T elemento) {
        if (cantElems == elems.size()) {
            elems.add(elemento);
        } else {
            elems.set(cantElems, elemento);
        }
        siftup(cantElems);
        cantElems++;
    }// O(log n). La complejidad sale de hacer siftup.

    public T verMayorPrioridad() { 
        return elems.get(0);
    }// O(1)

    public T obtenerMayorPrioridad() { 
        T res = elems.get(0);
        eliminar(0);
        return res;
    }// O(log n). Este metodo elimina el de mayor prioridad ademas de que lo returnea. 
    //eliminar cuesta O(log n), por eso la complejidad

    public int cantElems() { 
        return cantElems;
    }// O(1)
}
