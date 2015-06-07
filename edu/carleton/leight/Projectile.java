package edu.carleton.leight;


import java.awt.*;

/**
 * Created by leight on 5/31/15.
 */
public class Projectile {

    private Enemy enemyTarget;
    private final double towerXCoordinate;
    private final double towerYCoordinate;

    public Projectile(Enemy enemyTarget, double towerXCoordinate, double towerYCoordinate) {
        this.enemyTarget = enemyTarget;
        this.towerXCoordinate = towerXCoordinate;
        this.towerYCoordinate = towerYCoordinate;
    }

    public Enemy getEnemyTarget() {return enemyTarget;}

    public double getEndXCoordinate() {return enemyTarget.getX();}

    public double getEndYCoordinate() {return enemyTarget.getY();}

    public double getStartXCoordinate() {return this.towerXCoordinate;}

    public double getStartYCoordinate() {return this.towerYCoordinate;}

}
