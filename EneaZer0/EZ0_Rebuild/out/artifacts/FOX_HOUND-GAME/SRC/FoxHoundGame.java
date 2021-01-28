//MAIN GAME CLASS
import java.util.Scanner;
public class FoxHoundGame {
    private static final Scanner STDIN_SCAN = new Scanner(System.in);
    private static char swapPlayers(char currentTurn) {
        if (currentTurn == FoxHoundUtils.FOX_FIELD) {
            return FoxHoundUtils.HOUND_FIELD;
        } else {
            return FoxHoundUtils.FOX_FIELD;
        }
    }
    private static void gameLoop(int dim, String[] players) {
        if (dim == 0 || players == null) {
            throw new NullPointerException("ERROR!! THE GAMELOOP PARAMETERS CANNOT BE EMPTY");
        } else {
            // start each game with the Fox
            char turn = FoxHoundUtils.FOX_FIELD;
            boolean exit = false;
            while(!exit) {
//                System.out.println("#################################");
                System.out.println("######KIBU- FOXHOUND - GAME######");
//                System.out.print("#################################\n");
                try{
                    FoxHoundUI.displayBoard(players, dim);
                } catch (NullPointerException error) {
                    System.err.println(error.getMessage());
                }
                int choice = FoxHoundUI.mainMenuQuery(turn, STDIN_SCAN);
                switch(choice) { //process choices
                    case FoxHoundUI.MENU_MOVE:
                        try {
                            FoxHoundUtils.make_the_step(players,dim,STDIN_SCAN,turn);
                            turn = swapPlayers(turn);
                        } catch (NullPointerException error) {
                            System.err.println(error.getMessage());
                        }
                        break;
                    case FoxHoundUI.MENU_SAVE:
                        try {
                            FoxHoundIO.saveGame(players, turn, FoxHoundUI.fileQuery(STDIN_SCAN));
                        } catch (IllegalArgumentException error) {
                            System.err.println(error.getMessage());
                        } catch (NullPointerException error) {
                            System.err.println(error.getMessage());
                        }
                        break;
                    case FoxHoundUI.MENU_LOAD:
                        try {
                            FoxHoundIO.loadGame(players,FoxHoundUI.fileQuery(STDIN_SCAN));
                        } catch (NullPointerException error) {
                            System.err.println(error.getMessage());
                        } catch (IllegalArgumentException error) {
                            System.err.println(error.getMessage());
                        }
                        break;
                    case FoxHoundUI.MENU_EXIT:
                        exit = true;
                        break;
                    default:
                        System.err.println("ERROR: Your Menu Choice is Incorrect: " + choice);

                }
            }
        }
    }
    public static void main() {
        int dimension = FoxHoundUtils.DEFAULT_DIM;
//        if (args.length != 0) {
//            dimension = Integer.parseInt(args[0]);
//        }
        try {
            dimension = FoxHoundUtils.dimension_check(dimension);
        } catch (NullPointerException error) {
            System.err.println(error.getMessage());
            dimension = FoxHoundUtils.DEFAULT_DIM;
        }
        try {
            String[] players = FoxHoundUtils.initialisePositions(dimension);
            gameLoop(dimension, players);
        } catch (NullPointerException error) {
            System.err.println(error.getMessage());
        } catch (IllegalArgumentException error) {
            System.err.println(error.getMessage());
        }
        STDIN_SCAN.close(); // closes Scanner object
    }
}
