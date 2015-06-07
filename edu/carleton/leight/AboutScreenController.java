package edu.carleton.leight;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AboutScreenController {
    private Main main;
    @FXML private Button back;

    public AboutScreenController(Main main) {
        this.main = main;
    }
    @FXML public void onBackPress(ActionEvent event) {
        main.backToMain();
    }
}
