package edu.carleton.leight;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;





public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
//        Parent root = (Parent) loader.load();
//
        AnchorPane root = new AnchorPane();


//        Button btn1 = new Button();
//        btn1.setText("build tower");
//        btn1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });

        Circle c1 = new Circle(10.0);

        double x = 10.0;
        double y = 10.0;


        c1.setCenterX(x + 1);
        c1.setCenterY(y + 1);


        root.getChildren().add(c1);





//        while (x < 100 && y < 100) {
//            btn1.setLayoutX(x + 1);
//            btn1.setLayoutY(y + 1);
//            btn1.setCenterX();
//
//        }
        primaryStage.setTitle("Circle Defend'r");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();



//        primaryStage.setResizable(false);

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