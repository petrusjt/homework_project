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
    private final Entity player;
    private final Entity monster;
    private final List<Wall> walls;
    private final Canvas canvas;
    private final Grid grid;

    private static final int OVAL_MARGIN = 8;
    private static final int WALL_WIDTH = 10;

    /**
     * Creates {@code GraphicsDrawer} object.
     * This class is responsible for drawing the game's each state.
     * @param player The {@code Player} object that the player controls.
     * @param monster The {@code Monster} object representing the monster of the game.
     * @param walls {@link List} of {@code Wall} objects representing the walls in the game.
     * @param canvas {@code Canvas} object that the drawing will appear on.
     * @param grid {@code Grid} object that is the base of the drawing.
     * */
    public GraphicsDrawer(final Entity player,
                          final Entity monster,
                          final List<Wall> walls,
                          final Canvas canvas,
                          final Grid grid) {
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



    private void drawGrid() {
        Logger.debug("Drawing the grid");
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        for(int i = 0; i < grid.getGrid().size(); i++)
        {
            final double y = i * grid.getCellHeight();
            canvas.getGraphicsContext2D().fillRect(0, y, canvas.getWidth(), 1);

        }
        for(int i = 0; i < grid.getGrid().get(0).size(); i++)
        {
            final double x = i * grid.getCellWidth();
            canvas.getGraphicsContext2D().fillRect(x, 0, 1, canvas.getHeight());

        }
    }

    /**
     * Method for drawing the walls.
     * */
    private void drawWalls() {
        Logger.debug("Drawing walls");
        for(final Wall wall : walls)
        {

            double x;
            double y;

            final Point2D start = grid.getIJ(wall.getStart().getX(), wall.getStart().getY());
            final Point2D end = grid.getIJ(wall.getEnd().getX(), wall.getEnd().getY());
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
            canvas.getGraphicsContext2D().fillRect(x, y, w, h);
        }
    }

    /**
     * Method for drawing an {@code Entity} on the {@code canvas}.
     * @param entity the {@code Entity} object to draw on the canvas
     * */
    private void drawEntity(final Entity entity)
    {
        Logger.debug("Drawing entity: " + entity.toString());
        final Point2D entityPosition = grid.getIJ(entity.getPosition().getX(), entity.getPosition().getY());
        final double entityX = entityPosition.getX() + OVAL_MARGIN;
        final double entityY = entityPosition.getY() + OVAL_MARGIN;
        final double entityWidth = grid.getCellWidth() - 2* OVAL_MARGIN;
        final double entityHeight = grid.getCellHeight() - 2* OVAL_MARGIN;
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
}
