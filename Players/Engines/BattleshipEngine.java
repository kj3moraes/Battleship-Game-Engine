package Players.Engines;

import Players.Player;
import Services.Ship;

import java.util.Random;

public abstract class BattleshipEngine extends Player {
    Random rng;

    /**
     * generateRandomMapCoordinates() ---------------------------------------------------
     * generates any valid coordinates on the Battlefield. This includes any
     * coordinate with 'A' <= row <= 'J' and 1 <= column <= 10
     * @return - the random map coordinate as a String
     */
    protected String generateRandomMapCoordinates() {
        rng = new Random((int)(1 * 10000 * Math.random()));
        char row = (char) (rng.nextInt(10) + 'A');
        int col = 1 + rng.nextInt(10);
        return row + "" + col;
    }

    @Override
    public void manageShipHit(char row, int col) {
        arena.placePiece(row, col, arena.HIT);
        for (int i = 0; i < Ship.NO_OF_SHIPS; i++) {
            SHIPS.get(i).removeShipPart(row, col);
            if (SHIPS.get(i).isShipSunken()) {
                System.out.println("You sank a ship!");
                SHIPS.remove(i);
            } else {
                System.out.println("You hit a ship!");
            }
        }
    }

}
