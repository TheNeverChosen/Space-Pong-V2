package game.models;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA

import java.awt.Dimension;
import utils.Location;

//ELA DEVE SER UTILIZADA NA HERANÇA DE DESENHOS QUE POSSAM SER MOVIMENTADOS (ANIMADOS) DE FORMA AUTOMÁTICA
public abstract class DesenhoAnimado extends Desenho implements Runnable {
  
	public DesenhoAnimado() {}

  public DesenhoAnimado(String path, Dimension size) {
    super(path, size);
  }

  public DesenhoAnimado(String path, Location location, Dimension size) {
    super(path, location, size);
  }

}