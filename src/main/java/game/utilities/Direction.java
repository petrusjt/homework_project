package game.utilities;

import javafx.scene.input.KeyCode;

/**
 * Helper class for representing the four directions the entities can move in.
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
    RIGHT;

    /**
     * Returns a {@link Direction} object based on parameter {@code code}.
     * @param code {@link KeyCode} object representing a key on the keyboard
     * @return {@link Direction} based on {@code code}
     * */
    public static Direction getDirectionFromKeyCode(final KeyCode code) {
        switch (code) {
            case UP:
            case W:
                return Direction.UP;
            case DOWN:
            case S:
                return Direction.DOWN;
            case LEFT:
            case A:
                return Direction.LEFT;
            default:
                return Direction.RIGHT;
        }
    }
}
