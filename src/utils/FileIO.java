package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

public class FileIO {

  public FileIO() { }
  
  public boolean saveObjectFile(Object object, String fileName){
    
    try {
      String filePath = "src\\files\\"+fileName;
      
      FileOutputStream fos = new FileOutputStream(filePath);
      
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      
      oos.writeObject(object);
      oos.close();
      return true;
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
        return false;
    }
  }
  
  public Object readObjectFromFile(String fileName) {
    try {
      String filePath = "src\\files\\"+fileName;
      FileInputStream fileIn = new FileInputStream(new File(filePath));
      ObjectInputStream objectIn = new ObjectInputStream(fileIn);

      Object obj = objectIn.readObject();

      objectIn.close();
      return obj;
    } catch (IOException | ClassNotFoundException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }
  
  public String readTextFile(String fileName){
    // abertura do arquivo
    BufferedReader myBuffer;
    try {
      myBuffer = new BufferedReader(new InputStreamReader(
              new FileInputStream("src\\files\\"+fileName), "UTF-8"));
      
      StringBuilder text = new StringBuilder(myBuffer.readLine());
      String aux;
      do{
        aux = myBuffer.readLine();
        if(aux!=null){
          text.append('\n');
          text.append(aux);
        }
      }while(aux!=null);

      myBuffer.close();
      return text.toString();
    } catch (UnsupportedEncodingException | FileNotFoundException ex) {
      System.out.println(ex.getMessage());
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }

    return null;
  }
  
}
