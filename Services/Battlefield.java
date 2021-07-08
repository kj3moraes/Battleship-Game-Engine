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

    /** DEFINITIONS ---------------------------------------------------------------------
     *  > NORMALIZED
     *      This means that given a set of coordinates for a ship placement
     *      (roF, roS, coF, coS) then roF < roS and coF < coS.
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
        battlefield  = new char[10][10];
        this.HIT = 'X';
        this.MISS = 'M';
        this.WATER = '~';
        this.SHIP = 'O';

        // FOG OF WAR
        for (char[] row : battlefield) {
            Arrays.fill(row, WATER);
        }
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
        return salvoStatus(row, col) == SHIP || salvoStatus(row, col) == HIT;
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
     * isNavyAfloat() -------------------------------------------------------------------
     * Finds out if there is any ship / part of a ship still surviving
     * @return - False if all ships are destroyed. True otherwise
     */
    public boolean isNavyAfloat() {
        for (char[] row : battlefield) {
            for (char status : row) {
                if (status == SHIP)
                    return true;
            }
        }
        return false;
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
    public boolean isCorrectCoordinates(char roF, char roS, int coF, int coS, Ship s) {

        // CHECK FOR COORDINATES OUTSIDE THE BOARD
        if (roF > 'J' || roF < 'A' || roS > 'J' || roS < 'A') {
            System.out.print("\nError! Invalid Row Coordinates. Please enter a value between A and J.");
            System.out.println(" Try again :");
            return false;
        } else if (coF > 10 || coF < 1 || coS > 10 || coS < 1) {
            System.out.print("\nError! Invalid Column Coordinates. Please enter a value between 1 and 10.");
            System.out.println(" Try again :");
            return false;
        }

        // ENSURE THAT WE ARE USING A VALID SHIP
        if (s != null) {
            // CHECK FOR COORDINATES NOT CORRESPONDING TO STRAIGHT LINES
            if (roF != roS && coF != coS) {
                System.out.println("Error! Wrong ship location! Try again:");
                return false;
            } else if (roF == roS) {
                if (Math.abs(coF - coS) + 1 != s.getShipLength()) {
                    System.out.println("Error! Wrong length of the " + s.getShipName() + "! Try again:");
                    return false;
                }
            } else {
                if (Math.abs(roF - roS) + 1 != s.getShipLength()) {
                    System.out.println("Error! Wrong length of the " + s.getShipName() + "! Try again:");
                    return false;
                }
            }
            // CHECK IF THE SHIP IS CROSSING OUR TOUCHING ANY OTHER PRE-EXISTING SHIP
            if (isCrossing(roF, roS, coF, coS) || isTouching(roF, roS, coF, coS, false)) {
                return false;
            }
        }
        return true;
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
                    System.out.println("Error! Your ships cannot cross one another. Try again:");
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
     * @param isWartime -
     * @return - if the ship is touching any others (during setup)
     */
    public boolean isTouching(char roF, char roS, int coF, int coS, boolean isWartime) {
        // CHECK FOR TOUCHING OTHER SHIPS OR PIECES OF SHIPS
        boolean touch = false;
        for (int i = roF - 65; i <= roS - 65; i++) {
            for (int j = coF - 1; j <= coS - 1; j++) {
                if (roF == roS) {
                    if (coF - 2 >= 0)
                        touch = battlefield[roF - 65][coF - 2] == SHIP;
                    if (coS <= 9)
                        touch = battlefield[roF - 65][coS] == SHIP || touch;

                    if (roF - 66 >= 0)
                        touch = battlefield[roF - 66][j] == SHIP || touch;
                    if (roS - 64 <= 9)
                        touch = battlefield[roS - 64][j] == SHIP || touch;
                } else {
                    if (roF - 66 >= 0)
                        touch = battlefield[roF - 66][coF - 1] == SHIP;
                    if (roS - 64 <= 9)
                        touch = battlefield[roS - 64][coF - 1] == SHIP || touch;

                    if (coF - 2 >= 0)
                        touch = battlefield[i][coF - 2] == SHIP || touch;
                    if (coS <= 9)
                        touch = battlefield[i][coS] == SHIP || touch;
                }
                if (touch && isWartime) {
                    return true;
                }
                if (touch) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * isSunken(char, int) --------------------------------------------------------------
     * Finds out if the hit at position (rowCo, columnCo) is the final hit that sinks
     * the ship.
     * REQUIRES :
     *      - the (rowCo, columnCo) coordinate must be a HIT
     * @param rowCo - row coordinate of the hit
     * @param columnCo - column coordinate of the hit
     * @return - if the ship is sunken or not
     */
    public boolean isSunken(char rowCo, int columnCo) {
        return !isTouching(rowCo, rowCo, columnCo, columnCo, true);
    }
}
