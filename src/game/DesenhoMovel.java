package game;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA

import java.awt.Dimension;
import utils.Location;

//ELA DEVE SER UTILIZADA NA HERANÇA DE DESENHOS QUE POSSAM SER MOVIMENTADOS PELO USUÁRIO 
public class DesenhoMovel extends Desenho {
	
	public DesenhoMovel() {}

  public DesenhoMovel(String path, Location location) {
    super(path, location);
  }

  public DesenhoMovel(String path, Location location, Dimension size) {
    super(path, location, size);
  }
	
	public void moverDireita() {
    Location location = this.getLocation();
    location.setX(location.getX()+10);
	}
	
	public void moverEsquerda() {
		Location location = this.getLocation();
    location.setX(location.getX()-10);
	}
        
  public void moverCima() {
		Location location = this.getLocation();
    location.setY(location.getY()-10);
	}
	
	public void moverBaixo() {
		Location location = this.getLocation();
    location.setY(location.getY()+10);
	}
}
