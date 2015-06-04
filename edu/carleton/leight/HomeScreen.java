package edu.carleton.leight;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by tuchowj on 6/4/15.
 */

public class HomeScreen {
    private Stage stage;

    public HomeScreen(Stage stage) {
        this.stage = stage;
    }

    public void createPlayButton(Group homeRoot, Scene gameScene) {

        Button play = new Button();
        play.setText("PLAY!!!");
        play.setLayoutX(200);
        play.setLayoutY(200);
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = getStage();
                stage.setScene(gameScene);
            }
        });
        homeRoot.getChildren().add(play);
    }

    public void createAboutButton(Group homeRoot) {

        Button about = new Button();
        about.setText("ABOUT!!!");
        about.setLayoutX(200);
        about.setLayoutY(250);
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String aboutString = "For Fascism, the growth of empire, that is to say the expansion of the nation, is an essential manifestation of vitality, and its opposite a sign of decadence. Peoples which are rising, or rising again after a period of decadence, are always imperialist; and renunciation is a sign of decay and of death. Fascism is the doctrine best adapted to represent the tendencies and the aspirations of a people, like the people of Italy, who are rising again after many centuries of abasement and foreign servitude. But empire demands discipline, the coordination of all forces and a deeply felt sense of duty and sacrifice: this fact explains many aspects of the practical working of the regime, the character of many forces in the State, and the necessarily severe measures which must be taken against those who would oppose this spontaneous and inevitable movement of Italy in the twentieth century, and would oppose it by recalling the outworn ideology of the nineteenth century - repudiated wheresoever there has been the courage to undertake great experiments of social and political transformation; for never before has the nation stood more in need of authority, of direction and order. If every age has its own characteristic doctrine, there are a thousand signs which point to Fascism as the characteristic doctrine of our time. For if a doctrine must be a living thing, this is proved by the fact that Fascism has created a living faith; and that this faith is very powerful in the minds of men is demonstrated by those who have suffered and died for it.";
                Label newLabel = new Label(aboutString);
                printAboutInfo(homeRoot, newLabel);

            }
        });
        homeRoot.getChildren().add(about);
    }

    private void printAboutInfo(Group homeRoot, Label newLabel) {
        homeRoot.getChildren().add(newLabel);
    }

    public Stage getStage() {
        return this.stage;
    }
}
