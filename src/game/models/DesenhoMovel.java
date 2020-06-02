package game.models;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA

import game.Principal;
import java.awt.Dimension;
import utils.Location;

//ELA DEVE SER UTILIZADA NA HERANÇA DE DESENHOS QUE POSSAM SER MOVIMENTADOS PELO USUÁRIO 
public class DesenhoMovel extends Desenho {
	
  private Principal principal;
  private double speed;
  
	public DesenhoMovel(){ }

  public DesenhoMovel(Principal principal, double speed, String path, Dimension size) {
    super(path, size);
    initiate(principal, speed);
  }

  public DesenhoMovel(Principal principal, double speed, String path, Location location, Dimension size) {
    super(path, location, size);
    initiate(principal, speed);
  }
  
  private void initiate(Principal principal, double speed){
    this.principal=principal;
    this.speed=speed;
  }
	
	public void moverDireita() {
    Location location = this.getLocation();
    double newX = location.getX()+speed;
    if(newX + getWidth() > principal.getRightLimit())
      newX = principal.getRightLimit() - getWidth();
    location.setX(newX);
	}
	
	public void moverEsquerda() {
		Location location = this.getLocation();
    double newX = location.getX()-speed;
    if(newX < principal.getLeftLimit())
      newX = principal.getLeftLimit();
    location.setX(newX);
	}
        
  public void moverCima() {
		Location location = this.getLocation();
    double newY=location.getY()-speed;
    if(newY < principal.getTopLimit())
      newY = principal.getTopLimit();
    location.setY(newY);
	}
	
	public void moverBaixo() {
		Location location = this.getLocation();
    double newY=location.getY()+speed;
    if(newY + getHeight() > principal.getBottomLimit())
      newY = principal.getBottomLimit() - getHeight();
    location.setY(newY);
	}

  public Principal getPrincipal() {
    return principal;
  }
  
}
