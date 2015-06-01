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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
import java.time.Instant;


public class GameManager extends Application {
    final private double FRAMES_PER_SECOND = 40.0;

    long startTime;
    private Timer timer;
    private Stage primaryStage;
    private List<Enemy> enemies;
    private Group root;
    private Profile profile;

    @FXML private Button play;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
//        Parent root = (Parent) loader.load();
        this.profile = new Profile(10, 20);
        this.enemies = new ArrayList<>();
        this.primaryStage = primaryStage;
        root = new Group();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Circle Defend'r");
        primaryStage.setScene(scene);
        primaryStage.show();
        buildTower();
        createEnemy();
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


    private void buildTower() {
        Rectangle rectangle = new Rectangle(250, 200, 15, 15);
        this.root.getChildren().add(rectangle);
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

    private void createEnemy() {
        Circle circle = new Circle(300,550,15,Color.RED);
        this.root.getChildren().add(circle);
        Enemy enemy = new Enemy(false,100,5,10,300,550,circle);
        this.enemies.add(enemy);
    }

    private void setUpAnimationTimer() {

        this.startTime = System.nanoTime();

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
        final int enemyDelay = 75;

        //get a time delay from start of animation
        long delay = (System.nanoTime() - this.startTime)/10000000;
        for (int i = 1; i < 5; i++) {
            //delay enemy creation
            if (delay > i*enemyDelay && delay < i*enemyDelay + 5) {
                //we only want to create 1 enemy at a time
                if (enemies.size() == i) {
                    createEnemy();
                }
            }
        }


        for (Enemy enemy : enemies) {
            Circle circle = enemy.getCircle();
            if (circle.getCenterY() > 240 ) {
                circle.setCenterY(circle.getCenterY() - 2);
            }
            else if (circle.getCenterY() <= 240 && circle.getCenterX() > 220) {
                circle.setCenterX(circle.getCenterX() - 2);
            }
            else if (circle.getCenterY() <= 240 && circle.getCenterX() <= 220 ) {
                circle.setCenterY(circle.getCenterY() - 2);
            }
            updateCoordinates(enemy, circle.getCenterX(), circle.getCenterY());
        }
    }

    public void updateCoordinates(Enemy enemy, double x, double y) {
        enemy.setX(x);
        enemy.setY(y);
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

    public void upgrade(Tower tower) {}
    public void attackEnemy(Tower tower, Enemy enemy) {
        enemy.setHealth(tower.getDamage());
    }

//    public void attackEnemies() {
//        List<Tower> userTowers = this.profile.getUserTowers();
//        for (Tower tower : userTowers) {
//            List<Enemy> enemiesInRange = getEnemiesInRange();
//            for (Enemy enemy : enemiesInRange) {
//                attackEnemy(tower, enemy);
//            }
//        }
//    }

    public void deadEnemy(Enemy enemy) {}
    public void sellTower() {}


    //Iterates through enemies to find an enemy from the list of enemies
    // that is finished, then removes it from the list.
    public void removeEnemyIfFinished() {
        for(Enemy enemy : enemies) {
            if (enemy.isFinished()) {
                enemies.remove(enemies.indexOf(enemy));
            }
        }
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public static void main(String[] args) {
        launch(args);
    }
}