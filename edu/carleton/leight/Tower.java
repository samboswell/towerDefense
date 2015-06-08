package edu.carleton.leight;

/**
 * Model for the towers of the game. Gets and sets all the basic values for
 * the towers. Also works on enemy detection in the tower's range.
 *
 * @authors Jonah Tuchow, Tristan Leigh, Sam Boswell
 */

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Tower {

    private double range;
    private int damage;
    private int cost;
    private double xCoordinate;
    private double yCoordinate;
    private ImageView towerImage;
    
    public Tower(double xCoordinate, double yCoordinate, ImageView towerImage) {
        this.towerImage = towerImage;
        this.range = 102.0;
        this.damage = 20;
        this.cost = 50;

        //set coordinates to middle of the box
        this.xCoordinate = xCoordinate - xCoordinate%50 + 25;
        this.yCoordinate = yCoordinate - yCoordinate%50 + 25;
    }

    public ImageView getImage() {
        return this.towerImage;
    }
    public double getRange() {
        return this.range;
    }
    public int getDamage() {
        return this.damage;
    }
    public int getCost() {
        return this.cost;
    }
    public void setCost(int cost) { this.cost = cost;}
    public double getX() {return this.xCoordinate;}
    public double getY() {return this.yCoordinate;}
    public String getStats() {
        return "Range: " + this.range + "\nDamage: " + this.damage +
                "\nUpgrade: " + this.cost*1.25;
    }

    public void upgrade() {
        damage = damage + 5;
        range = range + 20.0;
    }

     public List<Enemy> getEnemiesInRange(List<Enemy> enemiesAlive) {
         List<Enemy> enemiesInRange = new ArrayList<>();
         xCoordinate = this.getX();
         yCoordinate = this.getY();
         double minXRange = this.getX() - this.getRange();
         double minYRange = this.getY() - this.getRange();
         double maxXRange = this.getX() + this.getRange();
         double maxYRange = this.getY() + this.getRange();
         for (Enemy enemy: enemiesAlive) {
             if ((enemy.getX() > minXRange && enemy.getX() < maxXRange) &&
                 (enemy.getY() > minYRange && enemy.getY() < maxYRange)) {
                 enemiesInRange.add(enemy);
             }
         }
         return enemiesInRange;
     }

    // just for testing ###############################################
    public static void main(String[] args) {
//        GameManager gameManager = new GameManager();
//        List<Enemy> enemies = gameManager.getAliveEnemies();
//        Tower tower = new Tower(300,550);
//        List<Enemy> enemiesRange = tower.getEnemiesInRange(enemies);
//        System.out.println(enemies.size());
//        System.out.println(enemiesRange.size());
    }
}