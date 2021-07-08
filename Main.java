
import Engines.*;
import Services.Battlefield;
import Services.Ship;
import java.util.Scanner;
import static java.util.stream.IntStream.range;

public class Main {
    public static void main(String[] args) {
        Scanner txt = new Scanner(System.in), setup = new Scanner(System.in);
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
        String humanName = setup.nextLine();
        player1 = new Human(humanName);

        // MENU 2 [ENGINE SELECTION MENU]
        char engine;
        do {
            System.out.println("What engine would you like to play against?");
            System.out.println("\t[N]aive-Solver (Easy)\n\t[I]ntermediate-Solver (Medium) \n\t[B]oogeyman (Crazy)");
            engine = txt.next().toUpperCase().trim().charAt(0);
            System.out.println(engine == 'N');
            switch (engine) {
                case 'N' :
                    player2 = new NaiveSolver();
                    break;

                case 'I':
                    player2 = new IntermediateAdversary();
                    break;

                case 'B' :
                    break;

                default:
                    System.out.println("Type in one of the letters : N,I or B");
                    continue;
            }
            break;
        } while (engine != 'N' || engine != 'I' || engine != 'B');


        // PLAYER 1 NAVY SETUP
        System.out.println("\nPlayer 1, place your ships on the game field\n");
        player1.arena.printBattlefield(false);
        for (int i = 0; i < NO_OF_SHIPS; i++) {
            player1.placeShip(SHIPS[i]);
            player1.arena.printBattlefield(false);
        }

        promptEnterKey();

        // PLAYER 2 NAVY SETUP
        System.out.println("\nKindly wait while the machine places its ships");
        for (int i = 0; i < NO_OF_SHIPS; i++) {
            assert player2 != null;
            player2.placeShip(SHIPS[i]);
        }

        // WARTIME
        System.out.println("The game starts!");
        String shot;
        char shotRow;
        int shotCol;
        
        boolean didP1Win = false;
        while (true) {
            player2.arena.printBattlefield(true);
            printDivider();
            player1.arena.printBattlefield(false);
            shot = player1.fireASalvo();
            shotRow = shot.charAt(0);
            shotCol = Integer.parseInt(shot.substring(1));

            // APPROPRIATE MESSAGE FOR THE SALVO
            if (player2.arena.isHit(shotRow, shotCol) ) {
                player2.arena.placePiece(shotRow, shotCol, player2.arena.HIT);
                if (player2.arena.isSunken(shotRow, shotCol)) {
                    System.out.println("You sank a ship!");
                } else {
                    System.out.println("You hit a ship");
                }
            } else if (player2.arena.isMiss(shotRow, shotCol)) {
                player2.arena.placePiece(shotRow, shotCol, player2.arena.MISS);
                System.out.println("You missed! Try again next turn");
            }

            // DID P1 WIN ?
            if (player2.arena.isNavyAfloat()) {
                didP1Win = true;
                break;
            }
            promptEnterKey();

            // P2 PLAYS
            System.out.println("Please wait while the engine makes its move");
            shot = player2.fireASalvo();
            shotRow = shot.charAt(0);
            shotCol = Integer.parseInt(shot.substring(1));

            // APPROPRIATE MESSAGE FOR THE SALVO
            if (player1.arena.isHit(shotRow, shotCol) ) {
                player1.arena.placePiece(shotRow, shotCol, player1.arena.HIT);
                if (player1.arena.isSunken(shotRow, shotCol)) {
                    System.out.println("The engine sank your ship at " + shot);
                } else {
                    System.out.println("The engine hit your ship at " + shot);
                }
            } else if (player2.arena.isMiss(shotRow, shotCol)) {
                player2.arena.placePiece(shotRow, shotCol, player2.arena.MISS);
                System.out.println("The engine missed.");
            }

            // DID P2 WIN ?
            if (player1.arena.isNavyAfloat()) {
                break;
            }
        }

        // GAME ENDS
        System.out.println("You sank the last ship. You won. Congratulations!");
        if (didP1Win)
            System.out.printf("Congrats %s, you have won this game of Battleship!", humanName);
        else
            System.out.println("The Engine won this game of Battleship!");
    }

    private static void trapdoorCheck(String input) {

    }

    private static void determineShotResult(Battlefield enemy, String firingCoord, boolean isEngine) {
        char shotRow = firingCoord.charAt(0);
        int columnCoord = Integer.parseInt(firingCoord.substring(1));
    }


    private static void promptEnterKey() {
        System.out.println("Press Enter and wait for the engines move");
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
