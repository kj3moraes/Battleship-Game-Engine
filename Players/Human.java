package Players;
import Services.Battlefield;

import java.util.Scanner;

public class Human extends Player {

    public Human() {
        super();
    }

    public Human(String name) {
        super(name);
    }

    @Override
    public void placeShip(int shipIndex) throws NumberFormatException {
        Scanner sc;
        System.out.println("\nEnter the coordinates for " + ships.get(shipIndex).getShipName() + " (" +
                ships.get(shipIndex).getShipLength() + " cells): ");
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

            int placementRes = arena.isCorrectCoordinates(rowOfFirst, rowOfSecond, columnOfFirst, columnOfSecond, ships.get(shipIndex));
            if (placementRes != Battlefield.VALID_COORD) {
                System.out.print("\nError! ");
                Battlefield.analyzeErrorInPlacement(placementRes);
                continue;
            }

            for (char i = rowOfFirst; i <= rowOfSecond; i++) {
                for (int j = columnOfFirst; j <= columnOfSecond; j++) {
                    arena.placePiece(i, j, arena.SHIP);
                }
            }
            ships.get(shipIndex).storeShipPlacement(rowOfFirst, rowOfSecond, columnOfFirst, columnOfSecond);
            break;
        }
    }

    @Override
    public String fireASalvo() {
        Scanner num = new Scanner(System.in);
        String firingPos;
        while (true) {
            System.out.print("\n" + name + ", enter the firing position : ");
            firingPos = trapdoorFilter(num.next().toUpperCase().trim());

            char rowCoord = firingPos.charAt(0);
            int columnCoord = Integer.parseInt(firingPos.substring(1));
            int placementRes = arena.isCorrectCoordinates(rowCoord, 'A', columnCoord, 9, null);
            if (placementRes != Battlefield.VALID_COORD) {
                System.out.println("Error! ");
                Battlefield.analyzeErrorInPlacement(placementRes);
                continue;
            }
            break;
        }
        return firingPos;
    }

    @Override
    public void manageShipHit(char row, int col) {
        arena.placePiece(row, col, arena.HIT);
        int length = ships.size();
        for (int i = 0; i < length; i++) {
            if (!ships.get(i).isPartOfShip(row, col))
                continue;
            ships.get(i).removeShipPartAndReport(row, col);
            if (ships.get(i).isShipSunken()) {
                System.out.println("The engine has sunken your " + ships.get(i).getShipName() +" at " + row + "" + col
                +". Make them pay!");
                ships.remove(i);
            } else {
                System.out.println("The engine has hit your " + ships.get(i).getShipName() +" at " + row + "" + col
                + ". Fire back!");
            }
            break;
        }
    }
}//end of class
