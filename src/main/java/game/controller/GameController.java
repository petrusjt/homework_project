package game.controller;

import game.highscore.HighScore;
import game.highscore.HighScores;
import game.utilities.Grid;
import game.utilities.Monster;
import game.utilities.Player;
import game.utilities.Wall;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.tinylog.Logger;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import xmlhelper.JAXBHelper;

/**
 * Class for controlling the game.
 * */
public class GameController {
    private Player player;
    private Monster monster;
    private List<Wall> walls;
    private Grid grid;
    private GraphicsDrawer graphicsDrawer;

    private final int NUMBER_OF_CELLS = 6;

    private List<KeyCode> moveKeys;

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private double startTime;
    private double endTime;
    private int numberOfSteps = 0;
    private String playerName;

    public GameController(Canvas canvas) {
        grid = new Grid(canvas.getWidth(), canvas.getHeight(), NUMBER_OF_CELLS);
        this.player = new Player(new Point(0,0), (float) (Math.min(grid.getCellHeight(), grid.getCellWidth())) / 2);
        this.monster = new Monster(new Point(4,2),(float) (Math.min(grid.getCellHeight(), grid.getCellWidth())) / 2);
        this.walls = new ArrayList<>();
        this.graphicsDrawer = new GraphicsDrawer(player,monster,walls,canvas,grid);
        this.playerName = "testPlayer";

        moveKeys = new ArrayList<>();
        moveKeys.add(KeyCode.W);
        moveKeys.add(KeyCode.S);
        moveKeys.add(KeyCode.A);
        moveKeys.add(KeyCode.D);
        moveKeys.add(KeyCode.UP);
        moveKeys.add(KeyCode.DOWN);
        moveKeys.add(KeyCode.LEFT);
        moveKeys.add(KeyCode.RIGHT);

        try {
            loadMap();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        if(!doesSaveFileExist())
        {
            try {
                createSaveFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        graphicsDrawer.draw();
        startTime = System.currentTimeMillis();
    }

    public void handleKeyEvent(KeyEvent keyEvent) {
        Logger.debug("Key pressed: " + keyEvent.getCode());
        try {
            doGameLogic(keyEvent);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    private void doGameLogic(KeyEvent keyEvent) throws IOException, JAXBException {
        KeyCode code = keyEvent.getCode();
        if(moveKeys.contains(code))
        {
            if(canPlayerMove(getDirectionFromKeyCode(code)))
            {
                numberOfSteps++;
                if(!isWinningMove(getDirectionFromKeyCode(code)))
                {
                    movePlayer(getDirectionFromKeyCode(code));
                    moveMonster();
                    graphicsDrawer.draw();
                    if(isGameLost())
                    {
                        handleGameOver(keyEvent);
                    }
                }
                else
                {
                    handleGameOver(keyEvent);
                }
            }
        }

    }

    private boolean canPlayerMove(Direction direction) {
        Point2D playerPosition = player.getPosition();
        Point wallStart = new Point(0,0);
        Point wallEnd = new Point(0,0);
        if(direction == Direction.UP)
        {
            wallStart.x = (int) playerPosition.getX();
            wallStart.y = (int) playerPosition.getY();
            wallEnd.x = (int) (playerPosition.getX() + 1);
            wallEnd.y = (int) playerPosition.getY();
        }
        else if(direction == Direction.DOWN)
        {
            wallStart.x = (int) playerPosition.getX();
            wallStart.y = (int) (playerPosition.getY() + 1);
            wallEnd.x = (int) (playerPosition.getX() + 1);
            wallEnd.y = (int) (playerPosition.getY() + 1);
        }
        else if(direction == Direction.LEFT)
        {
            wallStart.x = (int) playerPosition.getX();
            wallStart.y = (int) playerPosition.getY();
            wallEnd.x = (int) playerPosition.getX();
            wallEnd.y = (int) (playerPosition.getY() + 1);
        }
        else if(direction == Direction.RIGHT)
        {
            wallStart.x = (int) (playerPosition.getX() + 1);
            wallStart.y = (int) playerPosition.getY();
            wallEnd.x = (int) (playerPosition.getX() + 1);
            wallEnd.y = (int) (playerPosition.getY() + 1);
        }

        //TODO: find a better name for this object
        Wall checkWall = new Wall(wallStart,wallEnd);

        boolean canMove = canMove = !walls.contains(checkWall);

        if(canMove)
            Logger.debug("Did not find wall so Player can move in that direction");
        else
            Logger.debug("Found wall so Player cannot move in that direction");

        return canMove;
    }

    private void movePlayer(Direction direction) {
        Point2D playerPosition = player.getPosition();
        Point newPlayerPosition = new Point();
        if(direction == Direction.UP)
        {
            newPlayerPosition.x = (int) playerPosition.getX();
            newPlayerPosition.y = (int) playerPosition.getY() - 1;
        }
        else if(direction == Direction.DOWN)
        {
            newPlayerPosition.x = (int) playerPosition.getX();
            newPlayerPosition.y = (int) playerPosition.getY() + 1;
        }
        else if(direction == Direction.LEFT)
        {
            newPlayerPosition.x = (int) playerPosition.getX() - 1;
            newPlayerPosition.y = (int) playerPosition.getY();
        }
        else if(direction == Direction.RIGHT)
        {
            newPlayerPosition.x = (int) playerPosition.getX() + 1;
            newPlayerPosition.y = (int) playerPosition.getY();
        }
        player.setPosition(newPlayerPosition);
    }

    private void moveMonster() {
        moveMonsterOnce();
        moveMonsterOnce();
    }

    private boolean isGameLost() {
        return player.getPosition().equals(monster.getPosition());
    }

    private void moveMonsterOnce() {
        Point2D playerPosition = player.getPosition();
        Point2D monsterPosition = monster.getPosition();
        if(playerPosition.getX() < monsterPosition.getX() && canMonsterMove(Direction.LEFT))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX() - 1, (int) monsterPosition.getY()));
        }
        else if(playerPosition.getX() > monsterPosition.getX() && canMonsterMove(Direction.RIGHT))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX() + 1, (int) monsterPosition.getY()));
        }
        else if(playerPosition.getY() < monsterPosition.getY() && canMonsterMove(Direction.UP))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX(), (int) monsterPosition.getY() - 1));
        }
        else if(playerPosition.getY() > monsterPosition.getY() && canMonsterMove(Direction.DOWN))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX(), (int) monsterPosition.getY() + 1));
        }

    }

    private boolean canMonsterMove(Direction direction) {
        Point2D monsterPosition = monster.getPosition();
        Point wallStart = new Point(0,0);
        Point wallEnd = new Point(0,0);
        if(direction == Direction.UP)
        {
            wallStart.x = (int) monsterPosition.getX();
            wallStart.y = (int) monsterPosition.getY();
            wallEnd.x = (int) monsterPosition.getX() + 1;
            wallEnd.y = (int) monsterPosition.getY();
        }
        else if(direction == Direction.DOWN)
        {
            wallStart.x = (int) monsterPosition.getX();
            wallStart.y = (int) (monsterPosition.getY() + 1);
            wallEnd.x = (int) (monsterPosition.getX() + 1);
            wallEnd.y = (int) (monsterPosition.getY() + 1);
        }
        else if(direction == Direction.LEFT)
        {
            wallStart.x = (int) monsterPosition.getX();
            wallStart.y = (int) monsterPosition.getY();
            wallEnd.x = (int) monsterPosition.getX();
            wallEnd.y = (int) (monsterPosition.getY() + 1);
        }
        else if(direction == Direction.RIGHT)
        {
            wallStart.x = (int) (monsterPosition.getX() + 1);
            wallStart.y = (int) monsterPosition.getY();
            wallEnd.x = (int) (monsterPosition.getX() + 1);
            wallEnd.y = (int) (monsterPosition.getY() + 1);
        }

        //TODO: find a better name for this object
        Wall checkWall = new Wall(wallStart,wallEnd);

        boolean canMove = canMove = !walls.contains(checkWall);

        if(canMove)
            Logger.debug("Did not find wall so Monster can move in the tested direction");
        else
            Logger.debug("Found wall so Monster cannot move in the tested direction");

        return canMove;
    }

    private boolean isWinningMove(Direction direction) {
        if(direction == Direction.UP)
        {
            if(player.getPosition().getY() == 0)
            {
                return true;
            }
        }
        else if(direction == Direction.DOWN)
        {
            if(player.getPosition().getY() == NUMBER_OF_CELLS)
            {
                return true;
            }
        }
        else if(direction == Direction.LEFT)
        {
            if(player.getPosition().getX() == 0)
            {
                return true;
            }
        }
        else if(direction == Direction.RIGHT)
        {
            if(player.getPosition().getY() == NUMBER_OF_CELLS)
            {
                return true;
            }
        }
        return false;
    }

    private void loadMap() throws JAXBException {
        game.controller.mapxmlreader.Walls walls = JAXBHelper.fromXML(game.controller.mapxmlreader.Walls.class, getClass().getClassLoader().getResourceAsStream("maps/map.xml"));
        for(game.controller.mapxmlreader.Wall wall : walls.getWall())
        {
            this.walls.add(new Wall(wall.getStart().getX(),wall.getStart().getY(), wall.getEnd().getX(), wall.getEnd().getY()));
        }
        graphicsDrawer.setWalls(this.walls);

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
        savePlayerStats();

        resetMainWindow((Stage) ((Scene) keyEvent.getSource()).getWindow());
    }

    private void savePlayerStats() throws IOException, JAXBException {
        String userHome = System.getProperty("user.home");
        String separator = File.separator;
        String saveFilePath = userHome + separator + ".labyrinth" + separator + "savefile.xml";
        Logger.info("Loading save file from " + saveFilePath);

        File saveFile = new File(saveFilePath);
        InputStream is = new FileInputStream(saveFile);
        HighScores highScores = JAXBHelper.fromXML(HighScores.class, is);
        is.close();
        if(highScores.getHighScores() == null)
        {
            highScores.setHighScores(new ArrayList<>());
        }
        highScores.getHighScores().add(new HighScore(playerName, (endTime - startTime) / 1000.0, numberOfSteps, !isGameLost()));
        OutputStream os = new FileOutputStream(saveFile);
        JAXBHelper.toXML(highScores, os);
        os.close();

    }

    private void resetMainWindow(Stage stage)
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(FXMLLoader.getDefaultClassLoader().getResource("fxml/main_menu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Főmenü");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private boolean doesSaveFileExist()
    {
        Logger.info("Checking for save file");

        String userHome = System.getProperty("user.home");
        String separator = File.separator;
        String saveFilePath = userHome + separator + ".labyrinth" + separator + "savefile.xml";
        Logger.debug("Save file location: " + saveFilePath);

        File saveFile = new File(saveFilePath);
        return saveFile.exists();
    }

    private void createSaveFile() throws FileNotFoundException {
        Logger.info("Save file not found. Creating save file.");

        String userHome = System.getProperty("user.home");
        String separator = File.separator;
        String saveFilePath = userHome + separator + ".labyrinth" + separator + "savefile.xml";
        String saveFileDir = userHome + separator + ".labyrinth";
        new File(saveFileDir).mkdirs();
        File saveFile = new File(saveFilePath);
        PrintWriter printWriter = new PrintWriter(saveFile);
        printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        printWriter.println("<highScores></highScores>");
        printWriter.close();

    }

    private Direction getDirectionFromKeyCode(KeyCode code) {
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

    public void handleOnCloseRequest(WindowEvent event) {
        Logger.info("Closing GameWindow");
        Platform.exit();
    }
}
