package utils;

public class Location {
  
  private int x, y;

  public Location() { }

  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "Location{" + "x=" + x + ", y=" + y + '}';
  }
  
}
