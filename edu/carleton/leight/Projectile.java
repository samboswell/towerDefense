package edu.carleton.leight;

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

}
