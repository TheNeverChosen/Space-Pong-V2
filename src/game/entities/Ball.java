package game.entities;

//ESTA CLASSE FOI CRIADA AQUI APENAS COMO EXEMPLO. O ALUNO DEVE SUBSTITUI-LA POR OUTRA CLASSE CONFORME O PROJETO.

import game.Principal;
import game.models.DesenhoAnimado;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;
import utils.Angle;
import utils.Location;

public class Ball extends DesenhoAnimado {
	
  private double rotateDegrees, speed;
	private Thread t = new Thread(this);
  private Principal principal;
  private Angle dAngle;
  private boolean enemyFollow, repeat;
  
	public Ball() { }

  public Ball(Principal principal, String path, Dimension size) {
    super(path, size);
    initiate(principal);
  }

  public Ball(Principal principal, String path, Location location, Dimension size) {
    super(path, location, size);
    initiate(principal);
  }
  
  private void initiate(Principal principal){
    this.principal = principal;
    this.rotateDegrees = 0;
    this.repeat = true;
    this.speed = (principal.getScreenWidth()/3)/60;
    dAngle = new Angle(0, Angle.Units.DEGREES);
    enemyFollow = true;
    t.start();
  }
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(repeat) {
      tick();
      if(enemyFollow) moveEnemy();
      this.rotateDisplayImg(rotateDegrees);
			try {
				t.sleep(1000/60); // 60 -> frames por segundo
			} catch (InterruptedException e) {
        System.out.println(e.getMessage());
			}
      rotateDegrees+=3;
      rotateDegrees%=360;
		}
	}
  
  public void setRepeat(boolean repeat){
    this.repeat = false;
  }
  
  public int round(double value){
    return (int) Math.round(value);
  }
  
  public void reset(Location location, boolean playerServe){
    setLocation(location);
    this.speed = (principal.getScreenWidth()/3)/60;
    double degrees = playerServe ? 0 : 180;
    dAngle = new Angle(degrees, Angle.Units.DEGREES);
    enemyFollow = degrees==0.0;
  }
  
  private void increaseSpeed(){
    /*depois de 20 chamadas deste método (20 rebatidas com a bola), a bolinha
      soma + 1/3 de tela por segundo da speed atual*/
    speed+=(principal.getWidth()/(3.0*20.0))/60;
  }
  
  private void moveEnemy(){
    principal.getEnemy().followBall();
  }
  
  private void tick(){
    double x = getLocation().getX(), y = getLocation().getY(),
          dx = dAngle.getCos(), dy = dAngle.getSin();
    
    int width = getWidth(), height = getHeight();
    
    //para fazer a bola rebater nas paredes
    if(round(y + (dy * speed)) < principal.getTopLimit()){
      dy*=-1; // bateu no topo;
      dAngle.flipXAxis();
    }
    else if(round(y + (dy * speed) + height) > principal.getBottomLimit()){
      dy*=-1; // bateu na base
      dAngle.flipXAxis();
    }
    
		//para pontuar:
		if( x < principal.getLeftLimit() ){
			// Bateu na parede esquerda -> Ponto do inimigo 
			System.out.println("Ponto do INIMIGO!!");
			principal.setEntities(false);
			return;
		}else if(x + width > principal.getRightLimit()){
			//Bateu na parede direita -> Ponto do jogador
			System.out.println("Ponto do JOGADOR!!");
			principal.setEntities(true);
			return;
		}
		
		//tratando as colisões da bola com Player e Inimigo
    
    Player player = principal.getPlayer();
    Enemy enemy = principal.getEnemy();
    
		Rectangle 
      ballBounds = new Rectangle(
        new Point(round(x+(dx*speed)), round(y+(dy*speed))),
        new Dimension(width, height)),
            
      playerBounds = new Rectangle(new Point(player.getX(), player.getY()),
        new Dimension(player.getWidth(), player.getHeight())),

      enemyBounds = new Rectangle(new Point(enemy.getX(),enemy.getY()),
        new Dimension(enemy.getWidth(), enemy.getHeight()));
    
		
		if (ballBounds.intersects(playerBounds)){
      /*criando angulo com abertura minima de 30 graus e máximo de 150 e
        transladando ele para ficar entre os quadrantes 1 e 4, na direção do
        inimigo */
      increaseSpeed();
      dAngle.setAngle(new Random().nextInt(150-45)+45-90+1, Angle.Units.DEGREES);
      enemyFollow=true;
      
      //Somando +1 ponto no rally score
      principal.getScoreHUD().toUp();
		}else if(ballBounds.intersects(enemyBounds)){
      /*criando angulo com abertura minima de 30 graus e máximo de 150 e
        transladando ele para ficar entre os quadrantes 2 e 3, na direção do
        jogador */
      increaseSpeed();
      dAngle.setAngle(new Random().nextInt(150-45)+45+90+1, Angle.Units.DEGREES);
      enemyFollow=false;
		}
    
    setLocation(new Location(x+dAngle.getCos()*speed, y+dAngle.getSin()*speed));
  }

}