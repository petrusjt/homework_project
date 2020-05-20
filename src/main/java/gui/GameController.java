package gui;

import game.controller.GameLogic;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;
import org.tinylog.Logger;

import javax.xml.bind.JAXBException;
import java.io.*;

public class GameController {
    private Canvas canvas;
    GameLogic gameLogic;

    public GameController(String playerName, Canvas canvas) {
        this.canvas = canvas;
        gameLogic = new GameLogic(playerName, canvas);
    }

    public void handleKeyEvent(KeyEvent keyEvent) {
        Logger.debug("Key pressed: " + keyEvent.getCode());
        try {
            gameLogic.doGameLogic(keyEvent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            Logger.warn(e);
        }
    }

    public void handleOnCloseRequest(WindowEvent event) {
        Logger.info("Closing application GameWindow");
        Platform.exit();
    }
}
