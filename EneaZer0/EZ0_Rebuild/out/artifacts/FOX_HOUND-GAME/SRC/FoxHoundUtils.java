// TOOLS USED BY GAME
import java.util.Arrays;
import java.util.Scanner;
public class FoxHoundUtils { //UTILITIES  CLASS
    public static final int DEFAULT_DIM = 8;
    public static final int MIN_DIM = 4;
    public static final int MAX_DIM = 26;
    public static final char HOUND_FIELD = 'H';
    public static final char FOX_FIELD = 'F';
    public static final char DOT_FIELD = ' ';

    public  static final String NUMBERS_MARGIN = "|";
    public static String alphabet_margin (int dimension){
        String alphabet_margin = "";
        if (dimension == 0) {
            throw new NullPointerException("ERROR! DIMENSIONS CANNOT BE EMPTY");
        } else {
            if (dimension < 10) {
                alphabet_margin = "  ";
            } else {
                alphabet_margin = "   ";
            }

        }
        return alphabet_margin;
    }
    private static String[] letter_recognition;
    private static String[] number_recognition;
    private static int dimension;
    private static int row;
    public static int dimension_check (int dimension) {
        if (dimension == 0) {
            throw new NullPointerException("ERROR, DIMENSION NOT VALID");
        } else {
            if ((dimension < MIN_DIM) || (dimension > MAX_DIM)) {
                dimension = DEFAULT_DIM;
            }
        }

        return dimension;
    }
    public static String[] letter_recognition (String[] players) { //CHECKING LETTER
        String[] letters_recognised = new String[players.length];
        if (players == null) {
            throw new NullPointerException("ERROR, PLAYERS CANT BE NULL");
        } else {
            int k = 0;
            for (String str: players) {

                String [] split = str.split("");

                for (String elements: split) {
                    char element_char = elements.charAt(0);
                    if ((element_char >= (char)65) && (element_char < 91)) {
                        //System.out.println(element_char + " DETECTED");
                        letters_recognised[k] = ""+ element_char;
                        k++;

                    } else {

                    }
                }
            }
        }
        return letters_recognised;
    }
    public static String[] number_recognition (String[] players) {//CHECKING NUMBERS
        String[] numbers_recognised = null;
        if (players == null) {
            throw new NullPointerException("ERROR, PLAYERS CANT BE NULL");
        } else {
            numbers_recognised = new String[players.length];
            int k = 0;
            boolean numbers_together = false;
            for (String str: players) {
                String [] split = str.split("");
                for (String elements: split) {
                    char element_char = elements.charAt(0);
                    if ((element_char >= (char)48) && (element_char < (char)58)) {
                        if (numbers_together == false) {
                            numbers_recognised[k] = ""+ element_char;
                            k++;
                            numbers_together = true;
                        } else {
                            k--;
                            String two_digit_number = numbers_recognised[k] + element_char;
                            numbers_recognised[k] = two_digit_number;
                            k++;
                            numbers_together = false;}
                    } else {
                        numbers_together = false;

                    }
                }
            }
        }
        return numbers_recognised;
    }
    public static int fox_position (int dimension) {
        int position = 0;

        if (dimension == 0) {
            throw new NullPointerException("ERROR, DIMENSION CAN'T BE NULL");
        } else {
            // Black = array with the positions of the black squares were the fox can be
            int[] blacks = new int[dimension/2];
            if (dimension % 2 == 0) {
                for (int i = 0; i < dimension/2; i++) {
                    if (i == 0) {
                        blacks[i] = i;
                    } else {
                        blacks[i] = 2*i;
                    }
                }
            } else {
                for (int i = 0; i < dimension /2; i++) {
                    blacks[i] = 2*i + 1;
                }
            }
            position = blacks[blacks.length/2];
        }

        return position;
    }
    public static String[] initialisePositions(int dimension) {
        String[] position_array = null;
        if (dimension < 0) {
            throw new IllegalArgumentException("ERROR! DIMENSIONS MUST BE GREATER THAN 0");
        } else if ( dimension == 0) {
            throw new NullPointerException("ERROR! DIMENSIONS CANNOT BE EMPTY");
        } else {

            try {
                int number_Hound = (dimension / 2);
                int number_Fox = 1;
                position_array = new String[number_Hound + number_Fox];
                String hound_num_initial_position = "1";
                String fox_num_initial_position = String.valueOf(dimension);
                String[] hound_values = new String[number_Hound];
                for (int i = 0; i < number_Hound; i ++) {
                    hound_values[i] = (String.valueOf((char)(66 + 2*i)) + hound_num_initial_position);
                }
                for (int i = 0; i < number_Hound; i++) {//setting hound positions
                    position_array[i] = hound_values[i]; }
                int fox_position = fox_position(dimension); //setting fox positions
                int fox_letter = 65 + fox_position;
                String fox_value = String.valueOf((char)fox_letter) + fox_num_initial_position;
                position_array[position_array.length-1] = fox_value;
            } catch (NullPointerException error) {
                System.err.println(error.getMessage());
            }

        }
        return  position_array;
    }
    public static int[] line_diagram (String[] letter_recognition, String[] number_recognition, int dimension, int row) {
        int[] the_line = null;
        if (letter_recognition == null ||number_recognition == null || dimension == 0) {
            throw new NullPointerException("ERROR, PARAMETERS CANNOT BE EMPTY");
        } else {
            the_line = new int[dimension];
            for (int i = 0; i < dimension; i++) {
                the_line[i] = 0;
            }
            int position;int piece = 0;
            for (int i = 0; i < number_recognition.length; i++) {
                piece++;
                if (Integer.parseInt(number_recognition[i]) == row ) {
                    char letter_char = letter_recognition[i].charAt(0);
                    position = letter_char - 65;
                    if (piece < letter_recognition.length) {
                        the_line[position] = 1;
                    } else {
                        the_line[position] = 2;
                    }
                }
            }
        }
        return the_line;
    }
    public static int[][] griding (String[] letter_recognition, String[] number_recognition, int dimension, int row) {
        int[][] grid = null;

        if (letter_recognition == null ||number_recognition == null ||dimension == 0) {
            throw new NullPointerException("ERROR, PARAMETERS CAN'T BE NULL");
        } else {
            try {
                grid = new int[dimension][];
                for (int i = 0; i < dimension; i++) {
                    grid[i] = line_diagram(letter_recognition, number_recognition, dimension, i + 1);
                }
            } catch (NullPointerException error) {
                System.err.println(error.getMessage());
            }
        }
        return grid;
    }
    public static void board (int[][] grid, String alphabet, String[] numbers, int dimension) {
        if (grid == null || alphabet == null || numbers == null || dimension == 0) {
            throw new NullPointerException("ERROR PARAMETERS CAN`T BE NULL");
        } else{
            try {
                String letters = alphabet_margin(dimension) + alphabet;
                String xAlphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                char[] abc = xAlphabet.toCharArray();
                System.out.print("  ");//printing margin for letters
                for (int ix = 0;ix<dimension;ix++) {
                    //print letters
                    System.out.print(" "+abc[ix]+"  ");
                }
                System.out.print("\n");
                System.out.print(" ");//printing margin for letters
                for (int ix = 0;ix<dimension;ix++) {
                    System.out.print("|===");
                    //print boarder
                }
                System.out.print("|\n");
                String one_line = "";
                int row = 0;
                for (int[] line: grid) {
                    one_line = "" + numbers[row] + NUMBERS_MARGIN;
                    for (int values: line) {
                       one_line = one_line+" ";
                        if (values == 0) {
                            one_line = one_line + DOT_FIELD;
                        } else if (values == 1) {
                            one_line = one_line + HOUND_FIELD;
                        } else {
                            one_line = one_line + FOX_FIELD;
                        }
                        one_line = one_line+" |";
                    }
                    one_line = one_line + numbers[row];
                    row++;
                    System.out.println(one_line);
                    System.out.print(" ");
                    for (int ix = 0;ix<dimension;ix++) {
                        System.out.print("|===");
                        //print boarder
                    }
                    System.out.print("|\n");
                }
//                System.out.println("\n" + letters + "\n");
                System.out.print("  ");//printing margin for letters
                for (int ix = 0;ix<dimension;ix++) {
                    //print letters
                    System.out.print(" "+abc[ix]+"  ");
                }
                System.out.print("\n");
                System.out.print(" ");//printing margin for letters
            } catch (NullPointerException error ) {
                System.err.println(error.getMessage());
            }
        }

    }
    public static boolean isValidMove (int dimension, String[] players, char figure, String origin, String destination) {
        boolean isValid = true;
        try {
            isValid = coordinate_checker(dimension, origin) && isValid;
        } catch (IllegalArgumentException error) {
            isValid = false;
            System.err.println(error.getMessage());
        } catch (NullPointerException error) {
            isValid = false;
            System.err.println(error.getMessage());
        }
        try {
            isValid = in_players(origin,players) && isValid;
        } catch (NullPointerException error) {
            System.err.println(error.getMessage());
            isValid = false;
        }
        try {
            isValid = coordinate_checker(dimension, destination) && isValid;
        } catch (IllegalArgumentException error) {
            isValid = false;
            System.err.println(error.getMessage());
        } catch (NullPointerException error) {
            isValid = false;
            System.err.println(error.getMessage());
        }
        try {
            isValid = !(in_players(destination,players)) && isValid;
        } catch (NullPointerException error) {
            System.err.println(error.getMessage());
            isValid = false;
        }
        try{
            if (origin == null) {
                throw new NullPointerException("ERROR, ORIGIN CAN NOT BE NULL");
            } else {
                isValid = !(origin.equals(destination)) && isValid;
            }
        } catch (NullPointerException error) {
            isValid = false;
            System.err.println(error.getMessage());
        }
        try {
            isValid = char_checker(figure, origin, players) && isValid;
        } catch (IllegalArgumentException error) {
            System.err.println(error.getMessage());
            isValid = false;
        } catch (NullPointerException error) {
            System.err.println(error.getMessage());
            isValid = false;
        }
        try{
            isValid = in_diagonal(origin, destination) && isValid;
        } catch (NullPointerException error) {
            System.err.println(error.getMessage());
            isValid = false;
        }
        try {
            isValid = hound_movement_allowed(figure, origin, destination) && isValid;
        } catch (NullPointerException error) {
            System.err.println(error.getMessage());
            isValid = false;
        } catch (IllegalArgumentException error){
            System.err.println(error.getMessage());
            isValid = false;
        }
        return isValid;
    }
    public static boolean coordinate_checker (int dimension, String coordinate) {
        boolean validity = true;
        char letter = ' ';
        int number = 0;
        if (coordinate == null) {
            throw new NullPointerException("ERROR, COORDINATE CAN'T BE NULL");
        } else if (dimension == 0){
            throw new IllegalArgumentException("ERROR, DIMENSION NOT VALID");
        } else {
            try {
                letter = FoxHoundUtils.letter_coordinate(coordinate);
                validity = validity && true;

            } catch (IllegalArgumentException error){
                validity = false;
                System.err.println(error.getMessage());
            } catch (NullPointerException error) {
                validity = false;
                System.err.println(error.getMessage());
            }
            if ( 65 <= (int)letter && (int)letter <= (90 - (26 - dimension)) ){
                validity = validity && true;
            } else {
                validity = false;
                System.err.println("ERROR, LETTER " + letter + " IS NOT IN THE RANGE OF THE DIMENSION");
            }
            try {
                number = FoxHoundUtils.number_coordinate(coordinate);
                validity = validity && true;

            } catch (IllegalArgumentException error){
                validity = false;
                System.err.println(error.getMessage());
            } catch (NullPointerException error) {
                validity = false;
                System.err.println(error.getMessage());
            }
            if ( 1 <= number && number <= dimension) {
                validity = validity && true;
            } else {
                validity = false;
                if (number == 0) {
                    System.err.println("ERROR, NO NUMBER FOUND");
                } else {
                    System.err.println("ERROR, NUMBER " + number + " IS NOT IN THE RANGE OF THE DIMENSION");
                }
            }
            if (validity == false) {
                System.err.println("ERROR, " + coordinate + " IS NOT A VALID COORDINATE");
            }
        }
        return validity;
    }
    public static boolean in_players (String origin, String[] players) {
        boolean origin_players = false;
        if (players == null) {
            throw new NullPointerException("ERROR, PLAYERS CAN NOT BE NULL");
        } else {
            for (int i = 0; i < players.length; i++) {
                origin_players = origin_players || (origin.equals(players[i]));
            }
        }
        return origin_players;
    }
    public static boolean char_checker (char figure, String origin, String[] players) {
        boolean valid = false;
        int k = 0;
        if (origin == null || players == null) {
            throw new NullPointerException("ERROR, char_checker can't have null parameters");
        } else if (figure == ' ') {
            throw new IllegalArgumentException("ERROR, that figure is nos valid for this function");
        } else {
            for (int i = 0; i < players.length; i++) {
                k = i;
                if (origin.equals(players[i])) {
                    break;
                }
            }
            if (k < players.length - 1) {
                if (figure == 'H') {
                    valid = true;
                } else {
                    System.err.println("THE FIGURE " + figure + " IS NOT VALID");
                }
            } else {
                if (figure == 'F') {
                    valid = true;
                } else {
                    System.err.println("THE FIGURE " + figure + " IS NOT VALID");
                }
            }
        }
        return valid;
    }
    public static boolean in_diagonal (String origin, String destination) {
        boolean valid = true;
        char letter_origin = letter_coordinate(origin);
        int number_origin = number_coordinate(origin);
        char letter_destination = letter_coordinate(destination);
        int number_destination = number_coordinate(destination);
        if (origin == null || destination == null) {
            throw new NullPointerException("ERROR, THE PARAMETERS OF THIS FUNCTION CAN'T BE NULL");
        } else {
            if (((int)letter_destination == (int)letter_origin  + 1 ) || ( (int)letter_destination == (int)letter_origin  - 1 )) {
                valid = valid && true;
            } else {
                valid = false;
                System.err.println("ERROR, LETTERS ARE NOT IN DIAGONAL");
            }
            if ((number_destination == number_origin + 1) || (number_destination == number_origin - 1)) {
                valid = valid && true;
            } else {
                valid = false;
                System.err.println("ERROR, NUMBERS ARE NOT IN DIAGONAL");
            }
        }
        return valid;
    }
    public static char letter_coordinate (String coordinate) {
        char letter = ' ';
        if (coordinate == null) {
            throw new NullPointerException("ERROR, THE COORDINATE CAN'T BE NULL");
        } else {
            if (coordinate.length() == 2 || coordinate.length() == 3) {
                letter = coordinate.charAt(0);
                if ((int) letter > 90 || 65 > (int) letter) {
                    letter = coordinate.charAt(0);
                    System.err.println("ERROR " + letter + " IS NOT A VALID LETTER");
                }
            } else {
                System.err.println("ERROR " + coordinate + " IS NOT A VALID COORDINATE");
            }
        }
        return letter;
    }
    public static int number_coordinate (String coordinate) {
        char number1;
        char number2;
        String number = "";
        int final_number = 0;

        if (coordinate == null) {
            throw new NullPointerException("ERROR, COORDINATES CAN'T BE NULL (NUMBER COORDINATE)");
        } else {
            // GETTING NUMBER IN CASE DIMENSION IS ONE DIGIT DIMENSION
            if (coordinate.length() == 2) {

                number1 = coordinate.charAt(1);
                // CHECK IF THE NUMBER RECEIVED IS REALLY A NUMBER
                if (48 <= (int) number1 && (int) number1 <= 57) {
                    final_number = Integer.parseInt(String.valueOf(number1));
                } else {
                    throw new IllegalArgumentException("ERROR " + number1 + " IS NOT A NUMBER ");
                }

            } else if (coordinate.length() == 3) /* GETTING NUMBERS IN CASE DIMENSION IS A TWO DIGIT DIMENSION*/ {
                number1 = coordinate.charAt(1);
                number2 = coordinate.charAt(2);

                // CHECK IF THE NUMBERS RECEIVED ARE REALLY NUMBERS
                if ((48 <= (int) number1 && (int) number1 <= 57) && (48 <= (int) number2 && (int) number2 <= 57)) {
                    number = number + number1 + number2;
                    final_number = Integer.parseInt(number);
                } else {
                    throw new IllegalArgumentException("ERROR " + number1 + " OR " + number2 + " ARE NOT NUMBERS");
                }

            } else {

                throw  new IllegalArgumentException("ERROR " + coordinate + " IS NOT A VALID COORDINATE");

            }
        }
        return final_number;
    }
    public static boolean hound_movement_allowed(char figure, String origin, String destination) {
        boolean valid = true;
        if (origin == null || destination == null) {
            throw new NullPointerException("ERROR, THE PARAMETERS OF THIS FUNCTION CAN'T BE NULL");
        } else if (figure == ' ') {
            throw new IllegalArgumentException("ERROR, THE FIGURE IS NOT VALID");
        } else {
            if (figure == 'H' && (number_coordinate(destination) == number_coordinate(origin) + 1)){
                valid = true;
            } else if (figure == 'F') {
                valid = true;
            } else if (figure == 'H' && (number_coordinate(destination) < number_coordinate(origin) + 1)){
                valid = false;
                System.err.println("ERROR, HOUNDS CAN ONLY MOVE FORWARD");
            } else {
                valid = false;
                System.err.println("ERROR, PICK A HOUND TO MOVE");
            }
        }
        return valid;
    }
    public static boolean input_coordinates_valid (int dimension, String[] coordinates) {
        boolean valid = false;
        if (dimension == 0 || coordinates == null) {
            throw new NullPointerException("ERROR, PARAMETERS CAN'T BE NULL");
        } else {
            valid = true;
            if (coordinates.length != 2) {
                valid = false;
            }
            for (int i = 0; i < coordinates.length; i++) {

                if (!((int)coordinates[i].charAt(0) <= 90 -(26 - dimension) && (int)coordinates[i].charAt(0) >= 65 )) {
                    valid = false;
                    System.err.println("ERROR: Please enter valid coordinate pair separated by space.");
                    break;
                } else {
                    valid = true;
                }
                // CHECK NUMBERS
                if (number_coordinate(coordinates[i]) > dimension) {
                    valid = false;
                    System.err.println("ERROR: Please enter valid coordinate pair separated by space.");
                    break;
                } else {
                    valid = true;
                }
            }
        }
        return valid;
    }
    public static String[] new_players_position (String[] players, String[] positionQuery) {

        if (players == null ||positionQuery == null) {
            throw new NullPointerException("ERROR, PARAMETERS CAN'T BE NULL");
        } else {
            int k = 0;
            for (int i = 0; i < players.length; i++) {
                k = i;
                if (positionQuery[0].equals(players[i])){
                    break;
                }
            }

            players[k] = positionQuery[1];
        }
        return players;
    }
    public static String[] make_the_step (String[] players, int dimension, Scanner input, char figure) {

        if (players == null || dimension == 0 || figure == ' ' || input == null){
            throw new NullPointerException("ERROR, PARAMETERS CAN'T BE NLL");
        } else {
            try {
                String positionQuery_string = Arrays.toString(FoxHoundUI.positionQuery(dimension,input));
                //System.out.println(positionQuery_string);
                positionQuery_string = positionQuery_string.substring(1);
                positionQuery_string = positionQuery_string.replaceAll("]", "").trim();
                positionQuery_string = positionQuery_string.replaceAll(",", "").trim();
                //System.out.println(positionQuery_string);
                String[] positionQuery_array = positionQuery_string.split(" ");

                if (isValidMove(dimension,players,figure,positionQuery_array[0],positionQuery_array[1]) == true) {
                    FoxHoundUtils.new_players_position(players,positionQuery_array);
                } else {
                    make_the_step(players,dimension,input,figure);
                }
                if (figure == 'F') {
                    try{
                        isFoxWin(positionQuery_array[1]);
                    } catch (NullPointerException error) {
                        System.err.println(error.getMessage());
                    }
                }
                if (figure == 'H') {
                    try {
                        isHoundWin(players, dimension);
                    } catch (NullPointerException error){
                        System.err.println(error.getMessage());
                    }

                }
            } catch (NullPointerException error ){
                System.err.println(error.getMessage());
            }

        }

        return players;
    }
    public static boolean isFoxWin (String fox_position) {
        boolean fox_win = false;

        if (fox_position == null) {
            throw new NullPointerException("ERROR, THE PARAMETER CAN'T BE NULL");
        } else {
            if (number_coordinate(fox_position) == 1) {
                fox_win =true;
                System.out.println("FOX WON THE GAME");

            } else {
                System.out.println("FOX NOT WON THE GAME YTE");
            }
        }


        return fox_win;
    }
    public static boolean isHoundWin(String[] players, int dimension) {
        boolean fox_win = true;
        if (dimension < 4) {
            throw new IllegalArgumentException("ERROR: THE DIMENSION IS NOT VALID");
        }

        if (players == null || dimension == 0) {
            throw new NullPointerException("ERROR: THE PLAYERS CAN NOT BE NULL");
        } else {

            fox_win = false;
            String fox_coordinates = players[players.length-1];
            char fox_letter = letter_coordinate(fox_coordinates);
            int fox_number = number_coordinate(fox_coordinates);
            String[] destination = new String[4];
            destination[0] = "" + (char)((int)fox_letter + 1) + (fox_number + 1); // right top corner
            destination[1] = "" + (char)((int)fox_letter + 1) + (fox_number - 1); // right bottom corner
            destination[2] = "" + (char)((int)fox_letter - 1) + (fox_number + 1); // left top corner
            destination[3] = "" + (char)((int)fox_letter - 1) + (fox_number - 1); // left bottom corner
            for (int i = 0; i < destination.length; i++) {
                try {
                    fox_win = (isValidMove(dimension,players,'F',fox_coordinates,destination[i])) || fox_win;
                } catch (NullPointerException error) {
                    System.err.println(error.getMessage());
                    fox_win = false;
                }
            }
            if (!fox_win) {
                System.out.println("HOUNDS WIN THE GAME");
            } else {
                System.out.println("THE HOUNDS HAVE NOT WON YET");
            }
        }
        return !fox_win;
    }
}
