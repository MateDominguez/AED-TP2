package aed;

public class Ciudad {
  int id;
  int ganancia;
  int perdida;
  int handleSuperavit;

  public Ciudad(int id){
      this.id = id;
      ganancia = 0;
      perdida = 0;
  }

  public void modificarHandleSuperavit(int valor){
    handleSuperavit = valor;
  }

}
