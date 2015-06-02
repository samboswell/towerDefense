package edu.carleton.leight;


import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Tower extends Sprite {

    private double range;
    private int damage;
    private int cost;
    private int speed;
    private double xCoordinate;
    private double yCoordinate;
    private Circle circle;
    
    public Tower(double xCoordinate, double yCoordinate) {
        this.range = 3.0; // [minX, maxX, minY, maxY]
        this.damage = 20;
        this.cost = 12;
        this.speed = 10;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
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
    public int getSpeed() {
        return this.speed;
    }
    public double getX() {return this.xCoordinate;}
    public double getY() {return this.yCoordinate;}
//    public String getStats() {
//        //this is probably incomplete ######################################
//        int coveredRange = range[1]*range[3] - range[0]*range[2];
//        String stats = "Covered range: "+coveredRange;
//        stats += "Damage: " + this.damage;
//        stats += "Attack Speed: " + this.speed;
//        return stats;
//    }

     public List<Enemy> getEnemiesInRange(List<Enemy> enemiesAlive) {
        //get tower coordinates
        //compare enemy coordinates
         List<Enemy> enemiesInRange = new ArrayList<>();
         xCoordinate = this.getX();
         yCoordinate = this.getY();
         double minXRange = this.getX() - this.getRange();
         double minYRange = this.getY() - this.getRange();
         double maxXRange = this.getX() + this.getRange();
         double maxYRange = this.getY() + this.getRange();
         for (Enemy enemy: enemiesAlive) {
             if (enemy.getX() > minXRange && enemy.getX() < maxXRange &&
                 enemy.getY() > minYRange && enemy.getY() < maxYRange) {
                 enemiesInRange.add(enemy);
             }
         }
         return enemiesInRange;
     }

    public void attackEnemy(Enemy enemy) {

        //enemy.setHealth(this.getDamage());
    }

//    public void attackEnemies() {
//        List<Tower> userTowers = this.profile.getUserTowers();
//        for (Tower tower : userTowers) {
//            List<Enemy> enemiesInRange = getEnemiesInRange();
//            for (Enemy enemy : enemiesInRange) {
//                attackEnemy(tower, enemy);
//            }
//        }
//    }
    
    
    // just for testing ###############################################
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        List<Enemy> enemies = gameManager.getAliveEnemies();
        Tower tower = new Tower(300,550);
        List<Enemy> enemiesRange = tower.getEnemiesInRange(enemies);
        System.out.println(enemies.size());
        System.out.println(enemiesRange.size());
    }
}