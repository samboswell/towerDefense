package edu.carleton.leight;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
