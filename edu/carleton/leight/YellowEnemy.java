package edu.carleton.leight;

/**
 * YellowEnemy extends Enemy and is used to create the Yellow Enemies
 *
 * @authors Jonah Tuchow, Tristan Leigh, Sam Boswell
 */

import javafx.scene.paint.Color;

public class YellowEnemy extends Enemy {
    public static String name = "Yellow Enemy";

    public YellowEnemy(double xCoordinate, double yCoordinate) {
        super(name, 600, 20, 20, xCoordinate, yCoordinate, Color.YELLOW);
    }


}
