package game.entities;

import game.Principal;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.LineMetrics;

public class ScoreHUD {
  
  private Principal principal;
  private String base;
  private int score;
  private Font font;
  
  public ScoreHUD(Principal principal, String base, int score, Font font) {
    initiate(principal, base, score, font);
  }
  
  private void initiate(Principal principal, String base, int score, Font font){
    this.principal = principal;
    this.base = base;
    this.score = score;
    this.font = font;
  }
  
  public void toUp(){
    score++;
  }
  
  public void toDown(){
    score--;
  }

  public void setBase(String base) {
    this.base = base;
  }
  
  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public Font getFont() {
    return font;
  }

  public void setFont(Font font) {
    this.font = font;
  }
  
  public int getX(Graphics g){
    FontMetrics metrics = g.getFontMetrics(font);
    int width = metrics.stringWidth(base+score);
    double centerX = (principal.getLeftLimit()+principal.getRightLimit())/2.0;
    int x = (int) Math.round( centerX - width/2.0);
    return x;
  }
  
  public int getY(Graphics g){
    FontMetrics metrics = g.getFontMetrics(font);
    LineMetrics lm = metrics.getLineMetrics(base+score, g);
    double height = lm.getHeight();
    int y = (int) Math.round(principal.getTopLimit() + height);
    return y;
  }
  
  public void desenhar(Graphics g){
    int x = getX(g), y = getY(g);
    g.drawString(base+score, x, y);
  }
  
}
