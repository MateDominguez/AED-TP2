package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T>{
  private ArrayList<T> elems;
  private Comparator<T> comp;
  private int cantElems;

  public Heap(T[] elementos, Comparator<T> comp) {
    this.comp = comp;
    this.elems = heapify(elementos);
    this.cantElems = elems.size();
  }

  private ArrayList<T> heapify (T[] elementos) {
    ArrayList<T> heap = new ArrayList<>();
    for (int i = 0; i < elementos.length; i++) {
      actualizarHandle(elementos[i], i);
      heap.add(elementos[i]);
    }
    for (int i = ((heap.size() / 2) - 1); i >= 0; i--) {
      T actual = heap.get(i);
      T hijoConMayorOrdenPrioridad = (T) heap.get(hijo_izq(i));
      int index = hijo_izq(i);
      //Busco hijo mas grande
      if (hijo_der(i) < heap.size() && comp.compare(heap.get(hijo_der(i)), hijoConMayorOrdenPrioridad) > 0){
          hijoConMayorOrdenPrioridad = heap.get(hijo_der(i));
          index = hijo_der(i);
      }
      // Me fijo si el padre es mas grande/chico, si es asi los cambia de lugar
      if (comp.compare(hijoConMayorOrdenPrioridad, actual) > 0) {
        T holder = actual;
        heap.set(i, hijoConMayorOrdenPrioridad);
        heap.set(index, holder);
        actualizarHandle(hijoConMayorOrdenPrioridad, i);
        actualizarHandle(holder, index);
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
      return ( (i - 1) / 2);             
  }        

  private void siftup(int i) { // O(log n)
    if (i > 0) {
      T actual = elems.get(i); 
      T padre = elems.get(padre(i));
      // Me fijo si el padre es mas grande/chico, si es asi los cambia de lugar
      if (comp.compare(actual, padre) > 0) {
        T holder = padre;
        elems.set(padre(i), actual);
        elems.set(i, holder);
        // Si rs de tipo Traslado, modifica los handles
        actualizarHandle(actual, padre(i));
        actualizarHandle(holder, i);
        siftup(padre(i));
      } else {
        actualizarHandle(actual, i);
        actualizarHandle(padre, padre(i));
      }
    }
  }

  private void siftdown(int i) { // O(log n)
    if (i <= ((elems.size()/2) - 1)) {
      T actual = elems.get(i); 
      T hijoConMayorOrdenPrioridad = elems.get(hijo_izq(i));
      int index = hijo_izq(i);
      //Busco hijo mas grande
      if (hijo_der(i) < elems.size() && comp.compare(elems.get(hijo_der(i)), hijoConMayorOrdenPrioridad) > 0){
          hijoConMayorOrdenPrioridad = elems.get(hijo_der(i));
          index = hijo_der(i);
      }
      // Me fijo si el padre es mas grande/chico, si es asi los cambia de lugar
      if (comp.compare(hijoConMayorOrdenPrioridad, actual) > 0) {
        T holder = actual;
        elems.set(i, hijoConMayorOrdenPrioridad);
        elems.set(index, holder);
        actualizarHandle(hijoConMayorOrdenPrioridad, i);
        actualizarHandle(holder, index);
        siftdown(index);
      } else {
        actualizarHandle(actual, i);
        actualizarHandle(hijoConMayorOrdenPrioridad, index);
      }
    }
  }

  private void actualizarHandle(T elemento, int indice) { // O(1)
    if (elemento instanceof Traslado) { 
      if (comp instanceof TimeStampComparator) {
          Traslado elem = (Traslado) elemento;
          elem.modificarHandleTimeStamp(indice);
      } else if(comp instanceof ReditoComparator) {
        Traslado elem = (Traslado) elemento;
        elem.modificarHandleRedito(indice);
      }    
    } else if (elemento instanceof Ciudad) {
      Ciudad elem = (Ciudad) elemento;
      elem.modificarHandleSuperavit(indice);
    }
      
  }

  public void actualizar(int i){ // O(log n)
    siftup(i);
    siftdown(i);
  }

  public void eliminar(int i){
      elems.set(i,elems.get(cantElems-1));
      siftdown(i);
      cantElems--;
  }

  public  void agregar(T elemento) {
      elems.add(elemento);
      siftup(elems.size() - 1);
      cantElems++;
  }

  public T verMayorPrioridad(){
      return elems.get(0);
  }

  public T obtenerMayorPrioridad(){
      T res = elems.get(0);
      eliminar(0);
      return res;
  }
}
