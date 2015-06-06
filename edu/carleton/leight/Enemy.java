package edu.carleton.leight;

import javafx.scene.shape.Circle;

public class Enemy {

    private String name;
    private boolean isFinished;
    private int startHealth;
    private int health;
    private int startSpeed;
    private int speed;
    private int scoreValue;
    private int gold;
    private double xCoordinate;
    private double yCoordinate;

    private Circle circle;
    
    public Enemy (String name, int health, int scoreValue, int gold, double xCoordinate, double yCoordinate, Circle circle) {
        this.name = name;
        this.isFinished = false;
        this.health = health;
        this.speed = 2;
        this.scoreValue = scoreValue;
        this.gold = gold;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.circle = circle;
    }
    public boolean isFinished() {
//        if((this.getX()==circle.getRadius()+50.0) || (this.getY()==circle.getRadius()+50.0)) {
//            this.isFinished = true;
//            return true;
//        }
//        this.isFinished = false;
//        return false;
        return this.getY() < 0;
    }
    public String getName() {return this.name;}
    public int getHealth() {
        return this.health;
    }
    public int getSpeed() {
        return this.speed;
    }
    public int getValue() {
        return this.scoreValue;
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

    public void setHealth(int health, int damage) {
        this.health = health-damage;
    }
}