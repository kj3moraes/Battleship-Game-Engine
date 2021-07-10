package Services;

import java.util.Stack;

public class Ship {
    public String shipName;
    public int shipLength;
    private Stack<String> position;

    public Ship(String name, int length) {
        this.shipName = name;
        this.shipLength = length;
        position = new Stack<String>();
    }

    public String getShipName() {
        return shipName;
    }

    public int getShipLength() {
        return shipLength;
    }


}