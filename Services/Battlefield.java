package Services;
import java.util.Arrays;
public class Battlefield {
    private char[][] battlefield;
    public final char HIT;
    public final char MISS;
    public final char SHIP;
    public final char WATER;

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

    public boolean isHit(int row, int col) {
        return battlefield[row][col] == HIT;
    }

    public void placePiece(char row, int col, char result) {
        battlefield[row - 65][col] = result;
    }

    public char salvoStatus(int row, int col) {
        return battlefield[row][col];
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
     * This function determines if the inputed coordinates are valid for the particular
     * ship.
     * @param roF - row of the first coordinate
     * @param roS - row of the second coordinate
     * @param coF - column of the first coordinate
     * @param coS - column of the second coordinate
     * @param ship - enum descibing the specifications of the ship.
     * @return - if the coordinates are valid
     */
    public boolean isCorrectCoordinates(char roF, char roS, int coF, int coS, Ship ship) {

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

        if (ship != null) {
            // CHECK FOR COORDINATES NOT CORRESPONDING TO STRAIGHT LINES
            if (roF != roS && coF != coS) {
                System.out.println("Error! Wrong ship location! Try again:");
                return false;
            } else if (roF == roS) {
                if (Math.abs(coF - coS) + 1 != ship.getShipLength()) {
                    System.out.println("Error! Wrong length of the " + ship.getShipName() + "! Try again:");
                    return false;
                }
            } else {
                if (Math.abs(roF - roS) + 1 != ship.getShipLength()) {
                    System.out.println("Error! Wrong length of the " + ship.getShipName() + "! Try again:");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * isCrossing(char, char, int, int) -------------------------------------------------
     * Calculates if the inputed coordinates overlap with a pre-existing ship placement.
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
    protected boolean isSunken(char rowCo, int columnCo) {
        return !isTouching(rowCo, rowCo, columnCo, columnCo, true);
    }
}
