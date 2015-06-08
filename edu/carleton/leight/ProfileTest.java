package edu.carleton.leight;

/**
 * Testing file for the Profile model class. Only basic tests apply, because
 * testing situations have been cleared in the GameManager with controller
 * processes and cannot be unit tested.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ProfileTest {

    /**
     * Tests getLives(). Negative lives are dealt with in updateAnimation in
     * GameManager.
     */
    @Test
    public void testGetLives() throws Exception {
        Profile profile = new Profile(10, 100);
        assertEquals(10, profile.getLives());
    }

    /**
     * Tests setLives(). Negative lives are dealt with in updateAnimation in
     * GameManager.
     */
    @Test
    public void testSetLives() throws Exception {
        Profile profile = new Profile(10, 100);
        profile.setLives(1);
        assertEquals(1, profile.getLives());
    }

    /**
     * Tests getGold(). Negative gold is dealt with in building/upgrading towers
     * in GameManager.
     */
    @Test
    public void testGetGold() throws Exception {
        Profile profile = new Profile(10, 100);
        assertEquals(100, profile.getGold());
    }

    /**
     * tests getStats().
     */
    @Test
    public void testGetStats() throws Exception {
        Profile profile = new Profile(10, 100);
        assertEquals("Lives: 10\n" +
                "High Score: 0\n" +
                "Gold: 100", profile.getStats());
    }

    /**
     * Tests setGold(). Negative gold is dealt with in building/upgrading towers
     * in GameManager.
     */
    @Test
    public void testSetGold() throws Exception {
        Profile profile = new Profile(10, 100);
        profile.setGold(100000);
        assertEquals(100000, profile.getGold());
    }

    /**
     * tests getHighScore()
     */
    @Test
    public void testGetHighScore() throws Exception {
        Profile profile = new Profile(10, 100);
        assertEquals(0, profile.getHighScore());
    }

    /**
     * tests setHighScore(). Since highScore has no impact on the rest of the
     * game, it can be set to negative values, but never will.
     */
    @Test
    public void testSetHighScore() throws Exception {
        Profile profile = new Profile(10, 100);
        profile.setHighScore(1000);
        assertEquals(1000, profile.getHighScore());
    }
}