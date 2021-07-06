package Services;

public enum Ship {
    ARC("Aircraft Carrier", 5),
    BTL("Battleship", 4),
    CRU("Cruiser", 3),
    SUB("Submarine", 3),
    DES("Destroyer", 2);

    public final String shipName;
    public final int shipLength;

    Ship(String name, int length) { shipName = name; shipLength = length;}

    public String getShipName() {
        return shipName;
    }

    public int getShipLength() {
        return shipLength;
    }
}