package edu.carleton.leight;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GameManager extends Application {
    final private double FRAMES_PER_SECOND = 40.0;

    private long startTime;
    private Timer timer;
    private Stage stage;
    private List<Enemy> enemiesAlive;
    private List<Enemy> enemiesFinished;
    private List<Tower> towers;
    private Group root;
    private Profile profile;
    private GameScreen gameScreen;
    private int[][] gameGrid;
    private Scene gameScene;
    private Scene homeScene;


    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
//        Parent parent = (Parent) loader.load();


        this.profile = new Profile(10, 100);
        this.enemiesAlive = new ArrayList<>();
        this.enemiesFinished = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.stage = primaryStage;
        this.root = new Group();
        this.gameScreen = new GameScreen(this.profile, this.root);
        this.gameGrid = getDefaultGameGrid();

        //gameScene created
        createButton();
        this.gameScreen.drawPath(getGameGrid());
        this.gameScene = new Scene(root, 700, 500);

        //homeScreen created (this must be after gameScene is created)
        Group homeRoot = new Group();
        this.homeScene = new Scene(homeRoot, 700, 500);
        HomeScreen homeScreen = new HomeScreen(stage);
        homeScreen.createPlayButton(homeRoot, gameScene);
        homeScreen.createAboutButton(homeRoot);

        //stage set
        this.stage.setTitle("Circle Defend'r");
        this.stage.setScene(homeScene);
        this.stage.show();
        setUpAnimationTimer();


        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

    }

    public Stage getStage() {
        return this.stage;
    }

    public void createButton() {
        //this rectangle is the clickable zone for towers.
        Rectangle clickableRect = new Rectangle(500.0, 500.0);
        clickableRect.setOpacity(0.0); //hide clickable zone
        this.root.getChildren().add(clickableRect);

        Image towerImage = new Image("edu/carleton/leight/TowerImage.png",
                50,50,false,false);
        ImageView towerView = new ImageView(towerImage);
        towerView.setX(625);
        towerView.setY(150);
        this.root.getChildren().add(towerView);
        Button btn = new Button();
        btn.setText("Build Tower");
        btn.setLayoutX(600.0);
        btn.setLayoutY(200.0);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                TODO: get this button to work--https://blog.idrsolutions.com/2014/05/tutorial-change-default-cursor-javafx/
//                Task task = new Task() {
//                    @Override
//                    protected Integer call() throws Exception {
//                        int iterations;
//                        scene.setCursor(Cursor.WAIT); //Change cursor to wait style
//                        for (iterations = 0; iterations &lt; 100000; iterations++) {
//                            System.out.println("Iteration " + iterations);
//                        }
//                        scene.setCursor(Cursor.DEFAULT); //Change cursor to default style
//                        return iterations;
//                    }
//                };
//                Thread th = new Thread(task);
//                th.setDaemon(true);
//                th.start();
                clickableRect.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int column = (int) mouseEvent.getX() / 50;
                        int row = (int) mouseEvent.getY() / 50;

                        //non-final instance variables are not accessible here
                        int[][] gameGrid = getGameGrid();

                        //only build if not on path
                        if (gameGrid[row][column] == 0) {
                            buildTower(mouseEvent.getX(), mouseEvent.getY());
                        }
                    }
                });
                Image towerImage = new Image("edu/carleton/leight/TowerImage.png",
                        50, 50, false, false);
                gameScene.setCursor(new ImageCursor(towerImage));
            }
        });
        this.root.getChildren().add(btn);
    }

    public void buildTower(double rawX, double rawY) {
        //snaps tower to 50 by 50 blocks
        double x =  rawX - rawX % 50;
        double y = rawY - rawY % 50;

        //view
        Image towerImage = new Image("edu/carleton/leight/TowerImage.png",
                50,50,false,false);
        ImageView towerView = new ImageView(towerImage);
        towerView.setX(x);
        towerView.setY(y);
        this.root.getChildren().add(towerView);

        //model
        Tower tower = new Tower(x,y);
        this.towers.add(tower);
        this.profile.setGold(this.profile.getGold() - tower.getCost());

        int blockX = (int) x/50;
        int blockY = (int) y/50;
        this.gameGrid[blockX][blockY] = 2;
    }


    public void createEnemy(int x, int y) {
        //view
        Circle circle = new Circle(x,y,15,Color.RED);
        this.root.getChildren().add(circle);

        //model
        Enemy enemy = new Enemy(x,y,circle);
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
        enemiesFinished.add(enemy);
    }

    public void setUpAnimationTimer() {

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

    public void updateAnimation() {
        attemptEnemyGeneration();
        updateEnemyAnimation();
        updateAttacks();
        gameScreen.drawUpdatedLabel();
    }

    public void attemptEnemyGeneration() {
        final int enemyDelay = 75;

        //get a time delay from start of animation
        long delay = (System.nanoTime() - this.startTime)/10000000;
//        for (int i = 0; i < 20; i++) {
//            //delay enemy creation
//            if (delay > i*enemyDelay) {
//                //we only want to create 1 enemy at a time
        if (enemiesAlive.size()+enemiesFinished.size() <= 20
                && delay%enemyDelay>=0 && delay%enemyDelay<=2) {
            createEnemy(325,550);
        }
//            }
//        }
    }

    public void updateEnemyAnimation() {
        for (Enemy enemy : enemiesAlive) {
            //checks to see if the enemy is finished with the path,
            //then removes the enemy from the list.
            if (enemy.isFinished()) {
                howDidEnemyDie(enemy);
            }
            Circle circle = enemy.getCircle();

            //Sets the path for the enemies to move along. Updates coordinates
            //after the enemy moves.
            if (circle.getCenterY() >= 275) {
                circle.setCenterY(circle.getCenterY() - 2);
            } else if (circle.getCenterY() <= 275 && circle.getCenterX() >= 175) {
                circle.setCenterX(circle.getCenterX() - 2);
            } else if (circle.getCenterY() <= 275 && circle.getCenterX() <= 175) {
                circle.setCenterY(circle.getCenterY() - 2);
            }

            updateCoordinates(enemy, circle.getCenterX(), circle.getCenterY());

        }
    }

    public void updateAttacks() {
        // Iterates through the list of towers to find the enemies in range
        // for each tower. Each tower attacks the first enemy in its list.
        for (Tower tower : towers) {
            List<Enemy> enemiesInRange = tower.getEnemiesInRange(enemiesAlive);
            if (enemiesInRange.size()>0) {
                Enemy enemy = enemiesInRange.get(0);
                attackEnemy(tower, enemy);
                System.out.println(enemiesInRange);
            }
        }
    }

    public void updateCoordinates(Enemy enemy, double x, double y) {
        enemy.setX(x);
        enemy.setY(y);
    }

    public int[][] getDefaultGameGrid() {
        //0 means tower placeable
        //1 means enemy path
        return new int[][]{
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
    }

    public int[][] getGameGrid() {
        return this.gameGrid;
    }

    //Given a tower, finds the list of enemies in range and attacks the closest
    //enemy. If the enemy's health falls below 0, enemy is removed.
    public void attackEnemy(Tower tower, Enemy enemy) {
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

    public void sellTower() {}


    //Iterates through enemies to find an enemy from the list of enemies
    // that is finished, then removes it from the list.
    public void removeEnemyIfFinished() {
        for(Enemy enemy : enemiesAlive) {
            if (enemy.isFinished()) {
                enemiesAlive.remove(enemiesAlive.indexOf(enemy));
                enemiesFinished.add(enemy);
            }
        }
    }

    public void removeEnemyIfDead() {
        for (Enemy enemy : enemiesAlive) {
            if (enemy.getHealth()<= 0) {
                enemiesAlive.remove(enemiesAlive.indexOf(enemy));
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}