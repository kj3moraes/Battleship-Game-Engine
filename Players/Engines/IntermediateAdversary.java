package Players.Engines;

import Services.Battlefield;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class IntermediateAdversary extends NaiveSolver {
    Battlefield enemyArena;
    private ArrayList<Integer> targets;
    private ArrayList<Integer> hunts;
    private Stack<Integer> targetsFired;

    /** DEFINITIONS ---------------------------------------------------------------------
     *  > ENCODED COORDINATES
     *      This is the the integer representation of the Battlefield coordinates. It
     *      spans from 0-99 and is as follows :
     *          A   B   C   D   E  ...
     *       1  0   1   2   3   4  ...
     *       2  10  11  12  13  14 ...
     *       3          ...
     *
     *       All the 'A' column are 0 mod 10. All the 'B' columns are 1 mod 10 and so on
     */

    public IntermediateAdversary() {
        rng = new Random();
        enemyArena = new Battlefield();
        targets = new ArrayList<>();
        hunts = new ArrayList<>();
        targetsFired = new Stack<>();
    }

    /**
     * encode(String) -------------------------------------------------------------------
     * This encodes the String coordinates of the battlefield into the encoded coordinates.
     * @param coordinates - String coordinates.
     * @return - integer in encoded coordinates.
     */
    private int encode(String coordinates) {
        char row = coordinates.charAt(0);
        int column = Integer.parseInt(coordinates.substring(1));
        return (row - 'A') * 10 + column - 1;
    }

    /**
     * decode(int) ----------------------------------------------------------------------
     * Reverses the action of encode(String)
     * @param encodedCoord - encoded coordinates
     * @return - String coordinates.
     */
    private String decode(int encodedCoord) {
        char row = (char) (encodedCoord / 10  + 'A');
        int column = encodedCoord % 10 + 1;
        return row + "" + column;
    }

    @Override
    public String fireASalvo() {
        return null;
    }

    /**
     * createTargets(List<Integer>, List<Integer>) --------------------------------------
     * Produces a target list and a hunting list.
     * @param targets - parameter to create a list
     * @param hunts - parameter to c
     */
    private void createTargets(ArrayList<Integer> targets, ArrayList<Integer> hunts) {
        // TARGETS : All squares
        for (int i = 0; i < 100; i++) {
            targets.add(i);
        }

        // HUNTING : All even parity squares
        for (int i = 1; i < 100; i+=2) {
            if (Math.floor((float) i / 10) % 2 == 0) {
                hunts.add(i);
            } else if (Math.floor((float) i / 10) % 2 == 1) {
                hunts.add(i - 1);
            }
        }
    }

    /**
     * huntSquares() --------------------------------------------------------------------
     * Occurs at the start of the Wartime when no opponent ship is located. Picks out
     * random squares from the 'hunts' ArrayList and then removes them from huntList
     * if they are out of play.
     * @return - coordinates for hunting.
     */
    private String huntSquares() {
        String target;
        targetsFired.empty();
        int randIndex = 0, randHuntSqaure = 0;

        // STEP 1.1 : GENERATE HUNT COORDINATES (if we still have sqaures left)
        if (!hunts.isEmpty()) {
            randIndex = (int) rng.nextInt(hunts.size());
        }
        // STEP 1.2 : PICK FROM TARGET LIST
        else {
            randIndex = (int) rng.nextInt(targets.size());
        }
        randHuntSqaure = hunts.get(randIndex);

        // STEP 2 : DECODE FROM Integer TO Battlefield Coordinates
        target = decode(randHuntSqaure);

        // STEP 3 : REMOVE THE HUNTING COORDINATES FROM hunts AND targets
        hunts.remove(randHuntSqaure);
        targets.remove(randHuntSqaure);
        return target;
    }
}//end of class
