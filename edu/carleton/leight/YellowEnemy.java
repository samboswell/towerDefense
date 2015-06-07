package edu.carleton.leight;


import javafx.scene.paint.Color;

public class YellowEnemy extends Enemy {
    public static String name = "Yellow Enemy";

    public YellowEnemy(double xCoordinate, double yCoordinate) {
        super(name, 300, 150, 60, xCoordinate, yCoordinate, Color.YELLOW);
    }


}
