package start;

import game.Principal;
import javax.swing.JFrame;
import screens.Menu;

public class Main {
  
  //EVITAR ALTERAR ESSE MÉTODO
	public static void main(String[] args) {
    
    Window window = new Window();
    window.setVisible(true);
    window.changePanel(new Menu());
    
		//Criando uma instância da classe principal
    /*
      Principal t = new Principal("Space Pong V2");
      t.setSize(800, 600);
      t.createBufferStrategy(1);		
      t.setVisible(true);
      t.createBufferStrategy(2);
    */
	}
  
}
