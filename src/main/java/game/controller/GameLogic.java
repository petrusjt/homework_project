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
import org.tinylog.Logger;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    private Entity player;
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

    public boolean isGameLost() {
        return player.getPosition().equals(monster.getPosition());
    }

    public void setPlayer(Entity player) {
        this.player = player;
    }

    public void setMonster(Entity monster) {
        this.monster = monster;
    }
}
