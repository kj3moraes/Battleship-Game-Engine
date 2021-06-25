package Structures;

public enum Ship {
    ARC("Aircraft Carrier", 5),
    BTL("Battleship", 4),
    CRU("Cruiser", 3),
    SUB("Submarine", 3),
    DES("Destroyer", 2);

    final String shipName;
    final int shipLength;

    Ship(String name, int length) { shipName = name; shipLength = length;}

    public String getShipName() {
        return shipName;
    }

    public int getShipLength() {
        return shipLength;
    }
}
/**
 * package Structures;
 * import java.util.Scanner;
 * public class Ship {
 *     public String name;
 *     public int length;
 *
 *     public Ship() {
 *         name = null;
 *         length = -1;
 *     }
 *
 *     public Ship(String name, int length) {
 *         this.name = name;
 *         this.length = length;
 *     }
 *
 *     public void placeShip(Battlefield bf) {
 *         Scanner sc = new Scanner(System.in);
 *         System.out.println("\nEnter the coordinates of the " + name + " (" + length + " cells): ");
 *         while (true) {
 *             String firstCoordinate = sc.next().toUpperCase();
 *             String secondCoordinate = sc.next().toUpperCase();
 *
 *             char rowOfFirst = firstCoordinate.charAt(0);
 *             char rowOfSecond = secondCoordinate.charAt(0);
 *             int columnOfFirst = Integer.parseInt(firstCoordinate.substring(1));
 *             int columnOfSecond = Integer.parseInt(secondCoordinate.substring(1));
 *
 *             if (!bf.isCorrectCoordinates(rowOfFirst, rowOfSecond, columnOfFirst, columnOfSecond, this))
 *                 continue;
 *
 *             // NORMALIZE THE ROW AND COLUMN COORDINATES
 *             int temp = Math.max(columnOfFirst, columnOfSecond);
 *             columnOfFirst = Math.min(columnOfFirst, columnOfSecond);
 *             columnOfSecond = temp;
 *
 *             temp = rowOfFirst > rowOfSecond ? rowOfFirst : rowOfSecond;
 *             rowOfFirst = rowOfFirst < rowOfSecond ? rowOfFirst : rowOfSecond;
 *             rowOfSecond = (char) temp;
 *
 *             // CHECK FOR CROSSING OR TOUCHING OTHER SHIPS
 *             boolean cross = bf.isCrossing(rowOfFirst, rowOfSecond, columnOfFirst, columnOfSecond);
 *             boolean touch = bf.isTouching(rowOfFirst, rowOfSecond, columnOfFirst, columnOfSecond, false);
 *             if (cross || touch) continue;
 *
 *             for (int i = rowOfFirst - 65; i <= rowOfSecond - 65; i++) {
 *                 for (int j = columnOfFirst - 1; j < columnOfSecond; j++) {
 *                     bf.placeResultOfSalvo(i,j,'O');
 *                 }
 *             }
 *             break;
 *         }
 *     }
 * }
 */