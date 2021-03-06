package screens;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import utils.ImageManipulation;

public class BasePanel extends JPanel{
  
  private final String imageName="/images/space.jpg";
  private final ImageManipulation imageEdit = new ImageManipulation();
  
  public BasePanel() { }
  
  @Override
  protected void paintComponent(Graphics g) {
    
    ImageIcon bgImage = imageEdit.createImageIcon(imageName, "background image");
    
    super.paintComponent(g);
        g.drawImage(bgImage.getImage(), 0, 0, null);
  }
  
}
