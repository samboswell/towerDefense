package edu.carleton.leight;


public class GameScreen {

    private int[][] grid;
    private int[] path;
    private String stats;


    public GameScreen(int[][] grid, int[] path, String stats) {
        this.grid = grid;
        this.path = path;
        this.stats = stats;
    }

    public int[][] getGrid() {return this.grid;}
    public int[] getPath() {return this.path;}
    public String displayStats(Tower tower) {return this.stats;}
}
