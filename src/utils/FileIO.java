package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

public class FileIO {

  private final String APP_FOLDER = "SpacePong";
  
  public FileIO() {
    getAppFileFolder();
  }
  
  private String getAppFileFolder(){
    String fileFolder = System.getenv("APPDATA") + "\\" + APP_FOLDER;

    String os = System.getProperty("os.name").toUpperCase();
    if (os.contains("WIN")) fileFolder = System.getenv("APPDATA") + "\\" + APP_FOLDER;
    else if (os.contains("MAC"))fileFolder = System.getProperty("user.home") + "/Library/Application " + "Support"
              + APP_FOLDER;
    else if (os.contains("NUX")) fileFolder = System.getProperty("user.dir") + "." + APP_FOLDER;

    File directory = new File(fileFolder);

    if (!directory.exists()) directory.mkdir();
    return fileFolder;
  }
  
  public boolean saveObjectFile(Object object, String fileName){
    try {
      FileOutputStream fos = new FileOutputStream(getAppFileFolder()+"\\"+fileName);
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
      FileInputStream fileIn = new FileInputStream(getAppFileFolder()+"\\"+fileName);
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
              getClass().getResourceAsStream("/files/"+fileName), "UTF-8"));
      
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
