package utils;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import net.coobird.thumbnailator.Thumbnails;

public class ImageManipulation {

  public ImageManipulation() { }
  
  public ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL, description);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
  }
  
  public BufferedImage createBufferedImage(String path){
    java.net.URL imgURL = getClass().getResource(path);
    try {
      return ImageIO.read(imgURL);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }  
  
  public BufferedImage resize(BufferedImage image, Dimension d){
    if(image==null) return null;
    boolean aux=true;
    try {
      int height=(int)d.getHeight(), width=(int)d.getWidth();
      BufferedImage resizedImg;
      
      if( (image.getWidth()==width && image.getHeight()==height) ||
          (height<0 && width<0) )
        resizedImg=image;

      else if(height<0)
        resizedImg = Thumbnails.of(image).width(width).asBufferedImage();

      else if(width<0)
        resizedImg = Thumbnails.of(image).height(height).asBufferedImage();

      else
        resizedImg = Thumbnails.of(image).forceSize(width, height).asBufferedImage();
      
      return resizedImg;
    } catch (IOException | NullPointerException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }
  
  public BufferedImage rotate(BufferedImage src, double degrees) {
    if(src==null) return null;
    if(degrees%360==0) return src;
    double angle = Math.toRadians(degrees);

    AffineTransform transform = new AffineTransform();
    transform.rotate(angle, src.getWidth()/2, src.getHeight()/2);
    double offset = (src.getWidth()-src.getHeight())/2;
    transform.translate(offset,offset);

    BufferedImageOp operation = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);

    return operation.filter(src, null);
  }
  
}
