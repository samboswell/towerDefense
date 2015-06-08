package edu.carleton.leight;

/**
 * The Main class. It loads our FXML files and displays them.
 *
 * @authors Jonah Tuchow, Tristan Leigh, Sam Boswell
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
    public final static int stageXSize = 700;
    public final static int stageYSize = 500;
    private Stage stage;

    /**
     * Starts the game and loads the homepage.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        HomeScreenController homeScreenController =
                new HomeScreenController(this);
        loader.setController(homeScreenController);
        Parent root = (Parent) loader.load();

        //stage set
        this.stage.setTitle("Circle Defend'r");
        this.stage.setScene(new Scene(root, stageXSize, stageYSize));
        this.stage.show();
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

    }
    /**
     * Starts the game and loads the homepage.
     */
    public void playGame() {
        Parent root;
        FXMLLoader loader2 =
                new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        try {
            GameManager gameManager2 = new GameManager();
            loader2.setController(gameManager2);
            loader2.load();
            stage.setScene(gameManager2.getGameScene());
            stage.show();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * Starts the game and loads the homepage.
     */
    public void aboutGame() {
        Parent root;
        FXMLLoader loader3 =
                new FXMLLoader(getClass().getResource("About.fxml"));
        try {
            AboutScreenController aboutController =
                    new AboutScreenController(this);
            loader3.setController(aboutController);
            root = (Parent) loader3.load();
            this.stage.setScene(new Scene(root, stageXSize, stageYSize));
            this.stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * Used to go back to the homePage from the aboutPage.
     */
    public void backToMain() {
        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Used when the game is over.
     */
    public void endGame() {
        Parent root;
        FXMLLoader loader4 =
                new FXMLLoader(getClass().getResource("home.fxml"));
        try {
            HomeScreenController homeScreenController =
                    new HomeScreenController(this);
            loader4.setController(homeScreenController);
            root = (Parent) loader4.load();
            Scene scene = new Scene(root, stageXSize, stageYSize);
            scene.setRoot(root);
            this.stage.setScene(scene);
            this.stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

