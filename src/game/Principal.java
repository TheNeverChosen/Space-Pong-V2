package game;

import game.models.Desenho;
import game.entities.Ball;
import game.entities.Enemy;
import game.entities.Player;
import game.entities.ScoreHUD;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import screens.Menu;
import start.Window;
import utils.FileIO;
import utils.Location;
import utils.Score;
import utils.ScoresList;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA
public class Principal extends JFrame implements KeyListener {

	private Desenho spaceBack;
	private Player player;
  private Enemy enemy;
	private Ball ball;
  private ScoreHUD scoreHUD;
  private int leftLimit,topLimit, rightLimit, bottomLimit;
  private ScoresList scoresList;
  private FileIO fileIO;
	private static final long serialVersionUID = 1L;
  
  public Principal(String playerName){
    initiate("SpacePong V2", new Dimension(1000, 600), playerName);
  }
  
	public Principal(String title, String playerName) {
    initiate(title, new Dimension(1000, 600), playerName);  
	}
  
  private void initiate(String title, Dimension size, String playerName){
    setTitle(title);
    setMinimumSize(size); setMaximumSize(size); setSize(size);
    setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    pack();

    setScoresList();
    setLimits(size);
    setEntities(true);  player.setName(playerName);

    this.addKeyListener(this); 

    createBufferStrategy(1);		
    setVisible(true);
    createBufferStrategy(3);
  }

  private void setScoresList(){
    fileIO = new FileIO();
    scoresList = (ScoresList) fileIO.readObjectFromFile("scores.dat");
    if(scoresList==null) scoresList = new ScoresList();
  }
  
  private void setLimits(Dimension size){
    //Guardando o tamanho da tela real do jogo
   int width=getContentPane().getWidth(), height=getContentPane().getHeight(),
       border = (int) ((size.getWidth() - width)/2), //tamanho da borda da janela
       titleBar = (int) (size.getHeight() - height - border*2); //tamanho da barra de título da janela

   topLimit = border+titleBar; bottomLimit = topLimit + height;
   leftLimit = border; rightLimit = width+border;
  }

  public void setEntities(boolean playerServe){
   int height=getContentPane().getHeight();

    //calculando tamanho relativo das barras (proporção -> 1:8)
    //calculando tamanho da bola (lados iguais a 1/5 altura das barras)
   Dimension barSize = new Dimension(height/32, height/4),
             ballSize = new Dimension((int)(barSize.getHeight()/5), (int) (barSize.getHeight()/5));

   //Calculando location das entities
   Location playerLocation = new Location( leftLimit, (topLimit+bottomLimit)/2 - barSize.getHeight()/2 ),
            enemyLocation = new Location(rightLimit-barSize.getWidth(), playerLocation.getY()),
            ballLocation = new Location(
              (leftLimit+rightLimit)/2 - ballSize.getWidth()/2,
              (topLimit+bottomLimit)/2 - ballSize.getHeight()/2
            );

   //Setando entidades
   if(spaceBack==null) spaceBack = new Desenho("/images/space.jpg");

   if(player==null) player = new Player(this, 10, "/images/player.png", playerLocation, barSize);
   else player .setLocation(playerLocation);

   if(enemy==null) enemy = new Enemy(this, (height/3.5)/60.0, "/images/enemy.png", enemyLocation, barSize);
   else enemy.setLocation(enemyLocation);

   if(ball==null) ball = new Ball(this, "/images/ball.png", ballLocation, ballSize);
   else ball.reset(ballLocation, playerServe);

   if(scoreHUD==null) scoreHUD = new ScoreHUD(this, "Rally: ", 0, new Font("Arial", Font.BOLD, 20));
   else{
     if(scoreHUD.getScore()>0 && playerServe==true) saveScore();
     scoreHUD.setScore(0);
   }
  }

  public void saveScore(){    
    Score score = new Score(player.getName(), scoreHUD.getScore());
    
    // Se a lista adicionou um novo score e se alterou, então o arquivo é salvo
    if (scoresList.addScore(score))
      fileIO.saveObjectFile(scoresList, "scores.dat");
  }

 //EVITAR ALTERAR ESSE MÉTODO
 public void renderizarGraphics() {
   if (!getBufferStrategy().contentsLost()) getBufferStrategy().show();

   Graphics g = getBufferStrategy().getDrawGraphics();

   //Criando um contexto gr�fico
   Graphics g2 = g.create();
   //Limpando a tela
   g2.setColor(Color.WHITE);
   g2.setFont(scoreHUD.getFont());
   g2.fillRect(0, 0, getWidth(), getHeight());

   renderizarImagens(g2);

   //Liberando os contextos criados.
   g.dispose(); 
   g2.dispose();
 }

 //ESSE É O MÉTODO QUE DEVE SER ADAPTADO AO PROJETO
 public void renderizarImagens(Graphics g2) {
   //Desenhando as imagens
   spaceBack.desenhar(g2);
   enemy.desenhar(g2);
   player.desenhar(g2);
   ball.desenhar(g2);
   scoreHUD.desenhar(g2);
 }

 //EVITAR ALTERAR ESSE MÉTODO
 @Override
 public void paint(Graphics g) {
   this.renderizarGraphics();
   repaint();
 }

 @Override
 public void keyPressed(KeyEvent evt) {
   //Especificando o comportamento das teclas
   switch (evt.getKeyCode()) {
     case KeyEvent.VK_UP:
       player.moverCima();
       break;
     case KeyEvent.VK_DOWN:
       player.moverBaixo();
       break;
     case KeyEvent.VK_ESCAPE:
       //Sair do game e voltar ao menu
       setVisible(false);
       dispose();
       ball.setRepeat(false); // parar execução da thread
       Window window = new Window();
       window.changePanel(new Menu());
       window.setVisible(true);
       break;
     default:
       break;
   }
  }

  @Override
	public void keyReleased(KeyEvent arg0) {}
  @Override
	public void keyTyped(KeyEvent arg0) {}

  public int getLeftLimit() {
    return leftLimit;
  }

  public int getTopLimit() {
    return topLimit;
  }

  public int getRightLimit() {
    return rightLimit;
  }

  public int getBottomLimit() {
    return bottomLimit;
  }
  
  public int getScreenWidth(){
    return getContentPane().getWidth();
  }
  
  public int getScreenHeight(){
    return getContentPane().getHeight();
  }

  public Player getPlayer() {
    return player;
  }

  public Enemy getEnemy() {
    return enemy;
  }

  public Ball getBall() {
    return ball;
  }

  public ScoreHUD getScoreHUD() {
    return scoreHUD;
  }
  
  
}
