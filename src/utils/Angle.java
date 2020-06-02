package utils;

//Essa classe aceita instâncias e manipulação de angulos de 0 a 360 graus
public class Angle {
  
  public enum Units{
    RADIANS,
    DEGREES
  };

  double angle;

  public Angle(double angle, Units angleUnit) {
    define(angle, angleUnit);
  }
  
  private void define(double angle, Units angleUnit){
    switch(angleUnit){
      case RADIANS:
        angle%=(Math.PI*2);
        if(angle < 0) angle = Math.PI*2 + angle;
        this.angle = angle;
        break;
      case DEGREES:
        angle%=360;
        if(angle < 0) angle = 360 + angle;
        this.angle = Math.toRadians(angle);
        break;
    }
  }

  public double getAngle() {
    return angle;
  }
  
  public void setAngle(double angle, Units angleUnit) {
    define(angle, angleUnit);
  }
  
  public void flipXAxis(){
    this.angle = Math.PI*2-this.angle;
  }
  
  public void flipYAxis(){
    double aux = Math.toDegrees(angle);
    
    if(aux<=180) aux=180-aux;
    else aux=540-aux;
    
    this.angle = Math.toRadians(aux);
  }
  
  public double getSin(){
    return Math.sin(angle);
  }
  
  public double getCos(){
    return Math.cos(angle);
  }
  
}
