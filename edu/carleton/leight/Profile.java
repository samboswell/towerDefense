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

    public int getGold() {return this.gold;}

    public int getHighScore() {
        int userHighScore = this.highScore;
        return this.highScore;}

    public List<Tower> getUserTowers() {return this.userTowers;}

    public void buyTower(){
        //########################################
        //get x and y coordinates from user input in javaFX
        int x = 0;
        int y = 0;
        Tower tower = new Tower(x,y);

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
