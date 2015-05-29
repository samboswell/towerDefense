package edu.carleton.leight;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        Parent root = (Parent)loader.load();
        //I tried adding a button to root afterwards, and it didn't work.
        //We might need to do that in scene-builder

        GameScreen gameScreen = createDefaultGameScreen();
        //

        primaryStage.setTitle("Circle Defend'r");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    public GameScreen createDefaultGameScreen() {
        int[][] grid = new int[][]{
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
        };

        int[] path = new int[]{1,1,1,1,1,1,1};
        GameScreen gameScreen = new GameScreen(grid,path,"hi");
        return gameScreen;
    }

    public static void main(String[] args) {
        launch(args);
    }
}