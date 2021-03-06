package Players.Engines;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class IntermediateAdversary extends NaiveSolver {
    private boolean IS_TARGETING;
    private ArrayList<Integer> targets;
    private ArrayList<Integer> hunts;
    private Stack<Integer> targetsFired;
    private String previousShot;

    /** DEFINITIONS ---------------------------------------------------------------------
     *  > ENCODED COORDINATES
     *      This is the the integer representation of the Battlefield coordinates. It
     *      spans from 0-99 and is as follows :
     *          A   B   C   D   E  ...  J
     *       1  0   1   2   3   4  ...  9
     *       2  10  11  12  13  14 ...  19
     *       3          ...
     *
     *       All the 'A' column are 0 mod 10. All the 'B' columns are 1 mod 10 and so on
     */

    public IntermediateAdversary() {
        this.name = "In";
        IS_TARGETING = false;
        rng = new Random();
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
        String target;
        if (IS_TARGETING) {
            target = null;
        } else {
            target = huntSquares();
        }
        return null;
    }

    @Override
    public void manageShipHit(char row, int col) {
        arena.placePiece(row, col, arena.HIT);
        int length = ships.size();
        for (int i = 0; i < length; i++) {
            if (!ships.get(i).isPartOfShip(row, col))
                continue;

            ships.get(i).removeShipPartAndReport(row, col);
            if (ships.get(i).isShipSunken()) {
                System.out.println("You sank a ship!");
                ships.remove(i);
                IS_TARGETING = false;
            } else {
                System.out.println("You hit a ship!");
                IS_TARGETING = true;
            }
            break;
        }
    }

    /**
     * createTargets(ArrayList<Integer>, ArrayList<Integer>) ----------------------------
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
     * Occurs at the start of the Wartime and when no opponent ship is initially hit.
     * Picks out random squares from the 'hunts' ArrayList and then removes them from
     * huntList if they are out of play.
     * @return - coordinates for hunting.
     */
    private String huntSquares() {
        String target;
        targetsFired.empty();
        int randIndex, randHuntSqaure;

        // STEP 1.1 : GENERATE HUNT COORDINATES (if we still have sqaures left)
        if (!hunts.isEmpty()) {
            randIndex = rng.nextInt(hunts.size());
        }
        // STEP 1.2 : PICK FROM TARGET LIST
        else {
            randIndex = rng.nextInt(targets.size());
        }
        randHuntSqaure = hunts.get(randIndex);

        // STEP 2 : DECODE FROM Integer TO Battlefield Coordinates
        target = decode(randHuntSqaure);

        // STEP 3 : REMOVE THE HUNTING COORDINATES FROM hunts AND targets
        hunts.remove(randHuntSqaure);
        targets.remove(randHuntSqaure);
        return target;
    }

    /**
     * targetShip(int, bool) ------------------------------------------------------------
     * Determines the next firing position of h
     * @param isHit - if the previous salvo was a hit
     * @param previousShot - coordinates of the previous salvo
     * @return - the target of current salvo
     */
    private String targetShip(String previousShot, boolean isHit) {
        String target;
        int startingTarget = encode(previousShot);

        // STEP 1 : CALCULATE ALL THE POSITIONS AROUND THE TARGET SHOT
        int north = startingTarget - 10;
        int south = startingTarget + 10;
        int east = startingTarget + 1;
        int west = startingTarget - 1;

        // STEP 2 : DECIDE WHICH COORDINATES TO FIRE AT ARE VALID
        if (targets.contains(north) && targetsFired.contains(north)) {
            targetsFired.push(north);
        } else if (targets.contains(south) && targetsFired.contains(south)) {
            targetsFired.push(south);
        } else if (targets.contains(east) && targetsFired.contains(east)) {
            targetsFired.push(east);
        } else if (targets.contains(west) && targetsFired.contains(west)) {
            targetsFired.push(west);
        }

        // STEP 3.1 : RESUME HUNT MODE IF NO FIRING COORDINATES ARE VALID
        if (targetsFired.isEmpty()) {
            return huntSquares();
        }

        // STEP 3.2 : CHOOSE THE TOPMOST FIRING COORDINATE
        int coordinateToFireAt = targetsFired.pop();
        if (hunts.contains(coordinateToFireAt)) {
            hunts.remove(hunts.get(coordinateToFireAt));
        }
        target = decode(coordinateToFireAt);
        return target;
    }
}//end of class
