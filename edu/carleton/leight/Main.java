package edu.carleton.leight;


import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
//        Parent root = (Parent) loader.load();
//
        Group root = new Group();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Circle Defend'r");
        primaryStage.setScene(scene);
        primaryStage.show();
        animate(root);

    }

    private void animate(Group group) {
        for (int delay = 0; delay < 5; delay++ ) {
            Circle circle = new Circle(300, 550, 15);
            circle.setFill(Color.RED);
            Path path = getPath();
            group.getChildren().add(path);
            group.getChildren().add(circle);
            PathTransition transition = getPathTransition(circle, path, delay);
            transition.play();
        }
    }


    //thanks to the following guide on how to use path transitions:
    // http://www.javaworld.com/article/2074529/core-java/javafx-2-animation--path-transitions.html
    private PathTransition getPathTransition(Shape shape, Path path, int delay) {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(shape);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.seconds(10.0));
        pathTransition.setDelay(Duration.seconds(delay));
//        pathTransition.setCycleCount(Timeline.INDEFINITE);
        return pathTransition;
    }

    private Path getPath() {
        final Path path = new Path();
        path.getElements().add(new MoveTo(300, 550));
        path.getElements().add(new LineTo(300, 240));
        path.getElements().add(new LineTo(220, 240));
        path.getElements().add(new LineTo(220, -50));
        path.setOpacity(0.0); // comment this out if you want to show the path
        return path;
    }

    public GameScreen createDefaultGameScreen() {
        int[][] grid = new int[][]{
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
        };

        int[] path = new int[]{1,1,1,1,1,1,1};
        GameScreen gameScreen = new GameScreen(grid,path,"hi");
        return gameScreen;
    }

    public static void main(String[] args) {
        launch(args);
    }
}