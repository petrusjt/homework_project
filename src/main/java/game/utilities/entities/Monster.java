package game.utilities.entities;

import game.utilities.helpers.Point2D;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class for representing the monster that chases the Player.
 * */
@Data
@EqualsAndHashCode(callSuper = true)
public class Monster extends Entity{
    /**
     * Creates {@code Monster} object and sets the monster's position to the given position.
     * @param position The position to create the object at.
     * */
    public Monster(Point2D position) {
        super(position);
    }

}
