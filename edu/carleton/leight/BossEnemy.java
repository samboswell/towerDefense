package edu.carleton.leight;


import javafx.scene.shape.Circle;

public class BossEnemy extends Enemy {
    public static String name = "Boss Enemy";
    public static Circle circle;

    public BossEnemy(double xCoordinate, double yCoordinate) {
        super(name, 400, 200, 100, xCoordinate, yCoordinate, circle);
    }


}

