package edu.carleton.leight;

import org.junit.Test;

/**
 * Tests the testible methods in GameManager. Since much of GameManager deals with
 * controller properties, new tear-down methods have been created to test
 * the model aspects of GameManager.
 */

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameManagerTest {
    @org.junit.Before
    public void beginning() throws Exception {

    }

    /**
     * Tests the ability of the GameManager to access variables in the profile.
     */
    @Test
    public void testGetProfile() throws Exception {
        Profile profile = new Profile(10, 100);
        assertEquals(100, profile.getGold());
        assertEquals(10, profile.getLives());
    }

    /**
     * Tear-down version of createWaveButton in GameManager without the visual
     * components. Acts as createWaveButton for all related tests.
     */
    public int createWave(int wave, int enemyCount) {

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

    /**
     * Tests changing the wave Integer depending on the current wave integer
     * and the enemyCount. enemyCount is the total number of enemies created.
     */
    @Test
    public void testCreateWaveButton() throws Exception {
        //if input wave == 0, should come out as 1
        int enemyCount = 0;
        int wave = 0;
        wave = createWave(wave, enemyCount);
        assertEquals(1, wave);

        //if input wave == 1, and enemyCount > 9, wave == 2
        enemyCount = 10;
        wave = createWave(wave, enemyCount);
        assertEquals(2, wave);

        //if input wave == 2, but enemyCount < 29, wave == 2

        enemyCount = 20;
        wave = createWave(wave, enemyCount);
        assertEquals(2, wave);
    }


    /**
     * Tear-down version of createEnemy in GameManager without the visual
     * components. Acts as createEnemy for all related tests.
     */
    public ArrayList<Enemy> createEnemy() {
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

    /**
     * Tests whether enemies created in createEnemies are in the list
     * they should be in, and in the position they should be in the list.
     */
    @Test
    public void testCreateEnemy() throws Exception {
        ArrayList<Enemy> enemiesAlive = createEnemy();


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
        }
    }
}