package Players;
import java.util.Scanner;

public class Human extends Player {

    public Human() {
        super();
    }

    public Human(String name) {
        super(name);
    }

    @Override
    public void placeShip(int shipIndex) {
        Scanner sc;
        System.out.println("\nEnter the coordinates for " + SHIPS.get(shipIndex).getShipName() + " (" +
                SHIPS.get(shipIndex).getShipLength() + " cells): ");
        while (true) {
            sc = new Scanner(System.in);
            String firstCoordinate = trapdoorFilter(sc.next().toUpperCase());
            String secondCoordinate = trapdoorFilter(sc.next().toUpperCase());

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

            if (!arena.isCorrectCoordinates(rowOfFirst, rowOfSecond, columnOfFirst, columnOfSecond, SHIPS.get(shipIndex))) {
                System.out.print("\nError! Invalid Coordinates.");
                System.out.println("Try again :\n\n");
                continue;
            }

            for (char i = rowOfFirst; i <= rowOfSecond; i++) {
                for (int j = columnOfFirst; j <= columnOfSecond; j++) {
                    arena.placePiece(i, j, arena.SHIP);
                }
            }
            SHIPS.get(shipIndex).storeShipPlacement(rowOfFirst, rowOfFirst, columnOfFirst, columnOfSecond);
            break;
        }
    }

    @Override
    public String fireASalvo() {
        Scanner num = new Scanner(System.in);
        String firingPos;
        while (true) {
            System.out.print(name + ", enter the firing position : ");
            firingPos = trapdoorFilter(num.next().toUpperCase().trim());

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

    @Override
    public void manageShipHit(char row, int col) {

    }
}//end of class
