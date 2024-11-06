package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class LibretaTraslados {
        private Heap<Traslado> traslados;
        private int gananciaPromedioGlobal;

        public LibretaTraslados(Traslado[] t, Comparator<Traslado> comp){
            traslados = new Heap<Traslado>(t, );
        
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

        private void eliminarPrimero(){         

        }

}
