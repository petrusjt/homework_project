package game.controller;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.tinylog.Logger;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import xmlhelper.JAXBHelper;

/**
 * Class for controlling the game.
 * */
public class GameController {
    private Canvas canvas;
    private Player player;
    private Monster monster;
    private List<Wall> walls;
    private Grid grid;
    GraphicsDrawer graphicsDrawer;

    public GameController() {

    }

    public GameController(Canvas canvas) {
        grid = new Grid(canvas.getWidth(), canvas.getHeight(),6);
        this.canvas = canvas;
        this.player = new Player(new Point(0,0), (float) (Math.min(grid.getCellHeight(), grid.getCellWidth())) / 2);
        this.monster = new Monster(new Point(4,2),(float) (Math.min(grid.getCellHeight(), grid.getCellWidth())) / 2);
        this.walls = new ArrayList<>();
        this.graphicsDrawer = new GraphicsDrawer(player,monster,walls,canvas,grid);
        try {
            loadMap();
        } catch (ParserConfigurationException | IOException | SAXException | JAXBException e) {
            e.printStackTrace();
        }

        this.graphicsDrawer.draw();
    }

    private void loadMap() throws ParserConfigurationException, IOException, SAXException, JAXBException {
        game.controller.mapxmlreader.Walls walls = JAXBHelper.fromXML(game.controller.mapxmlreader.Walls.class, getClass().getClassLoader().getResourceAsStream("maps/map.xml"));
        for(game.controller.mapxmlreader.Wall wall : walls.getWall())
        {
            System.out.println(wall);
            this.walls.add(new Wall(wall.getStart().getX(),wall.getStart().getY(), wall.getEnd().getX(), wall.getEnd().getY()));
        }
        graphicsDrawer.setWalls(this.walls);
        System.out.println(walls);

    }

    public void handleKeyEvent(KeyEvent keyEvent) {
        Logger.debug("Key pressed: " + keyEvent.getCode());

        if(keyEvent.getCode().equals(KeyCode.Q))
        {
            resetMainWindow((Stage) ((Scene)keyEvent.getSource()).getWindow());
        }
        graphicsDrawer.draw();
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
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

    public void handleOnCloseRequest(WindowEvent event) {
        Logger.info("Closing GameWindow");
        Platform.exit();
    }
}
