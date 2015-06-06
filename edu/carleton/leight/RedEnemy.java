package edu.carleton.leight;


import javafx.scene.shape.Circle;

public class RedEnemy extends Enemy {
    public static String name = "Red Enemy";
    public static Circle circle;

    public RedEnemy(double xCoordinate, double yCoordinate) {
        super(name, 100, 50, 20, xCoordinate, yCoordinate, circle);
    }


}
