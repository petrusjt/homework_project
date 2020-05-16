package game.utilities;

import lombok.Data;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Class for representing the player that the user controls.
 * */
@Data
public class Player {
    private Point2D position;

    public Player()
    {
        position = new Point(0, 0);
    }

    public Player(Point2D position, float radius) {
        this.position = position;
    }

    public void setPosition(double x, double y){
        position.setLocation(x, y);
    }
}
