//package game;

import java.util.Scanner;
import java.util.Objects;
import java.util.Arrays;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FoxHoundUI {

    /** Number of main menu entries. */
    private static final int MENU_ENTRIES = 2;
    /** Main menu display string. */
    private static final String MAIN_MENU = "\n1. Move\n2. Exit\n\nEnter 1 - 2:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;
    /** Menu entry to terminate the program. */

    public static final int MENU_EXIT = 2;

    public static void displayBoard(String[] players, int dim) {
        //TODO: 2.2 Task 2 - Display Game Board
    }

    public static void displayBoardFancy(String[] players, int dim) {
        //TODO: 2.3 Task 3 - Fancy Print
    }



    public static int mainMenuQuery(char figureToMove, Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        if (figureToMove != FoxHoundUtils.FOX_FIELD && figureToMove != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("Given figure field invalid: " + figureToMove);
        }//gives error if not H or F

        String nextFigure = figureToMove == FoxHoundUtils.FOX_FIELD ? "Fox" : "Hounds";

        int input = -1;
        while (input == -1) {
            System.out.println(nextFigure + " to move");
            System.out.println(MAIN_MENU);

            boolean validInput = false;
            if (stdin.hasNextInt()) {
                input = stdin.nextInt();
                validInput = input > 0 && input <= MENU_ENTRIES;
            }

            if (!validInput) {
                System.out.println("Please enter valid number.");
                input = -1; // reset input variable
            }

            stdin.nextLine(); // throw away the rest of the line
        }

        return input;
    }

    public static String[] positionQuery(int dim, Scanner stdin) {
        //TODO: 2.4.2 Menu Integration
        return null;
    }

    public static Path fileQuery(Scanner stdin) {
        //TODO: 2.6.3 Menu Integration
        return null;
    }
}
