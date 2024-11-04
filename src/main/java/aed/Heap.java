package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<Traslado>{
  private ArrayList<Traslado> elems;
  private Comparator<Traslado> comp;

  public Heap(Traslado[] t, Comparator<Traslado> comp) {
    this.comp = comp;
    for (int i = 0; i < t.length; i++) {
      agregar(t[i]);
    }
  }

  private int hijo_izq(int i) {
      return (2 * i + 1);
  }
      
  private int hijo_der(int i) {
      return (2 * i + 2);
  }  

  private int padre(int i) {        
      return ( i - 1 / 2);             
  }        

  private void siftup(int i) {
    if (i > 0) {
      Traslado actual = elems.get(i); 
      Traslado padre = elems.get(padre(i));
      // Me fijo si el padre es mas grande/chico, si es asi los cambia de lugar
      if (comp.compare(actual, padre) > 0) {
        Traslado holder = padre;
        elems.set(padre(i), actual);
        elems.set(i, holder);
        siftup(padre(i));
      }
    }
  }

  private void siftdown(int i) {
    if (i <= ((elems.size()/2) - 1) ) {
      Traslado actual = elems.get(i); 
      Traslado hijoConMayorOrdenPrioridad = elems.get(hijo_izq(i));
      int index = hijo_izq(i);
      //Busco hijo mas grande
      if (hijo_der(i) < elems.size() && comp.compare(elems.get(hijo_der(i)), hijoConMayorOrdenPrioridad) > 0){
          hijoConMayorOrdenPrioridad = elems.get(hijo_der(i));
          index = hijo_der(i);
      }
      // Me fijo si el padre es mas grande/chico, si es asi los cambia de lugar
      if (comp.compare(hijoConMayorOrdenPrioridad, actual) > 0) {
        Traslado holder = actual;
        elems.set(i, hijoConMayorOrdenPrioridad);
        elems.set(index, holder);
        siftdown(index);
      }
    }
  }

  public void eliminar(int i){
      elems.set(i,elems.get(elems.size()-1));
      elems.remove(elems.size() - 1);
      siftdown(i);
  }

  public void agregar(Traslado traslado) {
      elems.add(traslado);
      if (elems.size() > 0) {
        siftup(elems.size() - 1);
      }
  }

  public Traslado verMayorPrioridad(){
      return elems.get(0);
  }

  public Traslado obtenerMayorPrioridad(){
      Traslado res = elems.get(0);
      eliminar(0);
      return res;
  }
}
