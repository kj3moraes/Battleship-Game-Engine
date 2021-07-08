
import Engines.*;
import Services.Ship;
import java.util.Scanner;
import static java.util.stream.IntStream.range;

public class Main {
    public static void main(String[] args) {
        Scanner txt = new Scanner(System.in);
        final int NO_OF_SHIPS = 5;
        final Ship[] SHIPS = {Ship.ARC, Ship.BTL, Ship.CRU, Ship.SUB, Ship.DES};

        // DEFINING THE PLAYERS
        Human player1;
        BattleshipEngine player2 = null;

        // MENU 1 [ACTION MENU]
        System.out.println("What would you like to do?\n\t[s]tart\n\te[x]it");
        char action = txt.next().toLowerCase().charAt(0);
        switch (action) {
            case 'x' :
                System.exit(0);
                break;

            case 's':
                break;

            default:
                main(new String[]{});
        }

        // BEGINNING WITH HUMAN
        System.out.println("Enter your name : ");
        String humanName = txt.nextLine();
        player1 = new Human(humanName);

        // MENU 2 [ENGINE SELECTION MENU]
        char engine;
        do {
            System.out.println("What engine would you like to play against?");
            System.out.println("\t[N]aive-Solver (Easy)\n\t[I]ntermediate-Solver (Medium) \n\t[B]oogeyman (Crazy)");
            engine = txt.next().toUpperCase().trim().charAt(0);
            switch (action) {
                case 'N' :
                    player2 = new Naive_Solver();
                    break;

                case 'I':
                    player2 = new Intermediate_Adversary();
                    break;

                case 'B' :
                    break;

                default:
                    System.out.println("Type in one of the letters : N,I,B");
                    continue;
            }
            break;
        } while (engine != 'N' || engine != 'I' || engine != 'B');


        // PLAYER 1 NAVY SETUP
        System.out.println("\nPlayer 1, place your ships on the game field");
        for (int i = 0; i < NO_OF_SHIPS; i++) {
            player1.placeShip(SHIPS[i]);
            player1.arena.printBattlefield(false);
        }

        promptEnterKey();

        // PLAYER 2 NAVY SETUP
        System.out.println("\nKindly wait while the machine places its ships");
        for (int i = 0; i < NO_OF_SHIPS; i++) {
            player2.placeShip(SHIPS[i]);
        }

        // WARTIME
        System.out.println("The game starts!");
        boolean didP1Win = false;
        while (!didP1Win) {

        }

        // GAME ENDS
        System.out.println("You sank the last ship. You won. Congratulations!");
        if (didP1Win)
            System.out.println("Player 1 won the game!");
        else
            System.out.println("Player 2 won the game!");
    }


    private static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        new Scanner(System.in).nextLine();
        clearScreen();
    }

    private static void clearScreen() {
        range(0, 25).forEach(i -> System.out.println());
    }

    protected static void printDivider() {
        for (int i = 1; i <= 20; i++) {
            System.out.print("-");
        }
    }
}
