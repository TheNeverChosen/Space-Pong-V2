package game.entities;

import game.Principal;
import game.models.DesenhoMovel;
import java.awt.Dimension;
import utils.Location;

public class Player extends DesenhoMovel {

  private String name;
  
	public Player() {}

  public Player(Principal principal, double speed, String path, Dimension size) {
    super(principal, speed, path, size);
  }

  public Player(Principal principal, double speed, String path, Location location, Dimension size) {
    super(principal, speed, path, location, size);
  }
  
  public Player(Principal principal, double speed, String path, Dimension size, String name) {
    super(principal, speed, path, size);
    this.name = name;
  }

  public Player(Principal principal, double speed, String path, Location location, Dimension size, String name) {
    super(principal, speed, path, location, size);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
