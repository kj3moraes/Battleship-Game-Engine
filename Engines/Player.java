package Engines;

import Services.Battlefield;
import Services.Ship;

public abstract class Player {
    String name;
    Battlefield arena;

    public Player() {
        name = "Luke Skywalker";
        arena = new Battlefield();
    }

    public Player(String name) {
        this.name = name;
        arena = new Battlefield();
    }

    public abstract void placeShip(Ship s);

    public abstract void fireASalvo();
}
