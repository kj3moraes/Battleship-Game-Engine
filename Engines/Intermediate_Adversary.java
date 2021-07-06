package Engines;

import Services.Battlefield;
import Services.Ship;

public class Intermediate_Adversary extends Naive_Solver {
    Battlefield enemyArena;

    public Intermediate_Adversary() {
        enemyArena = new Battlefield();
    }

    @Override
    public String fireASalvo() {
        return "A5";
    }

}//end of class
