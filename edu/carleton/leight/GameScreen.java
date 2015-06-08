package edu.carleton.leight;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameScreen {

    private Profile profile;
    private Group root;
    private Label profileLabel;
    private Label statsLabel;

    public GameScreen(Profile profile, Group root) {
        this.profile = profile;
        this.root = root;
        this.profileLabel = new Label();
        this.statsLabel = new Label();
    }
    public void createTowerButtonImage() {

        Image towerImage = new Image("edu/carleton/leight/TowerImage.png",
                50,50,false,false);
        ImageView towerView = new ImageView(towerImage);
        towerView.setX(GameManager.GRID_SIZE+50);
        towerView.setY(150);
        this.root.getChildren().add(towerView);

    }

    public void drawSideBar() {
        Rectangle sidebar = new Rectangle(GameManager.GRID_SIZE+25,0,150,
                GameManager.GRID_SIZE/2);
        sidebar.setFill(Color.LIGHTGRAY);
        this.root.getChildren().add(sidebar);
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
                    pathBlock.setOpacity(.8);
                    this.root.getChildren().add(pathBlock);
                }
            }
        }
    }

    public void drawUpdatedProfileLabel() {
        this.root.getChildren().remove(this.profileLabel);
        String newStats = this.profile.getStats();
        Label profileLabel = new Label(newStats);
        profileLabel.setLayoutX(GameManager.GRID_SIZE+50);
        this.root.getChildren().add(profileLabel);
        this.profileLabel = profileLabel;
    }

    public void drawUpdatedStatsLabel(String statsString) {
        this.root.getChildren().remove(this.statsLabel);
        Label statsLabel = new Label(statsString);
        statsLabel.setLayoutX(GameManager.GRID_SIZE+50);
        statsLabel.setLayoutY(200);
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
}
