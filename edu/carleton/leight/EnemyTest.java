package edu.carleton.leight;
/**
 *Testing file for methods in the Enemy class. Only basic tests apply, because
 * testing situations have been cleared in the GameManager with controller
 * processes and cannot be unit tested.
 */

import javafx.scene.paint.Color;

import static org.junit.Assert.*;

public class EnemyTest {

    @org.junit.Before
    public void beginning() throws Exception {
        Enemy enemy = new Enemy("Red Enemy", 100, 10, 10, 100.0, 700.0, Color.RED);
    }

    Enemy enemy = new Enemy("Red Enemy", 100, 10, 10, 100.0, 700.0, Color.RED);

    /**
     * Tests whether the enemy has finished the path (which is reaching
     * the top of the screen (y=0)).
     */
    @org.junit.Test
    public void testIsFinished() throws Exception {
        assertFalse("enemy HAS finished the path", enemy.isFinished());
        enemy.setX(0.0);
        assertFalse("enemy HAS finished the path", enemy.isFinished());
        enemy.setY(-1.0);
        assertTrue("enemy is still on the Y path", enemy.isFinished());
    }

    /**
     * Tests getting the enemy's health.
     */
    @org.junit.Test
    public void testGetHealth() throws Exception {
        assertEquals(enemy.getHealth(),100);
    }

    /**
     * Tests getting the enemy's horizontal location.
     */
    @org.junit.Test
    public void testGetX() throws Exception {
        assertEquals(enemy.getX(),100.0, 0.0);
    }

    /**
     * Tests getting the enemy's vertical location.
     */
    @org.junit.Test
    public void testGetY() throws Exception {
        assertEquals(enemy.getY(),700.0, 0.0);
    }

    /**
     * Tests setting the enemy's horizontal location.
     */
    @org.junit.Test
    public void testSetX() throws Exception {
        assertEquals(enemy.getX(),100.0, 0.0);
        enemy.setX(20.0);
        assertEquals(enemy.getX(),20.0, 0.0);
    }

    /**
     * Tests setting the enemy's vertical location.
     */
    @org.junit.Test
    public void testSetY() throws Exception {
        assertEquals(enemy.getY(),700.0, 0.0);
        enemy.setY(20.0);
        assertEquals(enemy.getY(),20.0, 0.0);
    }

    /**
     * Tests getting the enemy's setting the enemy's health.
     * Also reduces the health of the enemy based on the damage of the tower.
     */
    @org.junit.Test
    public void testSetHealth() throws Exception {
        int damage = 20;
        assertEquals(enemy.getHealth(), 100);
        enemy.setHealth(enemy.getHealth(),damage);
        assertEquals(enemy.getHealth(),80);
    }
}