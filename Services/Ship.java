package Services;

import java.util.ArrayList;

public class Ship {
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
     * REQUIRES : Coordinates must be normalized and a VALID PLACMENT.
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
     * removeShipPart(String) -----------------------------------------------------------
     * Removes a part of the ship that has been hit by the salvo
     *
     * REQUIRES : salvoCoordinates must be a hit.
     * @param salvoCoordinate - coordinate of the salvo.
     */
    public void removeShipPart(String salvoCoordinate) {

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