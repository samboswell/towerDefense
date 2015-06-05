//package edu.carleton.leight;
//
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.event.EventHandler;
//import javafx.geometry.Point2D;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.stage.WindowEvent;
//
//import java.util.ArrayList;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Adapted from Jeff Ondich's tutorial code for JavaFX shared in class.
// */
//public class SpriteManager extends Application {
//    private ArrayList<Sprite> sprites;
//    private GameManager gameManager;
//    final private double sceneWidth = 700;
//    final private double sceneHeight = 500;
//    final private double framesPerSecond = 20.0;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
////        Parent root = (Parent) loader.load();
//
//        this.gameManager = new GameManager();
//
//        Group root = new Group();
//        Scene scene = new Scene(root, sceneWidth, sceneHeight);
//
//        this.sprites = new ArrayList<Sprite>();
//
//        Sprite tower = new Tower(0,0);
//        tower.setLocation(250.0, 200.0);
//        root.getChildren().add(tower);
//        this.sprites.add(tower);
//
////        Sprite enemy = new Enemy();
//
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//
//    }
//}
