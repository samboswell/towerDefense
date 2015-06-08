package edu.carleton.leight;

/**
 * RedEnemy extends Enemy and is used to create the Red Enemies
 *
 * @authors Jonah Tuchow, Tristan Leigh, Sam Boswell
 */

import javafx.scene.paint.Color;

public class RedEnemy extends Enemy {
    public static String name = "Red Enemy";

    public RedEnemy(double xCoordinate, double yCoordinate) {
        super(name, 100, 10, 10, xCoordinate, yCoordinate, Color.RED);
    }


}
