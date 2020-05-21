package game.controller;

import game.utilities.Grid;
import game.utilities.entities.Entity;
import game.utilities.entities.Monster;
import game.utilities.entities.Player;
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
    private Entity player;
    private Entity monster;
    private List<Wall> walls;
    private Canvas canvas;
    private Grid grid;

    private final int OVAL_MARGIN = 8;
    private final int WALL_WIDTH = 10;

    /**
     * Creates {@code GraphicsDrawer} object.
     * This class is responsible for drawing the game's each state.
     * @param player The {@code Player} object that the player controls.
     * @param monster The {@code Monster} object representing the monster of the game.
     * @param walls {@link List} of {@code Wall} objects representing the walls in the game.
     * @param canvas {@code Canvas} object that the drawing will appear on.
     * @param grid {@code Grid} object that is the base of the drawing.
     * */
    public GraphicsDrawer(Entity player, Entity monster, List<Wall> walls, Canvas canvas, Grid grid) {
        this.player = player;
        this.monster = monster;
        this.walls = walls;
        this.canvas = canvas;
        this.grid = grid;
    }

    /**
     * Draws the state represented by the fields of this class on the {@code canvas}.
     * */
    public void draw()
    {
        Logger.debug("Clearing screen");
        canvas.getGraphicsContext2D().clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        Logger.debug("Drawing on the screen");
        drawGrid();
        drawWalls();
        drawEntity(player);
        drawEntity(monster);
    }

    /**
     * Method for drawing the walls.
     * */
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

    /**
     * Method for drawing an {@code Entity} on the {@code canvas}.
     * @param entity the {@code Entity} object to draw on the canvas
     * */
    private void drawEntity(Entity entity)
    {
        Logger.debug("Drawing entity: " + entity.toString());
        Point2D entityPosition = grid.getIJ(entity.getPosition().getX(), entity.getPosition().getY());
        double entityX = entityPosition.getX() + OVAL_MARGIN;
        double entityY = entityPosition.getY() + OVAL_MARGIN;
        double entityWidth = grid.getCellWidth() - 2* OVAL_MARGIN;
        double entityHeight = grid.getCellHeight() - 2* OVAL_MARGIN;
        if(entity.getClass() == Player.class)
        {
            canvas.getGraphicsContext2D().setFill(Color.DODGERBLUE);
        }
        else
        {
            canvas.getGraphicsContext2D().setFill(Color.BLACK);
        }
        canvas.getGraphicsContext2D().fillOval(entityX, entityY, entityWidth, entityHeight);
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
}
