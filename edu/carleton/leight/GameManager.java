package edu.carleton.leight;
/**
 * A class for the backbone of the game. Creates the buttons on the screen, tells
 * waves to go and what enemies go in the waves, and also deals with
 * tower/enemy interaction. Animates projectiles for an added benefit.
 *
 * @authors Jonah Tuchow, Tristan Leigh, Sam Boswell
 */
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GameManager {
    final public static double FRAMES_PER_SECOND = 37.0;
    final public static int BLOCK_SIZE = 50;
    final public static int GRID_SIZE = 15*BLOCK_SIZE;

    private long startTime;
    private Timer timer;
    private List<Enemy> enemiesAlive;
    private List<Enemy> enemiesFinished;
    private List<Tower> towers;
    private Group root;
    private Profile profile;
    private Waves wave;
    private GameScreen gameScreen;
    private Scene gameScene;
    private boolean isPlacingTower;
    private Button sellBtn;
    private Button upgradeBtn;
    private Main main;

    public int enemyCount;


    public GameManager(Main main) {
        this.profile = new Profile(10, 100);
        this.enemiesAlive = new ArrayList<>();
        this.enemiesFinished = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.wave = new Waves();
        this.root = new Group();
        this.gameScreen = new GameScreen(this.profile, this.root);
        this.main = main;

        this.enemyCount = 0;

        //draw background of gameScreen
        this.gameScreen.drawPath(gameScreen.getGameGrid());
        this.gameScreen.drawSideBar();
        this.gameScreen.drawTowerButtonImage();
        //the build tower button must be in GameManager, because its action
        //depends on the current state of the game
        createTowerButton();
        createWaveButton();

        this.gameScene = new Scene(root, GRID_SIZE+150, GRID_SIZE);

    }

    public void initialize() {
        setUpAnimationTimer();
    }

    public Scene getGameScene() {
        return this.gameScene;
    }

    public Profile getProfile() {
        return this.profile;
    }
    public void createTowerButton() {
        //This rectangle is the clickable zone for towers.
        Rectangle clickableRect = new Rectangle(GRID_SIZE, GRID_SIZE);
        clickableRect.setOpacity(0.0); //hide clickable zone
        this.root.getChildren().add(clickableRect);
        ToggleButton btn = new ToggleButton();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //note: non-final instance variables are not accessible here,
                //so we need to use methods
                setIsPlacingTower(true);

                //set cursor to towerImage when placeable
                Image towerImage =
                        new Image("edu/carleton/leight/TowerImage.png",
                                BLOCK_SIZE, BLOCK_SIZE, false, false);
                getGameScene().setCursor(new ImageCursor(towerImage));
                if (btn.isSelected()) {
                    clickableRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if (getIsPlacingTower()) {
                                //this conditional will only be the case if a tower has
                                //not yet been built after clicking the build button
                                int column = (int) mouseEvent.getX() / BLOCK_SIZE;
                                int row = (int) mouseEvent.getY() / BLOCK_SIZE;
                                int[][] gameGrid = gameScreen.getDefaultGameGrid();

                                //only build if you have the gold
                                if (getCurrentGold() >= 50) {
                                    //only build if not on path
                                    if (gameGrid[row][column] == 0) {
                                        buildTower(mouseEvent.getX(),
                                                mouseEvent.getY());
                                    }
                                }
                                getGameScene().setCursor(Cursor.DEFAULT);
                                btn.setSelected(false);
                                setIsPlacingTower(false);
                            }
                        }
                    });
                }
                else {
                    getGameScene().setCursor(Cursor.DEFAULT);
                    setIsPlacingTower(false);
                    clickableRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                        }
                    });
                }
            }
        });

        //view
        btn.setText("Build Tower");
        btn.setLayoutX(GRID_SIZE+35);
        btn.setLayoutY(200.0);
        this.root.getChildren().add(btn);
    }

    public void createWaveButton() {
        Button waveBtn = new Button("Start Wave");
        waveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (wave.getWaveCount() == 0) {
                    wave.setWaveCount(1);
                }
                if (wave.getWaveCount() == 1 && enemyCount > 9) {
                    wave.setWaveCount(2);
                }
                if (wave.getWaveCount() == 2 && enemyCount > 29) {
                    wave.setWaveCount(3);
                }
                if (wave.getWaveCount() == 3 && enemyCount > 59) {
                    wave.setWaveCount(4);
                }
                if (wave.getWaveCount() == 4 && enemyCount > 99) {
                    wave.setWaveCount(5);
                }
                if (wave.getWaveCount() == 5 && enemyCount > 124) {
                    wave.setWaveCount(6);
                }
                if (wave.getWaveCount() == 6 && enemyCount > 149) {
                    wave.setWaveCount(7);
                }
            }
        });

        //view
        waveBtn.setLayoutX(GRID_SIZE+35);
        waveBtn.setLayoutY(340);
        this.root.getChildren().add(waveBtn);
    }

    public boolean getIsPlacingTower() {
        return this.isPlacingTower;
    }

    public int getCurrentGold() {
        return this.profile.getGold();
    }

    public void setIsPlacingTower(boolean isPlacingTower) {
        this.isPlacingTower = isPlacingTower;
    }
    public void buildTower(double rawX, double rawY) {
        //snaps tower to 50 by 50 blocks
        double x =  rawX - rawX % BLOCK_SIZE;
        double y = rawY - rawY % BLOCK_SIZE;


        //model
        Image towerImage = new Image("edu/carleton/leight/TowerImage.png",
                BLOCK_SIZE,BLOCK_SIZE,false,false);
        ImageView towerView = new ImageView(towerImage);
        Tower tower = new Tower(x,y,towerView);
        this.towers.add(tower);
        this.profile.setGold(this.profile.getGold() - tower.getCost());

        //view
        gameScreen.drawTower(tower, x,y);
    }


    public void createEnemy(String name, int x, int y) {

        if(name.equals("Red Enemy")) {
            //model
            Enemy enemy = new RedEnemy(x, y);
            this.enemiesAlive.add(enemy);
            enemyCount ++;
        }
        if(name.equals("Blue Enemy")) {
            //model
            Enemy enemy = new BlueEnemy(x, y);
            this.enemiesAlive.add(enemy);
            enemyCount ++;
        }
        if(name.equals("Yellow Enemy")) {
            //model
            Enemy enemy = new YellowEnemy(x, y);
            this.enemiesAlive.add(enemy);
            enemyCount ++;
        }
        if(name.equals("Boss Enemy")) {
            //model
            Enemy enemy = new BossEnemy(x, y);
            this.enemiesAlive.add(enemy);
            enemyCount ++;
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

        if (wave.getWaveCount() == 1) {
            sendWave(delay, 1);
        } else if (wave.getWaveCount() == 2) {
            sendWave(delay, 2);
        } else if (wave.getWaveCount() == 3) {
            sendWave(delay, 3);
        } else if (wave.getWaveCount() == 4) {
            sendWave(delay, 4);
        } else if (wave.getWaveCount() == 5) {
            sendWave(delay, 5);
        }
        else if (wave.getWaveCount() == 6) {
            sendWave(delay, 6);
        }
        else if (wave.getWaveCount() == 7) {
            sendWave(delay, 7);
        }

        updateEnemyAnimation();
        updateAttacks();
        updateTowerClick();

        gameScreen.drawUpdatedProfileLabel();

        if (this.profile.getLives() <= 0) {
            gameScreen.drawGameOver();
        }
    }


    public void sendWave(long delay, int waveNum) {
        if (waveNum == 1) {
            final int enemyDelay = 75;
                if (enemyCount < 10 &&
                delay % enemyDelay >= 0 && delay % enemyDelay <=2) {
                    createEnemy("Red Enemy", 175, GRID_SIZE + 50);
                }
        }
        if (waveNum == 2) {
            final int enemyDelay = 70;
                if (enemyCount < 20 &&
                        delay % enemyDelay >= 0 && delay % enemyDelay <=2) {
                    createEnemy("Red Enemy", 175, GRID_SIZE + 50);
                }
                else if (enemyCount >= 20 && enemyCount < 30 &&
                    delay % enemyDelay >= 0 && delay % enemyDelay <=2) {
                createEnemy("Blue Enemy", 175, GRID_SIZE + 50);
                }
            }
        if (waveNum == 3) {
            final int enemyDelay = 65;
            if (enemyCount < 60 &&
                    delay % enemyDelay >= 0 && delay % enemyDelay <= 2) {
                createEnemy("Red Enemy", 175, GRID_SIZE + 50);
            }
        }
        if (waveNum == 4) {
            final int enemyDelay = 60;
            if (enemyCount < 100 &&
                    delay % enemyDelay >= 0 && delay % enemyDelay <= 2) {
                createEnemy("Yellow Enemy", 175, GRID_SIZE + 50);
            }
        }
        if (waveNum == 5) {
            final int enemyDelay = 55;
            if (enemyCount < 125 &&
                    delay % enemyDelay >= 0 && delay % enemyDelay <= 2) {
                createEnemy("Blue Enemy", 175, GRID_SIZE + 50);
            }
        }
        if (waveNum == 6) {
            final int enemyDelay = 50;
            if (enemyCount < 150 &&
                    delay % enemyDelay >= 0 && delay % enemyDelay <= 2) {
                createEnemy("Yellow Enemy", 175, GRID_SIZE + 50);
            }
        }
        if (waveNum == 7) {
            final int enemyDelay = 45;
            if (enemyCount < 175 &&
                    delay % enemyDelay >= 0 && delay % enemyDelay <= 2) {
                createEnemy("Boss Enemy", 175, GRID_SIZE + 50);
            }
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
                this.root.getChildren().remove(enemy.getCircle());
                break;
            }
            Circle circle = enemy.getCircle();

            double y = circle.getCenterY();
            double x = circle.getCenterX();

            //Sets the path for the enemies to move along. Updates coordinates
            //after the enemy moves.
            if ( y >= 475 && x <= 180 ) {
                circle.setCenterY(y - 2);
            } else if (y >= 470 && x <= 425) {
                circle.setCenterX(x + 2);
            } else if (y <= 625 && y >= 470 && x <= 430) {
                circle.setCenterY(y + 2);
            } else if (y <= 630 && y >= 470 && x <= 625) {
                circle.setCenterX(x + 2);
            } else if (y >= 125 && x <= 630 && x >= 500) {
                circle.setCenterY(y - 2);
            } else if (y >= 120 && x >= 425) {
                circle.setCenterX(x - 2);
            } else if (y <= 275 && x >= 420) {
                circle.setCenterY(y + 2);
            } else if (y <= 280 && x >= 175) {
                circle.setCenterX(x - 2);
            } else {
                circle.setCenterY(y - 2);
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
                    gameScreen.createProjectileAnimation(tower, enemy);
                }
            }
        }
    }

    public void updateTowerClick() {
        for (Tower tower : this.towers) {
            tower.getImage().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String statsString = tower.getStats();
                    gameScreen.drawUpdatedStatsLabel(statsString);

                    //add buttons whose actions depend on game state
                    createSellTowerButton(tower);
                    createUpgradeTowerButton(tower);
                }
            });
        }
    }


    public void createSellTowerButton(Tower tower) {
        this.root.getChildren().remove(this.sellBtn);
        this.sellBtn = new Button("Sell");
        this.sellBtn.setLayoutX(GRID_SIZE+35);
        this.sellBtn.setLayoutY(300);
        this.root.getChildren().add(sellBtn);
        this.sellBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sellTower(tower);
                getGameScreen().removeStatsLabel();
            }
        });
    }

    public void createUpgradeTowerButton(Tower tower) {
        this.root.getChildren().remove(this.upgradeBtn);
        this.upgradeBtn = new Button("Upgrade");
        this.upgradeBtn.setLayoutX(GRID_SIZE+75);
        this.upgradeBtn.setLayoutY(300);
        this.root.getChildren().add(upgradeBtn);
        this.upgradeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (getProfile().getGold() > tower.getCost() * 1.25){
                    tower.upgrade();
                    getProfile().setGold((int) (getProfile().getGold() - tower.getCost() * 1.25));
                    tower.setCost((int) (tower.getCost() * 1.25));
                    getGameScreen().removeStatsLabel();
                    removeButtons();
                }
            }
        });
    }

    public void removeButtons() {
        this.root.getChildren().remove(this.sellBtn);
        this.root.getChildren().remove(this.upgradeBtn);
    }

    public GameScreen getGameScreen() {
        return this.gameScreen;
    }

    public void updateCoordinates(Enemy enemy, double x, double y) {
        enemy.setX(x);
        enemy.setY(y);
        enemy.setCircleX(x);
        enemy.setCircleY(y);
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
            profile.setHighScore(profile.getHighScore() + enemy.getGold());
        }
        removeEnemyFromGame(enemy);
    }

    public void sellTower(Tower tower) {
        //model
        profile.setGold(profile.getGold() + (int) (tower.getCost()*0.95));
        towers.remove(tower);

        //view
        this.root.getChildren().remove(tower.getImage());
        this.root.getChildren().remove(this.sellBtn);
        this.root.getChildren().remove(this.upgradeBtn);
    }


}