package game.utilities.entities;

import game.utilities.helpers.Point2D;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * A class representing a generic {@code Entity} object of the game.
 * */
@NoArgsConstructor
@Data
@ToString
public class Entity {
    protected Point2D position;

    /**
     * Creates {@code Entity} object and sets the entity's position to the given position.
     * @param position The position to create the object at.
     * */
    public Entity(final Point2D position) {
        this.position = position;
    }
}
