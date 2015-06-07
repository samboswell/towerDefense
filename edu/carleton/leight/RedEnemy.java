package edu.carleton.leight;


import javafx.scene.paint.Color;

public class RedEnemy extends Enemy {
    public static String name = "Red Enemy";

    public RedEnemy(double xCoordinate, double yCoordinate) {
        super(name, 100, 50, 20, xCoordinate, yCoordinate, Color.RED);
    }


}
