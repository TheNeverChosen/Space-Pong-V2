package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERA��O REALIZADA DEVE OBEDECER A HIERARQUIA
public class Principal extends JFrame implements KeyListener {

	private Desenho espaco;
	private Barra barra;
	private Bola bola;
	private static final long serialVersionUID = 1L;

  
  
	public Principal(String title) {
    super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Desenhando a tela e seus elementos
		espaco = new Desenho(0,0,"space.jpg");
		barra = new Barra(20,280,"barra.png");
		bola = new Bola(60,30,"bola.png");
		//Adicionando o evento de teclado
		this.addKeyListener(this);
	}

	//EVITAR ALTERAR ESSE M�TODO
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
	
	//ESSE � O M�TODO QUE DEVE SER ADAPTADO AO PROJETO
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
