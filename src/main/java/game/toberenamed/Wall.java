package game.toberenamed;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.Point;

/**
 * Class for representing the walls in the labyrinth.
 * */
@Data
@AllArgsConstructor
public class Wall {
    Point start;
    Point end;

    public Wall(int startX, int startY, int endX, int endY) {
        start = new Point(startX, startY);
        end = new Point(endX, endY);
    }

}
