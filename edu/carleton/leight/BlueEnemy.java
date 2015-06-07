package edu.carleton.leight;


import javafx.scene.paint.Color;

public class BlueEnemy extends Enemy {
    public static String name = "Blue Enemy";

    public BlueEnemy(double xCoordinate, double yCoordinate) {
        super(name, 200, 100, 40, xCoordinate, yCoordinate, Color.BLUE);
    }


}