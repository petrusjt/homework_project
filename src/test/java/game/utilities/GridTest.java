package game.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void getCellWidth() {
        Grid grid = new Grid(600,600,6);
        assertEquals(100.0, grid.getCellWidth());
        grid = new Grid(800,600,6);
        assertEquals(800.0 / 6, grid.getCellWidth());
        grid = new Grid(1243,1244,11);
        assertEquals(1243 / 11, grid.getCellWidth());

    }

    @Test
    void getCellHeight() {
        Grid grid = new Grid(600,600,6);
        assertEquals(100.0, grid.getCellHeight());
        grid = new Grid(800,600,6);
        assertEquals(600.0 / 6, grid.getCellHeight());
        grid = new Grid(1243,1244,11);
        assertEquals(1244.0 / 11, grid.getCellHeight());
    }
}