
import Players.*;
import Players.Engines.*;
import Services.Ship;

import java.util.Scanner;
import static java.util.stream.IntStream.range;

public class Main {
    public static void main(String[] args) {
        Scanner txt = new Scanner(System.in), setup = new Scanner(System.in);

        // DEFINING THE PLAYERS
        Human player1;
        BattleshipEngine player2 = null;

        // MENU 1 [ACTION MENU]
        System.out.println("\nWhat would you like to do?\n\t[s]tart\n\te[x]it");
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
        System.out.print("\nEnter your name : ");
        String humanName = setup.nextLine();
        player1 = new Human(humanName);

        // MENU 2 [ENGINE SELECTION MENU]
        char engine;
        do {
            System.out.println("\nWhat engine would you like to play against?");
            System.out.println("\t[N]aive-Solver (Easy)\n\t[I]ntermediate-Solver (Medium) \n\t[B]oogeyman (Crazy)");
            engine = txt.next().toUpperCase().trim().charAt(0);
            switch (engine) {
                case 'N' :
                    player2 = new NaiveSolver();
                    break;

                case 'I':
                    player2 = new IntermediateAdversary();
                    break;

                case 'B' :
                    player2 = new Boogeyman();
                    break;

                default:
                    System.out.println("Type in one of the letters : N,I or B");
                    continue;
            }
            break;
        } while (engine != 'N' || engine != 'I' || engine != 'B');

        // ============================= GAME BEGINS ====================================

        // PLAYER 1 NAVY SETUP
        System.out.println("\nPlayer 1, place your ships on the game field\n");
        player1.arena.printBattlefield(false);
        for (int i = 0; i < Ship.NO_OF_SHIPS; i++) {
            player1.placeShip(i);
            player1.arena.printBattlefield(false);
        }

        promptEnterKey();

        // PLAYER 2 NAVY SETUP
        System.out.println("\nKindly wait while the machine places its ships");
        for (int i = 0; i < Ship.NO_OF_SHIPS; i++) {
            assert player2 != null;
            player2.placeShip(i);
        }
        System.out.println("\nThe machine has completed placing its ships!\n");
        player2.arena.printBattlefield(false);

        promptEnterKey();

        // WARTIME
        System.out.println("The game starts!");
        boolean didP1Win = false;
        String shot;
        char shotRow;
        int shotCol;

        while (true) {
            player2.arena.printBattlefield(true);
            printDivider();
            player1.arena.printBattlefield(false);

            shot = player1.fireASalvo();
            shotRow = shot.charAt(0);
            shotCol = Integer.parseInt(shot.substring(1));

            // APPROPRIATE MESSAGE FOR THE P1's SALVO
            if (player2.arena.isHit(shotRow, shotCol)) {
                player2.manageShipHit(shotRow, shotCol);
            } else if (player2.arena.isMiss(shotRow, shotCol)) {
                player2.arena.placePiece(shotRow, shotCol, player2.arena.MISS);
                System.out.println("You missed! Try again next turn");
            }
            // clearScreen();
            // DID P1 WIN ?
            if (player2.isNavySunken()) {
                didP1Win = true;
                break;
            }

            // P2 PLAYS
            System.out.println("\nPlease wait while the engine makes its move");
            shot = player2.fireASalvo();
            shotRow = shot.charAt(0);
            shotCol = Integer.parseInt(shot.substring(1));

            // APPROPRIATE MESSAGE FOR THE SALVO
            if (player1.arena.isHit(shotRow, shotCol) ) {
                player1.manageShipHit(shotRow, shotCol);
            } else if (player1.arena.isMiss(shotRow, shotCol)) {
                player1.arena.placePiece(shotRow, shotCol, player2.arena.MISS);
                System.out.println("The engine fired at " + shot + " and missed.");
            }

            // DID P2 WIN ?
            if (player1.isNavySunken()) {
                break;
            }
        }

        // GAME ENDS
        System.out.println("You sank the last ship. You won. Congratulations!");
        if (didP1Win)
            System.out.printf("Congrats %s, you have won this game of Battleship!", humanName);
        else
            System.out.printf("The %s Engine won this game of Battleship!", player2.getName());
    }

    private static void promptEnterKey() {
        System.out.println("Press Enter for the next step");
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
