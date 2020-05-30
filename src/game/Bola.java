package game;

//ESTA CLASSE FOI CRIADA AQUI APENAS COMO EXEMPLO. O ALUNO DEVE SUBSTITUI-LA POR OUTRA CLASSE CONFORME O PROJETO.

import java.awt.Dimension;
import utils.Location;

public class Bola extends DesenhoAnimado {
	
  private double rotateDegrees = 6; //(360/60) -> 1 seg para rodar a bola
	private Thread t = new Thread(this);
  
	public Bola() {}

  public Bola(String path, Location location) {
    super(path, location);
    t.start();
  }

  public Bola(String path, Location location, Dimension size) {
    super(path, location, size);
    t.start();
  }
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(true) {
      this.rotateDisplayImg(rotateDegrees);
			try {
				t.sleep(1000/60); // 60 - frames por segundo
			} catch (InterruptedException e) {
        System.out.println(e.getMessage());
			}
      rotateDegrees+=3;
      rotateDegrees%=360;
		}
	}

}