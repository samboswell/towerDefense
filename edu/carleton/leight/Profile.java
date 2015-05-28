package edu.carleton.leight;


public class Profile {

    private int lives;
    private int gold;
    private int highScore;
    private boolean canBePlaced;
    private Tower[] userTowers;

    public Profile(int lives, int gold, int highScore, boolean canBePlaced,
                   Tower[] userTowers) {
        this.lives = lives;
        this.gold = gold;
        this.highScore = highScore;
        this.canBePlaced = canBePlaced;
        this.userTowers = userTowers;
    }

    public int getLives() {return this.lives;}

    public int getGold() {return this.gold;}

    public int getHighScore() {return this.highScore;}

    public boolean canPlaceTower() {return this.canBePlaced;}

    public Tower[] getUserTowers() {return this.userTowers;}

    public void buyTower(){
        //Subtracts money from the player's total gold amount, and places a tower in the chosen spot.
    }


}
