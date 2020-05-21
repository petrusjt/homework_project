package game.utilities;

import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionsTest {

    @Test
    void getDirectionFromKeyCode() {
        assertEquals(Directions.UP, Directions.getDirectionFromKeyCode(KeyCode.W));
        assertEquals(Directions.DOWN, Directions.getDirectionFromKeyCode(KeyCode.S));
        assertEquals(Directions.LEFT, Directions.getDirectionFromKeyCode(KeyCode.A));
        assertEquals(Directions.RIGHT, Directions.getDirectionFromKeyCode(KeyCode.D));
        assertEquals(Directions.UP, Directions.getDirectionFromKeyCode(KeyCode.UP));
        assertEquals(Directions.DOWN, Directions.getDirectionFromKeyCode(KeyCode.DOWN));
        assertEquals(Directions.LEFT, Directions.getDirectionFromKeyCode(KeyCode.LEFT));
        assertEquals(Directions.RIGHT, Directions.getDirectionFromKeyCode(KeyCode.RIGHT));

        assertEquals(Directions.RIGHT, Directions.getDirectionFromKeyCode(KeyCode.Q));
        assertEquals(Directions.RIGHT, Directions.getDirectionFromKeyCode(KeyCode.ENTER));
        assertEquals(Directions.RIGHT, Directions.getDirectionFromKeyCode(KeyCode.SPACE));
    }
}