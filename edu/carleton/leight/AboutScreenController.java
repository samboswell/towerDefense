package edu.carleton.leight;
/**
 * Controller for the About screen, the back button brings the user back to
 * the home screen.
 *
 * @authors Jonah Tuchow, Tristan Leigh, Sam Boswell
 */

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
