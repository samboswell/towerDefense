package edu.carleton.leight;

import javafx.scene.paint.Color;

public class Enemy {
    
    private boolean isFinished;
    private int health;
    private int speed;
    private int value;
    private int xCoordinate;
    private int yCoordinate;

    private final int RADIUS = 10;
    private Color color;
    
    public Enemy (boolean isFinished, int health, int speed, int value, 
                     int xCoordinate, int yCoordinate,  Color color) {
        this.isFinished = isFinished;
        this.health = health;
        this.speed = speed;
        this.value = value;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.color = color;
    }
    public boolean isFinished() {
        return this.isFinished;
    }
    public int getHealth() {
        return this.health;
    }
    public int getSpeed() {
        return this.speed;
    }
    public int getValue() {
        return this.value;
    }
    public int getX() {
        return this.xCoordinate;
    }
    public int getY() {
        return this.yCoordinate;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // just for testing ###############################################
    public static void main(String[] args) {
        Enemy e = new Enemy(false,100,5,10,0,0, Color.RED);
        System.out.println(e.isFinished());
        System.out.println(e.getHealth());
    }
}