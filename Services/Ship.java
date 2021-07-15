package Services;

import java.util.ArrayList;

public class Ship {
    public static final int NO_OF_SHIPS = 5;

    public String shipName;
    public int shipLength;
    private final ArrayList<String> position;


    public Ship(String name, int length) {
        this.shipName = name;
        this.shipLength = length;
        position = new ArrayList<>();
    }

    public String getShipName() {
        return shipName;
    }

    public int getShipLength() {
        return shipLength;
    }

    /**
     * storeShipPlacement(char, char, int, int) -----------------------------------------
     * Stores the coordinates of the Ships placement on the battlefield into the local
     * Ship coordinate stack.
     *
     * REQUIRES : Coordinates must be normalized and a VALID PLACEMENT.
     * @param roF - row of the first coordinate
     * @param roS - row of the second coordinate
     * @param coF - column of the first coordinate
     * @param coS - column of the second coordinate
     */
    public void storeShipPlacement(char roF, char roS, int coF, int coS) {
        if (roF == roS) {
            for (;coF <= coS; coF++) {
                position.add(roF + "" + coF);
            }
        } else {
            for (; roF <= roS; roF++) {
                position.add(roF + "" + coF);
            }
        }
    }

    /**
     * isPartOfShip() -------------------------------------------------------------------
     * Determines whether the inputed coordinates are part of the Ships position.
     * @param row - the row coordinate
     * @param col - the column coordinate
     * @return - if the coordinate is part of the ships position
     */
    public boolean isPartOfShip(char row, int col) {
        return position.contains(row + "" + col);
    }

    /**
     * removeShipPart(String) -----------------------------------------------------------
     * Removes a part of the ship if that has been hit by the salvo specified by the
     * coordinates.
     *
     * REQUIRES : salvoCoordinates must be a hit.
     * @param row - row coordinate of the salvo.
     * @param col - column coordinate of the salvo.
     */
    public void removeShipPartAndReport(char row, int col) {
        position.remove(row + "" + col);
    }

    /**
     * isShipSunken() -------------------------------------------------------------------
     * Determines whether this Ship has been sunken (i.e all the parts of the ship are
     * hit).
     * @return - whether or not the Ship is sunken.
     */
    public boolean isShipSunken() {
        return position.isEmpty();
    }
}