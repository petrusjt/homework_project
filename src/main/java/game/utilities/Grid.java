package game.toberenamed;

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

    List<List<Point2D>> gridPoints;

    public Grid()
    {

    }

    /**
     * Creates {@code Grid} object that represents a grid.
     * For this implementation screenWidth and screenHeight should be the same value, however
     * it works on different values too.
     * If different values provided the cells of the grid won't represent a square but a rectangle.
     * @param screenWidth The width of the whole grid
     * @param screenHeight The height of the whole grid
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
                gridPoints.get(i).add(new Point2D.Double(i*verticalStep, j*horizontalStep));
            }
        }
        System.out.println(gridPoints);
    }
    public double getCellWidth()
    {
        return width / cells;
    }

    public double getCellHeight()
    {
        return height / cells;
    }

    public List<List<Point2D>> getGrid()
    {
        return gridPoints;
    }

    public Point2D getIJ(double x, double y)
    {
        return gridPoints.get((int) x).get((int) y);
    }
}
