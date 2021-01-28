// GAME USER-INTERFACE AND MENUS
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Objects;

public class FoxHoundUI {
    private static final int MENU_ENTRIES = 4; // available number of options
    private static final String MAIN_MENU =
        "1.[Move] 2.[Save Game] 3.[Load Game] 4.[Exit]\nCHOOSE OPTIONS 1-4(Enter Number):";
    public static final int MENU_MOVE = 1;// move action
    public static final int MENU_SAVE = 2; // save current game
    public static final int MENU_LOAD = 3; // load saved game
    public static final int MENU_EXIT = 4; // end game

    public static void displayBoard(String[] players, int dimension) {
        if (dimension == 0 || players == null) {
            throw new NullPointerException("ERROR! PARAMETERS CANNOT BE EMPTY");
        } else { // PRINT BOARD ON CONSOLE
            //FoxHoundUtils.griding(FoxHoundUtils.letter_recognition(players), FoxHoundUtils.number_recognition(players), dimension, 0);
            try {
                FoxHoundUtils.board(
                        FoxHoundUtils.griding(FoxHoundUtils.letter_recognition(players),FoxHoundUtils.number_recognition(players), dimension, 0),
                        FoxHoundUI.letters_array(dimension), FoxHoundUI.numbers_array(dimension), dimension);
            } catch (NullPointerException error) {
                System.err.println(error.getMessage());
            }
        }


        //System.out.println(FoxHoundUtils.line_diagram(FoxHoundUtils.letter_recognition(players), FoxHoundUtils.number_recognition(players), dimension, 1));
    }
    public static String[] numbers_array (int dimension) { //CREATES Y-AXIS (NUMBERS)
        String[] numbers_array = new String[dimension];
        if (dimension == 0) {
            throw new NullPointerException("ERROR, DIMENSION CANNOT BE EMPTY");
        }
        else {
            if (dimension < 10) {
                for (int i = 0; i < dimension; i++) {
                    numbers_array[i] = String.valueOf(i + 1); // NUMBER SHOULD START FROM 1
                }
            }
            else {
                for(int i = 0; i < 9; i++) {
                    String number = "0" + String.valueOf(i + 1);
                    numbers_array[i] = number;
                }
                for(int i = 9; i < dimension ; i++) {
                    numbers_array[i] = String.valueOf(i + 1);
                }
            }
        }
        return  numbers_array;
    }
    public static String letters_array (int dimension) {// CREATES X-AXIS (THE LETTERS)
        String letters_array   = "";
        if (dimension == 0){
            throw new NullPointerException("ERROR, DIMENSION CANT BE NULL");
        } else  {
            for(int i = 0; i < dimension; i++) {
                String letter = String.valueOf((char) (65 + i));
                letters_array = letters_array + letter;
            }
        }
        return letters_array;
    }
    public static int mainMenuQuery(char figureToMove, Scanner stdin) { //ASKS MENU QUESTIONS
        Objects.requireNonNull(stdin, "Scanner CANNOT BE EMPTY");
        if (figureToMove != FoxHoundUtils.FOX_FIELD 
         && figureToMove != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("figure incorrect: " + figureToMove);
        }
        String nextFigure = 
            figureToMove == FoxHoundUtils.FOX_FIELD ? "< --FOX--" : "< --HOUNDS-]";int input = -1;//reset
        while (input == -1) {
            System.out.println(nextFigure + "'s TURN TO PLAY >     YOUR OPTIONS:");
            System.out.println(MAIN_MENU);
            boolean validInput = false;
            if (stdin.hasNextInt()) {
                input = stdin.nextInt();
                validInput = input > 0 && input <= MENU_ENTRIES; }
            if (!validInput) {
                System.out.println("Invalid Option");input = -1;}
            stdin.nextLine();}
        return input;
    }
    public static String[] positionQuery (int dimension, Scanner input) { //GETTING COORDINATES
        String input_coordinate = "";
        boolean invalid_input = true;
        String[] coordinates = new String[2];
        if (dimension == 0 ||input == null) {
            throw new NullPointerException("ERROR, THE PARAMETERS CANNOT BE EMPTY");
        } else {
            while (invalid_input) {
                System.out.print("MAKING-MOVE\nInput original coordinate &\ndestination coordinate (A1-H8)\nseparated by space:");
                input_coordinate = input.nextLine();String[] separate_coordinates = input_coordinate.split(" ");
                if (separate_coordinates.length == 2) { invalid_input = !(FoxHoundUtils.input_coordinates_valid(dimension, separate_coordinates));
                    if (separate_coordinates.length == coordinates.length){
                        for (int i = 0; i < coordinates.length; i++) {
                            coordinates[i] = separate_coordinates[i]; }
                    } else {
                        System.err.println("ERROR! Invalid Coordinates or Format , Input Example [A1 B2]");}
                    System.out.println("");
                } else  {
                    invalid_input = true;
                    System.err.println("ERROR! Invalid Coordinates or Format , Input Example [A1 B2]");
                }
            }
        }
        return coordinates;
    }
    // FILE SAVING GAME MENU
    public static Path fileQuery (Scanner input) {
        Path path;
        if (input == null) {
            throw new NullPointerException("ERROR! INPUT CANNOT BE EMPTY");
        } else {
            System.out.println("SAVE FILE NAME: ");
            String answer = input.nextLine();
            if (!(answer.contains(".txt"))) {
                answer = answer + ".txt";
            }
            path = Paths.get(answer);
        }
        return path;
    }

}







