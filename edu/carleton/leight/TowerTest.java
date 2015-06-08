//package edu.carleton.leight;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.paint.Color;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// * Created by boswells on 6/1/15.
// */
//public class TowerTest {
//
//    @org.junit.Before
//    public void beginning() throws Exception {
//
//    }
//    Image towerImage = new Image("edu/carleton/leight/TowerImage.png",
//            50,50,false,false);
//    ImageView towerView = new ImageView(towerImage);
//    Tower tower = new Tower(10.0, 10.0, towerView);
//    @org.junit.Test
//    public void testGetRange() throws Exception {
//        assertEquals(tower.getRange(), 102.0, 0.0);
//    }
//
//    @org.junit.Test
//    public void testGetDamage() throws Exception {
//        assertEquals(tower.getDamage(), 20);
//    }
//
//    @org.junit.Test
//    public void testGetCost() throws Exception {
//        assertEquals(tower.getCost(),50);
//    }
//
//    @org.junit.Test
//    public void testGetStats() throws Exception {
//        assertEquals(tower.getStats(), "Damage: 20 Attack Speed: 1.0");
//    }
//
//    @org.junit.Test
//    public void testUpgrade() throws Exception {
//        assertEquals(tower.getDamage(),20);
//        tower.upgrade();
//        //after one upgrade, damage should be 20+1=21
//        assertEquals(tower.getDamage(),21);
//        tower.upgrade();
//        //after two upgrades, cost should be 50+30+30=110
//        assertEquals(tower.getCost(),110);
//        //range should no longer be 20
//        assertFalse("incorrect range for tower", tower.getRange()==20);
//    }
//
//    @org.junit.Test
//    public void testEnemiesInRange() throws Exception {
//        List<Enemy> enemiesAlive = new ArrayList<>();
//        List<Enemy> enemiesInRange = new ArrayList<>();
//        //distance of 10(root2)
//        Enemy enemy1 = new Enemy("Red Enemy", 100, 50, 20, 0.0, 0.0, Color.RED);
//        //distance of 15
//        Enemy enemy2 = new Enemy("Red Enemy", 100, 50, 20, 0.0, 5.0, Color.RED);
//        //distance of 9
//        Enemy enemy3 = new Enemy("Red Enemy", 100, 50, 20, 1.0, 10.0, Color.RED);
//        //distance of 5
//        Enemy enemy4 = new Enemy("Red Enemy", 100, 50, 20, 5.0, 10.0, Color.RED);
//        //distance of 20
//        Enemy enemy5 = new Enemy("Red Enemy", 100, 50, 20, 30.0, 30.0, Color.RED);
//        enemiesAlive.add(enemy1);
//        enemiesAlive.add(enemy2);
//        enemiesAlive.add(enemy3);
//        enemiesAlive.add(enemy4);
//        double minXRange = tower.getX() - tower.getRange();
//        double minYRange = tower.getY() - tower.getRange();
//        double maxXRange = tower.getX() + tower.getRange();
//        double maxYRange = tower.getY() + tower.getRange();
//        for (Enemy enemy : enemiesAlive) {
//            if (enemy.getX() > minXRange && enemy.getX() < maxXRange &&
//                    enemy.getY() > minYRange && enemy.getY() < maxYRange) {
//                enemiesInRange.add(enemy);
//            }
//        }
//        assertFalse("enemy1 is in the list", enemiesInRange.contains(enemy1));
//        assertFalse("enemy2 is in the list", enemiesInRange.contains(enemy2));
//        assertTrue("enemy3 is not in the list", enemiesInRange.contains(enemy3));
//        assertTrue("enemy4 is not in the list", enemiesInRange.contains(enemy4));
//        assertFalse("enemy5 is in the list", enemiesInRange.contains(enemy5));
//    }
//}