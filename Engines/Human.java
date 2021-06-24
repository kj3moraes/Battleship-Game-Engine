package Engines;

import Structures.Battlefield;

import java.util.Scanner;

public class Human extends Player{
    public Human() {
        super();
    }

    public Human(String name) {
        super(name);
    }

    @Override
    public void fireASalvo() {

    }

    @Override
    public boolean isMyNavyAfloat() {
        return false;
    }
}//end of class
