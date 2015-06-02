package edu.carleton.leight;

import javafx.scene.shape.Circle;

public class Enemy {
    
    private boolean isFinished;
    private int health;
    private int speed;
    private int value;
    private int gold;
    private double xCoordinate;
    private double yCoordinate;

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
        if((this.getX()==circle.getRadius()+50.0) || (this.getY()==circle.getRadius()+50.0)) {
            this.isFinished = true;
            return true;
        }
        this.isFinished = false;
        return false;
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
        if (xCoordinate < 0.0 || xCoordinate > 400) {
        }
        this.xCoordinate = xCoordinate;
    }
    public void setY(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setHealth(int health, int damage) {
        this.health = health-damage;
    }
}