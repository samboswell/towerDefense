package edu.carleton.leight;


import java.util.ArrayList;
import java.util.List;

public class Wave {
    private List<Enemy> enemies;
    private List<Integer> times;
    /**
     * @param enemies: the list of enemies
     * @param times: the list of times
     */
    public Wave(ArrayList<Enemy> enemies, ArrayList<Integer> times)
    {
        this.enemies = enemies;
        this.times = times;
    }

    /**
     * @return: the time to add the next enemy
     * 			If there are no more enemies return -1
     */
    public int getNextTime(){
        if(times.size() <= 0){
            return -1;
        }
        return times.get(0);
    }

    /**
     * adds the next enemy to the Board
     * @return true if an enemy was successfully added
     */
    public boolean addNextEnemy(){
        if(enemies.size()<=0){
            return false;
        }
        //removes both the enemy and the corresponding time from the lists
        GameManager gameManager = new GameManager();
        gameManager.removeEnemyFromGame(enemies.remove(0));
        times.remove(0);
        return true;
    }
}
