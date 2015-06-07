package edu.carleton.leight;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GameManager {
    final private double FRAMES_PER_SECOND = 40.0;
    private Stage stage;
    private long startTime;
    private Timer timer;
    private List<Enemy> enemiesAlive;
    private List<Enemy> enemiesFinished;
    private List<Tower> towers;
    private Group root;
    private Profile profile;
    private GameScreen gameScreen;
    private int[][] gameGrid;
    private Scene gameScene;
    private Scene homeScene;
    private boolean isPlacingTower;


    public GameManager() {
        this.profile = new Profile(10, 100);
        this.enemiesAlive = new ArrayList<>();
        this.enemiesFinished = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.root = new Group();
        this.gameScreen = new GameScreen(this.profile, this.root);
        this.gameGrid = getDefaultGameGrid();
        this.isPlacingTower = false;

        //gameScene created
        gameScreen.createButton();
        createTowerButton();
        this.gameScreen.drawPath(getGameGrid());
        this.gameScene = new Scene(root, 700, 500);
    }

    public void initialize() {
        setUpAnimationTimer();
    }

    public Scene getGameScene() {
        return this.gameScene;
    }
    public void createTowerButton() {
        //this rectangle is the clickable zone for towers.
        Rectangle clickableRect = new Rectangle(500.0, 500.0);
        clickableRect.setOpacity(0.0); //hide clickable zone
        this.root.getChildren().add(clickableRect);
        Button btn = new Button();
        btn.setText("Build Tower");
        btn.setLayoutX(600.0);
        btn.setLayoutY(200.0);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setIsPlacingTower(true);
                if (getIsPlacingTower()) {
                    clickableRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            int column = (int) mouseEvent.getX() / 50;
                            int row = (int) mouseEvent.getY() / 50;

                            //non-final instance variables are not accessible here
                            int[][] gameGrid = getGameGrid();

                            //only build if you have the gold
                            if (getCurrentGold() >= 50) {
                                //only build if not on path
                                if (gameGrid[row][column] == 0) {
                                    buildTower(mouseEvent.getX(), mouseEvent.getY());
                                }
                            }
                            getGameScene().setCursor(Cursor.DEFAULT);
                            setIsPlacingTower(false);
                        }
                    });
                }
                else {
                    clickableRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            System.out.println("yay");
                        }
                    });
                }
                Image towerImage = new Image("edu/carleton/leight/TowerImage.png",
                        50, 50, false, false);
                getGameScene().setCursor(new ImageCursor(towerImage));
            }
        });
        this.root.getChildren().add(btn);
    }

    public boolean getIsPlacingTower() {
        return this.isPlacingTower;
    }

    public void setIsPlacingTower(boolean isPlacingTower) {
        this.isPlacingTower = isPlacingTower;
    }


    public int getCurrentGold() {
        return this.profile.getGold();
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

    }


    public void createEnemy(String name, int x, int y) {
        if(name.equals("Red Enemy")) {
            //view
            Circle circle = new Circle(x, y, 15, Color.RED);

            //model
            Enemy enemy = new RedEnemy(x, y);
            this.enemiesAlive.add(enemy);
        }
        if(name.equals("Blue Enemy")) {
            //view
            Circle circle = new Circle(x, y, 15, Color.BLUE);

            //model
            Enemy enemy = new BlueEnemy(x, y);
            this.enemiesAlive.add(enemy);
        }
        if(name.equals("Yellow Enemy")) {
            //view
            Circle circle = new Circle(x, y, 15, Color.YELLOW);

            //model
            Enemy enemy = new YellowEnemy(x, y);
            this.enemiesAlive.add(enemy);
        }
        if(name.equals("Boss Enemy")) {
            //view
            Circle circle = new Circle(x, y, 15, Color.DARKGOLDENROD);

            //model
            Enemy enemy = new BossEnemy(x, y);
            this.enemiesAlive.add(enemy);
        }

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

        //get a time delay from start of animation
        long delay = (System.nanoTime() - this.startTime)/10000000;

        if (delay>5) {
            sendWave1(delay);
        }
        if (delay>1500) {
            sendWave2(delay);
        }
        if (delay > 3200) {
            sendWave3(delay);
        }
        if (delay > 5000) {
            sendWave4(delay);
        }
        updateEnemyAnimation();
        updateAttacks();

        gameScreen.drawUpdatedLabel();
        if (this.profile.getLives() <= 0) {
            this.stage.setScene(homeScene);
        }
    }


    public void sendWave1(long delay) {
        final int enemyDelay = 75;

        if (enemiesAlive.size() + enemiesFinished.size() >= 0 &&
                enemiesAlive.size() + enemiesFinished.size() <= 10
                && delay % enemyDelay >= 0 && delay % enemyDelay <= 2) {
            createEnemy("Red Enemy", 325, 550);
        }
    }

    public void sendWave2(long delay) {

        final int enemyDelay = 70;

        if (enemiesAlive.size() + enemiesFinished.size() >= 10 &&
                enemiesAlive.size() + enemiesFinished.size() <= 29
                && delay % enemyDelay >= 0 && delay % enemyDelay <= 2) {
            createEnemy("Yellow Enemy", 325, 550);
        }
    }

    public void sendWave3(long delay) {
        final int enemyDelay = 65;

        if (enemiesAlive.size() + enemiesFinished.size() >= 30 &&
                enemiesAlive.size() + enemiesFinished.size() <= 49
                && delay % enemyDelay >= 0 && delay % enemyDelay <= 2) {
            createEnemy("Blue Enemy", 325, 550);
        }
    }

    public void sendWave4(long delay) {
        final int enemyDelay = 60;
        if (enemiesAlive.size() + enemiesFinished.size() >= 49 &&
                enemiesAlive.size() + enemiesFinished.size() <= 50
                && delay % enemyDelay >= 0 && delay % enemyDelay <= 2) {
            createEnemy("Boss Enemy", 325, 550);
        }
    }

    public void updateEnemyAnimation() {
        for (Enemy enemy : enemiesAlive) {
            //checks to see if the enemy is finished with the path,
            //then removes the enemy from the list.
            if (enemy.isFinished()) {
                this.enemiesAlive.remove(enemy);
                this.enemiesFinished.add(enemy);
                this.profile.setLives(this.profile.getLives() - 1);
                System.out.println(this.root.getChildren().size());
                this.root.getChildren().remove(enemy.getCircle());
                System.out.println("2: " +this.root.getChildren().size());
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

            this.root.getChildren().remove(circle);
            updateCoordinates(enemy, circle.getCenterX(), circle.getCenterY());
            this.root.getChildren().add(circle);
        }
    }

    public void updateAttacks() {
        final long attackDelay = 50;
        long delay = (System.nanoTime() - this.startTime)/10000000;

        if (delay%attackDelay>=0 && delay%attackDelay<=5) {
            // Iterates through the list of towers to find the enemies in range
            // for each tower. Each tower attacks the first enemy in its list.
            for (Tower tower : towers) {
                List<Enemy> enemiesInRange = tower.getEnemiesInRange(enemiesAlive);
                if (enemiesInRange.size() > 0) {
                    Enemy enemy = enemiesInRange.get(0);
                    attackEnemy(tower, enemy);
                }
            }
        }
    }


    public void updateCoordinates(Enemy enemy, double x, double y) {
        enemy.setX(x);
        enemy.setY(y);
        enemy.setCircleX(x);
        enemy.setCircleY(y);
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
        attackedEnemy.setHealth(attackedEnemy.getHealth(), tower.getDamage());
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
            enemiesFinished.add(enemy);
        }
        // Enemy is killed, gold and points rewarded
        else {
            profile.setGold(profile.getGold() + enemy.getGold());
            profile.setHighScore(profile.getHighScore() + enemy.getValue());
        }
        removeEnemyFromGame(enemy);
    }

    public void sellTower() {}


//    //Iterates through enemies to find an enemy from the list of enemies
//    // that is finished, then removes it from the list.
//    public void removeEnemyIfFinished() {
//        for(Enemy enemy : enemiesAlive) {
//            if (enemy.isFinished()) {
//                enemiesAlive.remove(enemiesAlive.indexOf(enemy));
//                enemiesFinished.add(enemy);
//            }
//        }
//    }
//
//    public void removeEnemyIfDead() {
//        for (Enemy enemy : enemiesAlive) {
//            if (enemy.getHealth()<= 0) {
//                enemiesAlive.remove(enemiesAlive.indexOf(enemy));
//            }
//        }
//    }


}