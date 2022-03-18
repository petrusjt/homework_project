package game.utilities;

import game.utilities.helpers.Point2D;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class for representing the walls in the labyrinth.
 * */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Wall {
    private Point2D start;
    private Point2D end;

    /**
     * Creates wall object.
     * @param startX The x coordinate of the start of the wall
     * @param startY The y coordinate of the start of the wall
     * @param endX The x coordinate of the end of the wall
     * @param endY The y coordinate of the end of the wall
     * */
    public Wall(int startX, int startY, int endX, int endY) {
        start = new Point2D(startX, startY);
        end = new Point2D(endX, endY);
    }

}
