package game.utilities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Data;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Class for representing the monster that chases the Player.
 * */
@Data
public class Monster {
    private Point2D position;
    private Circle monsterCircle;

    /**
     * Sets the monster's position to (0,0) and creates the {@code Circle} visually representing the monster.
     * */
    public Monster()
    {
        position = new Point(0, 0);
        monsterCircle = new Circle(position.getX(),position.getY(),50, Color.BLACK);
    }

    /**
     * Sets the monster's position to the given position and creates the {@code Circle} visually representing the monster.
     * @param position The position to create the object at.
     * @param radius The radius of the {@code} Circle object representing the monster
     * */
    public Monster(Point2D position, float radius) {
        this.position = position;
        this.monsterCircle = new Circle(position.getX(), position.getY(), radius, Color.BLACK);;
    }

}
