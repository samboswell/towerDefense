package edu.carleton.leight;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;


public class Main extends Application {
    final private double FRAMES_PER_SECOND = 60.0;

    private Timer timer;
    private GameManager gameManager;
    private Stage primaryStage;
    private List<Circle> enemies;

    @FXML private Button play;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
//        Parent root = (Parent) loader.load();

        this.gameManager = new GameManager();
        this.primaryStage = primaryStage;
        Group root = new Group();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Circle Defend'r");
        primaryStage.setScene(scene);
        primaryStage.show();
        buildTower(root);
        createEnemies(root);
//        animateEnemies(root);


        setUpAnimationTimer();


        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });


//        this.play.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
    }


    private void buildTower(Group group) {
        Rectangle rectangle = new Rectangle(250, 200, 15, 15);
        group.getChildren().add(rectangle);
    }

//    private void animateEnemies(Group group) {
//        for (int delay = 0; delay < 5; delay++ ) {
//
//            enemies = new ArrayList<>();
//            Circle circle = new Circle(300,550,15,Color.RED);
//            enemies.add(circle);
//            group.getChildren().add(circle);
//            this.gameManager.addEnemy(new Enemy(false,100,5,10,0,0,Color.RED));
//
//            Path path = getEnemyPath();
//            group.getChildren().add(path);
//            PathTransition transition = getPathTransition(circle, path, delay);
//            transition.play();
//        }
//    }


//    //thanks to the following guide on how to use path transitions:
//    // http://www.javaworld.com/article/2074529/core-java/javafx-2-animation--path-transitions.html
//    private PathTransition getPathTransition(Shape shape, Path path, int delay) {
//        PathTransition pathTransition = new PathTransition();
//        pathTransition.setNode(shape);
//        pathTransition.setPath(path);
//        pathTransition.setDuration(Duration.seconds(10.0));
//        pathTransition.setDelay(Duration.seconds(delay));
////        pathTransition.setCycleCount(Timeline.INDEFINITE);
//        return pathTransition;
//    }
//
//    private Path getEnemyPath() {
//        Path path = new Path();
//        path.getElements().add(new MoveTo(300, 550));
//        path.getElements().add(new LineTo(300, 240));
//        path.getElements().add(new LineTo(220, 240));
//        path.getElements().add(new LineTo(220, -50));
//        path.setOpacity(0.0); // comment this out if you want to show the path
//        return path;
//    }

    private void createEnemies(Group group) {
        enemies = new ArrayList<>();
        Circle circle = new Circle(300,550,15,Color.RED);
        enemies.add(circle);
        group.getChildren().add(circle);
        this.gameManager.addEnemy(new Enemy(false,100,5,10,0,0,Color.RED));
    }

    private void setUpAnimationTimer() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        updateAnimation();
                    }
                });
            }
        };

        final long startTimeInMilliseconds = 0;
        final long repetitionPeriodInMilliseconds = 100;
        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer = new java.util.Timer();
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    private void updateAnimation() {
        for (Circle circle : enemies) {
            if (circle.getCenterY() > 240 ) {
                circle.setCenterY(circle.getCenterY() - 2);
            }
            else if (circle.getCenterY() <= 240 && circle.getCenterX() > 220) {
                circle.setCenterX(circle.getCenterX() - 2);
            }
            else if (circle.getCenterY() <= 240 && circle.getCenterX() <= 220 ) {
                circle.setCenterY(circle.getCenterY() - 2);
            }
        }
    }


//    public GameScreen createDefaultGameScreen() {
//        int[][] grid = new int[][]{
//                {0,0,0,1,0,0,0},
//                {0,0,0,1,0,0,0},
//                {0,0,0,1,0,0,0},
//                {0,0,0,1,0,0,0},
//                {0,0,0,1,0,0,0},
//                {0,0,0,1,0,0,0},
//                {0,0,0,1,0,0,0},
//        };
//
//        int[] path = new int[]{1,1,1,1,1,1,1};
//        GameScreen gameScreen = new GameScreen(grid,path,"hi");
//        return gameScreen;
//    }


    public static void main(String[] args) {
        launch(args);
    }
}