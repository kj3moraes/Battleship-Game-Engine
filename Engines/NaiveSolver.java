package Engines;

import Services.Ship;
public class NaiveSolver extends BattleshipEngine {

    @Override
    public void placeShip(Ship s) {
        String startingPosition = generateRandomMapCoordinates();
        char rowStart = startingPosition.charAt(0);
        int colStart = Integer.parseInt(startingPosition.substring(1));

        // DECIDE TO GO EITHER HORIZONTAL OR VERTICAL
        int horizontalOrVertical = (int) Math.round(Math.random());

        // HORIZONTAL OR VERTICAL PLACEMENT VIABILITY

        if (horizontalOrVertical == 0) {
            // CAN IT FIT HORIZONTALLY ?
            if (colStart - s.getShipLength() + 1 >= 1) {
                if (arena.isCorrectCoordinates(rowStart, rowStart, colStart - s.getShipLength() + 1, colStart, s)) {
                    for (int i = colStart - s.getShipLength() + 1; i <= colStart; i++) {
                        arena.placePiece(rowStart, i, arena.SHIP);
                    }
                    return;
                }
            } else if (colStart + s.getShipLength() - 1 <= 10) {
                if (arena.isCorrectCoordinates(rowStart, rowStart, colStart, colStart + s.getShipLength() - 1, s)) {
                    for (int i = colStart; i <= colStart + s.getShipLength() - 1; i++) {
                        arena.placePiece(rowStart, i, arena.SHIP);
                    }
                    return;
                }
            }
            placeShip(s);
        } else {
            // CAN IT FIT VERTICALLY ?
            if (rowStart - s.getShipLength() + 1 >= 'A') {
                if (arena.isCorrectCoordinates((char) (rowStart - s.getShipLength() + 1), rowStart, colStart, colStart, s)) {
                    for (char i = (char) (rowStart - s.getShipLength() + 1); i <= rowStart; i++) {
                        arena.placePiece(i, colStart, arena.SHIP);
                    }
                    return;
                }
            } else if (rowStart + s.getShipLength() - 1 <= 'J') {
                if (arena.isCorrectCoordinates(rowStart, (char) (rowStart + s.getShipLength() - 1), colStart, colStart, s)) {
                    for (char i = rowStart; i <= rowStart + s.getShipLength() - 1; i++) {
                        arena.placePiece(i, colStart, arena.SHIP);
                    }
                    return;
                }
            }
            placeShip(s);
        }
    }

    @Override
    public String fireASalvo() {
        return generateRandomMapCoordinates();
    }
}//end of class
