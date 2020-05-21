package game.utilities;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a grid that is the base of the drawing.
 * The grid is represented by the points where the grid's lines would intersect.
 * */
public class Grid {

    private double width;
    private double height;
    // The number of squares per row and per column.
    private int cells;

    private List<List<Point2D>> gridPoints;

    /**
     * Creates {@code Grid} object that represents a grid.
     * For this implementation screenWidth and screenHeight should be the same value, however
     * it works on different values too.
     * If different values are provided the cells of the cells won't represent a square but a rectangle.
     * @param screenWidth The width of the grid
     * @param screenHeight The height of the grid
     * @param cells The number of cells per row and per column
     * */
    public Grid(double screenWidth, double screenHeight, int cells) {
        this.width = screenWidth;
        this.height = screenHeight;
        this.cells = cells;
        createGrid();
    }

    private void createGrid()
    {
        double verticalStep = height / cells;
        double horizontalStep = width / cells;
        gridPoints = new ArrayList<>();
        for(int i = 0; i*verticalStep <= height; i++)
        {
            gridPoints.add(new ArrayList<>());
            for(int j = 0; j*horizontalStep <= width; j++)
            {
                gridPoints.get(i).add(new Point2D.Double(j*horizontalStep, i*verticalStep));
            }
        }
    }

    /**
     * Returns the width of a cell in the grid.
     * @return The width of a cell in the grid
     * */
    public double getCellWidth()
    {
        return width / cells;
    }

    /**
     * Returns the height of a cell in the grid.
     * @return The height of a cell in the grid
     * */
    public double getCellHeight()
    {
        return height / cells;
    }

    /**
     * Returns the internal list representing the grid.
     * @return The internal list representing the grid
     * */
    public List<List<Point2D>> getGrid()
    {
        return gridPoints;
    }

    /**
     * Returns the {@link Point2D} object specified by {@code x} and {@code y} indices.
     * @param x The x coordinate of the internal list
     * @param y The y coordinate of the internal list
     * @return The {@link Point2D} object specified by {@code x} and {@code y} indices.
     * */
    public Point2D getIJ(double x, double y)
    {
        return gridPoints.get((int) y).get((int) x);
    }
}
