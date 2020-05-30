package game;

//ESTA CLASSE FOI CRIADA AQUI APENAS COMO EXEMPLO. O ALUNO DEVE SUBSTITUI-LA POR OUTRA CLASSE CONFORME O PROJETO.

import java.awt.Dimension;
import utils.Location;

public class Barra extends DesenhoMovel {

	public Barra() {}

  public Barra(String path, Location location) {
    super(path, location);
  }

  public Barra(String path, Location location, Dimension size) {
    super(path, location, size);
  }
  
}
