package utils;

import java.io.Serializable;
import java.util.ArrayList;

public class ScoresList implements Serializable{
  
  private final ArrayList<Score> list; //Utilizando collection ArrayList
  private final int MAX_SCORES = 10;
  
  public ScoresList() {
    this.list = new ArrayList<>();
  }

  public ScoresList(ArrayList<Score> scores) {
    this.list = scores;
  }

  public ArrayList<Score> getList() {
    return list;
  }
  
  public Score getScore(int i){
    return list.get(i);
  }
  
  public boolean addScore(Score score){
    
    boolean changed = false;
    
    if(list.size()<MAX_SCORES){
      list.add(score);
      changed = true;
    }
    else{
      int last=MAX_SCORES-1;
      if(getScore(last).getScore() < score.getScore()){
        list.set(last, score);
        changed = true;
      }
    }
    
    if(changed) list.sort(Score.getCompByScore());
    
    
    return changed;
  }
  
}
