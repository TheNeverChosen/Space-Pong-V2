package game.entities;

import game.Principal;
import game.models.DesenhoMovel;
import java.awt.Dimension;
import utils.Location;

public class Enemy extends DesenhoMovel {

  public Enemy() {
  }

  public Enemy(Principal principal, double speed, String path, Dimension size) {
    super(principal, speed, path, size);
  }

  public Enemy(Principal principal, double speed, String path, Location location, Dimension size) {
    super(principal, speed, path, location, size);
  }
  
  public void followBall(){
    
    Ball ball = getPrincipal().getBall();
    
    double ballCenter = ball.getY()+(ball.getHeight()/2),
           enemyCenter = getY()+(getHeight()/2),
           dif = ballCenter-enemyCenter;
    
    if(dif!=0){
      if(dif>0) moverBaixo();
      else moverCima();
    }
    
  }
  
}
