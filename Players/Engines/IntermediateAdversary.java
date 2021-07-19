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

    public IntermediateAdversary() {
        rng = new Random();
        enemyArena = new Battlefield();
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
    private void createTargets(List<Integer> targets, List<Integer> hunts) {

    }

/*    private String hunt() {

        return target;
    }*/

}//end of class
