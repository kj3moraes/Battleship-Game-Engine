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

    public abstract void placeShip(Ship s);

    public abstract void fireASalvo();
}
