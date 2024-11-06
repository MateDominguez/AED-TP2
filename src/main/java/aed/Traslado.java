package aed;

public class Traslado {
    
    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;
    int handleTimeStamp;
    int handleRedito;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
    }

    private void modificarHandleTimeStamp(int valor){
        handleTimeStamp = valor;
    }

    private void modificarHandleRedito(int valor){
        handleRedito = valor;
    }
}
