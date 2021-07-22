package Players;

import Services.Battlefield;
import Services.Ship;

import java.util.ArrayList;

public abstract class Player {
    String name;
    public Battlefield arena;
    protected ArrayList<Ship> ships;

    private static final String TRAPDOOR = "2187AA23";

    public Player() {
        this("Luke Skywalker");
    }

    public Player(String name) {
        this.name = name;
        arena = new Battlefield();
        ships = new ArrayList<>();
        ships.add(new Ship("Aircraft Carrier", 5));
        ships.add(new Ship("Battleship", 4));
        ships.add(new Ship("Cruiser", 3));
        ships.add(new Ship("Submarine", 3));
        ships.add(new Ship("Destroyer", 2));
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
    public abstract String fireASalvo();

    /**
     * isNavySunken() -------------------------------------------------------------------
     * Determines if all my ships have been hit. Returns true if it has been.
     * @return - whether or not my navy is all shot down
     */
    public boolean isNavySunken() {
        return ships.isEmpty();
    }

    /**
     * manageShipHit(String) ------------------------------------------------------------
     * Prints appropriate message and handles various inner workings when the opponent
     * hits our ships. This function prints out a message notifying the opponent of
     * the result of their hit (whether or not it sunk one of our ships)
     *
     * REQURIES : The coordinates must be a hit.
     * @param row - opponents salvo row coordinate
     * @param col - opponents salvo column coordinate.
     */
    public abstract void manageShipHit(char row, int col);
}
