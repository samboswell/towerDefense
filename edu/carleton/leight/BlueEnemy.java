package edu.carleton.leight;


import javafx.scene.shape.Circle;

public class BlueEnemy extends Enemy {
    public static String name = "Blue Enemy";
    public static Circle circle;

    public BlueEnemy(double xCoordinate, double yCoordinate) {
        super(name, 200, 100, 40, xCoordinate, yCoordinate, circle);
    }


}