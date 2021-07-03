package Engines;

import Services.Ship;
public class Naive_Solver extends BattleshipEngine {

    @Override
    public void placeShip(Ship s) {
        String startingPosition = generateRandomMapCoordinates();
        char rowStart = startingPosition.charAt(0);
        int colStart = startingPosition.charAt(0);

        System.out.println("Starting Position: \t " + startingPosition);
        System.out.println("Ship : " + s.getShipName() + " \t " + s.getShipLength());

        // CAN IT FIT HORIZONTALLY ?
        if (colStart - s.getShipLength() + 1 > 0) {
            if (arena.isCorrectCoordinates(rowStart, rowStart, colStart, colStart - s.getShipLength() + 1, s)) {
                for (int i = colStart - s.getShipLength() + 1; i <= colStart; i++) {
                    arena.placePiece(rowStart, i, arena.SHIP);
                }
            }
        } else if (colStart + s.getShipLength() - 1 <= 10) {
            if (arena.isCorrectCoordinates(rowStart, rowStart, colStart, colStart - s.getShipLength() + 1, s)) {
                for (int i = colStart; i <= colStart + s.getShipLength() - 1; i++) {
                    arena.placePiece(rowStart, i, arena.SHIP);
                }
            }
        }

        // CAN IT FIT VERTICALLY ?
        if (rowStart - s.getShipLength() > 'A') {
            if (arena.isCorrectCoordinates(rowStart, rowStart, colStart, colStart - s.getShipLength() + 1, s)) {
                for (int i = colStart - s.getShipLength() + 1; i <= colStart; i++) {
                    arena.placePiece(rowStart, i, arena.SHIP);
                }
            }
        } else if (rowStart + s.getShipLength() < 'J') {
            if (arena.isCorrectCoordinates(rowStart, rowStart, colStart, colStart - s.getShipLength() + 1, s)) {
                for (int i = colStart - s.getShipLength() + 1; i <= colStart; i++) {
                    arena.placePiece(rowStart, i, arena.SHIP);
                }
            }

        }
    }

    @Override
    public void fireASalvo() {
        String fireCoordinates = generateRandomMapCoordinates();
        char salvoRow = fireCoordinates.charAt(0);
        int salvoColumn = Integer.parseInt(fireCoordinates.substring(1));



    }

    public static void main(String[] args) {
        Naive_Solver test = new Naive_Solver();
        for (int i = 0; i < 100; i++) {
            System.out.println(test.generateRandomMapCoordinates());
        }
    }
}//end of class
