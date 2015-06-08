package edu.carleton.leight;

/**
 * BlueEnemy extends Enemy and is used to create the Blue Enemies
 *
 * @authors Jonah Tuchow, Tristan Leigh, Sam Boswell
 */
import javafx.scene.paint.Color;

public class BlueEnemy extends Enemy {
    public static String name = "Blue Enemy";

    public BlueEnemy(double xCoordinate, double yCoordinate) {
        super(name, 250, 15, 15, xCoordinate, yCoordinate, Color.BLUE);
    }


}