package edu.carleton.leight;

/**
 * BossEnemy extends Enemy and is used to create the Boss Enemies
 *
 * @authors Jonah Tuchow, Tristan Leigh, Sam Boswell
 */
import javafx.scene.paint.Color;
public class BossEnemy extends Enemy {
    public static String name = "Boss Enemy";

    public BossEnemy(double xCoordinate, double yCoordinate) {
        super(name, 15000, 25, 1000, xCoordinate, yCoordinate,
              Color.DARKGOLDENROD);
    }


}

