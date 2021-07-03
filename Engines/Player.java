package Engines;

import Services.Battlefield;
import Services.Ship;

public abstract class Player {
    String name;
    public Battlefield arena;

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
     * placeShip(Ship) ------------------------------------------------------------------
     * Allows placememnt of the Ship s on the Battlefield.
     * @param s - enum describing the specifications of the ship.
     */
    public abstract void placeShip(Ship s);

    /**
     * fireASalvo() ---------------------------------------------------------------------
     * Allows for the Player to fire a shot at the enemies Battlefield.
     */
    public abstract void fireASalvo();
}
