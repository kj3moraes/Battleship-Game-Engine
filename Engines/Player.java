package Engines;

import Structures.Battlefield;

public abstract class Player {
    String name;
    private Battlefield arena;

    public Player() {
        name = "Luke Skywalker";
        arena = new Battlefield();
    }

    public Player(String name) {
        this.name = name;
        arena = new Battlefield();
    }

    public abstract void fireASalvo();

    public abstract boolean isMyNavyAfloat();
}
