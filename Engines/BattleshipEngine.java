package Engines;

import java.util.Random;

public abstract class BattleshipEngine extends Player {
    String[] successfulAtk;

    protected String generateRandomMapCoordinates() {
        Random rng = new Random();
        char row = (char) (rng.nextInt(10) + 'A');
        int col = rng.nextInt(10);
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
