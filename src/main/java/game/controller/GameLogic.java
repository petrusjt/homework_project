package game.controller;

import game.utilities.*;
import game.utilities.entities.Entity;
import game.utilities.entities.Monster;
import game.utilities.entities.Player;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.tinylog.Logger;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides the game's logic.
 * */
public class GameLogic {

    @Getter @Setter
    private Entity player;
    @Getter @Setter
    private Entity monster;
    private List<Wall> walls;
    private Grid grid;
    private GraphicsDrawer graphicsDrawer;

    private final int NUMBER_OF_CELLS = 6;

    private List<KeyCode> moveKeys;

    private double startTime;
    private double endTime;
    private int numberOfSteps = 0;
    private String playerName;

    /**
     * Creates {@link GameLogic} object containing every information the game needs.
     * @param playerName The player's name
     * @param canvas The {@link Canvas} object the game will be drawn on
     * */
    public GameLogic(String playerName, Canvas canvas)
    {
        if(canvas != null)
            grid = new Grid(canvas.getWidth(), canvas.getHeight(), NUMBER_OF_CELLS);
        this.player = new Player(new Point(0,0));
        this.monster = new Monster(new Point(4,2));
        this.walls = new ArrayList<>();
        this.graphicsDrawer = new GraphicsDrawer(player,monster,walls,canvas,grid);
        this.playerName = playerName;


        try {
            FileHandler.loadMap(walls);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        if(!FileHandler.doesSaveFileExist())
        {
            try {
                FileHandler.createSaveFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Logger.warn("Could not create save file.");
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        moveKeys = new ArrayList<>();
        moveKeys.add(KeyCode.W);
        moveKeys.add(KeyCode.S);
        moveKeys.add(KeyCode.A);
        moveKeys.add(KeyCode.D);
        moveKeys.add(KeyCode.UP);
        moveKeys.add(KeyCode.DOWN);
        moveKeys.add(KeyCode.LEFT);
        moveKeys.add(KeyCode.RIGHT);

        if(canvas != null)
            graphicsDrawer.draw();
        startTime = System.currentTimeMillis();
    }

    /**
     * Controls the game based on what event the {@link KeyEvent} parameter is representing.
     * @param keyEvent The event that occurred
     * */
    public void doGameLogic(KeyEvent keyEvent) throws IOException, JAXBException {
        KeyCode code = keyEvent.getCode();
        if(moveKeys.contains(code))
        {
            if(EntityMover.canEntityMove(player, Directions.getDirectionFromKeyCode(code), walls))
            {
                numberOfSteps++;
                if(!EntityMover.isWinningMove(player, Directions.getDirectionFromKeyCode(code), NUMBER_OF_CELLS))
                {
                    EntityMover.movePlayer(player, Directions.getDirectionFromKeyCode(code));
                    EntityMover.moveMonster(monster, player, walls);
                    graphicsDrawer.draw();
                    if(isGameLost())
                    {
                        Logger.debug("Monster caught the player. Game lost.");
                        handleGameOver(keyEvent);
                    }
                }
                else
                {
                    Logger.debug("Player's last step was the winning step. Game won.");
                    handleGameOver(keyEvent);
                }
            }
        }
    }

    /**
     * Handles when the game is over.
     * @param keyEvent The event that was triggered that resulted in the game over state.
     * @throws IOException if any problem occurs opening the save file
     * @throws JAXBException if any problem occurs during serialization or deserialization
     * */
    private void handleGameOver(KeyEvent keyEvent) throws IOException, JAXBException {
        endTime = System.currentTimeMillis();
        if(isGameLost())
        {
            Logger.debug("Player lost the game.");
        }
        else
        {
            Logger.debug("Player won the game.");
        }
        FileHandler.savePlayerStats(playerName, endTime - startTime, numberOfSteps, isGameLost());

        MainMenuLoader.loadMainMenu((Stage) ((Scene) keyEvent.getSource()).getWindow());
    }

    /**
     * Returns whether the game is lost.
     * @return Whether the game is lost.
     * */
    public boolean isGameLost() {
        return player.getPosition().equals(monster.getPosition());
    }
}
