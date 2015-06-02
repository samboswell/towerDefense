package edu.carleton.leight;

import java.util.ArrayList;
import java.util.List;

public class Profile {

    private int lives;
    private int gold;
    private int highScore;
    private List<Tower> userTowers;

    public Profile(int lives, int gold) {
        this.lives = lives;
        this.gold = gold;
        this.highScore = 0;
        this.userTowers = new ArrayList<>();
    }

    public int getLives() {return this.lives;}

    public void setLives(int lives) {this.lives = lives;}

    public int getGold() {return this.gold;}

    public void setGold(int gold) {this.gold = gold;}

    public int getHighScore() {return this.highScore;}

    public void setHighScore(int highScore) {this.highScore = highScore;}

    public List<Tower> getUserTowers() {return this.userTowers;}

    public void buyTower(){
        Tower tower = new Tower(0,0);

        int towerCost = tower.getCost();
        this.userTowers.add(tower);
        this.gold -= towerCost;

    }


    // just for testing ###############################################
    public static void main(String[] args) {
        Tower[] towerList = new Tower[1];
        Profile p = new Profile(2,200);
        System.out.println(p.getLives());
        System.out.println(p.getGold());
        System.out.println(p.getHighScore());
        System.out.println(p.getUserTowers());
    }

}
