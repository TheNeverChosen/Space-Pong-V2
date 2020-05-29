package custom_components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JComponent;
import net.coobird.thumbnailator.Thumbnails;

//USE UM MÉTODO MELHOR DE RESIZE PARA A IMAGE

public class JPictureBox extends JComponent {

  private BufferedImage image;
  private Dimension d;

  public JPictureBox() {
    start();
    this.image=null;
    this.d=new Dimension(-1, -1);
  }

  public JPictureBox(BufferedImage image){
    start();
    this.image=image;
    this.d=new Dimension(-1, -1);
  }

  public JPictureBox(BufferedImage image, Dimension d){
    start();
    this.image=image;
    this.d=d;
  }

  private void start(){
    JPictureBox.this.setOpaque(false);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponents(g);
    if (image != null) {

      int height=(int)d.getHeight(), width=(int)d.getWidth();
      BufferedImage resizedImage;
      
      try {
        if(height<0 && width<0) resizedImage=image;
        
        else if(height<0)
          resizedImage = Thumbnails.of(image).width(width).asBufferedImage();
        
        else if(width<0)
          resizedImage = Thumbnails.of(image).height(height).asBufferedImage();

        else
          resizedImage = Thumbnails.of(image).forceSize(width, height).asBufferedImage();
        
        System.out.println("ANTES:\nw = "+width+" h = "+height);
        System.out.println("w = "+resizedImage.getWidth()+" h = "+resizedImage.getHeight());
        
        width=resizedImage.getWidth(); height=resizedImage.getHeight();
        
        System.out.println("\nDEPOIS:\nw = "+width+" h = "+height);
        System.out.println("w = "+resizedImage.getWidth()+" h = "+resizedImage.getHeight());
        
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
        return;
      }
      
      setAllSizes(new Dimension(width, height));
      g.drawImage(resizedImage, 0, 0, width, height, null);
      
      g.dispose();
      revalidate();
    }
    else System.out.println("oi");
  }

  private void setAllSizes(Dimension d){
    setPreferredSize(d);
    setMinimumSize(d);
    setMaximumSize(d);
  }

  public BufferedImage getImage() {
    return image;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
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