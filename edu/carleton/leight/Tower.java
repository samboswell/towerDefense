package edu.carleton.leight;


import javafx.scene.shape.Circle;

import java.util.List;

public class Tower extends Sprite {
    
    private double range;
    private int damage;
    private int cost;
    private int speed;
    private double xCoordinate;
    private double yCoordinate;
    private Circle circle;
    private List<Enemy> enemiesInRange;
    
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

     public List<Enemy> getEnemiesInRange(List<Enemy> enemies) {
        //get tower coordinates
        //compare enemy coordinates
         xCoordinate = this.getX();
         yCoordinate = this.getY();
         double minXRange = this.getX() - this.getRange();
         double minYRange = this.getY() - this.getRange();
         double maxXRange = this.getX() + this.getRange();
         double maxYRange = this.getY() + this.getRange();
         for (int i = 0; i < enemies.size(); i++) {
             Enemy enemy = new Enemy(false,100,5,10,300,550,circle);
             if (enemy.getX() > minXRange && enemy.getX() < maxXRange &&
                 enemy.getY() > minYRange && enemy.getY() <maxYRange) {
                 enemiesInRange.add(enemy);
             }
         }
         return enemiesInRange;
     }
    
    
    // just for testing ###############################################
    public static void main(String[] args) {
//        List<Enemy> enemies = new
    }
}