package game.toberenamed;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Data;

import java.awt.geom.Point2D;

/**
 * Class for representing the player that the user controls.
 * */
@Data
public class Monster {
    private Point2D.Double position;
    private Circle monsterCircle;

    public Monster()
    {
        position = new Point2D.Double(0, 0);
        monsterCircle = new Circle(position.getX(),position.getY(),50, Color.ALICEBLUE);
    }

    public Monster(Point2D.Double position, Circle monsterCircle) {
        this.position = position;
        this.monsterCircle = monsterCircle;
    }

    public void setPosition(double x, double y){
        position.setLocation(x, y);
    }
}
