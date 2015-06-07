package edu.carleton.leight;

import javafx.scene.paint.Color;
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
    private boolean isDead;
    private int size;

    private Circle circle;
    
    public Enemy (String name, int health, int size, int scoreValue, int gold, double xCoordinate, double yCoordinate, Color color) {
        this.name = name;
        this.isFinished = false;
        this.health = health;
        this.size = size;
        this.speed = 2;
        this.scoreValue = scoreValue;
        this.gold = gold;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.circle = new Circle(325,550,this.getSize(), color);
        this.isDead = false;
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
    public int getSize() {return this.size;}
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
    public void setCircleX(double xCoordinate) {
        this.circle.setCenterX(xCoordinate);
    }
    public void setCircleY(double yCoordinate) {
        this.circle.setCenterY(yCoordinate);
    }

    public void setHealth(int health, int damage) {
        this.health = health-damage;
        if (health <= 0) {
            isDead = true;
            isFinished = false;
        }
    }

    public boolean isDead() {
        return this.isDead;
    }
}