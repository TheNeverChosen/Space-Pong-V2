package start;

import screens.Menu;

public class Main {
  
  
	public static void main(String[] args) {
    Window window = new Window();
    window.setVisible(true);
    window.changePanel(new Menu());
	}
  
}
