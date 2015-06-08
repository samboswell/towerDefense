package edu.carleton.leight;

import java.util.ArrayList;
import java.util.List;

public class Profile {

    private int lives;
    private int gold;
    private int highScore;

    public Profile(int lives, int gold) {
        this.lives = lives;
        this.gold = gold;
        this.highScore = 0;
    }

    public int getLives() {return this.lives;}

    public void setLives(int lives) {this.lives = lives;}

    public int getGold() {return this.gold;}

    public String getStats() {
        String stats = "Lives: " + this.lives + "\n";
        stats += "High Score: " + this.highScore + "\n";
        stats += "Gold: " + this.gold;
        return stats;
    }

    public void setGold(int gold) {this.gold = gold;}

    public int getHighScore() {return this.highScore;}

    public void setHighScore(int highScore) {this.highScore = highScore;}


    // just for testing ###############################################
    public static void main(String[] args) {
        Tower[] towerList = new Tower[1];
        Profile p = new Profile(2,200);
        System.out.println(p.getLives());
        System.out.println(p.getGold());
        System.out.println(p.getHighScore());
    }

}
