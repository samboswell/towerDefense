package edu.carleton.leight;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.Group;
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
    private GameScreen gameScreen;

    @Override
    public void start(Stage primaryStage) throws Exception{
    //    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
    //    Parent root = (Parent) loader.load();
        this.profile = new Profile(10, 20);
        this.enemiesAlive = new ArrayList<Enemy>();
        this.towers = new ArrayList<Tower>();
        this.primaryStage = primaryStage;
        this.root = new Group();

        this.gameScreen = new GameScreen( getDefaultGameGrid(),
                getStats(), this.profile, this.root);
        this.gameScreen.drawPath();

        Scene scene = new Scene(root, 700, 500);
        this.primaryStage.setTitle("Circle Defend'r");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        setUpAnimationTimer();


        this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });


        //note: this rect should be created after everything
        Rectangle rect = new Rectangle(600.0, 500.0);
        rect.setOpacity(0.0); //hide clickable box
        this.root.getChildren().add(rect);

        boolean placeable  = true;
        if (placeable) {
            rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int column = (int) mouseEvent.getX() / 50;
                    int row = (int) mouseEvent.getY() / 50;
                    //######################
                    //NOTE: WE CAN'T ACCESS INSTANCE VARIABLES HERE
                    //so towers can't be marked on gameBoard when built and
                    //we can't set placeable to false
                    int[][] gameGrid = getDefaultGameGrid();

                    //only build if not on path
                    if (gameGrid[row][column] == 0) {
                        buildTower(mouseEvent.getX(), mouseEvent.getY());
                    }
                }
            });
        }
    }


    private void buildTower(double rawX, double rawY) {
        //snaps tower to 50 by 50 blocks
        double x = rawX - rawX % 50;
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
        // ##################### mark location on gameGrid
    }


    private void createEnemy() {
        //view
        Circle circle = new Circle(325,550,15,Color.RED);
        this.root.getChildren().add(circle);

        //model
        Enemy enemy = new Enemy(325,550,circle);
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

//        if (enemiesAlive.size() == 5) {
//            removeEnemy(enemiesAlive.get(enemiesAlive.size()-1));
//        }

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
            gameScreen.updateLabel();

        }

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

    public String getStats() {
        String newStats = "Lives: " + this.profile.getLives() +"\n";
        newStats += "High Score: " + this.profile.getLives() +"\n";
        newStats += "Gold: " + this.profile.getLives();
        return newStats;
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