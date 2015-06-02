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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
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
    private List<Enemy> enemiesAlive;
    private List<Tower> towers;
    private Group root;
    private Profile profile;
    private GameScreen gameScreen;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
//        Parent root = (Parent) loader.load();
        this.profile = new Profile(10, 20);
        this.enemiesAlive = new ArrayList();
        this.towers = new ArrayList();
        this.gameScreen = createDefaultGameScreen();
        this.primaryStage = primaryStage;
        this.root = new Group();

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Circle Defend'r");
        primaryStage.setScene(scene);
        primaryStage.show();
        setUpAnimationTimer();


        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        Rectangle rect = new Rectangle(600.0,500.0);
        rect.setOpacity(0.0); //hide clickable box
        root.getChildren().add(rect);
        rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int column = (int) mouseEvent.getX()/50;
                int row = (int) mouseEvent.getY()/50;
                //######################
                //NOTE: WE CAN'T ACCESS INSTANCE VARIABLES HERE
                //so tower's can't be marked when built
                GameScreen gameScreen = createDefaultGameScreen();

                //only build if not on path
                if (gameScreen.getGrid()[row][column] != 1) {
                    buildTower(mouseEvent.getX(), mouseEvent.getY());
                }
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


    private void buildTower(double xCoordinate, double yCoordinate) {
        double x = xCoordinate - xCoordinate % 50;
        double y = yCoordinate - yCoordinate % 50;

        //view
        Image towerImage = new Image("edu/carleton/leight/TowerImage.png",
                50,50,false,false);
        ImageView towerView = new ImageView(towerImage);
        towerView.setX(x-25);
        towerView.setY(y-25);
        this.root.getChildren().add(towerView);

        //model
        Tower tower = new Tower(x,y);
        this.towers.add(tower);
        // ##################### mark location on gamescreen
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
        //view
        Circle circle = new Circle(300,550,15,Color.RED);
        this.root.getChildren().add(circle);

        //model
        Enemy enemy = new Enemy(300,550,circle);
        this.enemiesAlive.add(enemy);
    }

    public List<Enemy> getAliveEnemies() {
        return this.enemiesAlive;
    }

    public void addEnemy(Enemy enemy) {
        enemiesAlive.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemiesAlive.remove(enemy);
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
        for (int i = 0; i < 5; i++) {
            //delay enemy creation
            if (delay > i*enemyDelay && delay < i*enemyDelay + 15) {
                //we only want to create 1 enemy at a time
                if (enemiesAlive.size() == i) {
                    createEnemy();
                }
            }
        }
        // remove later
        if (enemiesAlive.size() == 5) {
            removeEnemy(enemiesAlive.get(4));
        }

        for (Enemy enemy : enemiesAlive) {
            Circle circle = enemy.getCircle();

            //set path
            if (circle.getCenterY() >= 250 ) {
                circle.setCenterY(circle.getCenterY() - 2);
            }
            else if (circle.getCenterY() <= 250 && circle.getCenterX() >= 150) {
                circle.setCenterX(circle.getCenterX() - 2);
            }
            else if (circle.getCenterY() <= 250 && circle.getCenterX() <= 150) {
                circle.setCenterY(circle.getCenterY() - 2);
            }

            updateCoordinates(enemy, circle.getCenterX(), circle.getCenterY());
        }

    }

    public void updateCoordinates(Enemy enemy, double x, double y) {
        enemy.setX(x);
        enemy.setY(y);
    }


    public GameScreen createDefaultGameScreen() {
        //1 means the square is used already
        int[][] grid = new int[][]{
                {0,0,0,1,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0},
                {0,0,0,1,1,1,1,0,0,0},
                {0,0,0,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,1,0,0,0}
        };

        int[] path = new int[]{1,1,1,1,1,1,1};
        GameScreen gameScreen = new GameScreen(grid,path,"hi");
        return gameScreen;
    }

    public void upgrade(Tower tower) {}


    public void deadEnemy(Enemy enemy) {
        // Enemy finished the path before getting killed
        if (enemy.isFinished()) {
            profile.setLives((profile.getLives() - 1));
        }
        // Enemy is killed, gold and points rewarded
        else {
            profile.setGold(profile.getGold() + enemy.getGold());
            profile.setHighScore(profile.getHighScore() + enemy.getValue());
        }
        //removeEnemy(enemy);
        root.getChildren().remove(enemy);
    }
    public void sellTower() {}


    //Iterates through enemies to find an enemy from the list of enemies
    // that is finished, then removes it from the list.
    public void removeEnemyIfFinished() {
        for(Enemy enemy : enemiesAlive) {
            if (enemy.isFinished()) {
                enemiesAlive.remove(enemiesAlive.indexOf(enemy));
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}