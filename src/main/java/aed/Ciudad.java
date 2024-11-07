package aed;

public class Ciudad {
  private int id;
  private int ganancia;
  private int perdida;
  private int handleSuperavit;

  public Ciudad(int id){
      this.id = id;
      this.ganancia = 0;
      this.perdida = 0;
  }

  public void modificarHandleSuperavit(int valor){
    this.handleSuperavit = valor;
  }

  public int id(){
    return id;
  }

  public int ganancia(){
    return ganancia;
  }

  public int perdida(){
    return perdida;
  }

  public int handleSuperavit(){
    return handleSuperavit;
  }

}
