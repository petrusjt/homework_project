package game.controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.tinylog.Logger;

/**
 * Class for controlling the game.
 * */
public class GameController {
    private static Canvas canvas;
    public static void handleKeyEvent(KeyEvent keyEvent) {
        Logger.debug("Key pressed: " + keyEvent.getCode());
    }

    public static void setCanvas(Canvas c)
    {
        canvas = c;
    }
}
