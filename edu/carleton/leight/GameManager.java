package edu.carleton.leight;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    final private double FRAMES_PER_SECOND = 20.0;

    private Timer timer;
    private Profile profile;
    private List<Enemy> enemies;

    public GameManager() {
        this.profile = new Profile(10, 20);
        this.enemies = new ArrayList();

    }
    public void upgrade(Tower tower) {}
    public void attackEnemy(Tower tower, Enemy enemy) {
        enemy.setHealth(tower.getDamage());
    }

//    public void attackEnemies() {
//        List<Tower> userTowers = this.profile.getUserTowers();
//        for (Tower tower : userTowers) {
//            List<Enemy> enemiesInRange = getEnemiesInRange();
//            for (Enemy enemy : enemiesInRange) {
//                attackEnemy(tower, enemy);
//            }
//        }
//    }

    public void deadEnemy(Enemy enemy) {}
    public void sellTower() {}


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
}
