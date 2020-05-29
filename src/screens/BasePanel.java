package screens;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import utils.Utils;

public class BasePanel extends JPanel{
  
  private final String imageName="/images/space.jpg";
  private final Utils utils = new Utils();
  
  public BasePanel() { }
  
  @Override
  protected void paintComponent(Graphics g) {
    
    ImageIcon bgImage = utils.createImageIcon(imageName, "background image");
    
    super.paintComponent(g);
        g.drawImage(bgImage.getImage(), 0, 0, null);
  }
  
}
