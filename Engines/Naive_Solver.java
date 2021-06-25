package Engines;

import Services.Ship;

import java.util.Scanner;

public class Naive_Solver extends BattleshipEngine {

    @Override
    public void placeShip(Ship s) {
        String startingPosition = generateRandomMapCoordinates();
        char rowStart = startingPosition.charAt(0);
        int colStart = startingPosition.charAt(0);

        System.out.println("Starting Position: \t " + startingPosition);
        System.out.println("Ship : " + s.getShipName() + " \t " + s.getShipLength());

        // CAN IT FIT HORIZONTALLY ?
        if (colStart - s.getShipLength() > 0) {

        } else if (colStart + s.getShipLength() < 10) {

        }

        // CAN IT FIT VERTICALLY ?
        if (rowStart - s.getShipLength() > 'A') {

        } else if (rowStart + s.getShipLength() < 'J') {

        }

    }

    @Override
    public void fireASalvo() {

    }

    public static void main(String[] args) {
        Naive_Solver test = new Naive_Solver();
        test.placeShip(Ship.BTL);
    }
}//end of class
