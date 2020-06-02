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
import utils.FileIO;
import utils.ScoresList;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import start.Window;

public class Instructions extends BasePanel{
  
  private final JButton[] buttons={
    new JButton("<- Voltar")
  };
  private final Box line;
  private final Font normalFont=new Font("SansSerif", Font.BOLD, 14);
  private ScoresList scoresList;
  private FileIO fileIO;
  private String text;
  
  private void setText(){
    fileIO = new FileIO();
    this.text = fileIO.readTextFile("instructions.txt");
  }

  public Instructions() {
    
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    
    setText();
    
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
    JLabel label = new JLabel("Instruções"); label.setFont(normalFont);label.setForeground(Color.white);
    line.add(label);
    line.add(Box.createHorizontalGlue());
    add(line);
    //================================
    
    add(Box.createRigidArea(new Dimension(0, 20)));
    
    add(Box.createVerticalGlue());
    
    //line 1: instructions
    JTextArea textArea = new JTextArea(2, 20);
    textArea.setText(text);
    textArea.setWrapStyleWord(true);
    textArea.setLineWrap(true);
    textArea.setOpaque(false);
    textArea.setEditable(false);
    textArea.setFocusable(false);
    textArea.setBackground(UIManager.getColor("Label.background"));
    textArea.setForeground(Color.white);
    textArea.setFont(normalFont);
    textArea.setBorder(UIManager.getBorder("Label.border"));
    
    add(textArea);
    
    add(Box.createVerticalGlue());
    
    events();
  }
  
  private void events(){
    buttons[0].addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Window topFrame = (Window) SwingUtilities.getWindowAncestor(Instructions.this);
        topFrame.changePanel(new Menu());
      }
    });
  }
  
}
