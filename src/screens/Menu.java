package screens;

import custom_components.JPictureBox;
import game.Principal;
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
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import utils.ImageManipulation;

public class Menu extends BasePanel{
  
  ImageManipulation imageManipulation = new ImageManipulation();
  Font normalFont=new Font("SansSerif", Font.BOLD, 14);
  
  BufferedImage image = imageManipulation.createBufferedImage("/images/logo.png");
  JPictureBox logo = new JPictureBox(image);
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
    
    //EVITAR ALTERAR ESSE MÉTODO
    buttons[0].addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Destroying current jframe to switch to game jframe
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Menu.this);
        topFrame.setVisible(false);
        topFrame.dispose();
        
        // Showing game JFrame
        Principal t = new Principal("Space Pong V2");
        t.setSize(1000, 600);
        t.setLocationRelativeTo(null);
        t.createBufferStrategy(1);		
        t.setVisible(true);
        t.createBufferStrategy(2);
      }
    });
    
    buttons[3].addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    
  }
  
}
