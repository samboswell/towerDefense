package edu.carleton.leight;


import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Tower extends Sprite {

    private double range;
    private int damage;
    private int cost;
    private double speed;
    private double xCoordinate;
    private double yCoordinate;
    private Circle circle;
    private List<Projectile> projectiles;
    
    public Tower(double xCoordinate, double yCoordinate) {
        this.range = 10.0; // [minX, maxX, minY, maxY]
        this.damage = 20;
        this.cost = 50;
        this.speed = 1.0;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        projectiles = new ArrayList<>();
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
    public double getSpeed() {
        return this.speed;
    }
    public double getX() {return this.xCoordinate;}
    public double getY() {return this.yCoordinate;}
    public String getStats() {
        String stats = "Covered range: " + this.range + "Damage: "
                        + this.damage + "Attack Speed: " + this.speed;
        return stats;
    }

    public void upgrade() {
        damage = damage + 1;
        speed = speed + 0.1;
        range = range + 3.0;
        cost += 30;
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

    public void attackEnemies(List<Enemy> enemiesAlive) {
        List<Enemy> enemiesInRange = getEnemiesInRange(enemiesAlive);
        if(enemiesInRange.size()>0) {
            makeProjectile(enemiesInRange.get(0));
        }
        //enemy.setHealth(this.getDamage());
    }

    public void makeProjectile(Enemy enemy) {
        Projectile projectile = new Projectile(enemy, xCoordinate, yCoordinate);
        projectiles.add(projectile);
    }
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