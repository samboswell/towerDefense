//package edu.carleton.leight;
//
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.input.KeyEvent;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class VisualManager implements EventHandler<KeyEvent> {
//    private GameManager gameManager;
//    private int score;
//    private boolean paused;
//    private Timer timer;
//    private final double FRAMES_PER_SECOND = 20.0;
//
//    @FXML
//    private Button Pause;
//    private Button Tower;
//    private Button livesLabel;
//
//
//    public VisualManager() {
//        this.gameManager = new GameManager();
//        this.paused = false;
//        this.score = 0;
//    }
//
//    public void initialize() {
//        this.setUpAnimationTimer();
//    }
//
//    private void setUpAnimationTimer() {
//        TimerTask timerTask = new TimerTask() {
//            public void run() {
//                Platform.runLater(new Runnable() {
//                    public void run() {
//                        updateAnimation();
//                    }
//                });
//            }
//        };
//
//        final long startTimeInMilliseconds = 0;
//        final long repetitionPeriodInMilliseconds = 100;
//        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
//        this.timer = new java.util.Timer();
//        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
//    }
//    private void updateAnimation() {
//
//        removingEnemies();
//    }
//
//    public void removingEnemies(){
//        for(Enemy enemy : gameManager.getCurrentEnemies()) {
//            if(enemy.isFinished()){
//                gameManager.removeEnemyIfFinished();
//                Profile().lives--;
//                this.livesLabel.setText(String.format("Lives: %d", this.lives));
//            }
//        }
//    }
//
//    @Override
//    public void handle(KeyEvent keyEvent) {
//
//    }
//
//    public void onPause(ActionEvent actionEvent) {
//        if (this.paused) {
//            this.setUpAnimationTimer();
//        } else {
//            this.timer.cancel();
//        }
//        this.paused = !this.paused;
//    }
//
//
//
//}