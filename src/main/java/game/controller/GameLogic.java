package game.controller;

import game.utilities.Direction;
import game.utilities.EntityMover;
import game.utilities.FileHandler;
import game.utilities.Grid;
import game.utilities.MainMenuLoader;
import game.utilities.Wall;
import game.utilities.entities.Entity;
import game.utilities.entities.Monster;
import game.utilities.entities.Player;
import game.utilities.helpers.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.tinylog.Logger;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides the game's logic.
 */
public class GameLogic {

    @Getter
    @Setter
    private Entity player;
    @Getter
    @Setter
    private Entity monster;
    private List<Wall> walls;
    private Grid grid;
    private final EntityMover entityMover;
    private final GraphicsDrawer graphicsDrawer;

    private static final int NUMBER_OF_CELLS = 6;

    private static final List<KeyCode> MOVE_KEYS = List.of(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.UP, KeyCode.DOWN,
            KeyCode.LEFT, KeyCode.RIGHT);

    private double startTime;
    private double endTime;
    private int numberOfSteps = 0;
    private final String playerName;

    /**
     * Creates {@link GameLogic} object containing every information the game needs.
     *
     * @param playerName The player's name
     * @param canvas     The {@link Canvas} object the game will be drawn on
     */
    public GameLogic(final String playerName, final Canvas canvas) {
        if (canvas != null) {
            grid = new Grid(canvas.getWidth(), canvas.getHeight(), NUMBER_OF_CELLS);
        }
        player = new Player(new Point2D(0, 0));
        this.playerName = playerName;
        monster = new Monster(new Point2D(4, 2));
        walls = new ArrayList<>();
        try {
            this.walls = FileHandler.loadMap();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        graphicsDrawer = new GraphicsDrawer(player, monster, walls, canvas, grid);
        entityMover = new EntityMover(walls);

        if (!FileHandler.doesSaveFileExist()) {
            try {
                FileHandler.createSaveFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Logger.warn("Could not create save file.");
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            }
        }

        if (canvas != null) {
            graphicsDrawer.draw();
        }
        startTime = System.currentTimeMillis();
    }

    /**
     * Controls the game based on what event the {@link KeyEvent} parameter is representing.
     *
     * @param keyEvent The event that occurred
     * @throws JAXBException if any problem occurs during serialization or deserialization
     */
    public void doGameLogic(final KeyEvent keyEvent) throws IOException, JAXBException {
        final KeyCode code = keyEvent.getCode();
        if (MOVE_KEYS.contains(code)) {
            if (entityMover.canEntityMove(player, Direction.getDirectionFromKeyCode(code))) {
                numberOfSteps++;
                if (!entityMover.isWinningMove(player, Direction.getDirectionFromKeyCode(code), NUMBER_OF_CELLS)) {
                    entityMover.movePlayer(player, Direction.getDirectionFromKeyCode(code));
                    entityMover.moveMonster(monster, player);
                    graphicsDrawer.draw();
                    if (isGameLost()) {
                        Logger.debug("Monster caught the player. Game lost.");
                        handleGameOver(keyEvent);
                    }
                } else {
                    Logger.debug("Player's last step was the winning step. Game won.");
                    handleGameOver(keyEvent);
                }
            }
        }
    }

    /**
     * Handles when the game is over.
     *
     * @param keyEvent The event that was triggered that resulted in the game over state.
     * @throws IOException   if any problem occurs opening the save file
     * @throws JAXBException if any problem occurs during serialization or deserialization
     */
    private void handleGameOver(final KeyEvent keyEvent) throws IOException, JAXBException {
        endTime = System.currentTimeMillis();
        if (isGameLost()) {
            Logger.debug("Player lost the game.");
        } else {
            Logger.debug("Player won the game.");
        }
        FileHandler.savePlayerStats(playerName, endTime - startTime, numberOfSteps, isGameLost());

        MainMenuLoader.loadMainMenu((Stage) ((Scene) keyEvent.getSource()).getWindow());
    }

    /**
     * Returns whether the game is lost.
     *
     * @return Whether the game is lost.
     */
    public boolean isGameLost() {
        return player.getPosition().equals(monster.getPosition());
    }
}
