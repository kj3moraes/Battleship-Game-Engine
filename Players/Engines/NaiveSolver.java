package Players.Engines;

import Services.Battlefield;

public class NaiveSolver extends BattleshipEngine {

    @Override
    public void placeShip(int shipIndex) {
        String startingPosition = generateRandomMapCoordinates();
        char rowStart = startingPosition.charAt(0);
        int colStart = Integer.parseInt(startingPosition.substring(1));
        
        // DECIDE TO GO EITHER HORIZONTAL OR VERTICAL
        int horizontalOrVertical = (int) Math.round(Math.random());
        int NEorSW = (int) Math.round(Math.random());
        int placementRes;

        // HORIZONTAL OR VERTICAL PLACEMENT VIABILITY
        if (horizontalOrVertical == 0) {
            // CAN IT FIT HORIZONTALLY ?
            if (NEorSW == 0 && colStart - ships.get(shipIndex).getShipLength() + 1 >= 1) {

                // DOES IT FIT HORIZONTALLY AND TO THE LEFT ?
                placementRes = arena.isCorrectCoordinates(rowStart, rowStart,
                        colStart - ships.get(shipIndex).getShipLength() + 1, colStart, ships.get(shipIndex));
                if (placementRes == Battlefield.VALID_COORD) {
                    for (int i = colStart - ships.get(shipIndex).getShipLength() + 1; i <= colStart; i++) {
                        arena.placePiece(rowStart, i, arena.SHIP);
                    }
                    
                    ships.get(shipIndex).storeShipPlacement(rowStart, rowStart,
                            colStart - ships.get(shipIndex).getShipLength() + 1, colStart);
                    return;
                }
            } else if (NEorSW == 1 && colStart + ships.get(shipIndex).getShipLength() - 1 <= 10) {

                // DOES IT FIT HORIZONTALLY AND TO THE RIGHT ?
                placementRes = arena.isCorrectCoordinates(rowStart, rowStart, colStart,
                        colStart + ships.get(shipIndex).getShipLength() - 1, ships.get(shipIndex));
                if (placementRes == Battlefield.VALID_COORD) {
                    for (int i = colStart; i <= colStart + ships.get(shipIndex).getShipLength() - 1; i++) {
                        arena.placePiece(rowStart, i, arena.SHIP);
                    }
                    
                    ships.get(shipIndex).storeShipPlacement(rowStart, rowStart,
                            colStart, colStart + ships.get(shipIndex).getShipLength() - 1);
                    return;
                }
            }
            placeShip(shipIndex);
        } else {
            // CAN IT FIT VERTICALLY ?
            if (NEorSW == 0 && rowStart - ships.get(shipIndex).getShipLength() + 1 >= 'A') {

                // DOES IT FIT VERTICALLY AND UP ?
                placementRes = arena.isCorrectCoordinates((char) (rowStart - ships.get(shipIndex).getShipLength() + 1),
                        rowStart, colStart, colStart, ships.get(shipIndex));
                if (placementRes == Battlefield.VALID_COORD) {
                    for (char i = (char) (rowStart - ships.get(shipIndex).getShipLength() + 1); i <= rowStart; i++) {
                        arena.placePiece(i, colStart, arena.SHIP);
                    }
                    
                    ships.get(shipIndex).storeShipPlacement((char) (rowStart - ships.get(shipIndex).getShipLength() + 1),
                            rowStart, colStart, colStart);
                    return;
                }
            } else if (NEorSW == 1 && rowStart + ships.get(shipIndex).getShipLength() - 1 <= 'J') {

                // DOES IT FIT VERTICALLY AND DOWN ?
                placementRes = arena.isCorrectCoordinates(rowStart, (char) (rowStart + ships.get(shipIndex).getShipLength() - 1),
                        colStart, colStart, ships.get(shipIndex));
                if (placementRes == Battlefield.VALID_COORD) {
                    for (char i = rowStart; i <= rowStart + ships.get(shipIndex).getShipLength() - 1; i++) {
                        arena.placePiece(i, colStart, arena.SHIP);
                    }
                    
                    ships.get(shipIndex).storeShipPlacement(rowStart, (char) (rowStart + ships.get(shipIndex).getShipLength() - 1)
                            , colStart, colStart);
                    return;
                }
            }
            placeShip(shipIndex);
        }
    }

    @Override
    public String fireASalvo() {
        return generateRandomMapCoordinates();
    }
}//end of class
