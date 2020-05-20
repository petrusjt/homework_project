package game.utilities;

import javafx.scene.input.KeyCode;

public class Directions {

    public static final Direction UP = Direction.UP;
    public static final Direction DOWN = Direction.DOWN;
    public static final Direction LEFT = Direction.LEFT;
    public static final Direction RIGHT = Direction.RIGHT;

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static Direction getDirectionFromKeyCode(KeyCode code) {
        if(code.equals(KeyCode.W) || code.equals(KeyCode.UP))
        {
            return Direction.UP;
        }
        else if(code.equals(KeyCode.S) || code.equals(KeyCode.DOWN))
        {
            return Direction.DOWN;
        }
        else if(code.equals(KeyCode.A) || code.equals(KeyCode.LEFT))
        {
            return Direction.LEFT;
        }
        else
        {
            return Direction.RIGHT;
        }
    }
}
