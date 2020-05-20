package game.utilities.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.geom.Point2D;

@NoArgsConstructor
@Data
public class Entity {
    protected Point2D position;

    /**
     * Creates {@code Entity} object and sets the entity's position to the given position.
     * @param position The position to create the object at.
     * */
    public Entity(Point2D position) {
        this.position = position;
    }
}
