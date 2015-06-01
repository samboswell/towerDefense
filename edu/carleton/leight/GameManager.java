package edu.carleton.leight;


import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private Profile profile;
    private List<Enemy> enemies;

    public GameManager() {
        this.profile = new Profile(10, 20);
        this.enemies = new ArrayList();
        mainLoop();
    }
    public void upgrade(Tower tower) {}
    public void attackEnemy(Tower tower, Enemy enemy) {
        enemy.setHealth(tower.getDamage());
    }

    public void attackEnemies() {
        List<Tower> userTowers = this.profile.getUserTowers();
        for (Tower tower : userTowers) {
            List<Enemy> enemiesInRange = getEnemiesInRange();
            for (Enemy enemy : enemiesInRange) {
                attackEnemy(tower, enemy);
            }
        }
    }

    // public List<Enemy> getEnemiesInRange(Tower tower) {
    // enemies = getCurrentEnemies();
    // enemiesInRange = reduce to only enemies in range.
    // return this.enemiesInRange;
    // }
    public void deadEnemy(Enemy enemy) {}
    public void sellTower() {}
    public List<Enemy> getCurrentEnemies() {
        return this.enemies;
    }
    public List<Enemy> getEnemiesInRange() {


        List<Enemy> enemiesInRange = new ArrayList<>();
        //###########################
        //should check if enemies are in correct squares and return a list of
        //enemies that are in the correct squares.
        return enemiesInRange;
    }

    //Iterates through enemies to find an enemy from the list of enemies
    // that is finished, then removes it from the list.
    public void removeEnemyIfFinished() {
        for(Enemy enemy : enemies) {
            if (enemy.isFinished()) {
                enemies.remove(enemies.indexOf(enemy));
            }
        }
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void mainLoop() {
//        while (true) {
//            attackEnemy();
//            updateEnemyPositions();
//        }
    }
}
