package edu.carleton.leight;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.scene.Node;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;

/**
 * Adapted from Jeff Ondich's tutorial code for JavaFX shared in class.
 */
public abstract class Sprite extends Group {

    private Point2D velocity;

    public Point2D getLocation() {
        Point2D location = new Point2D(this.getLayoutX(), this.getLayoutY());
        return location;
    }

    public void setLocation(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

//    public Point2D getVelocity() {
//        return this.velocity;
//    }
//
//    public void setVelocity(double velocityX, double velocityY) {
//        this.velocity = new Point2D(velocityX, velocityY);
//    }

    public void move() {
        Point2D location = this.getLocation();
        this.setLocation(location.getX() + this.velocity.getX(), location.getY() + this.velocity.getY());
    }
}

