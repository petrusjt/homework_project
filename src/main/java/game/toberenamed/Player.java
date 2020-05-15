package game.toberenamed;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Data;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Class for representing the player that the user controls.
 * */
@Data
public class Player {
    private Point2D position;
    private Circle playerCircle;

    public Player()
    {
        position = new Point(0, 0);
        playerCircle = new Circle(position.getX(),position.getY(),50, Color.DODGERBLUE);
    }

    public Player(Point2D position, float radius) {
        this.position = position;
        this.playerCircle = playerCircle;
        playerCircle = new Circle(position.getX(),position.getY(),radius, Color.DODGERBLUE);
    }

    public void setPosition(double x, double y){
        position.setLocation(x, y);
    }
}
