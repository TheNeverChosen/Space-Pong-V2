package start;

import screens.Menu;

public class Main {
  
  
	public static void main(String[] args) {
    Window window = new Window();
    window.changePanel(new Menu());
    window.setVisible(true);
	}
  
}
