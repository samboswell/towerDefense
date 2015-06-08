package edu.carleton.leight;

/**
 * The gameScreen class works with drawing labels and setting the grid  and
 * path for the game. Purely visual.
 *
 * @authors Jonah Tuchow, Tristan Leigh, Sam Boswell
 */


import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.text.Font;

public class GameScreen {

    private Profile profile;
    private Group root;
    private Label profileLabel;
    private Label statsLabel;

    final public static int BLOCK_SIZE = 50;
    final public static int GRID_SIZE = 15*BLOCK_SIZE;

    private int[][] gameGrid;


    public GameScreen(Profile profile, Group root) {
        this.profile = profile;
        this.root = root;
        this.profileLabel = new Label();
        this.statsLabel = new Label();

        this.gameGrid = getDefaultGameGrid();

    }
    public void drawTowerButtonImage() {

        Image towerImage = new Image("edu/carleton/leight/TowerImage.png",
                50,50,false,false);
        ImageView towerView = new ImageView(towerImage);
        towerView.setX(GameManager.GRID_SIZE+50);
        towerView.setY(140);
        this.root.getChildren().add(towerView);

    }

    public void drawSideBar() {
        Rectangle sidebar = new Rectangle(GameManager.GRID_SIZE+25,0,150,
                GameManager.GRID_SIZE/2);
        sidebar.setFill(Color.LIGHTGRAY);
        this.root.getChildren().add(sidebar);
    }

    public int[][] getDefaultGameGrid() {
        //0 means tower placeable
        //1 means enemy path
        return new int[][]{
                {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,1,1,1,1,1,0,0},
                {0,0,0,1,0,0,0,0,1,0,0,0,1,0,0},
                {0,0,0,1,0,0,0,0,1,0,0,0,1,0,0},
                {0,0,0,1,1,1,1,1,1,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,1,1,1,1,1,1,0,0,0,1,0,0},
                {0,0,0,1,0,0,0,0,1,0,0,0,1,0,0},
                {0,0,0,1,0,0,0,0,1,0,0,0,1,0,0},
                {0,0,0,1,0,0,0,0,1,1,1,1,1,0,0},
                {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
        };
    }

    public int[][] getGameGrid() {
        return this.gameGrid;
    }


    public void drawPath(int[][] gameGrid) {
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                int currentSquare = gameGrid[y][x];
                int xCorner = x*50;
                int yCorner = y*50;
                if (currentSquare == 1) {
                    Rectangle pathBlock = new Rectangle(xCorner, yCorner,
                            50.0, 50.0);
                    pathBlock.setOpacity(1.0);
                    this.root.getChildren().add(pathBlock);
                }
            }
        }
    }

    public void drawUpdatedProfileLabel() {
        this.root.getChildren().remove(this.profileLabel);
        String newStats = this.profile.getStats();
        Label profileLabel = new Label(newStats);
        profileLabel.setLayoutX(GameManager.GRID_SIZE+35);
        this.root.getChildren().add(profileLabel);
        this.profileLabel = profileLabel;
    }

    public void drawUpdatedStatsLabel(String statsString) {
        this.root.getChildren().remove(this.statsLabel);
        Label statsLabel = new Label(statsString);
        statsLabel.setLayoutX(GameManager.GRID_SIZE+35);
        statsLabel.setLayoutY(250);
        this.root.getChildren().add(statsLabel);
        this.statsLabel = statsLabel;
    }

    public void drawTower(Tower tower, double x, double y) {
        tower.getImage().setX(x);
        tower.getImage().setY(y);
        this.root.getChildren().add(tower.getImage());
    }

    public void removeStatsLabel() {
        this.root.getChildren().remove(this.statsLabel);
    }

    public void createProjectileAnimation(Tower tower, Enemy enemy) {

        final Path path = new Path();
        path.getElements().add(new MoveTo(tower.getX(), tower.getY()));
        path.getElements().add(new LineTo(enemy.getX(), enemy.getY()));
        path.setOpacity(0.0);
        this.root.getChildren().add(path);

        Circle projectile = new Circle(5);
        this.root.getChildren().add(projectile);
        final PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(0.1));
        pathTransition.setPath(path);
        pathTransition.setNode(projectile);
//        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.play();
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                removeProjectile(projectile);
            }
        };
    }

    public void drawGameOver() {
        this.root.getChildren().removeAll(this.root.getChildren());
        Label gameOver = new Label("GAME OVER :(");
        gameOver.setLayoutX(300);
        gameOver.setLayoutY(300);
        int fontSize = 50;
        gameOver.setFont(new Font("Comic Sans", fontSize));
        this.root.getChildren().add(gameOver);
    }

    public void removeProjectile(Circle projectile) {
        this.root.getChildren().remove(projectile);
    }

}
