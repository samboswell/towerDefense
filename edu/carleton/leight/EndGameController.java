package edu.carleton.leight;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EndGameController {
    private Main main;
    @FXML private Button tryAgain;
    @FXML private Button quit;

    public EndGameController(Main main) {
        this.main = main;
    }
    @FXML public void onRestartPress(ActionEvent event) {
        main.backToMain();
    }
    @FXML public void onQuitPress(ActionEvent event) {System.exit(0);}
}
