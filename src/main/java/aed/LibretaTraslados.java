package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class LibretaTraslados {
        private Heap<Traslado> trasladosRedito;
        private Heap<Traslado> trasladosTimeStamp;
        private float gananciaPromedioGlobal;

        public LibretaTraslados(Traslado[] traslados){
            ReditoComparator compRedito = new ReditoComparator();
            TimeStampComparator compTimeStamp = new TimeStampComparator();
            trasladosRedito = new Heap<Traslado>(traslados,compRedito);
            trasladosTimeStamp = new Heap<Traslado>(traslados,compTimeStamp);
            gananciaPromedioGlobal = 0;
        }

        public void agregarTraslado(Traslado traslado){
            trasladosRedito.agregar(traslado);
            trasladosTimeStamp.agregar(traslado);
        }

        public void despacharAntiguo(){
            Traslado despachado = trasladosTimeStamp.obtenerMayorPrioridad();
            trasladosRedito.eliminar(despachado.handleRedito());
            actualizarGananciaPromedio(despachado.gananciaNeta());
        }

        public void despacharRedituable(){
            Traslado despachado = trasladosRedito.obtenerMayorPrioridad();
            trasladosTimeStamp.eliminar(despachado.handleTimeStamp());
            actualizarGananciaPromedio(despachado.gananciaNeta());
        }
        
        public int idPrimerRedituable() {
            return trasladosRedito.verMayorPrioridad()
        }

        //falta idPrimerAntiguo

        private void actualizarGananciaPromedio(float ganancia){
            gananciaPromedioGlobal = (gananciaPromedioGlobal + ganancia) / 2;
        }

        public int gananciaPromedio(){
            return (int) gananciaPromedioGlobal;
        }

        public int cantTraslados() {
            return trasladosRedito.cantElems();
        }

}
