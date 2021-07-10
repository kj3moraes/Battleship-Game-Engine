package Engines;

import java.util.Random;

public abstract class BattleshipEngine extends Player {
    String[] successfulAtk;
    Random rng;

    /**
     * generateRandomMapCoordinates() ---------------------------------------------------
     * generates any valid coordinates on the Battlefield. This includes any
     * coordinate with 'A' <= row <= 'J' and 1 <= column <= 10
     * @return - the random map coordiante as a String
     */
    protected String generateRandomMapCoordinates() {
        rng = new Random((int)(1 * 10000 * Math.random()));
        char row = (char) (rng.nextInt(10) + 'A');
        int col = 1 + rng.nextInt(10);
        return row + "" + col;
    }

    protected boolean isSuccessfulAttack(String coordinate) {
        for (String coord : successfulAtk) {
            if (coord.equals(coordinate)) {
                return true;
            }
        }
        return false;
    }

}
