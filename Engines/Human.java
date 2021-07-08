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

            // NORMALIZE THE ROW AND COLUMN COORDINATES
            int temp = Math.max(columnOfFirst, columnOfSecond);
            columnOfFirst = Math.min(columnOfFirst, columnOfSecond);
            columnOfSecond = temp;

            temp = Math.max(rowOfFirst, rowOfSecond);
            rowOfFirst = (char) Math.min(rowOfFirst, rowOfSecond);
            rowOfSecond = (char) temp;

            if (!arena.isCorrectCoordinates(rowOfFirst, rowOfSecond, columnOfFirst, columnOfSecond, s))
                continue;

            for (char i = rowOfFirst; i <= rowOfSecond; i++) {
                for (int j = columnOfFirst; j <= columnOfSecond; j++) {
                    arena.placePiece(rowOfFirst, columnOfFirst, arena.SHIP);
                }
            }
            break;
        }
    }

    @Override
    public String fireASalvo() {
        Scanner num = new Scanner(System.in);
        String firingPos = "A1";
        while (true) {
            System.out.print(name + ", enter the firing position : ");
            firingPos = num.next().toUpperCase().trim();

            char rowCoord = firingPos.charAt(0);
            int columnCoord = Integer.parseInt(firingPos.substring(1));
            if (!arena.isCorrectCoordinates(rowCoord, 'A', columnCoord, 9, null)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }
            break;
        }

        return firingPos;
    }


/*if (arena.isHit(rowCoord, columnCoord) ) {
                arena.placePiece(rowCoord, columnCoord, arena.HIT);
                if (arena.isSunken(rowCoord, columnCoord)) {
                    System.out.println("You sank a ship!");
                } else {
                    System.out.println("You hit a ship");
                }
            } else if (arena.isMiss(rowCoord, columnCoord)) {
                arena.placePiece(rowCoord, columnCoord, arena.MISS);
                System.out.println("You missed! Try again next turn");
            }
            break;*/
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
