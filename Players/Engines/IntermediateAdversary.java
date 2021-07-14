package Players.Engines;

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
        return null;
    }

    /**
     * createTargets(List<Integer>, List<Integer>) --------------------------------------
     * Produces a target list and a hunting list.
     * @param targets - parameter to create a list
     * @param hunts - parameter to
     */
    private void createTargets(List<Integer> targets, List<Integer> hunts) {

    }

    private String hunt() {
        String target = "";
        final String alphabet = "ABCDEFGHIJ";
        int temp;
        int row=0;
        int col=0;
        int index = 0;
        list.empty(); //empty Target Mode stack because we are now in Hunt Mode

        //check to make sure hunts still has valid targets
        if(!hunts.isEmpty()){
            index = rng.nextInt(hunts.size()); //pick random target from hunts
            temp = hunts.get(index); //assign to temp

            //these following two lines of code are for encoding integers to battleship coordinates ("A1", "B2")

            row = (int) Math.floor(((float)temp)/10); //get 10^1 digit
            col = temp%10; //get 10^0 digit
            target = Character.toString(alphabet.charAt(col)) + Integer.toString(row+1); //coloumns are letters, rows are integers which are not 0 indexed

            //System.out.println("Target = " + target);

            //Remove target from both lists because it is no longe valid
            hunts.remove(temp);
            targets.remove(temp);
        }
        //if hunts is empty, pick a target from targets instead which holds remaining valid targets
        else {
            //same process as above
            index = rng.nextInt(targets.size());
            temp = targets.get(index);
            targets.remove((Object) temp);
            row = (int) Math.floor(((float)temp)/10);
            col = temp%10;
            target = Character.toString(alphabet.charAt(col)) + Integer.toString(row+1);
        }
        return target;
    }

    //returns true if Target Mode stack is empty
    public boolean isEmpty(){
        return list.isEmpty();
    }

}//end of class
