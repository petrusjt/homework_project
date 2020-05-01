package game.toberenamed;

import javafx.scene.shape.Rectangle;
import lombok.Data;

import java.awt.geom.Point2D;

/**
 * Class for representing the walls in the labyrinth.
 * */
@Data
public class Wall {
    Point2D.Double position;
    Rectangle wallRectangle;

    public Wall(double x, double y, double width, double height) {
        position = new Point2D.Double(x, y);
        wallRectangle = new Rectangle(x, y, width, height);
    }
}
