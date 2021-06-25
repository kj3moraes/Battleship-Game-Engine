
import Services.Battlefield;

import java.util.Scanner;
import static java.util.stream.IntStream.range;

public class Battleship {
    public static void main(String[] args) {

/*        Battlefield p1 = new Battlefield();
        Battlefield p2 = new Battlefield();


        // PLAYER 1 NAVY SETUP
        System.out.println("\nPlayer 1, place your ships on the game field");
        p1.printBattlefield(false);
        for (int i = 0; i < 5; i++) {
            ships[i].placeShip(p1);
            p1.printBattlefield(false);
        }

        promptEnterKey();

        // PLAYER 2 NAVY SETUP
        System.out.println("\nPlayer 2, place your ships on the game field");
        p2.printBattlefield(false);
        for (int i = 0; i < 5; i++) {
            ships[i].placeShip(p2);
            p2.printBattlefield(false);
        }*/

       /* promptEnterKey();

        //WARTIME
        System.out.println("The game starts!");
        boolean didP1Win = false;
        while(true) {
            // PLAYER 1's turn
            p2.printBattlefield(true);
            printDivider();
            p1.printBattlefield(false);
            System.out.println("Player 1, it's your turn:");
            firingASalvo(p2);

            if (!p2.isNavyAfloat()) {
                didP1Win = true;
                break;
            }

            // PLAYER 2's TURN
            promptEnterKey();
            p1.printBattlefield(true);
            printDivider();
            p2.printBattlefield(false);
            System.out.println("Player 2, it's your turn:");
            firingASalvo(p1);

            if (!p1.isNavyAfloat())
                break;
            else
                promptEnterKey();
        }*/
/*        System.out.println("You sank the last ship. You won. Congratulations!");
        if (didP1Win)
            System.out.println("Player 1 won the game!");
        else
            System.out.println("Player 2 won the game!");*/
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
    
    protected static void firingASalvo(Battlefield bf) {
        Scanner num = new Scanner(System.in);
        while (true) {
            String firingPos = num.next().toUpperCase().trim();

            char rowCoordinate = firingPos.charAt(0);
            int columnCoordinate = Integer.parseInt(firingPos.substring(1));

            if (!bf.isCorrectCoordinates(rowCoordinate, 'A', columnCoordinate, 9, null)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }

            char status = bf.salvoStatus(rowCoordinate - 65, columnCoordinate - 1);
            if (status == bf.SHIP || status == bf.HIT) {
                bf.placeResultOfSalvo(rowCoordinate - 65, columnCoordinate - 1, bf.HIT);
                bf.printBattlefield(true);
                if (isSunken(rowCoordinate, columnCoordinate, bf))
                    System.out.println("You sank a ship!");
                else
                    System.out.println("You hit a ship! ");
            } else if (status == bf.WATER || status == bf.MISS) {
                bf.placeResultOfSalvo(rowCoordinate - 65, columnCoordinate - 1, bf.MISS);
                bf.printBattlefield(true);
                System.out.println("You missed!");
            }
            break;
        }
    }

    public static boolean isSunken(char rowCo, int columnCo, Battlefield bf) {
        return !bf.isTouching(rowCo, rowCo, columnCo, columnCo, true);
    }
}
