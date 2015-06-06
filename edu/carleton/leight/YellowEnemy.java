package edu.carleton.leight;


import javafx.scene.shape.Circle;

public class YellowEnemy extends Enemy {
    public static String name = "Yellow Enemy";
    public static Circle circle;

    public YellowEnemy(double xCoordinate, double yCoordinate) {
        super(name, 300, 150, 60, xCoordinate, yCoordinate, circle);
    }


}
