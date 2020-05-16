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

    private int OVALMARGIN = 8;
    private int WALLWIDTH = 10;

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
        Logger.debug("Drawing the grid");
        drawGrid();
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
                h = WALLWIDTH;
                x = start.getX();
                y = start.getY() - 0.4*WALLWIDTH;
            }
            else
            {
                w = WALLWIDTH;
                h = end.getY() - start.getY();
                x = start.getX() - 0.4*WALLWIDTH;
                y = start.getY();
            }

            canvas.getGraphicsContext2D().setFill(Color.BLACK);
            canvas.getGraphicsContext2D().fillRect(x,y,w, h);
        }

        Logger.debug("Drawing Player");
        Point2D playerPosition = grid.getIJ(player.getPosition().getX(), player.getPosition().getY());
        double playerX = playerPosition.getX() + OVALMARGIN;
        double playerY = playerPosition.getY() + OVALMARGIN;
        double playerWidth = grid.getCellWidth() - 2*OVALMARGIN;
        double playerHeight = grid.getCellHeight() - 2*OVALMARGIN;
        canvas.getGraphicsContext2D().setFill(Color.DODGERBLUE);
        canvas.getGraphicsContext2D().fillOval(playerX, playerY, playerWidth, playerHeight);

        Logger.debug("Drawing Monster");
        Point2D monsterPosition = grid.getIJ(monster.getPosition().getX(), monster.getPosition().getY());
        double monsterX = monsterPosition.getX() + OVALMARGIN;
        double monsterY = monsterPosition.getY() + OVALMARGIN;
        double monsterWidth = grid.getCellWidth() - 2*OVALMARGIN;
        double monsterHeight = grid.getCellHeight() - 2*OVALMARGIN;
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillOval(monsterX, monsterY, monsterWidth, monsterHeight);
    }

    private void drawGrid() {
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        for(int i = 0; i < grid.getGrid().size(); i++)
        {
            double y = i * grid.getCellHeight();

            canvas.getGraphicsContext2D().fillRect(0, y - 1, canvas.getWidth(), 3);

        }
        for(int i = 0; i < grid.getGrid().get(0).size(); i++)
        {
            double x = i * grid.getCellWidth();

            canvas.getGraphicsContext2D().fillRect(x - 1, 0, 3, canvas.getHeight());

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
