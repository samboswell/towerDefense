package edu.carleton.leight;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Enemy {
    
    private boolean isFinished;
    private int health;
    private int speed;
    private int value;
    private int gold;
    private double xCoordinate;
    private double yCoordinate;

    private final int RADIUS = 10;
    private Circle circle;
    
    public Enemy (double xCoordinate, double yCoordinate, Circle circle) {
        this.isFinished = false;
        this.health = 100;
        this.speed = 2;
        this.value = 20;
        this.gold = 100;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.circle = circle;
    }
    public boolean isFinished() {
        // if enemy is off map, return true
        // else, return false ****#########EDITEDITEDIT###########***********
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

    public int getGold() {
        return this.gold;
    }
    public double getX() {
        return this.xCoordinate;
    }
    public double getY() {
        return this.yCoordinate;
    }
    public Circle getCircle() {
        return this.circle;
    }
    public void setX(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public void setY(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // just for testing ###############################################
    public static void main(String[] args) {
    }
}