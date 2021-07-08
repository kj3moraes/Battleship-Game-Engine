package Engines;

import Services.Battlefield;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class IntermediateAdversary extends NaiveSolver {
    Battlefield enemyArena;
    private List<Integer> targets;
    private List<Integer> hunts;
    private Stack<Integer> list;

    public IntermediateAdversary() {
        rng = new Random();
        enemyArena = new Battlefield();
    }

    @Override
    public String fireASalvo() {
        return "A5";
    }

}//end of class
