package screens;

import custom_components.JPictureBox;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import utils.Utils;

public class Menu extends BasePanel{
  
  Utils utils = new Utils();
  Font normalFont=new Font("SansSerif", Font.BOLD, 14);
  
  BufferedImage image = utils.createBufferedImage("/images/logo.png");
  JPictureBox logo = new JPictureBox(image, new Dimension(250, -1));
  JButton[] buttons={
    new JButton("Jogar"),
    new JButton("Instruções"),
    new JButton("Pontuações"),
    new JButton("Sair")
  };
  
  
  public Menu() {
    
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    add(Box.createVerticalGlue());
    
    //line 0: logo
    logo.setAlignmentX(CENTER_ALIGNMENT);
    add(logo);
    //================================
    
    add(Box.createRigidArea(new Dimension(0, 30)));
    
    //line 1-4: buttons
    
    for (int i=0;i<buttons.length;i++){
      if(i!=0) add(Box.createRigidArea(new Dimension(0, 10)));
      buttons[i].setAlignmentX(CENTER_ALIGNMENT);
      buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
      buttons[i].setFocusPainted(false);
      buttons[i].setFont(normalFont);
      add(buttons[i]);
    }
      
    add(Box.createVerticalGlue());
    
    events();
  }
  
  private void events(){
    
    buttons[3].addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    
  }
  
}
