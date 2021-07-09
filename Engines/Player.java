package Engines;

import Services.Battlefield;
import Services.Ship;

public abstract class Player {
    String name;
    public Battlefield arena;
    private static final String TRAPDOOR = "2187AA23";

    public Player() {
        name = "Luke Skywalker";
        arena = new Battlefield();
    }

    public Player(String name) {
        this.name = name;
        arena = new Battlefield();
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
     * @param s - enum describing the specifications of the ship.
     */
    public abstract void placeShip(Ship s);

    /**
     * fireASalvo() ---------------------------------------------------------------------
     * Allows for the Player to fire a shot at the enemies Battlefield.
     */
    public abstract String fireASalvo();
}
