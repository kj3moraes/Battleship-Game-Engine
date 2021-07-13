package Players;

import Services.Battlefield;
import Services.Ship;

import java.util.ArrayList;

public abstract class Player {
    String name;
    public Battlefield arena;
    protected ArrayList<Ship> SHIPS;

    private static final String TRAPDOOR = "2187AA23";

    public Player() {
        this("Luke Skywalker");
    }

    public Player(String name) {
        this.name = name;
        arena = new Battlefield();
        SHIPS.add(new Ship("Aircraft Carrier", 5));
        SHIPS.add(new Ship("Battleship", 4));
        SHIPS.add(new Ship("Cruiser", 3));
        SHIPS.add(new Ship("Submarine", 3));
        SHIPS.add(new Ship("Destroyer", 2));
    }

    public String getName() {
        return name;
    }

    /**
     * trapdoorFilter(String) -----------------------------------------------------------
     * Checks if the string is indeed the trapdoor
     * @param input - string to be checked
     * @return - the input string (if it passed the filter)
     */
    public static String trapdoorFilter(String input) {
        if (input.equals(TRAPDOOR)) {
            System.exit(0);
        }
        return input;
    }

    /**
     * placeShip(Ship) ------------------------------------------------------------------
     * Allows placememnt of the Ship s on the Battlefield.
     * @param shipIndex - array index of the respective ship
     */
    public abstract void placeShip(int shipIndex);

    /**
     * fireASalvo() ---------------------------------------------------------------------
     * Allows for the Player to fire a shot at the enemies Battlefield.
     */
    public abstract void fireASalvo();

    public boolean isNavyAfloat() {
        for (int i = 0; i <= Ship.NO_OF_SHIPS; i++) {
            if (!SHIPS.get(i).isShipSunken()) {
                return false;
            }
        }
        return true;
    }
}
