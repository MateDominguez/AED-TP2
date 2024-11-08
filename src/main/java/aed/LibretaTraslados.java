package aed;

public class LibretaTraslados {

    private Heap<Traslado> trasladosRedito;
    private Heap<Traslado> trasladosTimeStamp;
    private int cantTraslados;
    private int gananciaTotal;
    private int trasladosDespachados;

    public LibretaTraslados(Traslado[] traslados) {
        ReditoComparator compRedito = new ReditoComparator();
        TimeStampComparator compTimeStamp = new TimeStampComparator();
        this.trasladosRedito = new Heap<Traslado>(traslados, compRedito);
        this.trasladosTimeStamp = new Heap<>(traslados, compTimeStamp);
        this.cantTraslados = traslados.length;
        this.gananciaTotal = 0;
        this.trasladosDespachados = 0;
    }

    public void agregarTraslado(Traslado traslado) { //O(log n) porque agregar cuesta O(log n)
        trasladosRedito.agregar(traslado);
        trasladosTimeStamp.agregar(traslado);
        cantTraslados++;
    }

    public void despacharAntiguo() { //O(log n) porque obtenerMayorPrioridad cuesta O(log n)
        Traslado despachado = trasladosTimeStamp.obtenerMayorPrioridad(); //O(log n)
        trasladosDespachados++;
        gananciaTotal = gananciaTotal + despachado.gananciaNeta();
        trasladosRedito.eliminar(despachado.handleRedito()); //O(log n)
        //actualizarGananciaPromedio(despachado.gananciaNeta());
        cantTraslados--;
    }

    public void despacharRedituable() { //O(log n) idem que en despachatAntiguo
        Traslado despachado = trasladosRedito.obtenerMayorPrioridad();
        trasladosDespachados++;
        gananciaTotal = gananciaTotal + despachado.gananciaNeta();
        trasladosTimeStamp.eliminar(despachado.handleTimeStamp());
        //actualizarGananciaPromedio(despachado.gananciaNeta());
        cantTraslados--;
    }

    public Traslado trasladoMasRedituable() { //O(1) porque verMayorPrioridad cuesta O(1)
        return trasladosRedito.verMayorPrioridad();
    }

    public Traslado trasladoMasAntiguo() { //O(1) porque verMayorPrioridad cuesta O(1)
        return trasladosTimeStamp.verMayorPrioridad();
    }

    public int gananciaPromedio() { //O(1)
        return gananciaTotal / trasladosDespachados;
    }

    public int cantTraslados() { //O(1)
        return cantTraslados; 
    }

}
