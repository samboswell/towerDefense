package edu.carleton.leight;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.TimerTask;

public class HomeScreenController {
    private Main main;
    @FXML private Button play;
    @FXML private Button about;

    public HomeScreenController(Main main) {
        this.main = main;
    }

    @FXML public void onPlayPress(ActionEvent event) {
        main.playGame();
    }

//    @FXML public void onAboutPress(ActionEvent event) {
//        displayAbout();
//    }

}
