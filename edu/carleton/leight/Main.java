package edu.carleton.leight;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
    public final static int stageXSize = 1000;
    public final static int stageYSize = 1000;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        HomeScreenController homeScreenController = new HomeScreenController(this);
        loader.setController(homeScreenController);
        Parent root = (Parent) loader.load();
        //stage set
        this.stage.setTitle("Circle Defend'r");
        this.stage.setScene(new Scene(root, stageXSize, stageYSize));
        this.stage.show();
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

    }

    public void playGame() {
        Parent root;
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        try {
            GameManager gameManager2 = new GameManager();
            loader2.setController(gameManager2);
            loader2.load();
            stage.setScene(gameManager2.getGameScene());
            stage.show();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

