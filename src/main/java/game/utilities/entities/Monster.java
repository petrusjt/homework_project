package game.utilities.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Data;
import lombok.ToString;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Class for representing the monster that chases the Player.
 * */
@Data
public class Monster extends Entity{
    /**
     * Creates {@code Monster} object and sets the monster's position to the given position.
     * @param position The position to create the object at.
     * */
    public Monster(Point2D position) {
        super(position);
    }

}
