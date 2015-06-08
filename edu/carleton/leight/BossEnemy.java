package edu.carleton.leight;


import javafx.scene.paint.Color;
public class BossEnemy extends Enemy {
    public static String name = "Boss Enemy";

    public BossEnemy(double xCoordinate, double yCoordinate) {
        super(name, 10000, 25, 1000, xCoordinate, yCoordinate, Color.DARKGOLDENROD);
    }


}

