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
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import start.Window;
import utils.FileIO;
import utils.ImageManipulation;

public class Menu extends BasePanel{
  
  private ImageManipulation imageManipulation = new ImageManipulation();
  private Font normalFont=new Font("SansSerif", Font.BOLD, 14);
  private BufferedImage image = imageManipulation.createBufferedImage("/images/logo.png");
  private JPictureBox logo = new JPictureBox(image);
  private JButton[] buttons={
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
    
    
    buttons[0].addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean valido=false;
        
        do{
          String name = JOptionPane.showInputDialog(Menu.this, "Informe o nome do jogador (Apenas as 3 primeiras letras serão utilizadas");
          
          if(name==null) break;
          else{
            name = name.trim();
            if(name.length()>=3){
              //EVITAR ALTERAR ESSE MÉTODO
              
              valido=true;
              // Destroying current jframe to switch to game jframe
              Window topFrame = (Window) SwingUtilities.getWindowAncestor(Menu.this);
              topFrame.setVisible(false);
              topFrame.dispose();

              // Showing game JFrame
              Principal t = new Principal("Space Pong V2", name.subSequence(0, 3).toString());
            }
          }
          if(!valido) JOptionPane.showMessageDialog(Menu.this, "Informe um nome com 3 letras válidas");
        }while(!valido);
        
      }
    });
    
    buttons[1].addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Window topFrame = (Window) SwingUtilities.getWindowAncestor(Menu.this);
        topFrame.changePanel(new Instructions());
      }
    });
    
    buttons[2].addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Window topFrame = (Window) SwingUtilities.getWindowAncestor(Menu.this);
        topFrame.changePanel(new Scores());
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
