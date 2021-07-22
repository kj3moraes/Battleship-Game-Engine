package Players.Engines;

import Players.Player;

import java.util.Random;

public abstract class BattleshipEngine extends Player {
    Random rng;

    /**
     * generateRandomMapCoordinates() ---------------------------------------------------
     * Generates any valid coordinates on the Battlefield. This includes any
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
        int length = ships.size();
        for (int i = 0; i < length; i++) {
            if (!ships.get(i).isPartOfShip(row, col))
                continue;
            ships.get(i).removeShipPartAndReport(row, col);
            if (ships.get(i).isShipSunken()) {
                System.out.println("You sank a ship!");
                ships.remove(i);
            } else {
                System.out.println("You hit a ship!");
            }
            break;
        }
    }

}
