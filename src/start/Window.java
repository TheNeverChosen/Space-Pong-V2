package start;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Window extends JFrame{
  
  public Window() {
    super();
    setTitle("Pong V2");
    setPreferredSize(new Dimension(800, 600));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
  }
  
  public void changePanel(Component c){
    getContentPane().removeAll();
    getContentPane().add(c);
    revalidate();
  }
  
  
}
