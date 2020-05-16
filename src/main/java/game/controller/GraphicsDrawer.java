package game.controller;

import game.utilities.Grid;
import game.utilities.Monster;
import game.utilities.Player;
import game.utilities.Wall;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import org.tinylog.Logger;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Class purely for drawing objects to the screen.
 * NOTE: This class is intended to work only within this project.
 * */
public final class GraphicsDrawer {
    private Player player;
    private Monster monster;
    private List<Wall> walls;
    private Canvas canvas;
    private Grid grid;

    private final int OVAL_MARGIN = 8;
    private final int WALL_WIDTH = 10;

    public GraphicsDrawer() {
    }

    public GraphicsDrawer(Player player, Monster monster, List<Wall> walls, Canvas canvas, Grid grid) {
        this.player = player;
        this.monster = monster;
        this.walls = walls;
        this.canvas = canvas;
        this.grid = grid;
    }

    public void draw()
    {
        Logger.debug("Clearing screen");
        canvas.getGraphicsContext2D().clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        Logger.debug("Drawing on the screen");
        drawGrid();
        drawWalls();
        drawPlayer();
        drawMonster();
    }

    private void drawWalls() {
        Logger.debug("Drawing walls");
        for(Wall wall : walls)
        {

            double x;
            double y;

            Point2D start = grid.getIJ(wall.getStart().x, wall.getStart().y);
            Point2D end = grid.getIJ(wall.getEnd().x, wall.getEnd().y);
            double w;
            double h;
            if(start.getX() != end.getX())
            {
                w = end.getX() - start.getX();
                h = WALL_WIDTH;
                x = start.getX();
                y = start.getY() - 0.4* WALL_WIDTH;
            }
            else
            {
                w = WALL_WIDTH;
                h = end.getY() - start.getY();
                x = start.getX() - 0.4* WALL_WIDTH;
                y = start.getY();
            }

            canvas.getGraphicsContext2D().setFill(Color.BLACK);
            canvas.getGraphicsContext2D().fillRect(x,y,w, h);
        }
    }

    private void drawMonster() {
        Logger.debug("Drawing Monster");
        Point2D monsterPosition = grid.getIJ(monster.getPosition().getX(), monster.getPosition().getY());
        double monsterX = monsterPosition.getX() + OVAL_MARGIN;
        double monsterY = monsterPosition.getY() + OVAL_MARGIN;
        double monsterWidth = grid.getCellWidth() - 2* OVAL_MARGIN;
        double monsterHeight = grid.getCellHeight() - 2* OVAL_MARGIN;
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillOval(monsterX, monsterY, monsterWidth, monsterHeight);
    }

    private void drawPlayer() {
        Logger.debug("Drawing Player");
        Point2D playerPosition = grid.getIJ(player.getPosition().getX(), player.getPosition().getY());
        double playerX = playerPosition.getX() + OVAL_MARGIN;
        double playerY = playerPosition.getY() + OVAL_MARGIN;
        double playerWidth = grid.getCellWidth() - 2* OVAL_MARGIN;
        double playerHeight = grid.getCellHeight() - 2* OVAL_MARGIN;
        canvas.getGraphicsContext2D().setFill(Color.DODGERBLUE);
        canvas.getGraphicsContext2D().fillOval(playerX, playerY, playerWidth, playerHeight);
    }

    private void drawGrid() {
        Logger.debug("Drawing the grid");
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        for(int i = 0; i < grid.getGrid().size(); i++)
        {
            double y = i * grid.getCellHeight();

            canvas.getGraphicsContext2D().fillRect(0, y, canvas.getWidth(), 1);

        }
        for(int i = 0; i < grid.getGrid().get(0).size(); i++)
        {
            double x = i * grid.getCellWidth();

            canvas.getGraphicsContext2D().fillRect(x, 0, 1, canvas.getHeight());

        }
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

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
