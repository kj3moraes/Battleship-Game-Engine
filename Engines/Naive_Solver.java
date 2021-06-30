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

        // CAN IT FIT HORIZONTALLY ? n
        if (colStart - s.getShipLength() + 1 > 0) {
            if (arena.isCorrectCoordinates(rowStart, rowStart, colStart, colStart - s.getShipLength() + 1, s) {
                for (int i = colStart - s.getShipLength() + 1; i++) {

                }
            }
        } else if (colStart + s.getShipLength() < 10) {
            if (arena.isCorrectCoordinates(rowStart, rowStart, colStart, colStart - s.getShipLength() + 1, s) {


            }
        }

        // CAN IT FIT VERTICALLY ?
        if (rowStart - s.getShipLength() > 'A') {

        } else if (rowStart + s.getShipLength() < 'J') {

        }

    }

    @Override
    public void fireASalvo() {
        String fireCoordiantes = generateRandomMapCoordinates();
        char salvoRow = fireCoordiantes.charAt(0);
        int salvoColumn = Integer.parseInt(fireCoordiantes.charAt()
    }

    public static void main(String[] args) {
        Naive_Solver test = new Naive_Solver();
        for (int i = 0; i < 100; i++) {
            System.out.println(test.generateRandomMapCoordinates());
        }
    }
}//end of class
