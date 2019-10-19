import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

public class Main {


    public static final int RED_BOARD = 2000000;
    public static final int BLUE_BOARD = 1500000;
    static HashMap<Integer, Element> myBoard;

    public static void settingTheBoard(int size, int bombs) {
        // Setting the board
        myBoard = new HashMap<>();

        //Changed to doubles in order to get a ratio
        double sizeDouble = size;
        double bombsDouble = bombs;
        double ratio = sizeDouble/bombsDouble;

        // Depending on the ratio bombs:spaces the grid will be filled with spaces or bombs respectively
        if(ratio <= 0.5) {
            for(int i = 0; i < size; i++) {
                myBoard.put(i, new Space());
            }
            // Adds the mines
            minningTheBoard(bombs);
        } else {
            for(int i = 0; i < size; i++) {
                myBoard.put(i, new Mine());
            }
            // Adds the spaces
            spacingTheBoard(size-bombs);
        }

    }

    public static void minningTheBoard(int numberMines) {
        Random random = new Random();
        while (numberMines > 0) {
            Integer trial = new Integer(random.nextInt(myBoard.size()));

            if (myBoard.get(trial) instanceof Space) {
                myBoard.put(trial, new Mine());
                numberMines--;
            }
        }
    }

    public static void spacingTheBoard(int numberSpaces) {
        Random random = new Random();
        while (numberSpaces > 0) {
            Integer trial = new Integer(random.nextInt(myBoard.size()));

            if (myBoard.get(trial) instanceof Mine) {
                myBoard.put(trial, new Space());
                numberSpaces--;
            }
        }
    }

    public static void main(String[] args) {

        long startingTime = System.currentTimeMillis();
        settingTheBoard(RED_BOARD, 1500000);
        long elapsedTime = (System.currentTimeMillis() - startingTime);
        System.out.println("1,500,000 bombs planted in " + elapsedTime + " milliseconds");

        startingTime = System.currentTimeMillis();
        settingTheBoard(BLUE_BOARD, 1499999);
        elapsedTime = (System.currentTimeMillis() - startingTime);
        System.out.println("1,499,999 bombs planted in " + elapsedTime + " milliseconds");
    }
}
