package game.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Point;

/**
 * Class for representing the walls in the labyrinth.
 * */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Wall {
    Point start;
    Point end;

    public Wall(int startX, int startY, int endX, int endY) {
        start = new Point(startX, startY);
        end = new Point(endX, endY);
    }

}
