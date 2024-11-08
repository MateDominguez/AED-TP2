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

    public void agregarTraslado(Traslado traslado) {
        trasladosRedito.agregar(traslado);
        trasladosTimeStamp.agregar(traslado);
        cantTraslados++;
    }

    public void despacharAntiguo() {
        Traslado despachado = trasladosTimeStamp.obtenerMayorPrioridad();
        trasladosDespachados++;
        gananciaTotal = gananciaTotal + despachado.gananciaNeta();
        trasladosRedito.eliminar(despachado.handleRedito());
        //actualizarGananciaPromedio(despachado.gananciaNeta());
        cantTraslados--;
    }

    public void despacharRedituable() {
        Traslado despachado = trasladosRedito.obtenerMayorPrioridad();
        trasladosDespachados++;
        gananciaTotal = gananciaTotal + despachado.gananciaNeta();
        trasladosTimeStamp.eliminar(despachado.handleTimeStamp());
        //actualizarGananciaPromedio(despachado.gananciaNeta());
        cantTraslados--;
    }

    public Traslado trasladoMasRedituable() {
        return trasladosRedito.verMayorPrioridad();
    }

    public Traslado trasladoMasAntiguo() {
        return trasladosTimeStamp.verMayorPrioridad();
    }

    public int gananciaPromedio() {
        return gananciaTotal / trasladosDespachados;
    }

    public int cantTraslados() {
        return cantTraslados;
    }

}
