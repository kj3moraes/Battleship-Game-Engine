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

    /**
     * createTargets(List<Integer>, List<Integer>) --------------------------------------
     * Produces a target list and a hunting list.
     * @param targetList - parameter to create a list
     * @param huntList - parameter to
     */
    private void createTargets(List<Integer> targetList, List<Integer> huntList) {

    }
}//end of class
