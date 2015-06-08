package edu.carleton.leight;
/**
 * Created by boswells on 6/1/15.
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
     * Created by boswells on 6/1/15.
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
     * Created by boswells on 6/1/15.
     */
    @org.junit.Test
    public void testGetHealth() throws Exception {
        assertEquals(enemy.getHealth(),100);
    }

    /**
     * Created by boswells on 6/1/15.
     */
    @org.junit.Test
    public void testGetX() throws Exception {
        assertEquals(enemy.getX(),100.0, 0.0);
    }

    /**
     * Created by boswells on 6/1/15.
     */
    @org.junit.Test
    public void testGetY() throws Exception {
        assertEquals(enemy.getY(),700.0, 0.0);
    }

    /**
     * Created by boswells on 6/1/15.
     */
    @org.junit.Test
    public void testSetX() throws Exception {
        assertEquals(enemy.getX(),100.0, 0.0);
        enemy.setX(20.0);
        assertEquals(enemy.getX(),20.0, 0.0);
    }

    @org.junit.Test
    public void testSetY() throws Exception {
        assertEquals(enemy.getY(),700.0, 0.0);
        enemy.setY(20.0);
        assertEquals(enemy.getY(),20.0, 0.0);
    }

    @org.junit.Test
    public void testSetHealth() throws Exception {
        int damage = 20;
        assertEquals(enemy.getHealth(), 100);
        enemy.setHealth(enemy.getHealth(),damage);
        assertEquals(enemy.getHealth(),80);
    }
}