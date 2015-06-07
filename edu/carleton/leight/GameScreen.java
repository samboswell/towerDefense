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
        towerView.setX(625);
        towerView.setY(150);
        this.root.getChildren().add(towerView);

    }

    public void drawPath(int[][] gameGrid) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                int currentSquare = gameGrid[y][x];
                int xCorner = x*50;
                int yCorner = y*50;
                if (currentSquare == 1) {
                    Rectangle pathBlock = new Rectangle(xCorner, yCorner,
                            50.0, 50.0);
                    pathBlock.setOpacity(0.8);
                    this.root.getChildren().add(pathBlock);
                }
            }
        }
    }
    public void drawUpdatedProfileLabel() {
        this.root.getChildren().remove(this.profileLabel);
        String newStats = this.profile.getStats();
        Label profileLabel = new Label(newStats);
        profileLabel.setLayoutX(600);
        this.root.getChildren().add(profileLabel);
        this.profileLabel = profileLabel;
    }

    public void drawUpdatedStatsLabel() {
        Label statsLabel = new Label("*insert stats here*");
        statsLabel.setLayoutX(500);
        statsLabel.setLayoutY(400);
        this.root.getChildren().add(statsLabel);
    }

    public void drawEnemy() {
        Circle circle = new Circle(325,550,15, Color.RED);
        this.root.getChildren().add(circle);
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
