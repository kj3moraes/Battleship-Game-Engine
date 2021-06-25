package Engines;
import Services.Ship;
import java.util.Scanner;

public class Human extends Player{

    @Override
    public void placeShip(Ship s) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the coordinates of the " + s.getShipName() +
                " (" + s.getShipLength() + " cells): ");
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
}//end of class
