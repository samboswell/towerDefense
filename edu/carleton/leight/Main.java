package edu.carleton.leight;


import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    private GameManager gameManager;
    private Stage gameWindow;

    @Override
    public void start(Stage gameWindow) throws Exception {

        this.gameWindow = gameWindow;
        gameManager = new GameManager();
        gameManager.start(gameWindow);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
