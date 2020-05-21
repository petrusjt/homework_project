package game.utilities;

import javafx.scene.input.KeyCode;

/**
 * Helper class for representing the four directions the entities can move in.
 * */
public class Directions {

    /**
     * Constant representing the direction up.
     * */
    public static final Direction UP = Direction.UP;
    /**
     * Constant representing the direction down.
     * */
    public static final Direction DOWN = Direction.DOWN;
    /**
     * Constant representing the direction left.
     * */
    public static final Direction LEFT = Direction.LEFT;
    /**
     * Constant representing the direction right.
     * */
    public static final Direction RIGHT = Direction.RIGHT;

    /**
     * Internal enum for representing the directions.
     * */
    public enum Direction {
        /**
         * Internal representation of the direction up.
         * */
        UP,
        /**
         * Internal representation of the direction down.
         * */
        DOWN,
        /**
         * Internal representation of the direction left.
         * */
        LEFT,
        /**
         * Internal representation of the direction right.
         * */
        RIGHT
    }

    /**
     * Returns a {@link Direction} object based on parameter {@code code}.
     * @param code {@link KeyCode} object representing a key on the keyboard
     * @return {@link Direction} based on {@code code}
     * */
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
