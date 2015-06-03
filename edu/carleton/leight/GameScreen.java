package edu.carleton.leight;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class GameScreen {

    private Profile profile;
    private Group root;
    private Label label;


    public GameScreen(Profile profile, Group root) {
        this.profile = profile;
        this.root = root;
        label = new Label();
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
    public void updateLabel() {
        this.root.getChildren().remove(this.label);
        String newStats = this.profile.getStats();
        Label newLabel = new Label(newStats);
        this.root.getChildren().add(newLabel);
        this.label = newLabel;
    }

    private void setProfile(Profile profile) {
        this.profile = profile;
    }
}
