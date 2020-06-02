package screens;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import start.Window;
import utils.FileIO;
import utils.Score;
import utils.ScoresList;

public class Scores extends BasePanel{
  
  private final JButton[] buttons={
    new JButton("<- Voltar")
  };
  private Box line;
  private final Font normalFont=new Font("SansSerif", Font.BOLD, 14);
  private ScoresList scoresList;
  private FileIO fileIO;
  
  private void setScoresList(){
    fileIO = new FileIO();
    scoresList = (ScoresList) fileIO.readObjectFromFile("scores.dat");
    if(scoresList==null) scoresList = new ScoresList();
  }
  
  public Scores() {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    
    setScoresList();
    
    //line 0: Back Button & title
    line = Box.createHorizontalBox();
    for (int i=0;i<buttons.length;i++){
      if(i!=0) add(Box.createRigidArea(new Dimension(0, 10)));
      buttons[i].setAlignmentX(CENTER_ALIGNMENT);
      buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
      buttons[i].setFocusPainted(false);
      buttons[i].setFont(normalFont);
      line.add(buttons[i]);
    }
    line.add(Box.createRigidArea(new Dimension(10, 0)));
    JLabel label = new JLabel("Top 10 Rally Scores"); label.setFont(normalFont);label.setForeground(Color.white);
    line.add(label);
    line.add(Box.createHorizontalGlue());
    add(line);
    //================================
    
    add(Box.createVerticalGlue());
    
    //line 1-10: scores
    
    for (int i=0;i<scoresList.getList().size();i++){
      if(i!=0) add(Box.createRigidArea(new Dimension(0, 10)));
      line = Box.createHorizontalBox();
      
      Score s = scoresList.getScore(i);
      
      label = new JLabel(i+1+". "+s.getNome()+" = "+s.getScore());
      label.setForeground(Color.white);
      label.setFont(normalFont);
      line.add(Box.createHorizontalGlue());
      line.add(label);
      line.add(Box.createHorizontalGlue());
      add(line);
    }
      
    add(Box.createVerticalGlue());
    
    events();
  }
  
  private void events(){
    buttons[0].addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Window topFrame = (Window) SwingUtilities.getWindowAncestor(Scores.this);
        topFrame.changePanel(new Menu());
      }
    });
  }
  
}
