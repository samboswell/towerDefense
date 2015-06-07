//package edu.carleton.leight;
///**
// * Created by boswells on 6/1/15.
// */
//
//import javafx.scene.shape.Circle;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//public class EnemyTest {
//
//    @org.junit.Before
//    public void beginning() throws Exception {
//        Circle circle = new Circle(30,30,10);
//        Enemy enemy = new Enemy("Red Enemy", 100, 50, 20, 30.0, 30.0, circle);
//    }
//
//    Circle circle = new Circle(30,30,10);
//    Enemy enemy = new Enemy("Red Enemy", 100, 50, 20, 30.0, 30.0, circle);
//    @org.junit.Test
//    public void testIsFinished() throws Exception {
//        assertFalse("enemy HAS finished the path",enemy.isFinished());
//        enemy.setX(10.0);
//        assertTrue("enemy is still on the X path", enemy.isFinished());
//        enemy.setX(20.0);
//        enemy.setY(10.0);
//        assertTrue("enemy is still on the Y path", enemy.isFinished());
//    }
//
//    @org.junit.Test
//    public void testGetHealth() throws Exception {
//        assertEquals(enemy.getHealth(),100);
//    }
//
//    @org.junit.Test
//    public void testGetSpeed() throws Exception {
//        assertEquals(enemy.getSpeed(),2);
//    }
//
//    @org.junit.Test
//    public void testGetValue() throws Exception {
//        assertEquals(enemy.getValue(),20);
//    }
//
//    @org.junit.Test
//    public void testGetX() throws Exception {
//        assertEquals(enemy.getX(),30.0);
//    }
//
//    @org.junit.Test
//    public void testGetY() throws Exception {
//        assertEquals(enemy.getY(),30.0);
//    }
//
//    @org.junit.Test
//    public void testSetX() throws Exception {
//        assertEquals(enemy.getX(),30.0);
//        enemy.setX(20.0);
//        assertEquals(enemy.getX(),20.0);
//    }
//
//    @org.junit.Test
//    public void testSetY() throws Exception {
//        assertEquals(enemy.getY(),30.0);
//        enemy.setY(20.0);
//        assertEquals(enemy.getY(),20.0);
//    }
//
//    @org.junit.Test
//    public void testSetHealth() throws Exception {
//        int damage = 20;
//        assertEquals(enemy.getHealth(), 100);
//        enemy.setHealth(enemy.getHealth(),damage);
//        assertEquals(enemy.getHealth(),80);
//    }
//}