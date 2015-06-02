package edu.carleton.leight;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GameManager extends Application {
    final private double FRAMES_PER_SECOND = 40.0;

    long startTime;
    private Timer timer;
    private Stage primaryStage;
    private List<Enemy> enemiesAlive;
    private List<Tower> towers;
    private Group root;
    private Profile profile;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Circle Defend'r");
        primaryStage.setScene(loadHomeScene());
        primaryStage.show();



        this.profile = new Profile(10, 20);
        this.enemiesAlive = new ArrayList();
        this.towers = new ArrayList();
        this.root = new Group();

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
        this.root.getChildren().add(rect);
        rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int column = (int) mouseEvent.getX() / 50;
                int row = (int) mouseEvent.getY() / 50;
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

    }
    public Scene loadHomeScene() {
        Scene homeScene;
        Parent root = null;

        try {
            FXMLLoader tmp = new FXMLLoader(getClass().getResource("fxml/home.fxml"));
            root = tmp.load();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        homeScene = new Scene(root, 700, 500);
        return homeScene;
    }

    public void onPlayButton(ActionEvent event) {
        loadGameScene();
    }

    public Scene loadGameScene() {
        Scene gameScene;
        Parent root = null;

        try {
            FXMLLoader tmp = new FXMLLoader(getClass().getResource("fxml/GameScreen.fxml"));
            root = tmp.load();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        gameScene = new Scene(root, 700, 500);
        return gameScene;
    }


    private void buildTower(double xCoordinate, double yCoordinate) {
        double x = xCoordinate - xCoordinate % 50;
        double y = yCoordinate - yCoordinate % 50;

        //view
        Image towerImage = new Image("edu/carleton/leight/images/TowerImage.png",
                50,50,false,false);
        ImageView towerView = new ImageView(towerImage);
        towerView.setX(x - 25);
        towerView.setY(y - 25);
        this.root.getChildren().add(towerView);

        //model
        Tower tower = new Tower(x,y);
        this.towers.add(tower);
        // ##################### mark location on gamescreen
    }


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

    public void removeEnemyFromGame(Enemy enemy) {
        //view
        root.getChildren().remove(enemy.getCircle());

        //model
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
        final int towerDelay = 100;

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

        for (Enemy enemy : enemiesAlive) {
            //checks to see if the enemy is finished with the path,
            //then removes the enemy from the list.
            if (enemy.isFinished()) {
                howDidEnemyDie(enemy);
            }
            Circle circle = enemy.getCircle();

            //Sets the path for the enemies to move along. Updates coordinates
            //after the enemy moves.
            if (circle.getCenterY() >= 250) {
                circle.setCenterY(circle.getCenterY() - 2);
            } else if (circle.getCenterY() <= 250 && circle.getCenterX() >= 150) {
                circle.setCenterX(circle.getCenterX() - 2);
            } else if (circle.getCenterY() <= 250 && circle.getCenterX() <= 150) {
                circle.setCenterY(circle.getCenterY() - 2);
            }

            updateCoordinates(enemy, circle.getCenterX(), circle.getCenterY());

        }

        // Iterates through the list of towers to find the enemies in range
        // for each tower. Each tower attacks the first enemy in its list.
        for (Tower tower : towers) {
            List<Enemy> enemiesInRange = tower.getEnemiesInRange(enemiesAlive);
            if (enemiesInRange.size()>0) {
            attackEnemy(tower);
            System.out.println(enemiesInRange);
            }
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
    //Given a tower, finds the list of enemies in range and attacks the closest
    //enemy. If the enemy's health falls below 0, enemy is removed.
    public void attackEnemy(Tower tower) {
        List<Enemy> enemiesInRange = tower.getEnemiesInRange(enemiesAlive);
        Enemy attackedEnemy = enemiesInRange.get(0);
        attackedEnemy.setHealth(attackedEnemy.getHealth(),tower.getDamage());
        if(attackedEnemy.getHealth()<=0) {
            howDidEnemyDie(attackedEnemy);
        }

    }
    // Given an enemy, decides whether the enemy has finished the path
    // or if the user has killed the enemy. Punishes and rewards respectively.
    // Then removes the enemy from the game.
    public void howDidEnemyDie(Enemy enemy) {
        // Enemy finished the path before getting killed
        if (enemy.isFinished()) {
            profile.setLives((profile.getLives() - 1));
        }
        // Enemy is killed, gold and points rewarded
        else {
            profile.setGold(profile.getGold() + enemy.getGold());
            profile.setHighScore(profile.getHighScore() + enemy.getValue());
        }
        removeEnemyFromGame(enemy);
    }

//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
}