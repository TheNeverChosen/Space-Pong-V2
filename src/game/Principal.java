package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import utils.Location;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA
public class Principal extends JFrame implements KeyListener {

	private Desenho espaco;
	private Barra barra;
	private Bola bola;
  private int width, height;
	private static final long serialVersionUID = 1L;
  
	public Principal(String title) {
    super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Guardando o tamanho da tela do jogo
    width=getContentPane().getWidth(); height=getContentPane().getHeight();
		//Desenhando a tela e seus elementos
		espaco = new Desenho("/images/space.jpg", new Location(0, 0));
		barra = new Barra("/images/barra.png", new Location(20,280));
		bola = new Bola("/images/ball.png", new Location(50,50), new Dimension(100, 100));
		//Adicionando o evento de teclado
		this.addKeyListener(this);
	}

	//EVITAR ALTERAR ESSE MÉTODO
	public void renderizarGraphics() {
		if (!getBufferStrategy().contentsLost()) getBufferStrategy().show();
    
    Graphics g = getBufferStrategy().getDrawGraphics();

    //Criando um contexto gr�fico
    Graphics g2 = g.create();
    //Limpando a tela
    g2.setColor(Color.WHITE);        
    g2.fillRect(0, 0, getWidth(), getHeight());

    renderizarImagens(g2);

    //Liberando os contextos criados.
    g.dispose(); 
    g2.dispose();
	}
	
	//ESSE É O MÉTODO QUE DEVE SER ADAPTADO AO PROJETO
	public void renderizarImagens(Graphics g2) {
		//Desenhando as imagens
		espaco.desenhar(g2);
		barra.desenhar(g2);
    bola.desenhar(g2);
	}
	
  
  
	//EVITAR ALTERAR ESSE M�TODO
  @Override
	public void paint(Graphics g) {
		this.renderizarGraphics();
		repaint();
	}

  @Override
	public void keyPressed(KeyEvent evt) {
    //Especificando o comportamento das teclas
    if (evt.getKeyCode() == KeyEvent.VK_UP){
      barra.moverCima();;
      repaint();
    }
    else if (evt.getKeyCode() == KeyEvent.VK_DOWN){
      barra.moverBaixo();
      repaint();
    }
   }

  @Override
	public void keyReleased(KeyEvent arg0) {}
  @Override
	public void keyTyped(KeyEvent arg0) {}
}
