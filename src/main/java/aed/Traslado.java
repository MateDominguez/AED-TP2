package aed;

public class Traslado {

    private int id;
    private int origen;
    private int destino;
    private int gananciaNeta;
    private int timestamp;
    private int handleTimeStamp;
    private int handleRedito;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
    }

    public void modificarHandleTimeStamp(int valor) {
        this.handleTimeStamp = valor;
    }

    public void modificarHandleRedito(int valor) {
        this.handleRedito = valor;
    }

    public int id() {
        return id;
    }

    public int origen() {
        return origen;
    }

    public int destino() {
        return destino;
    }

    public int gananciaNeta() {
        return gananciaNeta;
    }

    public int timestamp() {
        return timestamp;
    }

    public int handleTimeStamp() {
        return handleTimeStamp;
    }

    public int handleRedito() {
        return handleRedito;
    }

}
