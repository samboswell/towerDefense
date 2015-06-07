package edu.carleton.leight;


import javafx.scene.image.ImageView;
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
    private ImageView towerImage;
    
    public Tower(double xCoordinate, double yCoordinate, ImageView towerImage) {
        this.towerImage = towerImage;
        this.range = 100.0; // [minX, maxX, minY, maxY]
        this.damage = 20;
        this.cost = 50;
        this.speed = 1.0;

        //set coordinates to middle of the box
        this.xCoordinate = xCoordinate - xCoordinate%50 + 25;
        this.yCoordinate = yCoordinate - yCoordinate%50 + 25;
        projectiles = new ArrayList<>();
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
    public double getSpeed() {
        return this.speed;
    }
    public double getX() {return this.xCoordinate;}
    public double getY() {return this.yCoordinate;}
    public String getStats() {
        return "Range: " + this.range + "\nDamage: " + this.damage +
                "\nAttack Speed: " + this.speed;
    }

    public void upgrade() {
        damage = damage + 5;
        speed = speed + 0.1;
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

    public List<Projectile> getProjectiles() {return this.projectiles;}
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