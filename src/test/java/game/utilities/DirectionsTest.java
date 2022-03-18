package game.utilities;

import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionsTest {

    @Test
    void getDirectionFromKeyCode() {
        assertEquals(Direction.UP, Direction.getDirectionFromKeyCode(KeyCode.W));
        assertEquals(Direction.DOWN, Direction.getDirectionFromKeyCode(KeyCode.S));
        assertEquals(Direction.LEFT, Direction.getDirectionFromKeyCode(KeyCode.A));
        assertEquals(Direction.RIGHT, Direction.getDirectionFromKeyCode(KeyCode.D));
        assertEquals(Direction.UP, Direction.getDirectionFromKeyCode(KeyCode.UP));
        assertEquals(Direction.DOWN, Direction.getDirectionFromKeyCode(KeyCode.DOWN));
        assertEquals(Direction.LEFT, Direction.getDirectionFromKeyCode(KeyCode.LEFT));
        assertEquals(Direction.RIGHT, Direction.getDirectionFromKeyCode(KeyCode.RIGHT));

        assertEquals(Direction.RIGHT, Direction.getDirectionFromKeyCode(KeyCode.Q));
        assertEquals(Direction.RIGHT, Direction.getDirectionFromKeyCode(KeyCode.ENTER));
        assertEquals(Direction.RIGHT, Direction.getDirectionFromKeyCode(KeyCode.SPACE));
    }
}