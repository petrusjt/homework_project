package game.toberenamed;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Data;

import java.awt.geom.Point2D;

/**
 * Class for representing the player that the user controls.
 * */
@Data
public class Player {
    private Point2D.Double position;
    private Circle playerCircle;

    public Player()
    {
        position = new Point2D.Double(0, 0);
        playerCircle = new Circle(position.getX(),position.getY(),50, Color.ALICEBLUE);
    }

    public Player(Point2D.Double position, Circle playerCircle) {
        this.position = position;
        this.playerCircle = playerCircle;
    }

    public void setPosition(double x, double y){
        position.setLocation(x, y);
    }
}
