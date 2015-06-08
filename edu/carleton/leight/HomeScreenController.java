package edu.carleton.leight;

/**
 * Controller for the Home screen, Play starts the game, while about brings
 * the user to the about page.
 *
 * @authors Jonah Tuchow, Tristan Leigh, Sam Boswell
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

    @FXML public void onAboutPress(ActionEvent event) {main.aboutGame(); }

    @FXML public void onGameOver(ActionEvent event) {main.endGame();}

}
