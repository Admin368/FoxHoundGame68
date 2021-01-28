// USER INPUT OF THE GAME
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FoxHoundIO { // GAME SAVING
  public static boolean saveGame (String[] players, char figure, Path path) {
    boolean save = false;
    if (players == null || path == null) {
      throw new NullPointerException("ERROR: Players can't be null");
    }
    if (players.length != 5) {
      throw new IllegalArgumentException("ERROR: Dimensions must be 8");
    }
    if (Files.exists(path)) {
      System.err.println("ERROR, THE FILE ALREADY EXISTS");

    } else {
      save = true;

      FileWriter writing = null;
      try {
        writing = new FileWriter(String.valueOf(path));
        writing.write(figure);

        for (int i = 0; i < players.length; i++) {
          writing.write(" " + players[i]);
        }
        System.out.println("FILE SUCCESSFULLY SAVED...");
        writing.close();

      } catch (IOException e) {
        save = false;
        System.err.println(e.getMessage());
        e.printStackTrace();
      }
    }
    return save;
  }
  public static char loadGame (String[] players, Path path) {//LOADING GAME
    char figure = ' ';
    if (path == null || players == null) {
      throw new NullPointerException("ERROR, PATH CAN NOT BE NULL");
    } else if (players.length != 5) {
      throw new IllegalArgumentException("ERROR, PLAYERS MUST BE VALID FOR A 8x8 DIMENSION");
    }
    try {
      if (Files.exists(path)) {
        Scanner reader = new Scanner(path);
        String[] players_copy = new String[players.length];
        String line = reader.nextLine();
        boolean valid = false;
        String[] saved_txt = line.split(" ");
        if ((saved_txt.length > 5) && ((saved_txt[0].equals("F")) || (saved_txt[0].equals("H")))){
          valid = true;
        } else {
          System.err.println("ERROR, THE FILE YOU WANT TO LOAD IS NOT A GAME");
          valid = false;
          figure = '#';
        }

        if (valid) {
          if (saved_txt[0].length() != 1){
            figure = '#';
          } else {
            figure = (saved_txt[0]).charAt(0);
          }
          for (int i = 0; i < players.length; i++) {

            if (FoxHoundUtils.coordinate_checker(8, saved_txt[i + 1])){
              players_copy[i] = saved_txt[i+1];
            } else {
              figure = '#';
              valid = false;
              break;
            }
          }
        }
        if (valid) {
          for (int i = 0; i < players.length; i ++) {
            players[i] = players_copy[i];
          }
        }
        reader.close();
      } else {
        throw new IOException("ERROR, THE FILE DOES NOT EXIST");
      }
    } catch (IOException error) {
      System.err.println(error.getMessage());
      figure = '#';
    }
    return figure;
  }


}
