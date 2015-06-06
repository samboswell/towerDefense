package edu.carleton.leight;


import java.util.ArrayList;

public class WaveManager {
    private static WaveManager waveManager;
    private Wave[] waves;
    private int currentWave;
    private int tick;
    private boolean isPlaying;
    private boolean gameOver;

    public WaveManager() {
        tick = 0;
        currentWave = 0;
        isPlaying = false;
        waves = new Wave[4];
        gameOver = false;
        createWaves();
    }

    public int getCurrentWave() {
        return currentWave + 1;
    }

    public Wave getWave() {
        return waves[currentWave];
    }

    public static WaveManager getWaveManager() {
        if(waveManager == null) {
            waveManager = new WaveManager();
        }
        return waveManager;
    }

    public void update() {
        if(isPlaying && currentWave<waves.length) {
            if(waves[currentWave].getNextTime()== -1){
                isPlaying = false;
                tick = 0;
                currentWave++;

                if(currentWave == waves.length) {
                    gameOver = true;
                    currentWave --;
                }
            }
            else if(waves[currentWave].getNextTime() == tick) {
                waves[currentWave].addNextEnemy();
            }
            tick++;
        }

        else {
            tick = 0;
        }
    }

    public boolean moreWaves() {
        return !gameOver;
    }

    public void startWave() {
        if(!isPlaying) {
            isPlaying = true;

        }
    }
    //Tells the rest of the game what enemies are in the waves.
    public void createWaves() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        ArrayList<Integer> times = new ArrayList<Integer>();

        for(int i=0; i < 5; i++){
            enemies.add(new RedEnemy(325, 550));
        }
        for(int i=0; i < 5; i++) {
            times.add(new Integer(i*50));
        }

        waves[0] = new Wave(enemies, times);

        enemies = new ArrayList<Enemy>();
        times = new ArrayList<Integer>();

        for(int i=0; i < 5; i++){
            enemies.add(new RedEnemy(325, 550));
        }
        for (int i=0; i < 10; i++) {
            enemies.add(new BlueEnemy(325, 550));
        }
        for(int i=0; i < 15; i++) {
            times.add(new Integer(i*50));
        }

        waves[1] = new Wave(enemies, times);
    }



}
