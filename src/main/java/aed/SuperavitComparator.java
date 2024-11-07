package aed;

import java.util.Comparator;

public class SuperavitComparator implements Comparator<Ciudad>{
  @Override
  public int compare(Ciudad c1, Ciudad c2){
      if (c1.ganancia() - c1.perdida() == c2.ganancia() - c2.perdida()) {
          return Integer.compare(c1.id(),c2.id());
      }
      return Integer.compare(c1.ganancia() - c1.perdida(),c2.ganancia() - c2.perdida());
  }
}