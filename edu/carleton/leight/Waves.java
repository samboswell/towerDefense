package edu.carleton.leight;

/**
 * Simple getter/setter for the waveCount int in GameManager. Used to figure
 * out what wave the game is running.
 *
 * @authors Jonah Tuchow, Tristan Leigh, Sam Boswell
 */
public class Waves {
    private int waveCount;

    public int getWaveCount() {
        return this.waveCount;
    }
    public void setWaveCount(int waveCount) {
        this.waveCount = waveCount;
    }

}
