package game.utilities.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.awt.geom.Point2D;

/**
 * Class for representing the player that the user controls.
 * */
@Data
@EqualsAndHashCode(callSuper = false)
public class Player extends Entity{
    /**
     * Creates {@code Player} object and sets the player's position to the given position.
     * @param position The position to create the object at.
     * */
    public Player(Point2D position) {
        super(position);
    }
}
