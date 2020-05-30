package custom_components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import utils.ImageManipulation;

public class JPictureBox extends JComponent {

  private BufferedImage img;
  private Dimension d;
  private ImageManipulation imageEdit=new ImageManipulation();

  public JPictureBox() {
    start();
    this.img=null;
    this.d=new Dimension(-1, -1);
  }

  public JPictureBox(BufferedImage image){
    start();
    this.img=image;
    this.d=new Dimension(-1, -1);
  }

  public JPictureBox(BufferedImage image, Dimension d){
    start();
    this.img=image;
    this.d=d;
  }

  private void start(){
    JPictureBox.this.setOpaque(false);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponents(g);
    BufferedImage displayImg = imageEdit.resize(img, d);
    if (displayImg != null) {
      int height=displayImg.getHeight(), width=displayImg.getWidth();
      
      setAllSizes(new Dimension(width, height));
      g.drawImage(displayImg, 0, 0, width, height, null);
      
      g.dispose();
      revalidate();
    }
  }

  private void setAllSizes(Dimension d){
    setPreferredSize(d);
    setMinimumSize(d);
    setMaximumSize(d);
  }

  public BufferedImage getImg() {
    return img;
  }

  public void setImg(BufferedImage img) {
    this.img = img;
  }
    
  public void setDimension(Dimension d){
    this.d = d;
  }

  public void setWidth(int width){
    d = new Dimension(width, (int)d.getHeight());
  }

  public void setHeight(int height){
    d = new Dimension((int)d.getWidth(), height);
  }
  
}