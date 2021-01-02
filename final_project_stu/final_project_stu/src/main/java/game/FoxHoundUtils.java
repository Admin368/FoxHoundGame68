package game;

import java.util.Arrays;

public class FoxHoundUtils {

    /** Default dimension of the game board in case none is specified. */
    public static final int DEFAULT_DIM = 8;
    /** Minimum possible dimension of the game board. */
    public static final int MIN_DIM = 4;
    /** Maximum possible dimension of the game board. */
    public static final int MAX_DIM = 26;

    /** Symbol to represent a hound figure. */
    public static final char HOUND_FIELD = 'H';
    /** Symbol to represent the fox figure. */
    public static final char FOX_FIELD = 'F';

    public static String[] initialisePositions(int dimension) {
        //TODO: 2.1.1 Initialising Player Positions
        return null;
    }


    public static boolean isValidMove(int dim, String[] players, char fh, String before, String after) {
        //TODO: 2.4.1 Move Validity
        return false;
    }



    public static boolean isHoundWin(String[] players, int dim) {
        //TODO: 2.5 Task 5 - Winning the Game
        return false;
    }

    public static boolean isFoxWin(String foxPosition) {
        //TODO: 2.5 Task 5 - Winning the Game
        return false;
    }



    public static int searchKey(String[] keys, String key) {
        int flag = 0;
        for (String theKey : keys) {
            if (theKey.equals(key)) {
                return flag;
            }
            flag++;
        }
        return -1;
        // helper function to find the position of same keys exist, return first same
        // keys's position

    }
}
