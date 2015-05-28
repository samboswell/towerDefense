package edu.carleton.leight;


import java.util.List;

public class GameManager {

    private int gold;
    private List<Enemy> enemies;

    public GameManager( int gold, int numEnemies) {
        this.gold = gold;
        this.enemies = enemies;
    }

    public void upgrade(Tower tower) {}
    public void attackEnemy(Enemy enemy) {}
    // public List<Enemy> getEnemiesInRange(Tower tower) {
    // enemies = getCurrentEnemies();
    // enemiesInRange = reduce to only enemies in range.
    // return this.enemiesInRange;
    // }
    public int deadEnemy(Enemy enemy) {return this.gold;}
    public void sellTower() {}
    public void placeTower(Tower tower, int xCoordinate, int yCoordinate) {}
    public List<Enemy> getCurrentEnemies() {return this.enemies;}
}
