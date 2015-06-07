//package edu.carleton.leight;
//
//
//import javafx.scene.Group;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Rectangle;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.ImageCursor;
//import javafx.scene.control.Button;
//
//
//public class GameOverScreen {
//    private Group root;
//
//    public GameOverScreen(Group root) {
//        this.root = root;
//    }
//
//    public void createRestartButton() {
//        Button waveBtn = new Button("Restart");
//        waveBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                ;
//            }
//        });
//
//        //view
//        waveBtn.setLayoutX(600);
//        waveBtn.setLayoutY(470);
//        this.root.getChildren().add(waveBtn);
//    }
//
//    public void createHomeButton() {
//        Button waveBtn = new Button("Home");
//        waveBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                setWaveCount(getWaveCount() + 1);
//            }
//        });
//
//        //view
//        waveBtn.setLayoutX(600);
//        waveBtn.setLayoutY(470);
//        this.root.getChildren().add(waveBtn);
//    }
//
//    public void createQuitButton() {
//        Button waveBtn = new Button("Quit");
//        waveBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                setWaveCount(getWaveCount() + 1);
//            }
//        });
//
//        //view
//        waveBtn.setLayoutX(600);
//        waveBtn.setLayoutY(470);
//        this.root.getChildren().add(waveBtn);
//    }
//
//
//
//
//    public void setProfile(Profile profile) {
//        this.profile = profile;
//    }
//
//}
