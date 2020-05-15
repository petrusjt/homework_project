package game.controller;

import game.toberenamed.Grid;
import game.toberenamed.Monster;
import game.toberenamed.Player;
import game.toberenamed.Wall;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
        Logger.debug("Drawing on the screen");
        for(Wall wall : walls)
        {
            Logger.trace("Looping through walls");
            double x;
            double y;

            Point2D start = grid.getIJ(wall.getStart().x, wall.getStart().y);
            Point2D end = grid.getIJ(wall.getEnd().x, wall.getEnd().y);
            double w;
            double h;
            if(start.getX() != end.getX())
            {
                w = end.getX() - start.getX();
                h = 3;
                x = start.getX();
                y = start.getY() - 1;
            }
            else
            {
                w = 3;
                h = end.getY() - start.getY();
                x = start.getX() - 1;
                y = start.getY();
            }

            Logger.trace(h + ", " + w);

            canvas.getGraphicsContext2D().setFill(Color.BLACK);
            canvas.getGraphicsContext2D().fillRect(x,y,w, h);
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
