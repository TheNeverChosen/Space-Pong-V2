package game.models;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Math.round;
import utils.ImageManipulation;
import utils.Location;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA.
public class Desenho {
   
  private final ImageManipulation imageEdit=new ImageManipulation();
  private Dimension size;
  private Location location;
	private BufferedImage img, resizedImg, displayImg;
	
	public Desenho() {}
  
  public Desenho(String path) {
		initialize(path, new Location(0, 0), new Dimension(-1, -1));
	}
	
	public Desenho(String path, Location location) {
		initialize(path, location, new Dimension(-1, -1));
	}
  
  public Desenho(String path, Dimension size) {
		initialize(path, new Location(0, 0), size);
	}
  
  public Desenho(String path, Location location, Dimension size) {
		initialize(path, location, size);
	}
  
  private void initialize(String path, Location location, Dimension size){
    this.location = location;
		this.img = imageEdit.createBufferedImage(path);
    this.size = size;
    setAuxiliarImages();
  }
  
  public int getX(){
    return (int) Math.round(location.getX());
  }
  
  public int getY(){
    return (int) Math.round(location.getY());
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }
  
  public int getWidth(){
    return (int)round(size.getWidth());
  }
  
  public int getHeight(){
    return (int) round(size.getHeight());
  }

  public Dimension getSize() {
    return size;
  }

  public void setSize(Dimension size) {
    this.size = size;
    setAuxiliarImages();
  }
  
  private void setAuxiliarImages() {
    displayImg = resizedImg = imageEdit.resize(img, size);
  }

  public void setImg(BufferedImage img) {
    this.img = img;
    setAuxiliarImages();
  }
  
  public void setImg(String path) {
    img = imageEdit.createBufferedImage(path);
    setAuxiliarImages();
	}
  
  public void rotateDisplayImg(double degrees){
    displayImg = imageEdit.rotate(resizedImg, degrees);
  }

	public void desenhar(Graphics g) {
		//Desenhando a imagem na tela, mas antes verificando se a imagem é válida
    if(displayImg!=null)
      g.drawImage(displayImg, (int) Math.round(location.getX()),
        (int) Math.round(location.getY()), null);
      
    else System.out.println("null display image");
	}
  
}
