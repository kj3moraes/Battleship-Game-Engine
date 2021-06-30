package Engines;
import Services.Ship;
import java.util.Scanner;

public class Human extends Player {

    public Human() {
        super();
    }

    public Human(String name) {
        super(name);
    }

    @Override
    public void placeShip(Ship s) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the coordinates for " + s.getShipName() + " (" +
                s.getShipLength() + " cells): ");
        while (true) {
            String firstCoordinate = sc.next().toUpperCase();
            String secondCoordinate = sc.next().toUpperCase();

            char rowOfFirst = firstCoordinate.charAt(0);
            char rowOfSecond = secondCoordinate.charAt(0);
            int columnOfFirst = Integer.parseInt(firstCoordinate.substring(1));
            int columnOfSecond = Integer.parseInt(secondCoordinate.substring(1));

            if (!arena.isCorrectCoordinates(rowOfFirst, rowOfSecond, columnOfFirst, columnOfSecond, s))
                continue;

            // NORMALIZE THE ROW AND COLUMN COORDINATES
            int temp = Math.max(columnOfFirst, columnOfSecond);
            columnOfFirst = Math.min(columnOfFirst, columnOfSecond);
            columnOfSecond = temp;

            temp = rowOfFirst > rowOfSecond ? rowOfFirst : rowOfSecond;
            rowOfFirst = rowOfFirst < rowOfSecond ? rowOfFirst : rowOfSecond;
            rowOfSecond = (char) temp;

            // CHECK FOR CROSSING OR TOUCHING OTHER SHIPS
            boolean cross = arena.isCrossing(rowOfFirst, rowOfSecond, columnOfFirst, columnOfSecond);
            boolean touch = arena.isTouching(rowOfFirst, rowOfSecond, columnOfFirst, columnOfSecond, false);
            if (cross || touch) continue;

            for (int i = rowOfFirst - 65; i <= rowOfSecond - 65; i++) {
                for (int j = columnOfFirst - 1; j < columnOfSecond; j++) {
                    arena.placeResultOfSalvo(i,j,'O');
                }
            }
            break;
        }
    }

    @Override
    public void fireASalvo() {

    }

    /*
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
     */
}//end of class
