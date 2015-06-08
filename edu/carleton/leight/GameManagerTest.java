package edu.carleton.leight;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameManagerTest {
    @org.junit.Before
    public void beginning() throws Exception {

    }
//    @Test
//    public void testGetGameScene() throws Exception {
//    }

    @Test
    public void testGetProfile() throws Exception {
        Profile profile = new Profile(10, 100);
        assertEquals(100, profile.getGold());
        assertEquals(10, profile.getLives());
    }

    public int changeWaves(int wave, int enemyCount) {

        if (wave == 0) {
            wave = 1;
        }
        if (wave == 1 && enemyCount > 9) {
            wave = 2;
        }
        if (wave == 2 && enemyCount > 29) {
            wave = 3;
        }
        if (wave == 3 && enemyCount > 59) {
            wave = 4;
        }
        if (wave == 4 && enemyCount > 99) {
            wave = 5;
        }
        if (wave == 5 && enemyCount > 124) {
            wave = 6;
        }
        if (wave == 6 && enemyCount > 149) {
            wave = 7;
        }

        return wave;
    }

    @Test
    public void testCreateWaveButton() throws Exception {
        //if input wave == 0, should come out as 1
        int enemyCount = 0;
        int wave = 0;
        wave = changeWaves(wave, enemyCount);
        assertEquals(1, wave);

        //if input wave == 1, and enemyCount > 9, wave == 2
        enemyCount = 10;
        wave = changeWaves(wave, enemyCount);
        assertEquals(2, wave);

        //if input wave == 2, but enemyCount < 29, wave == 2

        enemyCount = 20;
        wave = changeWaves(wave, enemyCount);
        assertEquals(2, wave);
    }



    public ArrayList<Enemy> createEnemyList() {
        int x = 100;
        int y = 800;
        int enemyCount = 0;


        ArrayList<Enemy> enemiesAlive = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            names.add("Red Enemy");
        }
        for (int i = 0; i < 10; i++) {
            names.add("Blue Enemy");
            names.add("Yellow Enemy");
        }


        for (String name : names) {
            if (name.equals("Red Enemy")) {
                //model
                Enemy enemy = new RedEnemy(x, y);
                enemiesAlive.add(enemy);
                enemyCount++;
            }
            if (name.equals("Blue Enemy")) {
                //model
                Enemy enemy = new BlueEnemy(x, y);
                enemiesAlive.add(enemy);
                enemyCount++;
            }
            if (name.equals("Yellow Enemy")) {
                //model
                Enemy enemy = new YellowEnemy(x, y);
                enemiesAlive.add(enemy);
                enemyCount++;
            }
            if (name.equals("Boss Enemy")) {
                //model
                Enemy enemy = new BossEnemy(x, y);
                enemiesAlive.add(enemy);
                enemyCount++;
            }
        }
        return enemiesAlive;
    }

    @Test
    public void testCreateEnemy() throws Exception {
        ArrayList<Enemy> enemiesAlive = createEnemyList();


        for (Enemy enemy : enemiesAlive) {
            //Are all the enemies in the right places in enemiesAlive?

            if (enemiesAlive.indexOf(enemy) < 10) {
                assertEquals(enemy.getName(), "Red Enemy");
            }
            if (enemiesAlive.indexOf(enemy) > 10 &&
                    enemiesAlive.indexOf(enemy) % 2 == 0){
                assertEquals(enemy.getName(), "Blue Enemy");
            }
            if (enemiesAlive.indexOf(enemy) > 10 &&
                    enemiesAlive.indexOf(enemy) % 2 == 1){
                assertEquals(enemy.getName(), "Yellow Enemy");
            }
            //Are there any enemies in the list that we didn't add? (Boss Enemy)
            assertNotEquals(enemy.getName(), "Boss Enemy");

        }
    }

//    @Test
//    public void testRemoveEnemyFromGame() throws Exception {
//        ArrayList<Enemy> enemiesAlive = createEnemyList();
//
//        //remove all redEnemies from enemiesAlive
//        for(Enemy enemy : enemiesAlive) {
//            if (enemy.getName().equals("Red Enemy")) {
//                enemiesAlive.remove(enemy);
//            }
//        }
//
//        //are there any redEnemies in enemiesAlive?
//        for(Enemy enemy : enemiesAlive) {
//            System.out.println(enemy.getName());
//        }
//
//    }


}