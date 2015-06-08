package edu.carleton.leight;

import javafx.scene.Scene;
import org.junit.Before;
import org.junit.Test;

import java.security.acl.Group;

import static org.junit.Assert.*;

public class GameManagerTest {
//    @Before
//    public void beginning() throws Exception {
//
//        Main main;
//        GameManager gameManager;
//    }

    @Test
    public void testGetGameScene() throws Exception {
    }

    @Test
    public void testGetProfile() throws Exception {
        GameManager gameManager = new GameManager();
        assertEquals(gameManager.getProfile().getLives(),10);
    }

    @Test
    public void testCreateWaveButton() throws Exception {

    }

    @Test
    public void testGetCurrentGold() throws Exception {

    }

    @Test
    public void testSetIsPlacingTower() throws Exception {

    }

    @Test
    public void testBuildTower() throws Exception {

    }

    @Test
    public void testCreateEnemy() throws Exception {

    }

    @Test
    public void testRemoveEnemyFromGame() throws Exception {

    }

    @Test
    public void testSetUpAnimationTimer() throws Exception {

    }

    @Test
    public void testUpdateAnimation() throws Exception {

    }

    @Test
    public void testSendWave() throws Exception {

    }

    @Test
    public void testUpdateEnemyAnimation() throws Exception {

    }

    @Test
    public void testUpdateAttacks() throws Exception {

    }

    @Test
    public void testUpdateTowerClick() throws Exception {

    }

    @Test
    public void testCreateSellTowerButton() throws Exception {

    }

    @Test
    public void testCreateUpgradeTowerButton() throws Exception {

    }

    @Test
    public void testRemoveButtons() throws Exception {

    }

    @Test
    public void testGetGameScreen() throws Exception {

    }

    @Test
    public void testUpdateCoordinates() throws Exception {

    }

    @Test
    public void testAttackEnemy() throws Exception {

    }

    @Test
    public void testHowDidEnemyDie() throws Exception {

    }

    @Test
    public void testSellTower() throws Exception {

    }
}