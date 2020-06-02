package utils;

import java.io.Serializable;
import java.util.Comparator;

public class Score implements Serializable{
  
  private final String nome; //MAX de 3 letras -> estilo retrô
  private final int score;

  public Score(String nome, int score) {
    this.nome = nome;
    this.score = score;
  }
  
  public static Comparator<Score> getCompByScore(){   
    Comparator comp = new Comparator<Score>(){
      @Override
      public int compare(Score s1, Score s2){
          return s2.getScore() - s1.getScore() ;
      }
    };
    return comp;
  }  

  public String getNome() {
    return nome;
  }

  public int getScore() {
    return score;
  }

  @Override
  public String toString() {
    return nome + " + " + score;
  }
  
}
