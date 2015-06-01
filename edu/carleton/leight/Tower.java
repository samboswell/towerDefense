package edu.carleton.leight;


import java.util.List;

public class Tower extends Sprite {
    
    private int range;
    private int damage;
    private int cost;
    private int speed;
    private int xCoordinate;
    private int yCoordinate;
    
    public Tower() {
        this.range = 3; // [minX, maxX, minY, maxY]
        this.damage = 20;
        this.cost = 12;
        this.speed = 10;
//        this.xCoordinate = xCoordinate;
//        this.yCoordinate = yCoordinate;
    }
    public int getRange() {
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
//    public int getX() {return this.xCoordinate;}
//    public int getY() {return this.yCoordinate;}
//    public String getStats() {
//        //this is probably incomplete ######################################
//        int coveredRange = range[1]*range[3] - range[0]*range[2];
//        String stats = "Covered range: "+coveredRange;
//        stats += "Damage: " + this.damage;
//        stats += "Attack Speed: " + this.speed;
//        return stats;
//    }

//     public List<Enemy> getEnemiesInRange() {
//        enemies = ;
//        enemiesInRange = reduce to only enemies in range.
//        return this.enemiesInRange;
//     }
    
    
    // just for testing ###############################################
    public static void main(String[] args) {
        Tower t = new Tower();
    }
}