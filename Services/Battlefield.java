package Services;

import java.util.Arrays;

public class Battlefield {
    private final char[][] battlefield;

    // BASE PIECE
    public final char WATER;

    // ADDED PIECE(s)
    public final char HIT;
    public final char MISS;
    public final char SHIP;

    //CODES FOR COORDINATE PLACEMENT
    public static final int VALID_COORD = 0x0F;

    // VALID ERROR CODES
    private static final int TOUCHING = 0x1C;
    private static final int CROSSING = 0xAF;
    private static final int OUT_OF_BOARD = 0xBD;
    private static final int WRONG_LENGTH = 0xFF;
    private static final int MISALIGN = 0x4E;

    /** DEFINITIONS ---------------------------------------------------------------------
     *  > NORMALIZED
     *      This means that given a set of coordinates for a ship placement
     *      (roF, roS, coF, coS) then roF <= roS and coF <= coS.
     *
     *  > TOUCHING
     *      Another ship's placement cannot be either to the immediate left or the
     *      right of any pre-existing ship.
     *
     *  > COORDINATES
     *      The row parameters must be between 'A' and 'J" inclusive.
     *      The column parameters must be between 1 and 10 inclusive.
     */

    public Battlefield() {
          this('X', 'M', '~', '0');
    }

    public Battlefield(char HIT, char MISS, char WATER, char SHIP) {
        battlefield  = new char[10][10];
        this.HIT = HIT;
        this.MISS = MISS;
        this.WATER = WATER;
        this.SHIP = SHIP;

        // FOG OF WAR
        for (char[] row : battlefield) {
            Arrays.fill(row, WATER);
        }
    }

    /**
     * isHit(char, int) -----------------------------------------------------------------
     * Determines if the specified row and column hits a ship or a previously hit target
     * @param row - row coordinate
     * @param col - column coordinate
     * @return - whether or not the row and column specify SHIP or HIT
     */
    public boolean isHit(char row, int col) {
        return salvoStatus(row, col) == SHIP;
    }

    /**
     * isMiss(char, int) -----------------------------------------------------------------
     * Determines if the specified row and column misses a ship
     * @param row - row coordinate
     * @param col - column coordinate
     * @return - whether or not the row and column specify MISS
     */
    public boolean isMiss(char row, int col) {
        return salvoStatus(row, col) == WATER;
    }

    /**
     * placePiece(char, int, result) ------------------------------------------------------------
     * Fills the specified coordinate with one of the ADDED PIECE(s).
     * @param row - the row coordinate
     * @param col - the column coordinate
     * @param result - the ADDED PIECE to be placed
     */
    public void placePiece(char row, int col, char result) {
        battlefield[row - 65][col - 1] = result;
    }

    /**
     * salvoStatus(char, int) -----------------------------------------------------------
     * Provides the status of the coordinate in the battlefield specified by the row
     * and column coordinate
     * @param row - row coordinate
     * @param col - column coordiante
     * @return - one of BASE PIECE or ADDED PIECE(s)
     */
    private char salvoStatus(char row, int col) {
        return battlefield[row - 65][col - 1];
    }

    /**
     * printBattlefield(isWartime) ------------------------------------------------------
     * prints out the 'exposed' battlefield during Setup and the 'cloaked' battlefield
     * during Wartime. The cloaked battlefield only shows HIT, MISS and WATER.
     * @param isWartime - describes which battlefield to show.
     */
    public void printBattlefield(boolean isWartime) {
        System.out.print("\n  ");
        for (int i = 1; i <= 10; i++){
            System.out.print(i + " ");
        }
        int row = 0;
        for (char ch = 'A'; ch <= 'J'; ch++){
            System.out.print("\n" + ch + " ");
            for (char position : battlefield[row]) {
                if (isWartime && position == SHIP)
                    System.out.print(WATER + " ");
                else System.out.print(position + " ");
            }
            row++;
        }
        System.out.println("\n");
    }

    /**
     * isCorrectCoordinates(char, char, int, int, Ship)----------------------------------
     * This function determines if the inputed coordinates for the Ship s is a valid
     * placement of the ship. (Refer to Battleship Playing Guide for valid placmement
     * criteria)
     *
     * REQUIRES : The coordinates must be normalized.
     * @param roF - row of the first coordinate
     * @param roS - row of the second coordinate
     * @param coF - column of the first coordinate
     * @param coS - column of the second coordinate
     * @param s - enum describing the specifications of the ship.
     * @return - if the coordinates are valid
     */
    public int isCorrectCoordinates(char roF, char roS, int coF, int coS, Ship s) {

        // CHECK FOR COORDINATES OUTSIDE THE BOARD
        if (roF > 'J' || roF < 'A' || roS > 'J' || roS < 'A') {
            return OUT_OF_BOARD;
        } else if (coF > 10 || coF < 1 || coS > 10 || coS < 1) {
            return OUT_OF_BOARD;
        }

        // ENSURE THAT WE ARE USING A VALID SHIP
        if (s != null) {
            // CHECK FOR COORDINATES NOT CORRESPONDING TO STRAIGHT LINES
            if (roF != roS && coF != coS) {
                return MISALIGN;
            } else if (roF == roS) {
                if (Math.abs(coF - coS) + 1 != s.getShipLength()) {
                    return WRONG_LENGTH;
                }
            } else {
                if (Math.abs(roF - roS) + 1 != s.getShipLength()) {
                    return WRONG_LENGTH;
                }
            }
            // CHECK IF THE SHIP IS CROSSING OUR TOUCHING ANY OTHER PRE-EXISTING SHIP
            if (isCrossing(roF, roS, coF, coS)) {
                return CROSSING;
            } else if (isTouching(roF, roS, coF, coS)) {
                return TOUCHING;
            }
        }
        return VALID_COORD;
    }

    /**
     * isCrossing(char, char, int, int) -------------------------------------------------
     * Calculates if the inputed coordinates overlap with a pre-existing ship placement.
     *
     * REQUIRES : The coordinates must be normalized.
     * @param roF - row of the first coordinate
     * @param roS - row of the second coordinate
     * @param coF - column of the first coordinate
     * @param coS - column of the second coordinate
     * @return - if the ship crosses another ship
     */
    public boolean isCrossing(char roF, char roS, int coF, int coS) {
        // CHECK FOR CROSSING OTHER SHIPS OR TOUCHING OTHER SHIPS
        for (int i = roF - 65; i <= roS - 65; i++) {
            for (int j = coF - 1; j <= coS - 1; j++) {
                if (battlefield[i][j] == SHIP) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * isTouching(char, char, int, int, boolean) ----------------------------------------
     * Determines if the ship is touching any other ship either vertically or
     * horizontally during Setup. During Wartime, it find out if the Ship is sunken.
     *
     * REQUIRES : The coordinates must be normalized.
     * @param roF - row of the first coordinate
     * @param roS - row of the second coordinate
     * @param coF - column of the first coordinate
     * @param coS - column of the second coordinate
     * @return - if the ship is touching any others (during setup)
     */
    public boolean isTouching(char roF, char roS, int coF, int coS) {
        // CHECK FOR TOUCHING OTHER SHIPS OR PIECES OF SHIPS
        boolean touch = false;
        for (int i = roF - 65; i <= roS - 65; i++) {
            for (int j = coF - 1; j <= coS - 1; j++) {
                // HORIZONTAL SHIP PLACEMENT
                if (roF == roS) {
                    // IS THERE A SHIP A COLUMN TO THE RIGHT
                    if (coF - 2 >= 0)
                        touch = battlefield[roF - 65][coF - 2] == SHIP;
                    if (coS <= 9)
                        touch = battlefield[roF - 65][coS] == SHIP || touch;

                    // IS THERE A SHIP PIECE DIRECTLY ONE ROW UP ?
                    if (roF - 66 >= 0)
                        touch = battlefield[roF - 66][j] == SHIP || touch;
                    // IS THERE A SHIP PIECE DIRECTLY ONE ROW DOWN ?
                    if (roS - 64 <= 9)
                        touch = battlefield[roS - 64][j] == SHIP || touch;
                }
                // VERTICAL SHIP PLACEMENT
                else {
                    if (roF - 66 >= 0)
                        touch = battlefield[roF - 66][coF - 1] == SHIP;
                    if (roS - 64 <= 9)
                        touch = battlefield[roS - 64][coF - 1] == SHIP || touch;

                    if (coF - 2 >= 0)
                        touch = battlefield[i][coF - 2] == SHIP || touch;
                    if (coS <= 9)
                        touch = battlefield[i][coS] == SHIP || touch;
                }
                if (touch) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * analyzeErrorInPlacement(int) ------------------------------------------------------
     * Prints out the appropriate message for the specified error code.
     *
     * REQUIRES : errorOut must be a VALID ERROR CODE as defined at the start
     * @param errorOut - a valid error code
     */
    public static void analyzeErrorInPlacement(int errorOut) {
        switch (errorOut) {
            case TOUCHING -> System.out.print("You placed the ships too close to each other");
            case CROSSING -> System.out.print("Your ship cannot cross another ship");
            case OUT_OF_BOARD -> System.out.print("The row coordinates must be between A and J and " +
                    "the column coordinates must be between 1 and 10");
            case MISALIGN -> System.out.print("The placement is not aligned horizontally or vertically");
            case WRONG_LENGTH -> System.out.print("Your placement has wrong length");
        }
        System.out.println(". Try again!");
    }
}
