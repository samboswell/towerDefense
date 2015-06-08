package edu.carleton.leight;

/**
 * Testing file to test the tower class and its methods. Only basic tests apply, because
 * testing situations have been cleared in the GameManager with controller
 * processes and cannot be unit tested.
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class TowerTest {

    /**
     * creates an instance of the tower without the image.
     * The image is not necessary for model testing, so it is
     * set to null.
     * Tests are copied from code used in the Tower class.
     */
    public Tower createTower() {
        Image towerImage = null;
        ImageView towerView = new ImageView(towerImage);
        Tower tower = new Tower(200.0, 200.0, towerView);
        return tower;
    }

    /**
     * Testing file to test the tower class and its methods.
     */
    @org.junit.Test
    public void testGetRange() throws Exception {
        Tower tower = createTower();
        assertEquals(tower.getRange(), 102.0, 0.0);
    }

    /**
     * Testing file to test the tower class and its methods.
     */
    @org.junit.Test
    public void testGetDamage() throws Exception {
        Tower tower = createTower();
        assertEquals(tower.getDamage(), 20);
    }

    /**
     * Tests getCost().
     *
     */
    @org.junit.Test
    public void testGetCost() throws Exception {
        Tower tower = createTower();
        assertEquals(tower.getCost(),50);
    }

    /**
     * Tests getStats().
     */
    @org.junit.Test
    public void testGetStats() throws Exception {
        Tower tower = createTower();
        assertEquals(tower.getStats(), "Range: 102.0\n" +
                "Damage: 20\n" +
                "Upgrade: 62.5");
    }

    /**
     * Tests upgrade().
     */
    @org.junit.Test
    public void testUpgrade() throws Exception {
        Tower tower = createTower();
        assertEquals(tower.getDamage(),20);
        tower.upgrade();
        //after one upgrade, damage should be 20+5
        assertEquals(tower.getDamage(),25);
        tower.upgrade();
        //since upgrade cost is different than original cost, getCost() returns 50
        assertEquals(tower.getCost(),50);
        //range should no longer be 20
        assertFalse("incorrect range for tower", tower.getRange()==20);
    }

    /**
     * Tests enemiesInRange().
     */
    @org.junit.Test
    public void testEnemiesInRange() throws Exception {
        Tower tower = createTower();
        List<Enemy> enemiesAlive = new ArrayList<>();
        List<Enemy> enemiesInRange = new ArrayList<>();
        //distance of 50
        Enemy enemy1 = new Enemy("Red Enemy", 100, 50, 20, 200.0, 150.0, Color.RED);
        //distance of 102. Should be false because range < 102.0
        Enemy enemy2 = new Enemy("Red Enemy", 100, 50, 20, 200.0, 98.0, Color.RED);
        //distance of 100
        Enemy enemy3 = new Enemy("Red Enemy", 100, 50, 20, 200.0, 300.0, Color.RED);
        enemiesAlive.add(enemy1);
        enemiesAlive.add(enemy2);
        enemiesAlive.add(enemy3);

        double minXRange = tower.getX() - tower.getRange();
        double minYRange = tower.getY() - tower.getRange();
        double maxXRange = tower.getX() + tower.getRange();
        double maxYRange = tower.getY() + tower.getRange();
        for (Enemy enemy: enemiesAlive) {
            if ((enemy.getX() > minXRange && enemy.getX() < maxXRange) &&
                    (enemy.getY() > minYRange && enemy.getY() < maxYRange)) {
                enemiesInRange.add(enemy);
            }
        }
        assertTrue("enemy1 is not the list", enemiesInRange.contains(enemy1));
        assertFalse("enemy2 is in the list", enemiesInRange.contains(enemy2));
        assertTrue("enemy3 is not in the list", enemiesInRange.contains(enemy3));

    }
}